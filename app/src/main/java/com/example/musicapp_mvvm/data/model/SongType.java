package com.example.musicapp_mvvm.data.model;

import android.support.annotation.StringDef;

import static com.example.musicapp_mvvm.data.model.SongType.TYPE_LOCAL;
import static com.example.musicapp_mvvm.data.model.SongType.TYPE_ONLINE;

@StringDef({TYPE_ONLINE, TYPE_LOCAL})
public @interface SongType {
    String TYPE_ONLINE = "online";
    String TYPE_LOCAL = "local";
}
