package com.andrey7mel.testrx.other.dagger;

import com.andrey7mel.testrx.model.api.ApiInterface;
import com.andrey7mel.testrx.model.api.ApiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return ApiModule.getApiInterface();
    }
}
