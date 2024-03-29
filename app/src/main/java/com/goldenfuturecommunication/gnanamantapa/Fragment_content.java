package com.goldenfuturecommunication.gnanamantapa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.List;


public class Fragment_content extends Fragment {
    View view;

    String url = "https://vp254.co.ke/vishwa/fetch_vishwa.php";
    List<ListDataItems> list_data_items;
    String[] id;
    String[] cat;
    String[] des;

    int l;
    ListView listView;
    ProgressBar progressBar;
    String language="English";


    public Fragment_content() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_fragment_vishwanandini,container,false);

        //check language
        SharedPreferences b=getActivity().getSharedPreferences(language, Context.MODE_PRIVATE);
        String l=b.getString("language","English");

        if (l.equals("Kanada")){
            url = "https://vp254.co.ke/vishwa/fetch_vishwa_kanada.php";

        }else {
            url = "https://vp254.co.ke/vishwa/fetch_vishwa.php";
        }



        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        list_data_items=new ArrayList<>();

        listView=view.findViewById(R.id.listview);

        fetchData fd=new fetchData();
        new Thread(fd).start();



        return view;
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
            convertView = getLayoutInflater().inflate(R.layout.articles_list, null);

            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView content=(TextView)convertView.findViewById(R.id.content);

            title.setText(cat[position]);
            content.setText(des[position]);


            return convertView;
        }
    }

    class fetchData implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("data");
                        id=new String[array.length()];
                        cat=new String[array.length()];
                        des=new String[array.length()];

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject ob = array.getJSONObject(i);
                            ListDataItems listdataitems = new ListDataItems(
                                    ob.getString("cid"),
                                    ob.getString("CategoryName"),
                                    ob.getString("Description"));
                            list_data_items.add(listdataitems);

                            l = i;

                            id[i] = listdataitems.getId();
                            cat[i] = listdataitems.getCat();
                            des[i] = listdataitems.getDes();


                        }
                        CustomAdaptor customAdaptor=new CustomAdaptor();
                        listView.setAdapter(customAdaptor);
                        progressBar.setVisibility(View.INVISIBLE);


                        //listView item click action
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(getContext(),vishwa_articles.class);
                                intent.putExtra("head",id[i]);
                                startActivity(intent);
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
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }
    }



}

