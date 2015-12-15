package com.andrey7mel.testrx.presenter.filters;

public class RepoFilter implements IFilter {
    private String owner;
    private String repo;


    public RepoFilter(String owner, String repo) {
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
