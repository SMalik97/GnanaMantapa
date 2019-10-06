package com.example.vishwanandini;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment_samaskrutha extends Fragment {
Button register;

    public Fragment_samaskrutha() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_samaskrutha, container, false);
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
