package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {

//    private MaterialButton startBtn,logOutBtn;
//    private SharedPreferences sharedPreferences;
//    public static final String MyPREFERENCES = "MyPrefs";
//    public static final String FIRSTNAME = "firstName";
//    public static final String LASTNAME = "lastName";
//    public static final String EMAIL = "email";
//    public static final String PHONE = "phone";
//    public static final String ISLOGGEDIN = "isLoggedIn";
    private dashboardFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new dashboardFragment();

        transaction.replace(R.id.flMain, fragment);
        transaction.commit();

//        startBtn = findViewById(R.id.startBtn);
//        logOutBtn = findViewById(R.id.logoutBtn);
//
//        logOutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logOut();
////                Toast.makeText(HomeActivity.this, "......", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(intent);
////                finish();
//            }
//        });
//
//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

//    private void logOut() {
//        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(FIRSTNAME, "");
//        editor.putString(LASTNAME, "");
//        editor.putString(EMAIL, "");
//        editor.putString(PHONE, "");
//        editor.putString(ISLOGGEDIN, "false");
//        editor.commit();
//    }
}