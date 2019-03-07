package com.example.musicapp_mvvm.data.model;

import android.support.annotation.StringDef;

import static com.example.musicapp_mvvm.data.model.GenreType.ALL_AUDIO;
import static com.example.musicapp_mvvm.data.model.GenreType.ALL_MUSIC;
import static com.example.musicapp_mvvm.data.model.GenreType.ALTERNATIVE_ROCK;
import static com.example.musicapp_mvvm.data.model.GenreType.AMBIENT;
import static com.example.musicapp_mvvm.data.model.GenreType.CLASSICAL;
import static com.example.musicapp_mvvm.data.model.GenreType.COUNTRY;

@StringDef({ALL_MUSIC, ALL_AUDIO, AMBIENT, COUNTRY, ALTERNATIVE_ROCK, CLASSICAL})
public @interface GenreType {
    String ALL_MUSIC = "all-music";
    String ALL_AUDIO = "all-audio";
    String AMBIENT = "ambient";
    String COUNTRY = "country";
    String ALTERNATIVE_ROCK = "alternativerock";
    String CLASSICAL = "classical";
}
