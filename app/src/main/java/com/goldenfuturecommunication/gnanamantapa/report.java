package com.goldenfuturecommunication.gnanamantapa;

import androidx.appcompat.app.AppCompatActivity;

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

public class report extends AppCompatActivity {

    EditText issue_name,issue_phone,issue_email,issue_issue;
    Button issue_submit;

    String issue_url="https://vp254.co.ke/vishwa/insert_issue.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        issue_name=(EditText)findViewById(R.id.issue_name);
        issue_phone=(EditText)findViewById(R.id.issue_phone);
        issue_email=(EditText)findViewById(R.id.issue_email);
        issue_issue=(EditText)findViewById(R.id.issue_issue);

        issue_submit=(Button)findViewById(R.id.issue_submit);

        issue_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                backwork ob=new backwork();
                new Thread(ob).start();

            }
        });

    }

    class backwork implements Runnable
    {

        @Override
        public void run() {


            StringRequest request=new StringRequest(Request.Method.POST, issue_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(report.this, ""+response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(report.this, "Error..!!!", Toast.LENGTH_SHORT).show();

                }
            })
            {
                protected HashMap<String,String> getParams()throws AuthFailureError{



                    HashMap<String,String> params=new HashMap<String, String>();

                    params.put("issue_name",issue_name.getText().toString());
                    params.put("issue_phone",issue_phone.getText().toString());
                    params.put("issue_email",issue_email.getText().toString());
                    params.put("issue_issue",issue_issue.getText().toString());

                    return params;
                }

            };

            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);

        }
    }
}
