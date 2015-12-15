package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.dataprovider.RepoDataProvider;
import com.andrey7mel.testrx.model.dataprovider.UserDataProvider;
import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.filters.UserRepoFilter;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataRepository implements IDataRepository {

    private final Observable.Transformer schedulersTransformer;

    RepoDataProvider repoDataProvider = new RepoDataProvider();
    UserDataProvider userDataProvider = new UserDataProvider();


    public DataRepository() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()) // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
        ;
    }

    @Override
    public Observable<List<RepositoryVO>> getRepoList(UserRepoFilter filter) {
        return userDataProvider.getUserRepositories(filter)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<BranchVO>> getRepoBranches(RepoFilter filter) {
        return repoDataProvider.getRepoBranches(filter)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<ContributorVO>> getRepoContributors(RepoFilter filter) {
        return repoDataProvider.getRepoContributors(filter)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
