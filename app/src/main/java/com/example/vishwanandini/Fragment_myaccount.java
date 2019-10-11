package com.example.vishwanandini;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class Fragment_myaccount extends Fragment {
Button register,login;
String login_status="No";
String ls;
LinearLayout profileView, loginView;

    public Fragment_myaccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_myaccount, container, false);

        loginView=(LinearLayout)view.findViewById(R.id.loginView);
        profileView=(LinearLayout)view.findViewById(R.id.profileView);
        login=(Button)view.findViewById(R.id.login);

       //check if user already logged in or not
        SharedPreferences a=getActivity().getSharedPreferences(login_status, Context.MODE_PRIVATE);
        ls=a.getString("loginStatus","No");

        //if already logged in then hide loginView and show profileView
        if (ls.equals("Yes")){
            loginView.setVisibility(View.GONE);
            profileView.setVisibility(View.VISIBLE);
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //after successful login make loginStatus Yes
                SharedPreferences sp=getActivity().getSharedPreferences(login_status, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("loginStatus","Yes");
                editor.apply();
            }
        });



                register=(Button)view.findViewById(R.id.registration);
                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(getContext(), registration.class);
                        startActivity(i);
                    }
                });
        return view;
    }


}
