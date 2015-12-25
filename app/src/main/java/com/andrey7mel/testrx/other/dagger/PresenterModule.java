package com.andrey7mel.testrx.other.dagger;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.model.DataRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module

public class PresenterModule {

    @Provides
    @Singleton
    DataRepository provideDataRepository() {
        return new DataRepositoryImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
