package com.example.musicapp_mvvm.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musicapp_mvvm.R;
import com.example.musicapp_mvvm.data.model.Genre;

import java.util.List;

public class DiscoverVerticalAdapter
        extends RecyclerView.Adapter<DiscoverVerticalAdapter.ViewHolder> {
    private Context mContext;
    private List<Genre> mGenres;
    private OnVerticalItemClickListener mOnVerticalItemClickListener;
    private DiscoverHorizontalAdapter.OnHorizontalItemClickListener mOnHorizontalItemClickListenerHorizontal;
    private LayoutInflater mLayoutInflater;

    public DiscoverVerticalAdapter(Context context,
                                   OnVerticalItemClickListener listener,
                                   DiscoverHorizontalAdapter.OnHorizontalItemClickListener horizontalItemClickListener) {
        mContext = context;
        mLayoutInflater = mLayoutInflater.from(context);
        mOnVerticalItemClickListener = listener;
        mOnHorizontalItemClickListenerHorizontal = horizontalItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.discover_vertical_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setListener(mOnVerticalItemClickListener);
        viewHolder.setListenerHorizontal(mOnHorizontalItemClickListenerHorizontal);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Genre genre = mGenres.get(position);
        viewHolder.bindData(mContext, genre);
        viewHolder.setGenres(mGenres);
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public void setGenres(List<Genre> genres){
        mGenres = genres;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewGenreTitle;
        private RecyclerView mRecyclerViewHorizontal;
        private OnVerticalItemClickListener mListener;
        private DiscoverHorizontalAdapter.OnHorizontalItemClickListener mListenerHorizontal;
        private List<Genre> mGenres;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewGenreTitle = itemView.findViewById(R.id.text_genre_title);
            itemView.findViewById(R.id.text_view_more).setOnClickListener(this);
            itemView.findViewById(R.id.img_view_more).setOnClickListener(this);
            mRecyclerViewHorizontal = itemView.findViewById(R.id.horizontal_recycler_view);
            mRecyclerViewHorizontal.setHasFixedSize(true);
            mTextViewGenreTitle.setOnClickListener(this);
        }

        public void setGenres(List<Genre> genres) {
            mGenres = genres;
        }

        public void setListener(OnVerticalItemClickListener listener) {
            mListener = listener;
        }

        public void setListenerHorizontal(DiscoverHorizontalAdapter.OnHorizontalItemClickListener listenerHorizontal) {
            mListenerHorizontal = listenerHorizontal;
        }

        public void bindData(Context context, Genre genre) {
            if (genre == null) return;
            mTextViewGenreTitle.setText(genre.getTitle());
            DiscoverHorizontalAdapter discoverHorizontalAdapter =
                    new DiscoverHorizontalAdapter(context, genre, mListenerHorizontal);
            RecyclerView.LayoutManager horizontalLayoutManager =
                    new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerViewHorizontal.setAdapter(discoverHorizontalAdapter);
            mRecyclerViewHorizontal.setLayoutManager(horizontalLayoutManager);
        }

        @Override
        public void onClick(View v) {
            Genre genre = mGenres.get(getAdapterPosition());
            mListener.onClick(genre);
        }
    }

    public interface OnVerticalItemClickListener {
        void onClick(Genre genre);
    }

}
