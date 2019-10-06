package com.example.vishwanandini;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment_prashnauttaras extends Fragment {


    public Fragment_prashnauttaras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_prashnauttaras, container, false);

        String id=getActivity().getIntent().getExtras().getString("head");
       // Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();

        return view;
    }

}
