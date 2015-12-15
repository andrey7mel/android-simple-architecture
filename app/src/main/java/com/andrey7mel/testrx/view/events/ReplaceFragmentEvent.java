package com.andrey7mel.testrx.view.events;

import android.support.v4.app.Fragment;

public class ReplaceFragmentEvent {
    private Fragment fragment;

    public ReplaceFragmentEvent(Fragment fragment) {

        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
