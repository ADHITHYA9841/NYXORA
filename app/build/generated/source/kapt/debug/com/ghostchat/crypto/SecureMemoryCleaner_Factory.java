package com.ghostchat.crypto;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SecureMemoryCleaner_Factory implements Factory<SecureMemoryCleaner> {
  @Override
  public SecureMemoryCleaner get() {
    return newInstance();
  }

  public static SecureMemoryCleaner_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SecureMemoryCleaner newInstance() {
    return new SecureMemoryCleaner();
  }

  private static final class InstanceHolder {
    private static final SecureMemoryCleaner_Factory INSTANCE = new SecureMemoryCleaner_Factory();
  }
}
