package mobileappscompany.w5weekend;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity: ";
    private RecyclerView recyclerView;
    private AdapterBook adapterBook;
    private List<Book> bookList;
    public static final String URL_DATA = "http://de-coding-test.s3.amazonaws.com/books.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookList = new ArrayList<>();
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        //From Volley
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            //JSONObject jsonObject = new JSONObject(response);
                            //JSONArray array = jsonObject.getJSONArray("books");

                            JSONArray array = new JSONArray(response);

                            String title, author, imageURL;
                            Book book;

                            for (int i = 0; i < 10; i++) {

                                JSONObject object = array.getJSONObject(i);

                                Log.d(TAG, "Title: " + object.getString("title"));
                                Log.d(TAG, "imageURL: " + object.getString("imageURL"));

                                title = object.getString("title");
                                author = "";
                                imageURL = object.getString("imageURL");

                                if(author.isEmpty() && imageURL.isEmpty()){
                                    book = new Book(
                                            object.getString("title"),
                                            "",
                                            "");
                                }else if(author.isEmpty()){
                                    book = new Book(
                                            object.getString("title"),
                                            "",
                                            imageURL);
                                } else if (imageURL.isEmpty()) {
                                    book = new Book(
                                            object.getString("title"),
                                            object.getString("author"),
                                            ""
                                    );
                                }else
                                    book = new Book(
                                            object.getString("title"),
                                            object.getString("author"),
                                            object.getString("imageURL")

                                    );
                                bookList.add(book);
                            }
                            adapterBook = new AdapterBook(getApplicationContext(), bookList);
                            recyclerView.setAdapter(adapterBook);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}