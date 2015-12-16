package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.presenter.filters.RepoListFilter;
import com.andrey7mel.testrx.presenter.mappers.UserReposMapper;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.fragments.IRepoListView;

import java.util.List;

import rx.Observer;
import rx.Subscription;

public class RepoListPresenter extends BasePresenter {

    private IRepoListView view;

    private UserReposMapper userReposMapper = new UserReposMapper();

    public RepoListPresenter(IRepoListView view) {
        this.view = view;
    }

    public void loadData(RepoListFilter filter) {
        Subscription subscription = dataRepository.getRepoList(filter.getName())
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
                            view.setRepoList(list);

                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription, USER_REPOSITORIES_KEY);

    }

    public void clickRepo(RepositoryVO repositoryVO) {
        view.startRepoInfoFragment(repositoryVO);
    }

}
