package com.calc.calculator.Core;

import android.content.Context;
import com.calc.calculator.Model.Currency;

public class CurrencyPresenter implements CurrencyContract.Presenter, CurrencyContract.onOperationListener {
    private CurrencyContract.View mView;
    private CurrencyInteractor mInteractor;

    public CurrencyPresenter(CurrencyContract.View mView) {
        this.mView = mView;
        mInteractor = new CurrencyInteractor(this);
    }

    @Override
    public void NewConversion(Context context, Currency currency) {
        mInteractor.performNewConversion(context, currency);
    }

    @Override
    public void onSuccess(String conversionValue) {
        mView.onNewConversionSuccess(conversionValue);
    }

    @Override
    public void onFailure(String message) {
        mView.onNewConversionFailure(message);
    }
}
