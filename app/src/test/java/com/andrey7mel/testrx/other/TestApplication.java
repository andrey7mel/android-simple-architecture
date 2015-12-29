package com.andrey7mel.testrx.other;

import com.andrey7mel.testrx.other.di.AppComponent;
import com.andrey7mel.testrx.other.di.DaggerTestComponent;
import com.andrey7mel.testrx.other.di.ModelModule;
import com.andrey7mel.testrx.other.di.PresenterModule;

public class TestApplication extends App {

    @Override
    protected AppComponent buildComponent() {
        return DaggerTestComponent.builder()
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .build();
    }
}
