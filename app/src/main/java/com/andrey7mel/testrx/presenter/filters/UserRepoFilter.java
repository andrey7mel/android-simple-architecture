package com.andrey7mel.testrx.presenter.filters;

public class UserRepoFilter implements IFilter {
    private String name;

    public UserRepoFilter(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
