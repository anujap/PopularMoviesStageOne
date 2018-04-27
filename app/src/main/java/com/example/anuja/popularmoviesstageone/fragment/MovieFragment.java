package com.example.anuja.popularmoviesstageone.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.anuja.popularmoviesstageone.model.MoviePage;
import com.example.anuja.popularmoviesstageone.viewmodel.MovieViewModel;

public abstract class MovieFragment extends Fragment {

    public interface MovieListener {
        public void notifyMovieListChange();
    }

    private static final String UID_KEY = "uid";
    private MovieViewModel viewModel;

    private MovieListener listener;

    public void setMovieListener(MovieListener listner) {
        this.listener = listner;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String uId = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.init(uId);

        viewModel.getMoviePage().observe(this, new Observer<MoviePage>() {
            @Override
            public void onChanged(@Nullable MoviePage moviePage) {
                //create a method may be an interface that will be implemented by the
                //popular movie and the high rated movie fragment
                notifyMovieListChange();
            }
        });
    }

    private void notifyMovieListChange() {
        listener.notifyMovieListChange();
    }
}
