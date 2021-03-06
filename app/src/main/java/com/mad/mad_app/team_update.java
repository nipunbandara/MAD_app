package com.mad.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class team_update extends AppCompatActivity {

    private String tid;
    private TextView TxtTmname;
    private EditText TeamName, Tdesc;
    private Button Btn_update, Btn_cancel;
    private String TmnameString, TdescString;


    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference teamRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_update);


        this.setTitle("Update Team");

        Btn_update = findViewById(R.id.save23);

        Btn_cancel = findViewById(R.id.button44);


        Intent myIntent = getIntent();
        tid = myIntent.getStringExtra("MAINEXTRA1");

        mAuth = FirebaseAuth.getInstance();


        teamRef = FirebaseDatabase.getInstance().getReference().child("teams").child(tid);

        teamRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Team team = task.getResult().getValue(Team.class);

                    TeamName = findViewById(R.id.update_teamname);
                    Tdesc = findViewById(R.id.update_team_des);

                    TeamName.setText(team.getTmname());
                    Tdesc.setText(team.getTdesc());

                }
            }
        });

        Btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TeamName = findViewById(R.id.create_teamname);
                Tdesc = findViewById(R.id.create_team_des);

                TmnameString = TeamName.getText().toString();
                TdescString = Tdesc.getText().toString();
                //assigning new data to hashmap
                HashMap userInfo = new HashMap();
                userInfo.put("tnname", TmnameString);
                userInfo.put("tdesc", TdescString);
                //setting update data to db
                teamRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(team_update.this, "Updated successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(team_update.this, team_home.class);
                            startActivity(intent);
                            finish();
                            loader.dismiss();

                        }else {
                            Toast.makeText(team_update.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                        finish();

                    }
                });


            }
        });

    }

}