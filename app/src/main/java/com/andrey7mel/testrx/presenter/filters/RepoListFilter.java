package com.andrey7mel.testrx.presenter.filters;

public class RepoListFilter implements IFilter {
    private String name;

    public RepoListFilter(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
