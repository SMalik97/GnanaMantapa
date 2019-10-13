package com.example.vishwanandini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class notification extends AppCompatActivity {

    String notifyId[],notifyTitle[],notifyContent[],fetch_notify="https://vp254.co.ke/vishwa/fetch_notify2.php";

    CardView nonotificationView,loadingView;

    int l;

    ListView listView;
    List<ListDataArticles> list_data_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        nonotificationView=(CardView)findViewById(R.id.nonotificationView) ;
        loadingView=(CardView)findViewById(R.id.loadingView);

        list_data_items=new ArrayList<>();

        listView=(ListView)findViewById(R.id.listview);

        fetchdata fd=new fetchdata();
        new Thread(fd).start();




    }

    class fetchdata implements Runnable
    {

        @Override
        public void run() {


            StringRequest request=new StringRequest(Request.Method.POST, fetch_notify, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (!response.trim().equals("No")) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");

                            notifyId = new String[array.length()];
                            notifyTitle = new String[array.length()];
                            notifyContent = new String[array.length()];

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject ob = array.getJSONObject(i);
                                ListDataArticles notify_articles = new ListDataArticles(
                                        ob.getString("notifyId"),
                                        ob.getString("notifyTitle"),
                                        ob.getString("notifyContent"));

                                list_data_items.add(notify_articles);

                                l = i;

                                notifyId[i] = notify_articles.getId();
                                notifyTitle[i] = notify_articles.getTitle();
                                notifyContent[i] = notify_articles.getContent();


                            }

                            CustomAdapter customAdapter = new CustomAdapter();
                            listView.setAdapter(customAdapter);
                            loadingView.setVisibility(View.GONE);
                            nonotificationView.setVisibility(View.GONE);


                        } catch (Exception e) {
                            loadingView.setVisibility(View.GONE);

                        }
                    }else {
                        loadingView.setVisibility(View.GONE);
                        nonotificationView.setVisibility(View.VISIBLE);

                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    run();
                    loadingView.setVisibility(View.GONE);
                    nonotificationView.setVisibility(View.VISIBLE);

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);


        }
    }

    class CustomAdapter extends BaseAdapter
    {

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

            convertView=getLayoutInflater().inflate(R.layout.notify_articles,null);

            TextView notify_title=(TextView)convertView.findViewById(R.id.notify_title);
            TextView notify_content=(TextView)convertView.findViewById(R.id.notify_content);

            notify_title.setText(notifyTitle[position]);
            notify_content.setText(notifyContent[position]);

            return convertView;
        }
    }
}