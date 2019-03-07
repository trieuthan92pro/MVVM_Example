package com.example.musicapp_mvvm.data.source.remote;

import com.example.musicapp_mvvm.BuildConfig;
import com.example.musicapp_mvvm.data.model.DataHelper;
import com.example.musicapp_mvvm.data.source.SongDataSource;

public class SongRemoteDataSource implements SongDataSource.RemoteDataSource {
    private static SongRemoteDataSource sInstance;

    private SongRemoteDataSource() {
    }

    public static SongRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getSongsByGenre(String genre, int limit, SongDataSource.LoadSongCallback callBack) {
        getSongsDataFromAPI(genre, limit, callBack);
    }

    @Override
    public void searchSong(String searchKey, int limit, SongDataSource.LoadSongCallback callBack) {
        getSongsBySearchKey(searchKey, limit, callBack);
    }

    private void getSongsBySearchKey(String searchKey, int limit, SongDataSource.LoadSongCallback callBack) {
        String url = DataHelper.SoundCloud.BASE_URL
                + DataHelper.SoundCloud.SEARCH
                + DataHelper.SoundCloud.QUERY_SEARCH
                + searchKey
                + DataHelper.SoundCloud.PARAM_CLIENT_ID
                + BuildConfig.API_KEY
                + DataHelper.SoundCloud.PARAM_LIMIT
                + limit;

        new SearchSongRemoteAsyncTask(callBack, searchKey).execute(url);
    }

    private void getSongsDataFromAPI(String genre, int limit, SongDataSource.LoadSongCallback callBack) {
        String url = DataHelper.SoundCloud.BASE_URL
                + DataHelper.SoundCloud.PARAM_KIND
                + DataHelper.SoundCloud.PARAM_GENRE
                + DataHelper.SoundCloud.PARAM_TYPE
                + genre + DataHelper.SoundCloud.PARAM_CLIENT_ID
                + BuildConfig.API_KEY
                + DataHelper.SoundCloud.PARAM_LIMIT
                + limit;
        new RemoteDataHelperAsyncTask(callBack).execute(url);
    }

    public static String getStreamUrl(int id) {
        String stringBuilder = DataHelper.Stream.STREAM_URL +
                String.valueOf(id) +
                DataHelper.Stream.STREAM +
                DataHelper.Stream.STREAM_CLIENT_ID +
                BuildConfig.API_KEY;
        return stringBuilder;
    }
}
