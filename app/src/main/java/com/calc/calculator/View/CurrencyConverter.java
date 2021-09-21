package com.calc.calculator.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.calc.calculator.Core.CurrencyContract;
import com.calc.calculator.Core.CurrencyPresenter;
import com.calc.calculator.Model.Currency;
import com.calc.calculator.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class CurrencyConverter extends AppCompatActivity implements CurrencyContract.View {
    private TextView result;
    private CurrencyPresenter mPresenter;

    @Override
    public boolean onSupportNavigateUp(){
        finish();

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_converter);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Button conv_button = findViewById(R.id.convert_button);
        EditText currency_editText = findViewById(R.id.currency_to_be_converted);
        ImageView im_swap = findViewById(R.id.swap);
        mPresenter = new CurrencyPresenter(this);
        result = findViewById(R.id.currency_result);

        Spinner convertFromDropdown, convertToDropdown;
        convertFromDropdown = findViewById(R.id.convert_from);
        convertToDropdown = findViewById(R.id.convert_to);

        String[] CurrencyList = getResources().getStringArray(R.array.currency_codes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, CurrencyList);
        //set adapter for Currency Spinners
        convertFromDropdown.setAdapter(adapter);
        convertToDropdown.setAdapter(adapter);

        conv_button.setOnClickListener(view -> {
            try{
                if (currency_editText.getText().toString().isEmpty())
                    Toasty.error(this, getString(R.string.enter_convert_value), Toast.LENGTH_SHORT).show();

                else {
                    Double amountToConvert = Double.valueOf(currency_editText.getText().toString());
                    Currency currency = new Currency(convertFromDropdown.getSelectedItem().toString(), convertToDropdown.getSelectedItem().toString(), amountToConvert);

                    mPresenter.NewConversion(this, currency);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        im_swap.setOnClickListener(view -> {        //swap the convertFrom and the convertTo currencies
            int fromvalue_position = adapter.getPosition(convertFromDropdown.getSelectedItem().toString());
            int tovalue_position = adapter.getPosition(convertToDropdown.getSelectedItem().toString());
            convertFromDropdown.setSelection(tovalue_position);
            convertToDropdown.setSelection(fromvalue_position);
        });
    }

    @Override
    public void onNewConversionSuccess(String conversionValue) {
        result.setText(conversionValue);
    }

    @Override
    public void onNewConversionFailure(String message) {
        Toast.makeText(CurrencyConverter.this, message,
                Toast.LENGTH_SHORT).show();
    }
}
