package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.model.api.ApiInterface;
import com.andrey7mel.testrx.model.api.ApiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;

@Module
public class ModelTestModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return spy(ApiModule.getApiInterface());
    }
}
