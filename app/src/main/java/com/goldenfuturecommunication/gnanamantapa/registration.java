package com.goldenfuturecommunication.gnanamantapa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class registration extends AppCompatActivity {

    EditText name,city,mobile,email,pass,repass;
    Button register;
    String database_url="https://vp254.co.ke/vishwa/insert.php";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name=(EditText)findViewById(R.id.name);
        city=(EditText)findViewById(R.id.city);
        mobile=(EditText)findViewById(R.id.mobile);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        repass=(EditText)findViewById(R.id.repass);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait, processing...");
        progressDialog.setCancelable(true);

        register=(Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().trim().equals("")) {
                    name.setError("Enter Valid Name");
                    name.requestFocus();
                }else if(city.getText().toString().trim().equals(""))
                {
                    city.setError("Enter Your City");
                    city.requestFocus();
                }
                else if (mobile.getText().toString().trim().equals(""))
                {
                    mobile.setError("Enter Mobile");
                    mobile.requestFocus();
                }
                else if(email.getText().toString().trim().equals(""))
                {
                    email.setError("Enter Your E-mail");
                    email.requestFocus();
                }
                else if(pass.getText().toString().trim().equals(""))
                {
                    pass.setError("Enter a valid password");
                    pass.requestFocus();
                }
                else if(!repass.getText().toString().trim().equals(pass.getText().toString().trim()))
                {
                    repass.setError("Password not matched");
                    repass.requestFocus();
                }
                else
                {
                    if(mobile.getText().toString().trim().length()==10)
                    {
                        progressDialog.show();
                        backwork ob=new backwork();
                        new Thread(ob).start();



                    }
                    else
                        mobile.setError("Mobile Number should be of 10 Digits");
                        mobile.requestFocus();
                }

            }
        });

    }

    class backwork implements Runnable
    {

        @Override
        public void run() {

            final String names=name.getText().toString().trim(),
                    citys=city.getText().toString().trim(),
                    mobiles=mobile.getText().toString().trim(),
                    emails=email.getText().toString().trim();

            StringRequest request=new StringRequest(Request.Method.POST, database_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if(response.equals("1"))
                    {
                        Intent i=new Intent(getApplicationContext(),Fragment_myaccount.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(registration.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(registration.this, "Slow internet connection, please try again", Toast.LENGTH_SHORT).show();

                }
            }){

                protected HashMap<String,String> getParams()throws AuthFailureError
                {
                    HashMap<String,String> params=new HashMap<>();
                    params.put("names",names);
                    params.put("citys",citys);
                    params.put("mobiles",mobiles);
                    params.put("emails",emails);
                    params.put("pass",pass.getText().toString().trim());


                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);





        }
    }


}
