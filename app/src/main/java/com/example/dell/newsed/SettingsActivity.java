package com.example.dell.newsed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
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
            //get reference to max stories preference
            Preference maxStories = findPreference(getString(R.string.max_stories));
            //update summary value
            bindPreferenceSummaryToValue(maxStories);
            Preference newsField = findPreference(getString(R.string.news_field));
            bindPreferenceSummaryToValue(newsField);

        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            //      String stringValue = newValue.toString();
            // preference.setSummary(stringValue);
            if(preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(newValue.toString());
                if(prefIndex>=0){
                    CharSequence[] labels =listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);

                }
                else {
                    preference.setSummary(newValue.toString());
                }
            }
            return true;
        }
        private void bindPreferenceSummaryToValue(Preference preference) {
               preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = sharedPreferences.getString(preference.getKey(),"");
            onPreferenceChange(preference,preferenceString);
        }
    }
}