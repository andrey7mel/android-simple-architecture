package com.andrey7mel.testrx.view.fragments;

import com.andrey7mel.testrx.presenter.vo.Repository;

import java.util.List;

public interface IRepoListView extends IView {

    void setRepoList(List<Repository> vo);

    void startRepoInfoFragment(Repository vo);

    void showEmptyList();

    String getInputName();
}
