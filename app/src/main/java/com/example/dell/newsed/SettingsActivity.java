package com.example.dell.newsed;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public static class NewsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return false;
        }
    }



}