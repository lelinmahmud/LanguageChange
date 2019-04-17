package com.example.languagechange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_setting);

        if (findViewById(R.id.container)!=null){
            if (savedInstanceState!=null)
                return;

            getSupportFragmentManager().beginTransaction().add(R.id.container,new SettingFrament()).commit();
        }

    }


    public void appSetLocale(String localeCode){
        android.content.res.Resources resources=getResources();
        android.util.DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        android.content.res.Configuration configuration=resources.getConfiguration();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(new Locale(localeCode.toLowerCase()));
        }
        else {
            configuration.locale=new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(configuration,displayMetrics);


        SharedPreferences.Editor editor =getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_lang",localeCode);
        editor.apply();
    }

    public void loadLocale()
    {
         SharedPreferences preferences =getSharedPreferences("Settings", MODE_PRIVATE);
        String language  = preferences.getString("My_lang","");
        appSetLocale(language);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(SettingActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
