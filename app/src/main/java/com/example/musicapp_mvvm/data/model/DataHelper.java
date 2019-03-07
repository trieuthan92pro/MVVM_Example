package com.example.musicapp_mvvm.data.model;

public class DataHelper {

    public class SoundCloud {
        public static final String BASE_URL = "https://api-v2.soundcloud.com/";
        public static final String PARAM_KIND = "charts?kind=top";
        public static final String PARAM_GENRE = "&genre=soundcloud";
        public static final String PARAM_CLIENT_ID = "&client_id=";
        public static final String PARAM_TYPE = "%3Agenres%3A";
        public static final String PARAM_LIMIT = "&limit=";
        public static final String LIMIT = "20";
        public static final String METHOD_GET = "GET";
        public static final String SEARCH = "search";
        public static final String QUERY_SEARCH = "/tracks?q=";
        public static final int READ_TIMEOUT = 5000;
        public static final int CONNECTION_TIMEOUT = 5000;
    }

    public class Stream {
        public static final String STREAM_URL = "http://api.soundcloud.com/tracks/";
        public static final String STREAM = "/stream";
        public static final String STREAM_CLIENT_ID = "?client_id=";
    }
}
