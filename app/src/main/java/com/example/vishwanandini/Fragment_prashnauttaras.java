package com.example.vishwanandini;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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

public class Fragment_prashnauttaras extends Fragment {
    String url = "https://vp254.co.ke/vishwa/fetch_prashnauttaras.php";
    List<ListDataArticles> list_data_articles;
    String[] id;
    String[] title;
    String[] content;
    int l;
    ListView listView;
    ProgressBar progressBar;
    String head;


    public Fragment_prashnauttaras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_prashnauttaras, container, false);

        head=getActivity().getIntent().getExtras().getString("head");
       // Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
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
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject ob = array.getJSONObject(i);
                            ListDataArticles listdataarticles = new ListDataArticles(
                                    ob.getString("id"),
                                    ob.getString("title"),
                                    ob.getString("content"));
                            list_data_articles.add(listdataarticles);

                            l = i;

                            id[i] = listdataarticles.getId();
                            title[i] = listdataarticles.getTitle();
                            content[i] = listdataarticles.getContent();

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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.articles, null);

            TextView atitle = (TextView) convertView.findViewById(R.id.atitle);
            TextView acontent=(TextView)convertView.findViewById(R.id.acontent);

            atitle.setText(title[position]);
            acontent.setText(content[position]);

            return convertView;
        }
    }


}
