package com.andrey7mel.testrx.presenter;

import android.support.v4.util.ArrayMap;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.model.IDataRepository;

import rx.Subscription;

public abstract class BasePresenter implements IPresenter {
    public static int USER_REPOSITORIES_KEY = 1;
    public static int BRANCHES_KEY = 2;
    public static int CONTRIBUTORS_KEY = 3;
    private final ArrayMap<Integer, Subscription> subscriptions = new ArrayMap<>();
    protected IDataRepository dataRepository = new DataRepository();

    protected void addSubscription(Subscription subscription, int id) {
        final Subscription oldSubscription = subscriptions.put(id, subscription);
        if (oldSubscription != null && !oldSubscription.isUnsubscribed()) {
            oldSubscription.unsubscribe();
        }
    }

    @Override
    public void unsubscribe() {
        for (Subscription s : subscriptions.values()) {
            if (s != null && !s.isUnsubscribed()) {
                s.unsubscribe();
            }
        }
    }

}
