package com.goldenfuturecommunication.gnanamantapa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    TabLayout tablayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    String language="English";
    String l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);





        //check language
        SharedPreferences b=getSharedPreferences(language, Context.MODE_PRIVATE);
        l=b.getString("language","English");




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Commentposting.class);
                startActivity(i);
            }
        });






        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (l.equals("Kanada")) {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_home).setTitle("ಮನೆ");
            menu.findItem(R.id.nav_ask_a_ques).setTitle("ಪ್ರಶ್ನೆ ಕೇಳಿ");
            menu.findItem(R.id.nav_about).setTitle("ಬಗ್ಗೆ");
            menu.findItem(R.id.nav_report_app_issues).setTitle("ವರದಿ");
            menu.findItem(R.id.nav_home).setTitle("ಅಪ್ಲಿಕೇಶನ್ ಹಂಚಿಕೊಳ್ಳಿ");
            navigationView.setNavigationItemSelectedListener(this);
        }



            tablayout=(TabLayout)findViewById(R.id.tab);
            viewPager=(ViewPager)findViewById(R.id.viewPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);


        if (l.equals("Kanada")){
            pageAdapter=new PageAdapter(getSupportFragmentManager());
            pageAdapter.addFragments(new Fragment_content(),"ಉಪನ್ಯಾಸ");   //Contents
            pageAdapter.addFragments(new Fragment_others(),"ವಿಶೇಷ ಮಾಹಿತಿ"); //Others
            pageAdapter.addFragments(new Fragment_myaccount(),"ಲೇಖನಗಳು"); //My Account
        }else {
            pageAdapter=new PageAdapter(getSupportFragmentManager());
            pageAdapter.addFragments(new Fragment_content(),"Content");   //Contents
            pageAdapter.addFragments(new Fragment_others(),"Others"); //Others
            pageAdapter.addFragments(new Fragment_myaccount(),"My Account"); //My Account
        }


        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(1);  //prevent refreshing tab when tab switching
        tablayout.setupWithViewPager(viewPager);



        //Select tab programmatically
        try{
            String r=getIntent().getExtras().getString("register");
            if (r.equals("1")){
                TabLayout.Tab tab=tablayout.getTabAt(1);
                tab.select();
            }
        }catch (Exception e){

        }




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        if (id==R.id.action_refresh){
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
            return true;
        }else if (id==R.id.action_notification){
            Intent i=new Intent(getApplicationContext(),notification.class);
            startActivity(i);
            return true;
        }else if (id==R.id.action_language){
            if (item.getTitle().equals("English")) {
                item.setTitle("Kanada");
                SharedPreferences sp=getSharedPreferences(language, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("language","English");
                editor.apply();
            }else {
                item.setTitle("English");
                SharedPreferences sp=getSharedPreferences(language, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("language","Kanada");
                editor.apply();
            }
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            overridePendingTransition(0,0);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id=menuItem.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_ask_a_ques) {
            Intent intent=new Intent(getApplicationContext(),askQuestion.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_report_app_issues) {
            Intent intent=new Intent(getApplicationContext(),report.class);
            startActivity(intent);
        } else if (id == R.id.nav_shareapp) {
            Intent p=new Intent(Intent.ACTION_SEND);
            p.setType("text/plain");
            p.putExtra(Intent.EXTRA_TEXT,"Download our app now from playstore. Click here "+"https://play.google.com/store/apps/details?id="+getPackageName());
            startActivity(p);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //check language
        SharedPreferences b=getSharedPreferences(language, Context.MODE_PRIVATE);
        String l=b.getString("language","English");
        if (l.equals("Kanada")){
            menu.findItem(R.id.action_language).setTitle("English");
        }else {
            menu.findItem(R.id.action_language).setTitle("Kannada");
        }
        return super.onPrepareOptionsMenu(menu);
    }




}
