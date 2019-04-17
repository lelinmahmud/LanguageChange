package com.example.languagechange;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
    }



    public void appSetLocale(String localeCode){
        Resources resources=getResources();
        DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        Configuration configuration=resources.getConfiguration();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(new Locale(localeCode.toLowerCase()));
        }
        else {
            configuration.locale=new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(configuration,displayMetrics);


        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_lang",localeCode);
        editor.apply();
    }

    public void loadLocale()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language  = sharedPreferences.getString("My_lang","");
        appSetLocale(language);
    }

    public void bangla(View view) {
        appSetLocale("bn");
        recreate();
    }

    public void english(View view) {
        appSetLocale("en");
        recreate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.setting){
            Intent intent=new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
