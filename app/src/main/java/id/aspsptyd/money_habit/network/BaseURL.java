package id.aspsptyd.money_habit.network;

import android.content.Context;

public class BaseURL {
    Context context;
    String BASE_URL_SERVER = "http://192.168.224.237";
    String PORT_SERVER = ":8089";

    public BaseURL (Context context) {
        this.context = context;
    }

    public String apiListIncome() {
        String listIncome = BASE_URL_SERVER + PORT_SERVER + "/api/v1/income";
        return listIncome;
    }
}
