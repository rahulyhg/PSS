package com.dp.patidatsamajdirectoryapp.mainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.dp.patidatsamajdirectoryapp.R;

public class WelcomeActivity extends AppCompatActivity {

    ImageButton skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        skip = (ImageButton) findViewById(R.id.skip_button);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this , LoginActivity.class));
                finish();
            }
        });
    }
}
