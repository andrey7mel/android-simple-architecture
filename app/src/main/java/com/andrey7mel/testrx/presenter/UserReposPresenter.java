package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.presenter.filters.UserRepoFilter;
import com.andrey7mel.testrx.presenter.mappers.UserReposMapper;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.fragments.IRepoListView;

import java.util.List;

import rx.Observer;
import rx.Subscription;

public class UserReposPresenter extends BasePresenter {

    private UserRepoFilter filter;
    private IRepoListView view;

    private UserReposMapper userReposMapper = new UserReposMapper();

    public UserReposPresenter(IRepoListView view) {
        this.view = view;
    }

    public void loadData() {
        Subscription subscription = dataRepository.getRepoList(filter)
                .map(userReposMapper)
                .subscribe(new Observer<List<RepositoryVO>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<RepositoryVO> list) {
                        if (list != null && !list.isEmpty()) {
                            view.showList(list);

                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription, USER_REPOSITORIES_KEY);

    }

    public void setFilter(UserRepoFilter filter) {
        this.filter = filter;
    }

}
