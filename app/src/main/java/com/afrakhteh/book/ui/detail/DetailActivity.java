package com.afrakhteh.book.ui.detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.afrakhteh.book.R;
import com.afrakhteh.book.util.Constants;
import com.afrakhteh.book.util.SharedPreferenceSingle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView image;
    private ImageView back;
    private FloatingActionButton share;
    private TextView title;
    private TextView description;
    private CardView cardView;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (SharedPreferenceSingle.getInstance(DetailActivity.this).getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_detail);

        findViews();
    }

    private void findViews() {
        image = findViewById(R.id.image_detail);
        back = findViewById(R.id.back_detail);
        share = findViewById(R.id.floatingActionButton);
        title = findViewById(R.id.title_detail);
        description = findViewById(R.id.desc_detail);
        cardView = findViewById(R.id.cardView_detail);

        getIntents();
        onCliCk();

    }

    @Override
    protected void onResume() {
        super.onResume();
        animate();
    }


    private void onCliCk() {
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, title.getText().toString().trim());
                sendIntent.putExtra(Intent.EXTRA_TEXT, description.getText().toString().trim());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getIntents() {
        id = getIntent().getStringExtra(Constants.ID);
        title.setText(getIntent().getStringExtra(Constants.TITLE));
        description.setText(getIntent().getStringExtra(Constants.DESCRPTION));
        Picasso.get().load(getIntent().getStringExtra(Constants.IMAGE)).into(image);
    }

    private void animate() {
      /* share.setScaleX(0);
       share.setScaleY(0);
       share.animate().scaleX(1).scaleY(1).start();*/

        ObjectAnimator titleAnimationX = ObjectAnimator.ofFloat(title, "scaleX", 1, 2, 1);
        ObjectAnimator titleAnimationY = ObjectAnimator.ofFloat(title, "scaleY", 1, 2, 1);

        ObjectAnimator shareAnimationX = ObjectAnimator.ofFloat(share, "scaleX", 0, 1);
        ObjectAnimator shareAnimationY = ObjectAnimator.ofFloat(share, "scaleY", 0, 1);

        ObjectAnimator cardAnimationAlpha = ObjectAnimator.ofFloat(cardView, "alpha", 0, (float) 0.5, 1);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(cardAnimationAlpha, titleAnimationX, titleAnimationY, shareAnimationX, shareAnimationY);
        set.setDuration(2000);
        set.start();
    }

}
