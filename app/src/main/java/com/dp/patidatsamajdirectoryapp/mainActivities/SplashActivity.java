package com.dp.patidatsamajdirectoryapp.mainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.dp.patidatsamajdirectoryapp.R;

public class SplashActivity extends AppCompatActivity {
    ImageView logoBack, bottomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoBack=(ImageView) findViewById(R.id.logo_background);
        bottomText = (ImageView)findViewById(R.id.bottom_text);

        bottomText.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_up_in));
        logoBack.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anim));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
                finish();
            }
        },3000);


    }
}
