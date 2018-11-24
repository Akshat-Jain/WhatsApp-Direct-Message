package com.example.akjn.whatsappdirectmessage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity{
    Switch simpleSwitch;
    private static final String IS_DARK_KEY= "IS_DARK";
    private boolean isDarkTheme=false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);


    }



}
