package com.dnkilic.application38;

public enum CurrencyCode {
    TRY("TÜRK LİRASI"),
    USD("ABD DOLARI"),
    AUD("AVUSTRALYA DOLARI"),
    DKK("DANİMARKA KRONU"),
    EUR("EURO"),
    GBP("İNGİLİZ STERLİNİ"),
    CHF("İSVİÇRE FRANGI"),
    SEK("İSVEÇ KRONU"),
    CAD("KANADA DOLARI"),
    KWD("KUVEYT DİNARI"),
    NOK("NORVEÇ KRONU"),
    SAR("SUUDİ ARABİSTAN RİYALİ"),
    JPY("JAPON YENİ"),
    BGN("BULGAR LEVASI"),
    RON("RUMEN LEYİ"),
    RUB("RUS RUBLESİ"),
    IRR("İRAN RİYALİ"),
    CNY("ÇİN YUANI"),
    PKR("PAKİSTAN RUPİSİ");

    private String mCurrencyName;

    CurrencyCode(String name)
    {
        mCurrencyName = name;
    }

    @Override
    public String toString() {
        return mCurrencyName;
    }
}
