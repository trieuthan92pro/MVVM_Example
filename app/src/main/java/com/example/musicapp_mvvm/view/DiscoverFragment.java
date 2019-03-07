package com.example.musicapp_mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.musicapp_mvvm.R;
import com.example.musicapp_mvvm.data.model.Genre;

import java.util.List;

public class DiscoverFragment extends Fragment implements DiscoverVerticalAdapter.OnVerticalItemClickListener,
        DiscoverHorizontalAdapter.OnHorizontalItemClickListener {
    private DiscoverViewModel mDiscoverViewModel;
    private RecyclerView mRecyclerView;
    private DiscoverVerticalAdapter mDiscoverAdapter;
    private ProgressBar mProgressBar;

    public DiscoverFragment() {

    }

    public static DiscoverFragment getInstance() {
        return new DiscoverFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDiscoverViewModel = DiscoverActivity.obtainViewModel(getActivity());
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        mProgressBar = view.findViewById(R.id.discover_progress_bar);
        mRecyclerView = view.findViewById(R.id.discover_vertical_recyclerview);
        RecyclerView.LayoutManager linerLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linerLayoutManager);
        mDiscoverAdapter = new DiscoverVerticalAdapter(getContext(), this, this);
        mRecyclerView.setAdapter(mDiscoverAdapter);
        mRecyclerView.setHasFixedSize(true);
        showProgressbar(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDiscoverViewModel.getGenresMutableLiveData().observe(getActivity(), new Observer<List<Genre>>() {
            @Override
            public void onChanged(@Nullable List<Genre> genres) {
                showProgressbar(false);
                showSongList(genres);
            }
        });
        mDiscoverViewModel.start();
        mDiscoverViewModel.getOnGetSongEvent().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showErr(s);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void showSongList(List<Genre> genres) {
        if (!genres.isEmpty()) {
            mDiscoverAdapter.setGenres(genres);
        }
    }

    public void showErr(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showProgressbar(boolean isShowLoading) {
        if (isShowLoading) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public void onHorizontalItemClick(int position, Genre genre) {
    }

    public void onClick(Genre genre) {
    }
}
