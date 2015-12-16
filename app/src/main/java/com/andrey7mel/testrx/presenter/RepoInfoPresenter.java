package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.presenter.filters.RepoInfoFilter;
import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.view.fragments.IRepoInfoView;

import java.util.List;

import rx.Observer;
import rx.Subscription;

public class RepoInfoPresenter extends BasePresenter {

    private IRepoInfoView view;

    private RepoBranchesMapper branchesMapper = new RepoBranchesMapper();
    private RepoContributorsMapper contributorsMapper = new RepoContributorsMapper();

    public RepoInfoPresenter(IRepoInfoView view) {
        this.view = view;
    }

    public void loadData(RepoInfoFilter filter) {
        Subscription subscriptionBranches = dataRepository.getRepoBranches(filter.getOwner(), filter.getRepo())
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
                        view.setBranches(list);
                    }
                });
        addSubscription(subscriptionBranches, BRANCHES_KEY);

        Subscription subscriptionContributors = dataRepository.getRepoContributors(filter.getOwner(), filter.getRepo())
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
                        view.setContributors(list);
                    }
                });
        addSubscription(subscriptionContributors, CONTRIBUTORS_KEY);
    }


}
