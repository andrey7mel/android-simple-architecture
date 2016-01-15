package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.model.DataRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.spy;

@Module

public class PresenterTestModule {

    @Provides
    @Singleton
    DataRepository provideDataRepository() {
        return spy(new DataRepositoryImpl());
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
