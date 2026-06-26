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
public final class AESGCMEncryptionManager_Factory implements Factory<AESGCMEncryptionManager> {
  @Override
  public AESGCMEncryptionManager get() {
    return newInstance();
  }

  public static AESGCMEncryptionManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AESGCMEncryptionManager newInstance() {
    return new AESGCMEncryptionManager();
  }

  private static final class InstanceHolder {
    private static final AESGCMEncryptionManager_Factory INSTANCE = new AESGCMEncryptionManager_Factory();
  }
}
