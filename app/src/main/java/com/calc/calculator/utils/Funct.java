package com.calc.calculator.utils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funct {
    public static double round(double value, int places){   //round the given value to the first (places) digits
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public static void scrollToTop(RecyclerView recyclerView){    //scroll recyclerView to top
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView
                .getLayoutManager();
        assert layoutManager != null;
        layoutManager.scrollToPositionWithOffset(0, 0);
    }
}
