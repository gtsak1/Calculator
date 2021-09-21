package com.calc.calculator.Core;

import android.content.Context;
import com.calc.calculator.Model.Currency;

public interface CurrencyContract {
    interface View{
        void onNewConversionSuccess(String conversionValue);
        void onNewConversionFailure(String message);
    }

    interface Presenter{
        void NewConversion(Context context, Currency currency);
    }

    interface Interactor{
        void performNewConversion(Context context, Currency Currency);
    }

    interface onOperationListener{
        void onSuccess(String conversionValue);
        void onFailure(String message);
    }
}
