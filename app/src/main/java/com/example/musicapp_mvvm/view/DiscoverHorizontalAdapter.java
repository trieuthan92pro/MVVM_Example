package com.example.musicapp_mvvm.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicapp_mvvm.R;
import com.example.musicapp_mvvm.data.model.Genre;
import com.example.musicapp_mvvm.data.model.Song;

import java.util.List;

public class DiscoverHorizontalAdapter extends RecyclerView.Adapter<DiscoverHorizontalAdapter.ViewHolder> {
    private List<Song> mSongs;
    private Genre mGenre;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnHorizontalItemClickListener mOnHorizontalItemClickListener;

    public DiscoverHorizontalAdapter(Context context, Genre genre,
                                     OnHorizontalItemClickListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mGenre = genre;
        mSongs = genre.getSongs();
        mOnHorizontalItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.song_horizontal_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setOnHorizontalItemClickListener(mOnHorizontalItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Song song = mSongs.get(position);
        viewHolder.setGenre(mGenre);
        viewHolder.bindData(song);
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    public void setGenre(Genre genre) {
        mGenre = genre;
    }

    public interface OnHorizontalItemClickListener {
        void onHorizontalItemClick(int position, Genre genre);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewArtist;
        private TextView mTextViewSongTitle;
        private ImageView mImageViewArtwork;
        private OnHorizontalItemClickListener mOnHorizontalItemClickListener;
        private Genre mGenre;
        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTextViewSongTitle = itemView.findViewById(R.id.text_song_title_horizontal);
            mTextViewArtist = itemView.findViewById(R.id.text_artist_horizontal);
            mImageViewArtwork = itemView.findViewById(R.id.image_artwork_horizontal);

            mImageViewArtwork.setOnClickListener(this);
            mTextViewArtist.setOnClickListener(this);
            mTextViewSongTitle.setOnClickListener(this);
        }

        public void setOnHorizontalItemClickListener(OnHorizontalItemClickListener onHorizontalItemClickListener) {
            mOnHorizontalItemClickListener = onHorizontalItemClickListener;
        }

        public void setGenre(Genre genre) {
            mGenre = genre;
        }

        public void bindData(Song song) {
            if (song == null) return;
            String artworkUrl = song.getArtworkUrl();
            Glide.with(mContext)
                    .load(artworkUrl.trim())
                    .error(R.drawable.ic_artwork_item_default)
                    .fallback(R.drawable.ic_artwork_item_default)
                    .into(mImageViewArtwork);
            mTextViewArtist.setText(song.getArtist());
            mTextViewSongTitle.setText(song.getTitle());
        }

        @Override
        public void onClick(View v) {
            mOnHorizontalItemClickListener.onHorizontalItemClick(getAdapterPosition(), mGenre);
        }
    }
}
