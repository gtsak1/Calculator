package com.calc.calculator.Adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calc.calculator.DataBase.Model.Calculator;
import com.calc.calculator.R;

import java.util.List;

public class Calculator_Adapter extends RecyclerView.Adapter<Calculator_Adapter.MyViewHolder> {

    private List<Calculator> historyList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView input, result;

        public MyViewHolder(View view) {
            super(view);
            input = view.findViewById(R.id.input);
            result = view.findViewById(R.id.result);

            input.setOnClickListener(view1 -> copyToclipboard(input));

            result.setOnClickListener(view2 -> copyToclipboard(result));

        }
    }


    public Calculator_Adapter(Context context, List<Calculator> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Calculator historyItem = historyList.get(position);

        holder.input.setText(historyItem.getInput());
        holder.result.setText("= " + historyItem.getResult());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void copyToclipboard(TextView copyText){

        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copyText", copyText.getText().toString().replaceAll("=", "").trim());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(context, context.getString(R.string.copy_to_clipboard),
                Toast.LENGTH_SHORT).show();
    }

}
