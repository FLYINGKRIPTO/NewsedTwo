package com.example.dell.newsed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
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
            Preference maxStories = findPreference(getString(R.string.max_stories));
            bindPreferenceSummaryToValue(maxStories);

        }

        private void bindPreferenceSummaryToValue(Preference preference) {
               preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = sharedPreferences.getString(preference.getKey(),"");
            onPreferenceChange(preference,preferenceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
           String stringValue = newValue.toString();
           preference.setSummary(stringValue);

            return false;
        }
    }



}