package com.ghostchat;

import com.ghostchat.security.ScreenSecurityManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<ScreenSecurityManager> screenSecurityManagerProvider;

  public MainActivity_MembersInjector(
      Provider<ScreenSecurityManager> screenSecurityManagerProvider) {
    this.screenSecurityManagerProvider = screenSecurityManagerProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<ScreenSecurityManager> screenSecurityManagerProvider) {
    return new MainActivity_MembersInjector(screenSecurityManagerProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectScreenSecurityManager(instance, screenSecurityManagerProvider.get());
  }

  @InjectedFieldSignature("com.ghostchat.MainActivity.screenSecurityManager")
  public static void injectScreenSecurityManager(MainActivity instance,
      ScreenSecurityManager screenSecurityManager) {
    instance.screenSecurityManager = screenSecurityManager;
  }
}
