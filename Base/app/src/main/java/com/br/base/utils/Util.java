package com.br.base.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * Created by peterson on 09/01/17.
 */

public class Util {

    public static final String parserDate(final String date){
        try {
            final SimpleDateFormat yyyyDDmm = new SimpleDateFormat(Constants.DATE_FORMATTER,  Locale.US);
            final SimpleDateFormat MMMMDDyyyy = new SimpleDateFormat(Constants.LITERAL_DATE_FORMATTER, Locale.US);
            final Date date1 = yyyyDDmm.parse(date);
            return MMMMDDyyyy.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatValue(double value) {
        DecimalFormat format = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("en", "US")));
        String symbol = Currency.getInstance(new Locale("en", "US")).getSymbol();
        format.setMinimumFractionDigits(2);
        format.setParseBigDecimal(true);
        return symbol + " " + format.format(value);
    }

}
