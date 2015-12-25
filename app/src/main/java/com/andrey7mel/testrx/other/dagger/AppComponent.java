package com.andrey7mel.testrx.other.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {

}
