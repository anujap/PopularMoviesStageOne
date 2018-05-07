package com.example.anuja.popularmoviesstageone.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.anuja.popularmoviesstageone.R;
import com.example.anuja.popularmoviesstageone.databinding.ItemListBinding;
import com.example.anuja.popularmoviesstageone.model.MovieDetails;
import com.example.anuja.popularmoviesstageone.webservice.MovieUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MoviesViewHolder> {

    private List<MovieDetails> moviePageList;
    private GridItemClickListener itemClickListener;
    private Context context;

    public interface GridItemClickListener {
        void onGridItemClick(MovieDetails movie);
    }

    public MovieGridAdapter(List<MovieDetails> movies, GridItemClickListener listener) {
        this.moviePageList = movies;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        MoviesViewHolder viewHolder = new MoviesViewHolder(ItemListBinding.inflate(inflater, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        MovieDetails movie = moviePageList.get(position);

        String imagePath = MovieUtils.MOVIE_PATH + MovieUtils.IMG_SIZE + movie.getPosterPath();

        Picasso.with(context)
                .load(imagePath)
                .placeholder(R.drawable.movie_poster_placeholder)
                .error(R.drawable.movie_poster_placeholder_error)
                .into(holder.itemListBinding.ivMovieImages);

        //get the images;
    }

    @Override
    public int getItemCount() {
        if (moviePageList == null)
            return 0;

        return moviePageList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private ItemListBinding itemListBinding;

        public MoviesViewHolder(ItemListBinding itemListBinding) {
            super(itemListBinding.getRoot());

            this.itemListBinding = itemListBinding;

            //Ref:-https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4
            itemListBinding.executePendingBindings();
            //click listener
        }
    }
}
