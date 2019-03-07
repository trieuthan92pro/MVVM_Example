package com.example.musicapp_mvvm.data.source.remote;

import android.os.AsyncTask;

import com.example.musicapp_mvvm.data.model.Song;
import com.example.musicapp_mvvm.data.source.SongDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class RemoteDataHelperAsyncTask extends AsyncTask<String, Void, List<Song>> {
    private SongDataSource.LoadSongCallback mCallback;
    private Exception mException;

    public RemoteDataHelperAsyncTask(SongDataSource.LoadSongCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Song> doInBackground(String... strings) {
        try {
            String jsonStringResult = SongLoaderUtils.getJSONFromAPI(strings[0]);
            List<Song> songs = SongLoaderUtils.getSongsFromJSONString(jsonStringResult);
            return songs;
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        } catch (JSONException e) {
            e.printStackTrace();
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Song> songs) {
        super.onPostExecute(songs);
        if (mCallback == null) {
            return;
        }

        if (songs == null) {
            mCallback.onDataNotAvailable(mException);
        } else {
            mCallback.onSongsLoaded(songs);
        }
    }
}
