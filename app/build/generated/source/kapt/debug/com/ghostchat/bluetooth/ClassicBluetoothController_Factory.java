package com.ghostchat.bluetooth;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ClassicBluetoothController_Factory implements Factory<ClassicBluetoothController> {
  private final Provider<Context> contextProvider;

  public ClassicBluetoothController_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ClassicBluetoothController get() {
    return newInstance(contextProvider.get());
  }

  public static ClassicBluetoothController_Factory create(Provider<Context> contextProvider) {
    return new ClassicBluetoothController_Factory(contextProvider);
  }

  public static ClassicBluetoothController newInstance(Context context) {
    return new ClassicBluetoothController(context);
  }
}
