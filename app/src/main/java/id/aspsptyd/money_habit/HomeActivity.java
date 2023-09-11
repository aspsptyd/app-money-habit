package id.aspsptyd.money_habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.aspsptyd.money_habit.adapter.AdapterListIncome;
import id.aspsptyd.money_habit.network.BaseURL;

public class HomeActivity extends AppCompatActivity {

    BaseURL baseURL;

    RecyclerView rlistIncome;
    TextView tvTextMessage;

    private ArrayList<String> id_income;
    private ArrayList<String> title_income;
    private ArrayList<String> category_income;
    private ArrayList<String> description_income;
    private ArrayList<String> income;
    private ArrayList<String> creator_id;
    private ArrayList<String> created_at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        baseURL = new BaseURL(HomeActivity.this);

        rlistIncome = (RecyclerView)findViewById(R.id.rvIncomeList);
        RecyclerView.LayoutManager layoutManagerProduct = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rlistIncome.setLayoutManager(layoutManagerProduct);

        tvTextMessage = (TextView) findViewById(R.id.txtMessage);

        getAllIncome();
    }

    public void getAllIncome() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseURL.apiListIncome(),
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);

                        boolean statusResponse = Boolean.parseBoolean(obj.getString("status"));
                        if (statusResponse) {
                            tvTextMessage.setText(obj.getString("msg"));

                            JSONArray listIncome = new JSONArray(obj.getString("data_income"));
                            id_income = new ArrayList<>();
                            title_income = new ArrayList<>();
                            category_income = new ArrayList<>();
                            description_income = new ArrayList<>();
                            income = new ArrayList<>();
                            creator_id = new ArrayList<>();
                            created_at = new ArrayList<>();

                            for(int i = 0; i < listIncome.length(); i++){
                                JSONObject objArray = listIncome.getJSONObject(i);
                                id_income.add(objArray.getString("id"));
                                title_income.add(objArray.getString("income_title"));
                                category_income.add(objArray.getString("category_income"));
                                description_income.add(objArray.getString("description_of_income"));
                                income.add(objArray.getString("income"));
                                creator_id.add(objArray.getString("creator"));
                                created_at.add(objArray.getString("created_at"));
                            }

                            AdapterListIncome adapterListIncome = new AdapterListIncome(this, id_income, title_income, category_income, description_income, income, creator_id, created_at);
                            rlistIncome.setAdapter(adapterListIncome);
                        } else {
                            tvTextMessage.setText(obj.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    tvTextMessage.setText(error.toString());
                }
        ){

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };

        try {
            stringRequest.setShouldCache(false);
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    30000,                        // 30 Second then Retry
                    0,                                          // maxNumRetries = 0 means no retry
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(stringRequest);
        } catch (Exception error) {
            tvTextMessage.setText(error.toString());
        }
    }
}