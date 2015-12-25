package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.model.DataRepositoryImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements IPresenter {

    protected DataRepository dataRepository = new DataRepositoryImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.unsubscribe();
    }

}
