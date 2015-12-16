package com.andrey7mel.testrx.presenter.filters;

public class RepoInfoFilter {
    private String owner;
    private String repo;


    public RepoInfoFilter(String owner, String repo) {
        this.owner = owner;
        this.repo = repo;
    }

    public String getOwner() {
        return owner;
    }

    public String getRepo() {
        return repo;
    }
}
