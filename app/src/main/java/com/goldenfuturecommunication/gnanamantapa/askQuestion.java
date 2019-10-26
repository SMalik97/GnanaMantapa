package com.goldenfuturecommunication.gnanamantapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class askQuestion extends AppCompatActivity {
    TextView register;

    EditText ask_name,ask_phone,ask_email,ask_ques;

    String login_status="No",ls,askques_url="https://vp254.co.ke/vishwa/insert_askques.php";

    LinearLayout askingme,askme;
    Button ask;
    ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        register=(TextView)findViewById(R.id.register);
        askingme=(LinearLayout)findViewById(R.id.askingme);
        askme=(LinearLayout)findViewById(R.id.askme);
        ask=(Button)findViewById(R.id.ask);
        ask_name=(EditText)findViewById(R.id.ask_name);
        ask_phone=(EditText)findViewById(R.id.ask_phone);
        ask_email=(EditText)findViewById(R.id.ask_email);
        ask_ques=(EditText)findViewById(R.id.ask_ques);

        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);


        SharedPreferences a=getSharedPreferences(login_status, Context.MODE_PRIVATE);
        ls=a.getString("loginStatus","No");


        if(ls.equals("Yes"))
        {
            askingme.setVisibility(View.GONE);
            askme.setVisibility(View.VISIBLE);
        }
        else {

            askingme.setVisibility(View.VISIBLE);
            askme.setVisibility(View.GONE);
        }

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("register","1");
                    startActivity(intent);
                }
            });



        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                backwork ob=new backwork();
                new Thread(ob).start();
                progressBar3.setVisibility(View.VISIBLE);


            }
        });



    }

    class backwork implements Runnable
    {

        @Override
        public void run() {

            StringRequest request=new StringRequest(Request.Method.POST, askques_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(askQuestion.this, "Your question is submitted!", Toast.LENGTH_SHORT).show();
                    progressBar3.setVisibility(View.INVISIBLE);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(askQuestion.this, "Slow internet connection, please try again", Toast.LENGTH_SHORT).show();
                    progressBar3.setVisibility(View.INVISIBLE);

                }
            })
            {

                protected HashMap<String,String> getParams()throws AuthFailureError
                {
                    HashMap<String,String> params=new HashMap<>();

                    params.put("ask_name",ask_name.getText().toString());
                    params.put("ask_phone",ask_phone.getText().toString());
                    params.put("ask_email",ask_email.getText().toString());
                    params.put("ask_ques",ask_ques.getText().toString());

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);

        }
    }
}