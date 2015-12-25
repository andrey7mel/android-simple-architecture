package com.andrey7mel.testrx.other;

import android.app.Application;

import com.andrey7mel.testrx.other.dagger.AppComponent;
import com.andrey7mel.testrx.other.dagger.DaggerAppComponent;
import com.andrey7mel.testrx.other.dagger.ModelModule;
import com.andrey7mel.testrx.other.dagger.PresenterModule;
import com.andrey7mel.testrx.other.dagger.ViewModule;

public class App extends Application {
    private static AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .viewModule(new ViewModule())
                .build();
    }


}
