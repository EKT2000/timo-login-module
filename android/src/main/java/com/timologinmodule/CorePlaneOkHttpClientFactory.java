package com.timologinmodule;

import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;

import okhttp3.OkHttpClient;

public class CorePlaneOkHttpClientFactory implements OkHttpClientFactory {
  @Override
  public OkHttpClient createNewNetworkModuleClient() {
    return OkHttpClientProvider.createClientBuilder().dns(new CorePlaneHttpDNSSelector(CorePlaneHttpDNSSelector.IPvMode.IPV4_FIRST))
      .build();
  }
}
