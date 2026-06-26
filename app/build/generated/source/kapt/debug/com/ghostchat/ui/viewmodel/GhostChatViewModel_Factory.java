package com.ghostchat.ui.viewmodel;

import com.ghostchat.repository.GhostChatRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class GhostChatViewModel_Factory implements Factory<GhostChatViewModel> {
  private final Provider<GhostChatRepository> repositoryProvider;

  public GhostChatViewModel_Factory(Provider<GhostChatRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GhostChatViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static GhostChatViewModel_Factory create(
      Provider<GhostChatRepository> repositoryProvider) {
    return new GhostChatViewModel_Factory(repositoryProvider);
  }

  public static GhostChatViewModel newInstance(GhostChatRepository repository) {
    return new GhostChatViewModel(repository);
  }
}
