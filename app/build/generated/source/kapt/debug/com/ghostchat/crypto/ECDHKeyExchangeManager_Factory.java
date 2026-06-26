package com.ghostchat.crypto;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ECDHKeyExchangeManager_Factory implements Factory<ECDHKeyExchangeManager> {
  private final Provider<SecureMemoryCleaner> secureMemoryCleanerProvider;

  public ECDHKeyExchangeManager_Factory(Provider<SecureMemoryCleaner> secureMemoryCleanerProvider) {
    this.secureMemoryCleanerProvider = secureMemoryCleanerProvider;
  }

  @Override
  public ECDHKeyExchangeManager get() {
    return newInstance(secureMemoryCleanerProvider.get());
  }

  public static ECDHKeyExchangeManager_Factory create(
      Provider<SecureMemoryCleaner> secureMemoryCleanerProvider) {
    return new ECDHKeyExchangeManager_Factory(secureMemoryCleanerProvider);
  }

  public static ECDHKeyExchangeManager newInstance(SecureMemoryCleaner secureMemoryCleaner) {
    return new ECDHKeyExchangeManager(secureMemoryCleaner);
  }
}
