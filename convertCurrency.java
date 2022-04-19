package convertCurrency;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author ExLibrisSN
 *
 */
public class convertCurrency {

    public static String convertCurrencyDigsToString(Double number, String currencyDecimal, String currencyFraction) {

        DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);
        String numStr = df.format(number);
        String result = "";
        String[] parts = numStr.split("\\.");
        String decimalPart = parts[0];
        String fractionPart = parts[1];

        decimalPart = decimalPartConvert(decimalPart).trim();
        fractionPart = treeDigsConvert(fractionPart).trim();
        String fullStr = (decimalPart + " " + currencyDecimal + " " + fractionPart + " " + currencyFraction);

        if (!"i".equals(fullStr.substring(0, 1))) {
            result = fullStr.substring(0, 1).toUpperCase() + fullStr.substring(1);
        } else {
            result = (fullStr.substring(0, 1).replace('i', '\u0130')) + fullStr.substring(1);
        }
        return result;

    }

    public static String convertCurrencyDigsToString(Long number, String currencyDecimal, String currencyFraction) {
        String numStr;
        numStr = number.toString();
        String fullStr = (decimalPartConvert(numStr).trim()) + " " + currencyDecimal;
        String result = "";

        if (!"i".equals(fullStr.substring(0, 1))) {
            result = fullStr.substring(0, 1).toUpperCase() + fullStr.substring(1);
        } else {
            result = (fullStr.substring(0, 1).replace('i', '\u0130')) + fullStr.substring(1);
        }
        return result;

    }

    public static String convertCurrencyDigsToString(String number, String currencyDecimal, String currencyFraction) {
        String result = "";
        String decimalPart = "";
        String fractionPart = "";
        String fullStr = "";

        String numStr = number.replace(",", ".");

        try {
            String[] parts = numStr.split("\\.");
            decimalPart = parts[0];
            fractionPart = parts[1];

            decimalPart = decimalPartConvert(decimalPart).trim();
            fractionPart = treeDigsConvert(fractionPart).trim();
            fullStr = (decimalPart + " " + currencyDecimal + " " + fractionPart + " " + currencyFraction);
            if (!"i".equals(fullStr.substring(0, 1))) {
                result = fullStr.substring(0, 1).toUpperCase() + fullStr.substring(1);
            } else {
                result = (fullStr.substring(0, 1).replace('i', '\u0130')) + fullStr.substring(1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            decimalPart = decimalPartConvert(numStr).trim();
            fullStr = (decimalPart + " " + currencyDecimal + " " + fractionPart + " " + currencyFraction);
            if (!"i".equals(fullStr.substring(0, 1))) {
                result = fullStr.substring(0, 1).toUpperCase() + fullStr.substring(1);
            } else {
                result = (fullStr.substring(0, 1).replace('i', '\u0130')) + fullStr.substring(1);
            }
        }

        return result;
    }

    public static String convertCurrencyDigsToString(Integer number, String currencyDecimal, String currencyFraction) {
        String numStr;
        numStr = number.toString();
        String fullStr = (decimalPartConvert(numStr).trim()) + " " + currencyDecimal;
        String result = "";

        if (!"i".equals(fullStr.substring(0, 1))) {
            result = fullStr.substring(0, 1).toUpperCase() + fullStr.substring(1);
        } else {
            result = (fullStr.substring(0, 1).replace('i', '\u0130')) + fullStr.substring(1);
        }
        return result;

    }

    private static String decimalPartConvert(String decimalPart) {

        String hundredsStr = "";
        String thousandsStr = "";
        String millionsStr = "";
        String billionsStr = "";
        String trillionStr = "";

        if ((decimalPart.length() % 3 == 2)) {
            decimalPart = "0" + decimalPart;
        }

        if ((decimalPart.length() % 3 == 1)) {
            decimalPart = "0" + "0" + decimalPart;
        }

        String[] partitionedList = decimalPart.split("(?<=\\G...)");

        List<String> list = Arrays.asList(partitionedList);
        Collections.reverse(list);

        HashMap<Integer, String> keyWords = new HashMap<>();
        keyWords.put(1, "min");
        keyWords.put(2, "milyon");
        keyWords.put(3, "milyard");
        keyWords.put(4, "trilyon");

        int index = 0;
        for (String elem : partitionedList) {

            switch (index) {

                case 0 ->
                    hundredsStr = treeDigsConvert(elem);

                case 1 ->
                    thousandsStr = treeDigsConvert(elem) + " " + keyWords.get(index);

                case 2 ->
                    millionsStr = treeDigsConvert(elem) + " " + keyWords.get(index);

                case 3 ->
                    billionsStr = treeDigsConvert(elem) + " " + keyWords.get(index);

                case 4 ->
                    trillionStr = treeDigsConvert(elem) + " " + keyWords.get(index);
            }

            index++;
        }

        return (trillionStr + " " + billionsStr + " " + millionsStr + " " + thousandsStr + " " + hundredsStr);
    }

    private static String treeDigsConvert(String digs) {

        StringBuilder digsToReverse = new StringBuilder(digs);
        String digsReversed = digsToReverse.reverse().toString();

        String onesStr = "";
        String tensStr = "";
        String hundredsStr = "";

        HashMap<Integer, String> ones = new HashMap<>();
        ones.put(0, "");
        ones.put(1, "bir");
        ones.put(2, "iki");
        ones.put(3, "üç");
        ones.put(4, "dörd");
        ones.put(5, "beş");
        ones.put(6, "altı");
        ones.put(7, "yeddi");
        ones.put(8, "səkkiz");
        ones.put(9, "doqquz");

        HashMap<Integer, String> tens = new HashMap<>();
        tens.put(0, "");
        tens.put(1, "on");
        tens.put(2, "iyirmi");
        tens.put(3, "otuz");
        tens.put(4, "qırx");
        tens.put(5, "əlli");
        tens.put(6, "altmış");
        tens.put(7, "yetmiş");
        tens.put(8, "səksən");
        tens.put(9, "doxsan");

        HashMap<Integer, String> hundreds = new HashMap<>();
        hundreds.put(0, "");
        hundreds.put(1, "yüz");

        for (int i = 0; i < digsReversed.length(); i++) {

            int num;
            num = digsReversed.charAt(i) - '0';

            switch (i) {
                case 0 ->
                    onesStr = ones.get(num);

                case 1 ->
                    tensStr = tens.get(num);

                case 2 -> {
                    if (num == 0) {
                        hundredsStr = ones.get(num) + " " + hundreds.get(0);
                    } else if (num > 1) {
                        hundredsStr = ones.get(num) + " " + hundreds.get(1);
                    } else if (num == 1) {
                        hundredsStr = hundreds.get(1);
                    }
                }
            }

        }
        return hundredsStr + " " + tensStr + " " + onesStr;

    }

}