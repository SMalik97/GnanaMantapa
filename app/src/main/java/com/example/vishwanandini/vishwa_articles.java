package com.example.vishwanandini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class vishwa_articles extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vishwa_articles);

        tablayout=(TabLayout)findViewById(R.id.tab);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        pageAdapter=new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragments(new Fragment_articles(),"Articles");
        pageAdapter.addFragments(new Fragment_upanasyas(),"Upanasyas");
        pageAdapter.addFragments(new Fragment_prashnauttaras(),"Prashnauttaras");


        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(1);  //prevent refreshing tab when tab switching
        tablayout.setupWithViewPager(viewPager);


//        String id=getIntent().getExtras().getString("head");
//        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
    }
}
