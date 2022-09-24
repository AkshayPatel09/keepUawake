package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link dashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MaterialButton startBtn,logOutBtn;
    private SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String ISLOGGEDIN = "isLoggedIn";
    private cameraFragment fragment;

    public dashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static dashboardFragment newInstance(String param1, String param2) {
        dashboardFragment fragment = new dashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        startBtn = v.findViewById(R.id.startBtn);
        logOutBtn = v.findViewById(R.id.logoutBtn);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA }, 100);

        }
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
//                Toast.makeText(HomeActivity.this, "......", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(
                        getActivity(), MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragment = new cameraFragment();
                transaction.replace(R.id.flMain, fragment);
                transaction.commit();

            }
        });
        return v;
    }
    private void logOut() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIRSTNAME, "");
        editor.putString(LASTNAME, "");
        editor.putString(EMAIL, "");
        editor.putString(PHONE, "");
        editor.putString(ISLOGGEDIN, "false");
        editor.commit();
    }
}