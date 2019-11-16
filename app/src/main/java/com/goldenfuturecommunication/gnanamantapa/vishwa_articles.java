package com.goldenfuturecommunication.gnanamantapa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class vishwa_articles extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    String head;
    String language="English";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vishwa_articles);
        //check language
        SharedPreferences b=getSharedPreferences(language, Context.MODE_PRIVATE);
        String l=b.getString("language","English");

        tablayout=(TabLayout)findViewById(R.id.tab);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        if (l.equals("Kanada")){
            pageAdapter=new PageAdapter(getSupportFragmentManager());
            pageAdapter.addFragments(new Fragment_articles(),"ಲೇಖನಗಳು");
            pageAdapter.addFragments(new Fragment_upanasyas(),"ಉಪನಸಾಯರು");
            pageAdapter.addFragments(new Fragment_prashnauttaras(),"ಪ್ರಶ್ನೆ ಮತ್ತು ಎ");
        }else {
            pageAdapter=new PageAdapter(getSupportFragmentManager());
            pageAdapter.addFragments(new Fragment_articles(),"Articles");
            pageAdapter.addFragments(new Fragment_upanasyas(),"Upanasyas");
            pageAdapter.addFragments(new Fragment_prashnauttaras(),"Prashnauttaras");
        }



        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(1);  //prevent refreshing tab when tab switching
        tablayout.setupWithViewPager(viewPager);


        head=getIntent().getExtras().getString("head");



//        String id=getIntent().getExtras().getString("head");
       // Toast.makeText(this, ""+head, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.refresh_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id==R.id.action_refresh){
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),vishwa_articles.class);
            intent.putExtra("head",head);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
