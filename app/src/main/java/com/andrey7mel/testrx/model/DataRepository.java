package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.api.ApiInterface;
import com.andrey7mel.testrx.model.api.ApiModule;
import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.filters.RepoListFilter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataRepository implements IDataRepository {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiModule.getApiInterface();

    public DataRepository() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()) // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
        ;
    }

    @Override
    public Observable<List<RepositoryDTO>> getRepoList(RepoListFilter filter) {
        return apiInterface
                .getRepositories(filter.getName())
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<BranchDTO>> getRepoBranches(RepoFilter filter) {
        return apiInterface
                .getBranches(filter.getOwner(), filter.getRepo())
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<ContributorDTO>> getRepoContributors(RepoFilter filter) {
        return apiInterface
                .getContributors(filter.getOwner(), filter.getRepo())
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
