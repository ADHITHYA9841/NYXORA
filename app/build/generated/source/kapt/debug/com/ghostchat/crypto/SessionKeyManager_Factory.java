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
public final class SessionKeyManager_Factory implements Factory<SessionKeyManager> {
  private final Provider<SecureMemoryCleaner> secureMemoryCleanerProvider;

  public SessionKeyManager_Factory(Provider<SecureMemoryCleaner> secureMemoryCleanerProvider) {
    this.secureMemoryCleanerProvider = secureMemoryCleanerProvider;
  }

  @Override
  public SessionKeyManager get() {
    return newInstance(secureMemoryCleanerProvider.get());
  }

  public static SessionKeyManager_Factory create(
      Provider<SecureMemoryCleaner> secureMemoryCleanerProvider) {
    return new SessionKeyManager_Factory(secureMemoryCleanerProvider);
  }

  public static SessionKeyManager newInstance(SecureMemoryCleaner secureMemoryCleaner) {
    return new SessionKeyManager(secureMemoryCleaner);
  }
}
