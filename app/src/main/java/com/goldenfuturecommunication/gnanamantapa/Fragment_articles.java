package com.goldenfuturecommunication.gnanamantapa;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_articles extends Fragment {
    String url;

    String comment_url="https://vp254.co.ke/vishwa/insert_comment.php";
    String login_status="No",login_name="No",login_email="No";
    String em;
    List<ListDataArticles> list_data_articles;
    String[] id;
    String[] title;
    String[] content;
    String[] audio;
    int l;
    ListView listView;
    ProgressBar progressBar;
    String head;
    String ls;
    String language="English";

    public Fragment_articles() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_articles, container, false);



        //check language
        SharedPreferences b=getActivity().getSharedPreferences(language, Context.MODE_PRIVATE);
        String l=b.getString("language","English");
       // Toast.makeText(getContext(), l+"", Toast.LENGTH_SHORT).show();
        if (l.equals("Kanada")){
            url = "https://vp254.co.ke/vishwa/fetch_articles_kanada.php";
        }else {
            url = "https://vp254.co.ke/vishwa/fetch_articles.php";
        }


        head=getActivity().getIntent().getExtras().getString("head");

        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);

        listView=(ListView)view.findViewById(R.id.listview);

        list_data_articles=new ArrayList<>();

        fetcharticles fa=new fetcharticles();
        new Thread(fa).start();




        return view;
    }

    class fetcharticles implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("data");
                        //s=array.length();
                        id=new String[array.length()];
                        content=new String[array.length()];
                        title=new String[array.length()];
                        audio=new String[array.length()];
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject ob = array.getJSONObject(i);
                            ListDataArticles listdataarticles = new ListDataArticles(
                                    ob.getString("id"),
                                    ob.getString("title"),
                                    ob.getString("content"),
                                    ob.getString("audioLink"));
                            list_data_articles.add(listdataarticles);

                            l = i;

                            id[i] = listdataarticles.getId();
                            title[i] = listdataarticles.getTitle();
                            content[i] = listdataarticles.getContent();
                            audio[i] = listdataarticles.getAudio();

                            CustomAdaptor customAdaptor=new CustomAdaptor();
                            listView.setAdapter(customAdaptor);

                        }

                        progressBar.setVisibility(View.INVISIBLE);


                        //listView item click action
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                Intent intent=new Intent(getContext(),vishwa_articles.class);
//                                intent.putExtra("head",head[i]);
//                                startActivity(intent);
                            }
                        });


                    } catch (JSONException e) {

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Slow internet connection!", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("cat",head);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }
    }

    class CustomAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return l+1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.articles, null);

            TextView atitle = (TextView) convertView.findViewById(R.id.atitle);
            TextView acontent=(TextView)convertView.findViewById(R.id.acontent);
            final TextView comment=(TextView)convertView.findViewById(R.id.comment);
            final EditText typeComment=(EditText) convertView.findViewById(R.id.typeComment);
            final LinearLayout commentView=(LinearLayout) convertView.findViewById(R.id.commentView);
            final ImageView commentPost=(ImageView) convertView.findViewById(R.id.commentPost);
            final ImageView audioStream=(ImageView)convertView.findViewById(R.id.audioStream);


            atitle.setText(title[position]);
            acontent.setText(content[position]);

            audioStream.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getContext(),audioPlay.class);
                    i.putExtra("title",title[position]);
                    i.putExtra("content",content[position]);
                    i.putExtra("link",audio[position]);
                    startActivity(i);
//
                }

            });





            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences a=getActivity().getSharedPreferences(login_status, Context.MODE_PRIVATE);
                    ls=a.getString("loginStatus","No");

                    if (ls.equals("Yes")) {
                        comment.setVisibility(View.GONE);
                        commentView.setVisibility(View.VISIBLE);
                        typeComment.requestFocus();
                        comment.setVisibility(View.GONE);
                        commentView.setVisibility(View.VISIBLE);
                        audioStream.setVisibility(View.INVISIBLE);
                    }else {
                        Toast.makeText(getContext(), "You have to login to comment on the post", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            commentPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    comment.setVisibility(View.VISIBLE);
                    commentView.setVisibility(View.GONE);
                    audioStream.setVisibility(View.VISIBLE);



                    SharedPreferences b=getActivity().getSharedPreferences(login_name, Context.MODE_PRIVATE);
                    final String comment_name=b.getString("loginName","No");


                    SharedPreferences e=getActivity().getSharedPreferences(login_email, Context.MODE_PRIVATE);
                    em=e.getString("loginEmail","No");

                    StringRequest request=new StringRequest(Request.Method.POST, comment_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getContext(), response.trim()+"", Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Slow internet connection!", Toast.LENGTH_SHORT).show();

                        }
                    }){

                        protected HashMap<String,String> getParams() throws AuthFailureError
                        {
                            //  Toast.makeText(getContext(), typecomments+" "+comment_name, Toast.LENGTH_SHORT).show();

                            HashMap<String,String> params=new HashMap<>();

                            params.put("typecomments",typeComment.getText().toString().trim());
                            params.put("comment_name",comment_name);
                            params.put("comment_email",em);
                            params.put("postid",id[position]);
                            params.put("postcatagory","Articles");

                            return params;
                        }

                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                    requestQueue.add(request);

                }
            });

            return convertView;
        }
    }






}