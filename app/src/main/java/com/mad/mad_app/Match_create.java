package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Match_create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_create);

        //Set the Title of the activity
        this.setTitle("Create Match");
    }
}