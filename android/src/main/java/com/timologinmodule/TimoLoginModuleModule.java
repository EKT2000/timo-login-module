package com.timologinmodule;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@ReactModule(name = TimoLoginModuleModule.NAME)
public class TimoLoginModuleModule extends ReactContextBaseJavaModule {
    public static final String NAME = "TimoLoginModule";

    public TimoLoginModuleModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void multiply(double a, double b, Promise promise) {
        promise.resolve(a * b);
    }

    @ReactMethod
    public void checkCompanyExpiry(String company, String endpoint, Promise promise) {
      OkHttpClientProvider.setOkHttpClientFactory(new CorePlaneOkHttpClientFactory());
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
        .url(endpoint)
        .build();
      Call call = client.newCall(request);
      try {
        Response response = call.execute();
        promise.resolve(response.body().string());
      } catch (IOException e) {
        e.printStackTrace();
        promise.reject("Custom Error", e);
      }
    }

}
