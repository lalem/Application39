package com.dnkilic.application38;

public interface ConversionResultListener {
    void onError(String errorMessage);
    void onSuccess(Double result);
}
