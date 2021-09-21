package com.calc.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.calc.calculator.Adapters.Calculator_Adapter;
import com.calc.calculator.DataBase.DataBaseHelper;
import com.calc.calculator.DataBase.Model.Calculator;
import com.calc.calculator.View.CurrencyConverter;
import com.calc.calculator.utils.Funct;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText display;
    private DataBaseHelper db;
    private Calculator_Adapter mAdapter;
    private List<Calculator> historyList;
    private Dialog dialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);
        historyList = new ArrayList<>();
        historyList.addAll(db.getAllHistory());  //fill calculation history list from SQLite

        display = findViewById(R.id.praxeis);
        display.setShowSoftInputOnFocus(false); //hide keyboard

        createDialog();

        recyclerView = dialog.findViewById(R.id.history_recy);
        mAdapter = new Calculator_Adapter(this, historyList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bClear, bParenth, bPerc, bDivision, bX, bminus, bAdd, bSynPlhn, bPoint, bEquals;
        b0 = findViewById(R.id.zero); b1 = findViewById(R.id.one); b2 = findViewById(R.id.two); b3 = findViewById(R.id.three); b4 = findViewById(R.id.four);
        b5 = findViewById(R.id.five); b6 = findViewById(R.id.six); b7 = findViewById(R.id.sieben); b8 = findViewById(R.id.acht); b9 = findViewById(R.id.neun);
        bClear = findViewById(R.id.clear); bParenth = findViewById(R.id.parentheses); bPerc = findViewById(R.id.percentage); bDivision = findViewById(R.id.divide); bX = findViewById(R.id.multiply);
        bminus = findViewById(R.id.substract); bAdd = findViewById(R.id.add); bSynPlhn = findViewById(R.id.plusminus); bPoint = findViewById(R.id.point); bEquals = findViewById(R.id.equals);

        ImageButton history = findViewById(R.id.history), backSpace = findViewById(R.id.backspace);
        ImageView exchange = findViewById(R.id.exchange);

        b0.setOnClickListener(this); b1.setOnClickListener(this); b2.setOnClickListener(this); b3.setOnClickListener(this); b4.setOnClickListener(this);
        b5.setOnClickListener(this); b6.setOnClickListener(this); b7.setOnClickListener(this); b8.setOnClickListener(this); b9.setOnClickListener(this);
        bClear.setOnClickListener(this); bParenth.setOnClickListener(this); bPerc.setOnClickListener(this); bDivision.setOnClickListener(this); bX.setOnClickListener(this);
        bminus.setOnClickListener(this); bAdd.setOnClickListener(this); bSynPlhn.setOnClickListener(this); bPoint.setOnClickListener(this); bEquals.setOnClickListener(this);
        history.setOnClickListener(this); backSpace.setOnClickListener(this); exchange.setOnClickListener(this);

    }

    private void updateText(String input) {//updating the text and moving the cursor. Also setting text according to left or right parentheses
        String beforeText = display.getText().toString();
        int pos = display.getSelectionStart();

        String left = beforeText.substring(0, pos);
        String right = beforeText.substring(pos);

        if (input.equals(""))
            display.setText(input);
        else {
            if (beforeText.equals(""))
                display.setText(input);
            else
                display.setText(String.format("%s%s%s", left, input, right));
            display.setSelection(pos + 1);
        }
    }

    private void createHistoryItem(String input, String result) {

        long id = db.insertHistory(input, result);

        // get the newly inserted note from db
        Calculator c = db.getHistory(id);

        if (c != null) {
            // adding new history to array list at last position
            historyList.add(0, c);

            // refreshing the list
            mAdapter.notifyDataSetChanged();
            Funct.scrollToTop(recyclerView);
        }
    }

    //create custom dialog for calculator history
    public void createDialog(){
        dialog = new Dialog(this, R.style.MyDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv = dialog.findViewById(R.id.ok_custom);
        tv.setOnClickListener(v -> dialog.dismiss());

        TextView tv2 = dialog.findViewById(R.id.clear_history_custom);
        tv2.setOnClickListener(view1 -> {
            db.Delete_Table();
            historyList.clear();
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), getString(R.string.no_history),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onClick(View view) {
        int pos = display.getSelectionStart();
        switch (view.getId()){

            case R.id.one:
                updateText(getString(R.string.one));

                break;

            case R.id.two:
                updateText(getString(R.string.two));

                break;


            case R.id.three:
                updateText(getString(R.string.three));

                break;

            case R.id.four:
                updateText(getString(R.string.four));

                break;

            case R.id.five:
                updateText(getString(R.string.five));

                break;

            case R.id.six:
                updateText(getString(R.string.six));

                break;

            case R.id.sieben:
                updateText(getString(R.string.sieben));

                break;

            case R.id.acht:
                updateText(getString(R.string.acht));

                break;

            case R.id.neun:
                updateText(getString(R.string.neun));

                break;

            case R.id.zero:
                updateText(getString(R.string.zero));

                break;

            case R.id.divide:
                updateText(getString(R.string.divide));

                break;

            case R.id.multiply:
                updateText(getString(R.string.multiply));

                break;

            case R.id.substract:

            case R.id.plusminus:
                updateText(getString(R.string.substract));

                break;

            case R.id.add:
                updateText(getString(R.string.add));

                break;


            case R.id.equals:
                String expr = display.getText().toString();

                expr = expr.replaceAll(getString(R.string.divide), "/");
                expr = expr.replaceAll(getString(R.string.multiply), "*");

                Expression mathExpr = new Expression(expr);
                String result = String.valueOf(mathExpr.calculate());

                if (!result.equals("NaN"))
                    createHistoryItem(expr, result);

                display.setText(result);
                display.setSelection(result.length());

                break;

            case R.id.backspace:
                //covers the backspace case and moves the cursor accordingly
                if (pos != 0 && display.getText().length() != 0){
                    SpannableStringBuilder sel = (SpannableStringBuilder) display.getText();
                    sel.replace(pos - 1, pos, "");
                    display.setText(sel);
                    display.setSelection(pos - 1);
                }

                break;

            case R.id.clear:
                updateText("");

                break;

            case R.id.parentheses:        //code for deciding whether to insert left or right parenthesis
                int openPar = 0, closePar = 0;
                int len = display.getText().length();

                for (int i=0; i<pos; i++){
                    if (display.getText().toString().substring(i, i+1).equals("("))
                        openPar++;
                    if (display.getText().toString().substring(i, i+1).equals(")"))
                        closePar++;
                }

                if (openPar == closePar || display.getText().toString().substring(len - 1, len).equals("("))
                    updateText("(");
                else if (closePar < openPar && !display.getText().toString().substring(len - 1, len).equals("("))
                    updateText(")");
                display.setSelection(pos + 1);

                break;

            case R.id.percentage:
                updateText(getString(R.string.percentage));

                break;

            case R.id.point:
                updateText(getString(R.string.point));

                break;

            case R.id.history:
                if (db.getHistoryCount() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_history),
                            Toast.LENGTH_SHORT).show();
                }

                else {
                    Funct.scrollToTop(recyclerView);
                    dialog.show();
                }
                break;

            case R.id.exchange:
                startActivity(new Intent(this, CurrencyConverter.class));

                break;

        }
    }
}