package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.view.fragments.IRepoInfoView;

import java.util.List;

import rx.Observer;
import rx.Subscription;

public class RepoInfoPresenter extends BasePresenter {

    private RepoFilter filter;
    private IRepoInfoView view;

    private RepoBranchesMapper branchesMapper = new RepoBranchesMapper();
    private RepoContributorsMapper contributorsMapper = new RepoContributorsMapper();

    public RepoInfoPresenter(IRepoInfoView view) {
        this.view = view;
    }

    public void loadData() {
        Subscription subscriptionBranches = dataRepository.getRepoBranches(filter)
                .map(branchesMapper)
                .subscribe(new Observer<List<BranchVO>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<BranchVO> list) {
                        view.showBranches(list);
                    }
                });
        addSubscription(subscriptionBranches, BRANCHES_KEY);

        Subscription subscriptionContributors = dataRepository.getRepoContributors(filter)
                .map(contributorsMapper)
                .subscribe(new Observer<List<ContributorVO>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<ContributorVO> list) {
                        view.showContributors(list);
                    }
                });
        addSubscription(subscriptionContributors, CONTRIBUTORS_KEY);
    }

    public void setFilter(RepoFilter filter) {
        this.filter = filter;
    }

}
