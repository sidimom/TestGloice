package com.example.gloicetest.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gloicetest.R;
import com.example.gloicetest.api.model.response.FilmResult;
import com.example.gloicetest.databinding.ItemRvFilmsBinding;
import com.example.gloicetest.utils.Const;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<FilmResult> results = new ArrayList<>();
    private Context context;
    private onItemClickListener listener;
    private boolean isEnabled = true;

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRvFilmsBinding binding = ItemRvFilmsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        FilmResult film = results.get(position);
        holder.binding.tvName.setText(film.getTitle());
        holder.binding.tvVote.setText(String.valueOf(film.getVoteAverage()));
        String dateFormatFrom = "yyyy-MM-dd";
        String dateFormatTo = "dd.MM.yyyy";
        try {
            @SuppressLint("SimpleDateFormat") Date date = new SimpleDateFormat(dateFormatFrom).parse(film.getReleaseDate());
            if (date != null){
                @SuppressLint("SimpleDateFormat") String dateString = new SimpleDateFormat(dateFormatTo).format(date);
                holder.binding.tvReleaseDate.setText(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(film.getPosterPath())){
            holder.binding.ivPoster.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_poster_not_found));
        }else{
            Picasso.get()
                    .load(Const.IMAGE_URL + film.getPosterPath())
                    .placeholder(R.drawable.ic_poster_not_found)
                    .error(R.drawable.ic_poster_not_found)
                    .into(holder.binding.ivPoster);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setData(List<FilmResult> results){
        this.results.clear();
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final onItemClickListener listener) {
        this.listener = listener;
    }

    public void setEnable(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    class FilmViewHolder extends RecyclerView.ViewHolder{

        ItemRvFilmsBinding binding;

        FilmViewHolder(ItemRvFilmsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.cvCard.setOnClickListener(v -> {
                if (isEnabled
                        && listener != null)
                    listener.onItemClick(results.get(getAdapterPosition()));
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(FilmResult item);
    }
}
