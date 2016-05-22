package com.dnkilic.application38;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity implements ConversionResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NodeList nodes = doc.getElementsByTagName("CurrencyCode");
    }

    public void convertCurrency(View v)
    {
        CurrencyReader cr = new CurrencyReader(this);
        cr.convertCurrency(CurrencyCode.USD, CurrencyCode.TRY, Double.valueOf("1"));
    }

    @Override
    public void onError(String errorMessage) {
        Log.d("onError" , errorMessage);
    }

    @Override
    public void onSuccess(Double result) {

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(result + "");
    }

    /*switch (currency)
        {
            case "ABD DOLARI":
                currencyCode = CurrencyCode.USD;
                break;
            case "AVUSTRALYA DOLARI":
                currencyCode = CurrencyCode.AUD;
                break;
            case "DANİMARKA KRONU":
                currencyCode = CurrencyCode.DKK;
                break;
            case "EURO":
                currencyCode = CurrencyCode.EUR;
                break;
            case "İNGİLİZ STERLİNİ":
                currencyCode = CurrencyCode.GBP;
                break;
            case "İSVİÇRE FRANGI":
                currencyCode = CurrencyCode.CHF;
                break;
            case "İSVEÇ KRONU":
                currencyCode = CurrencyCode.SEK;
                break;
            case "KANADA DOLARI":
                currencyCode = CurrencyCode.CAD;
                break;
            case "KUVEYT DİNARI":
                currencyCode = CurrencyCode.KWD;
                break;
            case "NORVEÇ KRONU":
                currencyCode = CurrencyCode.NOK;
                break;
            case "SUUDİ ARABİSTAN RİYALİ":
                currencyCode = CurrencyCode.SAR;
                break;
            case "JAPON YENİ":
                currencyCode = CurrencyCode.JPY;
                break;
            case "BULGAR LEVASI":
                currencyCode = CurrencyCode.BGN;
                break;
            case "RUMEN LEYİ":
                currencyCode = CurrencyCode.RON;
                break;
            case "RUS RUBLESİ":
                currencyCode = CurrencyCode.RUB;
                break;
            case "İRAN RİYALİ":
                currencyCode = CurrencyCode.IRR;
                break;
            case "ÇİN YUANI":
                currencyCode = CurrencyCode.CNY;
                break;
            case "PAKİSTAN RUPİSİ" :
                currencyCode = CurrencyCode.PKR;
                break;
            default:
                currencyCode = CurrencyCode.USD;
                break;
        }*/

}
