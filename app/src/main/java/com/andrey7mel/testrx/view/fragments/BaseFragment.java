package com.andrey7mel.testrx.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.andrey7mel.testrx.presenter.BasePresenter;

public abstract class BaseFragment extends Fragment {


    protected abstract BasePresenter getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().unsubscribe();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


}

