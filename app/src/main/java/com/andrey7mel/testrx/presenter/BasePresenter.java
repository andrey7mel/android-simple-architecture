package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.other.App;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements IPresenter {

    @Inject
    protected DataRepository dataRepository;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.unsubscribe();
    }

}
