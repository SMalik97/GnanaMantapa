package com.example.vishwanandini;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Fragment_myaccount extends Fragment {
Button register,login,logout;
String login_status="No",login_name="No",login_city="No",login_phone="No",login_email="No";
LinearLayout profileView, loginView;
    String ls,fetch_url="https://vp254.co.ke/vishwa/login.php";
    EditText userid,password;
    ProgressDialog progressDialog;
    List<ListDataUdetails> list_data_details;
    TextView username,usercity,useremail,userphoneno;


    public Fragment_myaccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_myaccount, container, false);

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait, processing...");
        progressDialog.setCancelable(true);

        username=(TextView)view.findViewById(R.id.username);
        usercity=(TextView)view.findViewById(R.id.usercity);
        userphoneno=(TextView)view.findViewById(R.id.userphoneno);
        useremail=(TextView)view.findViewById(R.id.useremail);
        logout=(Button)view.findViewById(R.id.logout);

        list_data_details=new ArrayList<>();


        userid=(EditText)view.findViewById(R.id.userid);
        password=(EditText)view.findViewById(R.id.password);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getActivity().getSharedPreferences(login_status, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("loginStatus","No");
                editor.apply();

                loginView.setVisibility(View.VISIBLE);
                profileView.setVisibility(View.GONE);
            }
        });

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

            SharedPreferences b=getActivity().getSharedPreferences(login_name, Context.MODE_PRIVATE);
            String n=b.getString("loginName","No");
            username.setText(n);

            SharedPreferences c=getActivity().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
            String p=c.getString("loginPhone","No");
            userphoneno.setText(p);

            SharedPreferences d=getActivity().getSharedPreferences(login_city, Context.MODE_PRIVATE);
            String ci=d.getString("loginCity","No");
            usercity.setText(ci);

            SharedPreferences e=getActivity().getSharedPreferences(login_email, Context.MODE_PRIVATE);
            String em=e.getString("loginEmail","No");
            useremail.setText(em);
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                login_backwork ob=new login_backwork();
                new Thread(ob).start();

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

    class login_backwork implements Runnable
    {

        @Override
        public void run() {

            final String userids=userid.getText().toString().trim(),
                    passwords=password.getText().toString().trim();

            StringRequest request=new StringRequest(Request.Method.POST, fetch_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if(!response.trim().equals("0")) {


                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject ob = array.getJSONObject(i);
                                ListDataUdetails listDataUdetails = new ListDataUdetails(
                                        ob.getString("id"),
                                        ob.getString("name"),
                                        ob.getString("mobile"),
                                        ob.getString("city"),
                                        ob.getString("email"));

                                list_data_details.add(listDataUdetails);
                                username.setText(listDataUdetails.getName());
                                userphoneno.setText(listDataUdetails.getMobile());
                                usercity.setText(listDataUdetails.getCity());
                                useremail.setText(listDataUdetails.getEmail());


                                SharedPreferences sp=getActivity().getSharedPreferences(login_status, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sp.edit();
                                editor.putString("loginStatus","Yes");
                                editor.apply();

                                SharedPreferences sp2=getActivity().getSharedPreferences(login_name, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2=sp2.edit();
                                editor2.putString("loginName",listDataUdetails.getName());
                                editor2.apply();

                                SharedPreferences sp3=getActivity().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor3=sp3.edit();
                                editor3.putString("loginPhone",listDataUdetails.getMobile());
                                editor3.apply();

                                SharedPreferences sp4=getActivity().getSharedPreferences(login_city, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor4=sp4.edit();
                                editor4.putString("loginCity",listDataUdetails.getCity());
                                editor4.apply();

                                SharedPreferences sp5=getActivity().getSharedPreferences(login_email, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor5=sp5.edit();
                                editor5.putString("loginEmail",listDataUdetails.getEmail());
                                editor5.apply();


                            }
                            loginView.setVisibility(View.GONE);
                            profileView.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), " Logged In Successful ", Toast.LENGTH_SHORT).show();




                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Something went on wrong!, please try again", Toast.LENGTH_SHORT).show();

                        }



                    }else{
                        Toast.makeText(getContext(), " Invalid phone number or password!", Toast.LENGTH_SHORT).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Slow internet connection, please try again", Toast.LENGTH_SHORT).show();

                }
            }){

                protected HashMap<String,String> getParams()throws AuthFailureError
                {
                    HashMap<String,String> params=new HashMap<>();
                    params.put("userids",userids);
                    params.put("passwords",passwords);



                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);

        }


    }



}
