package com.ghostchat.repository;

import com.ghostchat.bluetooth.ClassicBluetoothController;
import com.ghostchat.crypto.AESGCMEncryptionManager;
import com.ghostchat.crypto.ECDHKeyExchangeManager;
import com.ghostchat.crypto.SecureMemoryCleaner;
import com.ghostchat.crypto.SessionKeyManager;
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
public final class GhostChatRepository_Factory implements Factory<GhostChatRepository> {
  private final Provider<ClassicBluetoothController> bluetoothControllerProvider;

  private final Provider<ECDHKeyExchangeManager> keyExchangeManagerProvider;

  private final Provider<AESGCMEncryptionManager> encryptionManagerProvider;

  private final Provider<SessionKeyManager> sessionKeyManagerProvider;

  private final Provider<SecureMemoryCleaner> secureMemoryCleanerProvider;

  public GhostChatRepository_Factory(
      Provider<ClassicBluetoothController> bluetoothControllerProvider,
      Provider<ECDHKeyExchangeManager> keyExchangeManagerProvider,
      Provider<AESGCMEncryptionManager> encryptionManagerProvider,
      Provider<SessionKeyManager> sessionKeyManagerProvider,
      Provider<SecureMemoryCleaner> secureMemoryCleanerProvider) {
    this.bluetoothControllerProvider = bluetoothControllerProvider;
    this.keyExchangeManagerProvider = keyExchangeManagerProvider;
    this.encryptionManagerProvider = encryptionManagerProvider;
    this.sessionKeyManagerProvider = sessionKeyManagerProvider;
    this.secureMemoryCleanerProvider = secureMemoryCleanerProvider;
  }

  @Override
  public GhostChatRepository get() {
    return newInstance(bluetoothControllerProvider.get(), keyExchangeManagerProvider.get(), encryptionManagerProvider.get(), sessionKeyManagerProvider.get(), secureMemoryCleanerProvider.get());
  }

  public static GhostChatRepository_Factory create(
      Provider<ClassicBluetoothController> bluetoothControllerProvider,
      Provider<ECDHKeyExchangeManager> keyExchangeManagerProvider,
      Provider<AESGCMEncryptionManager> encryptionManagerProvider,
      Provider<SessionKeyManager> sessionKeyManagerProvider,
      Provider<SecureMemoryCleaner> secureMemoryCleanerProvider) {
    return new GhostChatRepository_Factory(bluetoothControllerProvider, keyExchangeManagerProvider, encryptionManagerProvider, sessionKeyManagerProvider, secureMemoryCleanerProvider);
  }

  public static GhostChatRepository newInstance(ClassicBluetoothController bluetoothController,
      ECDHKeyExchangeManager keyExchangeManager, AESGCMEncryptionManager encryptionManager,
      SessionKeyManager sessionKeyManager, SecureMemoryCleaner secureMemoryCleaner) {
    return new GhostChatRepository(bluetoothController, keyExchangeManager, encryptionManager, sessionKeyManager, secureMemoryCleaner);
  }
}
