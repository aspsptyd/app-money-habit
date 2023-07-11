package id.aspsptyd.money_habit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.aspsptyd.money_habit.R;

public class AdapterListIncome extends RecyclerView.Adapter<AdapterListIncome.MyViewHolder>{

    Context context;

    private ArrayList<String> id_income;
    private ArrayList<String> title_income;
    private ArrayList<String> category_income;
    private ArrayList<String> description_income;
    private ArrayList<String> income;
    private ArrayList<String> creator_id;
    private ArrayList<String> created_at;

    public AdapterListIncome(Context context,
                                       ArrayList<String> id_income,
                                       ArrayList<String> title_income,
                                       ArrayList<String> category_income,
                                       ArrayList<String> description_income,
                                       ArrayList<String> income,
                                       ArrayList<String> creator_id,
                                       ArrayList<String> created_at) {
        this.context = context;
        this.id_income = id_income;
        this.title_income = title_income;
        this.category_income = category_income;
        this.description_income = description_income;
        this.income = income;
        this.creator_id = creator_id;
        this.created_at = created_at;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_income, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id.setText(id_income.get(position));
        holder.title.setText(title_income.get(position));
        holder.category.setText(category_income.get(position));
        holder.desc.setText(description_income.get(position));
        holder.income.setText(income.get(position));
        holder.user_id.setText(creator_id.get(position));
        holder.created_at.setText(created_at.get(position));

        holder.item_access.setOnClickListener(v -> {
            Toast.makeText(context, title_income.get(position), Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public int getItemCount() {
        return id_income.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, title, category, desc, income, user_id, created_at;

        View item_access;

        public MyViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.lbl_id);
            title = itemView.findViewById(R.id.lbl_title);
            category = itemView.findViewById(R.id.lbl_category);
            desc = itemView.findViewById(R.id.lbl_description);
            income = itemView.findViewById(R.id.lbl_income);
            user_id = itemView.findViewById(R.id.lbl_id_user);
            created_at = itemView.findViewById(R.id.lbl_created_at);

            item_access = itemView.findViewById(R.id.item);
        }
    }
}
