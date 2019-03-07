package com.example.musicapp_mvvm.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.musicapp_mvvm.data.source.remote.SongLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class Song implements Parcelable {
    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    private int mId;
    private int mDuration;
    private String mTitle;
    private String mArtworkUrl;
    private String mGenre;
    private String mDownloadURL;
    private String mArtist;
    private boolean mDownloadable;
    private boolean mDownloaded;
    private boolean mIsFavorite;
    private String mSongType;
    private String mAlbum;

    public Song() {
    }

    public Song(JSONObject jsonObject) throws JSONException {
        mId = jsonObject.getInt(JSonKey.ID);
        mTitle = jsonObject.getString(JSonKey.TITLE);
        mArtworkUrl = jsonObject.getString(JSonKey.ARTWORK_URL);
        mDuration = jsonObject.getInt(JSonKey.DURATION);
        mGenre = jsonObject.getString(JSonKey.GENRE);
        mDownloadable = jsonObject.getBoolean(JSonKey.DOWNLOADABLE);
        mDownloadURL = jsonObject.getString(JSonKey.DOWNLOAD_URL);
        mArtist = SongLoaderUtils.getPublisherMetadataItem(jsonObject, JSonKey.ARTIST);
        mAlbum = SongLoaderUtils.getPublisherMetadataItem(jsonObject, JSonKey.ALBUM);
    }

    protected Song(Parcel in) {
        mId = in.readInt();
        mDuration = in.readInt();
        mTitle = in.readString();
        mArtworkUrl = in.readString();
        mGenre = in.readString();
        mDownloadURL = in.readString();
        mArtist = in.readString();
        mDownloadable = in.readByte() != 0;
        mDownloaded = in.readByte() != 0;
        mIsFavorite = in.readByte() != 0;
        mSongType = in.readString();
        mAlbum = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mDuration);
        dest.writeString(mTitle);
        dest.writeString(mArtworkUrl);
        dest.writeString(mGenre);
        dest.writeString(mDownloadURL);
        dest.writeString(mArtist);
        dest.writeByte((byte) (mDownloadable ? 1 : 0));
        dest.writeByte((byte) (mDownloaded ? 1 : 0));
        dest.writeByte((byte) (mIsFavorite ? 1 : 0));
        dest.writeString(mSongType);
        dest.writeString(mAlbum);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getDownloadURL() {
        return mDownloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        mDownloadURL = downloadURL;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public boolean isDownloadable() {
        return mDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        mDownloadable = downloadable;
    }

    public boolean isDownloaded() {
        return mDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        mDownloaded = downloaded;
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setFavorite(boolean favorite) {
        mIsFavorite = favorite;
    }

    public String getSongType() {
        return mSongType;
    }

    public void setSongType(String songType) {
        mSongType = songType;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public class JSonKey {
        public static final String COLLECTION = "collection";
        public static final String TRACK = "track";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URI = "url";
        public static final String ARTWORK_URL = "artwork_url";
        public static final String DURATION = "duration";
        public static final String GENRE = "genre";
        public static final String DOWNLOADABLE = "downloadable";
        public static final String DOWNLOAD_URL = "download_url";
        public static final String USER_NAME = "username";
        public static final String USER = "user";
        public static final String ARTIST = "artist";
        public static final String ALBUM = "album_title";
        public static final String PUBLISHER_METADATA = "publisher_metadata";
    }
}
