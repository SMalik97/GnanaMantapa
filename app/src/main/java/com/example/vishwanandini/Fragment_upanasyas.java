package com.example.vishwanandini;


import android.app.ProgressDialog;
import android.content.Context;
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


public class Fragment_upanasyas extends Fragment {
    String url = "https://vp254.co.ke/vishwa/fetch_upanasyas.php";
    List<ListDataArticles> list_data_articles;
    String[] id;
    String[] title;
    String[] content;
    String[] audio;
    int l;
    ListView listView;
    ProgressBar progressBar;
    String head;

    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;

    String comment_url="https://vp254.co.ke/vishwa/insert_comment.php";
    String login_status="No",login_name="No",login_email="No";

    public Fragment_upanasyas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_upanasyas, container, false);

        head=getActivity().getIntent().getExtras().getString("head");
        //Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();

        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);

        listView=(ListView)view.findViewById(R.id.listview);

        list_data_articles=new ArrayList<>();

        fetchupanasyas fa=new fetchupanasyas();
        new Thread(fa).start();


        //initialize media player
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        progressDialog = new ProgressDialog(getContext());


        return view;
    }

    class fetchupanasyas implements Runnable{
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
                    Toast.makeText(getContext(), "Some error occurred!", Toast.LENGTH_SHORT).show();
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
                    if (!playPause) {
                        audioStream.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);

                        if (initialStage) {
                            new Player().execute(audio[position]);
                        } else {
                            if (!mediaPlayer.isPlaying())
                                mediaPlayer.start();
                        }

                        playPause = true;

                    } else {
                        audioStream.setImageResource(R.drawable.ic_play_arrow_black_24dp);


                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        }

                        playPause = false;
                    }
                }

            });






            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    comment.setVisibility(View.GONE);
                    commentView.setVisibility(View.VISIBLE);
                    typeComment.requestFocus();
                    comment.setVisibility(View.GONE);
                    commentView.setVisibility(View.VISIBLE);
                }
            });

            commentPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    comment.setVisibility(View.VISIBLE);
                    commentView.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Posting comment...", Toast.LENGTH_SHORT).show();

                    final String typecomments=typeComment.getText().toString().trim();


                    SharedPreferences b=getActivity().getSharedPreferences(login_name, Context.MODE_PRIVATE);
                    final String comment_name=b.getString("loginName","No");

                    SharedPreferences e=getActivity().getSharedPreferences(login_email, Context.MODE_PRIVATE);
                    final String comment_email=e.getString("loginEmail","No");

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
                            HashMap<String,String> params=new HashMap<>();

                            params.put("typecomments",typecomments);
                            params.put("comment_name",comment_name);
                            params.put("comment_email",comment_email);
                            params.put("postid",id[position]);
                            params.put("postcatagory","Upanyasas");

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

    @Override
    public void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    class Player extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initialStage = true;
                        playPause = false;
                        //show play icon
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.prepare();
                prepared = true;

            } catch (Exception e) {

                prepared = false;
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            mediaPlayer.start();
            initialStage = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Buffering...");
            progressDialog.show();
        }


    }
}
