package com.goldenfuturecommunication.gnanamantapa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.goldenfuturecommunication.gnanamantapa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commentposting extends AppCompatActivity {
    String url = "https://vp254.co.ke/vishwa/fetch_comment.php";
    ListView listView;
    String[] postId;
    String[] postcatagory;
    String[] name;
    String[] email;
    String[] comment;
    String[] postingDate;
    List<ListDatacomnt> list_data_comnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentposting);
        listView=(ListView)findViewById(R.id.listview);
        list_data_comnt=new ArrayList<>();
        fetchcomment fc=new fetchcomment();
        new Thread(fc).start();

    }


    class fetchcomment implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("data");
                        //s=array.length();
                        postId=new String[array.length()];
                        postcatagory=new String[array.length()];
                        name=new String[array.length()];
                        email=new String[array.length()];
                        comment=new String[array.length()];
                        postingDate=new String[array.length()];
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject ob = array.getJSONObject(i);
                            ListDatacomnt listDatacomnts = new ListDatacomnt(
                                    ob.getString("postId"),
                                    ob.getString("postcatagory"),
                                    ob.getString("name"),
                                    ob.getString("email"),
                                    ob.getString("comment"),
                                    ob.getString("postingDate"));
                            list_data_comnt.add(listDatacomnts);



                            postId[i] = listDatacomnts.getPostId();
                            postcatagory[i] = listDatacomnts.getPostcatagory();
                            name[i] = listDatacomnts.getName();
                            email[i] = listDatacomnts.getEmail();
                            comment[i] = listDatacomnts.getComment();
                            postingDate[i] = listDatacomnts.getPostingDate();

                            Toast.makeText(Commentposting.this, comment[i]+"", Toast.LENGTH_SHORT).show();
//                            Fragment_articles.CustomAdaptor customAdaptor=new Fragment_articles.CustomAdaptor();
//                            listView.setAdapter(customAdaptor);

                        }

                       // progressBar.setVisibility(View.INVISIBLE);


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
                    Toast.makeText(getApplicationContext(), "Slow internet connection!", Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

}
