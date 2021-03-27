package com.afrakhteh.book.ui.article;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afrakhteh.book.R;
import com.afrakhteh.book.ui.about.AboutActivity;
import com.afrakhteh.book.ui.adapter.BookAdapter;
import com.afrakhteh.book.data.entities.BookModel;
import com.afrakhteh.book.ui.setting.SettingsActivity;
import com.afrakhteh.book.util.Constants;
import com.afrakhteh.book.util.SharedPreferenceSingle;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends LocalizationActivity implements View.OnClickListener {
    private TextView menu;
    private RecyclerView recyclerView;

    private TextView about;
    private TextView settings;

    private DrawerLayout drawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (SharedPreferenceSingle.getInstance(MainActivity.this).getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity);

        findViews();

    }


    private void findViews() {
        menu = findViewById(R.id.main_toolbar_title);
        recyclerView = findViewById(R.id.main_recycler_showTitles);
        drawerLayout = findViewById(R.id.drawer);
        about = findViewById(R.id.nav_menu_about);
        settings = findViewById(R.id.nav_menu_settings);

        onClick();
        setRecycler();
    }

    private void setRecycler() {
        List<BookModel> book = new ArrayList<>();
        BookAdapter adapter = new BookAdapter(MainActivity.this, book);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
        book.add(new BookModel(1, Constants.INTRO_IMAGE_URL,
                getResources().getString(R.string.app_title_one), getResources().getString(R.string.app_description_one)));

        book.add(new BookModel(2, Constants.WORKOUT_IMAGE_URL,
                getResources().getString(R.string.app_title_two), getResources().getString(R.string.app_description_two)));

        book.add(new BookModel(3, Constants.BREAKFAST_IMAGE_URL,
                getResources().getString(R.string.app_title_three), getResources().getString(R.string.app_description_three)));

        book.add(new BookModel(4, Constants.SUCCESS_IMAGE_URL,
                getResources().getString(R.string.app_title_for), getResources().getString(R.string.app_description_for)));

        book.add(new BookModel(5, Constants.CALENDER_IMAGE_URL,
                getResources().getString(R.string.app_title_five), getResources().getString(R.string.app_description_five)));

        book.add(new BookModel(6, Constants.LUNCH_IMAGE_URL,
                getResources().getString(R.string.app_title_six), getResources().getString(R.string.app_description_six)));

        adapter.notifyDataSetChanged();
    }

    private void onClick() {
        menu.setOnClickListener(this);
        about.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_toolbar_title:
                drawerLayout.openDrawer(Gravity.END);
                break;

            case R.id.nav_menu_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                drawerLayout.closeDrawers();
                break;

            case R.id.nav_menu_settings:
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
                drawerLayout.closeDrawers();
                break;
        }

    }

}
