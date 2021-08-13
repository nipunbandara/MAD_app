package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tournament_home(View view){

        Intent intent = new Intent(this, tournament_home.class);
        startActivity(intent);

    }

    public void team_create(View view){

        Intent intent = new Intent(this, team_create.class);
        startActivity(intent);

    }

}