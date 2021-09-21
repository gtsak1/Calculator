package com.calc.calculator.Core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.calc.calculator.Model.Currency;
import com.calc.calculator.R;
import com.calc.calculator.utils.Funct;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyInteractor implements CurrencyContract.Interactor {
    private CurrencyContract.onOperationListener mListener;

    public CurrencyInteractor(CurrencyContract.onOperationListener mListener) {
        this.mListener = mListener;
    }

    //performs conversion between the 2 given currencies, requesting the rates from fixerio api using Volley
    @Override
    public void performNewConversion(Context context, Currency Currency) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://data.fixer.io/api/latest?access_key=" + context.getString(R.string.fixerio_access_key) + "&symbols="
                + Currency.FromCurrency + "," + Currency.ToCurrency;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        if (response.has("rates")) {
                            JSONObject rates = response.getJSONObject("rates");
                            double from_double, to_double;
                            from_double = rates.getDouble(Currency.FromCurrency);
                            to_double = rates.getDouble(Currency.ToCurrency);

                            mListener.onSuccess(Currency.amount + " " + Currency.FromCurrency + " = "
                                    + Funct.round((to_double / from_double) * Currency.amount, 2) + " " + Currency.ToCurrency);

                        }
                    } catch (JSONException exception) {
                        mListener.onFailure(context.getString(R.string.error_message));
                        exception.printStackTrace();
                    }

                }, error -> {
                    mListener.onFailure(context.getString(R.string.error_message));
                    error.printStackTrace();
                });
        queue.add(jsonObjectRequest);
    }
}
