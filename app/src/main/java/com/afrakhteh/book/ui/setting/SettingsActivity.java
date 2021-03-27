package com.afrakhteh.book.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.afrakhteh.book.R;
import com.afrakhteh.book.ui.article.MainActivity;
import com.afrakhteh.book.util.Constants;
import com.afrakhteh.book.util.SharedPreferenceSingle;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;


public class SettingsActivity extends LocalizationActivity {

    private ImageView themeDark;
    private ImageView themeLight;
    private ImageView languageFA;
    private ImageView languageEN;

    boolean isEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(Constants.LANG)) {
            isEnglish = savedInstanceState.getBoolean(Constants.LANG);
        }
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (SharedPreferenceSingle.getInstance(SettingsActivity.this).getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        setContentView(R.layout.activity_settings);


        findViews();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        boolean isPersian = isEnglish;
        outState.putBoolean(Constants.LANG, isPersian);
        super.onSaveInstanceState(outState);
    }


    private void findViews() {
        themeDark = findViewById(R.id.theme_night_im);
        themeLight = findViewById(R.id.theme_day_im);
        setUpTheme();
        languageFA = findViewById(R.id.language_fa_im);
        languageEN = findViewById(R.id.language_en_im);
        setUpLanguage();

    }

    private void setUpLanguage() {
        languageFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("fa");
                restart();
            }
        });
        languageEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
                restart();

            }
        });

    }

    private void restart() {
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        finish();
    }

    private void setUpTheme() {
        themeLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceSingle.getInstance(SettingsActivity.this).setTheme(false);

                lightIcon();

                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
                restart();
            }
        });

        themeDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceSingle.getInstance(SettingsActivity.this).setTheme(true);

                darkIcon();

                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
                restart();
            }
        });
    }

    public void lightIcon() {

        DrawableCompat.setTint(themeLight.getDrawable(),
                ContextCompat.getColor(getApplicationContext(), R.color.gray));
        DrawableCompat.setTint(themeDark.getDrawable(),
                ContextCompat.getColor(getApplicationContext(), R.color.gray));
    }

    public void darkIcon() {

        DrawableCompat.setTint(themeLight.getDrawable(),
                ContextCompat.getColor(getApplicationContext(), R.color.colorTextOrIcon));
        DrawableCompat.setTint(themeDark.getDrawable(),
                ContextCompat.getColor(getApplicationContext(), R.color.colorTextOrIcon));
    }

}
