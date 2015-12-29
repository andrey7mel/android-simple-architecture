package com.andrey7mel.testrx.other.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface TestComponent extends AppComponent {


}
