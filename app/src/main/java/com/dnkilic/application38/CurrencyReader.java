package com.dnkilic.application38;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CurrencyReader {

    private String TAG = "CurrencyReader";

    private ConversionResultListener mListener;


    public CurrencyReader(ConversionResultListener listener)
    {
        mListener = listener;
    }

    public void convertCurrency(CurrencyCode sourceCurrency, CurrencyCode destinationCurrency, Double amount)
    {
        if(sourceCurrency != destinationCurrency)
        {
            if(sourceCurrency != CurrencyCode.USD && sourceCurrency != CurrencyCode.TRY)
            {
                if(destinationCurrency != CurrencyCode.USD && destinationCurrency != CurrencyCode.TRY)
                {
                    mListener.onError("Girilen Kurlar İçin Çeviri Yapılamaz");
                }
                else
                {
                    new CurrencyConverter().execute(new ConversionRequest(sourceCurrency, destinationCurrency, amount));
                }
            }
            else
            {
                new CurrencyConverter().execute(new ConversionRequest(sourceCurrency, destinationCurrency, amount));
            }
        }
        else
        {
            mListener.onError("Aynı Kur İçin Çeviri Yapmayı Denediniz");
        }
    }

    private String getCharacterDataFromElement(Element e) {
        try {
            Node child = e.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        } catch (Exception ex) {
        }
        return "";
    }

    private Double getFloat(String value) {
        if (value != null && !value.equals("")) {
            double x = Double.valueOf(value);

            return x;
        }
        else
        {
            return new Double(0.0000);
        }
    }

    private String getElementValue(Element parent, String label) {
        return getCharacterDataFromElement((Element) parent
                .getElementsByTagName(label).item(0));
    }

    private CurrencyCode convertStringToCurrencyCode(String currency)
    {
        CurrencyCode currencyCode = null;

        switch (currency)
        {
            case "USD":
                currencyCode = CurrencyCode.USD;
                break;
            case "AUD":
                currencyCode = CurrencyCode.AUD;
                break;
            case "DKK":
                currencyCode = CurrencyCode.DKK;
                break;
            case "EUR":
                currencyCode = CurrencyCode.EUR;
                break;
            case "GBP":
                currencyCode = CurrencyCode.GBP;
                break;
            case "CHF":
                currencyCode = CurrencyCode.CHF;
                break;
            case "SEK":
                currencyCode = CurrencyCode.SEK;
                break;
            case "CAD":
                currencyCode = CurrencyCode.CAD;
                break;
            case "KWD":
                currencyCode = CurrencyCode.KWD;
                break;
            case "NOK":
                currencyCode = CurrencyCode.NOK;
                break;
            case "SAR":
                currencyCode = CurrencyCode.SAR;
                break;
            case "JPY":
                currencyCode = CurrencyCode.JPY;
                break;
            case "BGN":
                currencyCode = CurrencyCode.BGN;
                break;
            case "RON":
                currencyCode = CurrencyCode.RON;
                break;
            case "RUB":
                currencyCode = CurrencyCode.RUB;
                break;
            case "IRR":
                currencyCode = CurrencyCode.IRR;
                break;
            case "CNY":
                currencyCode = CurrencyCode.CNY;
                break;
            case "PKR" :
                currencyCode = CurrencyCode.PKR;
                break;
        }

        return currencyCode;
    }



    private class CurrencyConverter extends AsyncTask<ConversionRequest, Integer, Double>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Double doInBackground(ConversionRequest... params) {

            ConversionRequest request = params[0];
            HashMap<CurrencyCode, Currency> currencyMap = new HashMap<>();

            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();

                URL u = new URL("http://www.tcmb.gov.tr/kurlar/today.xml");

                Document doc = builder.parse(u.openStream());
                NodeList date = doc.getElementsByTagName("Tarih_Date");
                NodeList nodes = doc.getElementsByTagName("Currency");

                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);

                    Currency currency = new Currency();

                    currency.setCurrencyCode(convertStringToCurrencyCode(element.getAttribute("CurrencyCode")));
                    currency.setCrossOrder(Integer.parseInt(element.getAttribute("CrossOrder")));
                    currency.setCurrencyName(getElementValue(element, "Isim"));
                    currency.setCurrencyUnit(Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setBanknoteBuying(getFloat(getElementValue(element, "BanknoteBuying")));
                    currency.setBanknoteSelling(getFloat(getElementValue(element, "BanknoteSelling")));
                    currency.setForexBuying(getFloat(getElementValue(element, "ForexBuying")));
                    currency.setForexSelling(getFloat(getElementValue(element, "ForexSelling")));
                    currency.setCrossRateUSD(getFloat(getElementValue(element, "CrossRateUSD")));
                    currency.setCrossRateOther(getFloat(getElementValue(element, "CrossRateOther")));

                    Log.d(TAG, currency.getCurrencyCode().toString());
                    Log.d(TAG, currency.getCrossOrder() + "");
                    Log.d(TAG, currency.getCurrencyName());
                    Log.d(TAG, currency.getCurrencyUnit() + "");
                    Log.d(TAG, currency.getBanknoteBuying() + "");
                    Log.d(TAG, currency.getBanknoteSelling() + "");
                    Log.d(TAG, currency.getForexBuying() + "");
                    Log.d(TAG, currency.getForexSelling() + "");
                    Log.d(TAG, currency.getCrossRateUSD() + "");
                    Log.d(TAG, currency.getCrossRateOther() + "");
                    Log.d(TAG, "*************************************");

                    currencyMap.put(currency.getCurrencyCode(), currency);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            CurrencyCode source = request.getSourceCurrency();
            CurrencyCode destination = request.getDestinationCurrency();

            Double result = 0.0;

            for(Map.Entry<CurrencyCode, Currency> item : currencyMap.entrySet())
            {
                Log.d("denememmem", item.getValue().getCurrencyCode().toString());
                Log.d("denememmem", item.getValue().getCrossOrder() + "");
                Log.d("denememmem", item.getValue().getCurrencyName());
                Log.d("denememmem", item.getValue().getCurrencyUnit() + "");
                Log.d("denememmem", item.getValue().getBanknoteBuying() + "");
                Log.d("denememmem", item.getValue().getBanknoteSelling() + "");
                Log.d("denememmem", item.getValue().getForexBuying() + "");
                Log.d("denememmem", item.getValue().getForexSelling() + "");
                Log.d("denememmem", item.getValue().getCrossRateUSD() + "");
                Log.d("denememmem", item.getValue().getCrossRateOther() + "");
                Log.d("denememmem", "*************************************");
            }


            if(destination == CurrencyCode.TRY)
            {
                for(Map.Entry<CurrencyCode, Currency> item : currencyMap.entrySet())
                {
                    if(item.getKey() == source)
                    {
                        Currency c = item.getValue();

                        Log.d("denememmem", item.getValue().getCurrencyCode().toString());
                        Log.d("denememmem", item.getValue().getCrossOrder() + "");
                        Log.d("denememmem", item.getValue().getCurrencyName());
                        Log.d("denememmem", item.getValue().getCurrencyUnit() + "");
                        Log.d("denememmem", item.getValue().getBanknoteBuying() + "");
                        Log.d("denememmem", item.getValue().getBanknoteSelling() + "");
                        Log.d("denememmem", item.getValue().getForexBuying() + "");
                        Log.d("denememmem", item.getValue().getForexSelling() + "");
                        Log.d("denememmem", item.getValue().getCrossRateUSD() + "");
                        Log.d("denememmem", item.getValue().getCrossRateOther() + "");
                        Log.d("denememmem", "*************************************");

                        Double val1 = c.getBanknoteSelling();
                        Double val2 = request.getAmount();
                        Double val3 = val1 * val2;

                        result =  val3;
                    }
                }
            }

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Double s) {
            mListener.onSuccess(s);
            super.onPostExecute(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}
