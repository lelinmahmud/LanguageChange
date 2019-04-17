package com.example.languagechange;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import static android.content.Context.MODE_PRIVATE;

public class SettingFrament extends PreferenceFragmentCompat {
    public static final String KEY_GET_LANGUAGE_SETTING = "languageSettings";
    private Context context;
    SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        preferenceChangeListener=new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(KEY_GET_LANGUAGE_SETTING)){

                    Preference changeLan=findPreference(key);
                    changeLan.setSummary(sharedPreferences.getString(key,""));
                    String val=sharedPreferences.getString(key,"");

                    if (val.equals("English")){
                        String lan="en";
                        editor= getContext().getSharedPreferences("Settings", MODE_PRIVATE).edit();
                        editor.putString("My_lang",lan);
                        editor.apply();
                        getActivity().recreate();

                    }
                    else if (val.equals("Bangla")){
                        String lan="bn";
                        editor= getContext().getSharedPreferences("Settings", MODE_PRIVATE).edit();
                        editor.putString("My_lang",lan);
                        editor.apply();
                        getActivity().recreate();


                    }


                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        Preference changeLan=findPreference(KEY_GET_LANGUAGE_SETTING);
        changeLan.setSummary(getPreferenceScreen().getSharedPreferences().getString(KEY_GET_LANGUAGE_SETTING,""));

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);

    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }



}
