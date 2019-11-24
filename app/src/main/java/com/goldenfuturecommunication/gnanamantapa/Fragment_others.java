package com.goldenfuturecommunication.gnanamantapa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment_others extends Fragment {
    Button allarticles,allupanyasas,allprashnottara,rate;
    String language="English";

    public Fragment_others() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_others, container, false);

        allarticles=(Button)view.findViewById(R.id.allarticles);
        allupanyasas=(Button)view.findViewById(R.id.allupanyasas);
        allprashnottara=(Button)view.findViewById(R.id.allprashnottara);
        rate=(Button)view.findViewById(R.id.rate);

        SharedPreferences b=getActivity().getSharedPreferences(language, Context.MODE_PRIVATE);
        String l=b.getString("language","English");
        if (l.equals("Kanada")){
            allarticles.setText("ಎಲ್ಲಾ ಲೇಖನಗಳು");
            allupanyasas.setText("ಎಲ್ಲಾ ಉಪನ್ಯಾಸಗಳು");
            allprashnottara.setText("ಎಲ್ಲ ಪ್ರಶ್ನೋತ್ತರ");
            rate.setText("ದರ ಅಪ್ಲಿಕೇಶನ್");
        }


        allarticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),all_content_fetch.class);
                i.putExtra("type","articles");
                startActivity(i);
            }
        });

        allupanyasas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),all_content_fetch.class);
                i.putExtra("type","upanyasas");
                startActivity(i);
            }
        });

        allprashnottara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),all_content_fetch.class);
                i.putExtra("type","prashnottara");
                startActivity(i);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //Rate activity
                    String g="https://play.google.com/store/apps/details?id="+getActivity().getPackageName();
                    Uri w=Uri.parse(g);
                    Intent d=new Intent(Intent.ACTION_VIEW,w);
                    if(d.resolveActivity(getActivity().getPackageManager())!=null)
                    {
                        startActivity(d);
                    }
            }
        });




        return view;
    }


}
