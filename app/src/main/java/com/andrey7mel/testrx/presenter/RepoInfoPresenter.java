package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.view.fragments.IRepoInfoView;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class RepoInfoPresenter extends BasePresenter {

    private RepoFilter filter;
    private IRepoInfoView view;

    public RepoInfoPresenter(IRepoInfoView view) {
        this.view = view;
    }

    public void loadData() {
        Subscription subscriptionBranches = dataRepository.getRepoBranches(filter)
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
                        StringBuilder stringBuilder = new StringBuilder();
                        Observable.from(list).subscribe(s -> stringBuilder.append(s.getName()).append(", "));
                        if (stringBuilder.length() > 1) {
                            stringBuilder.setLength(stringBuilder.length() - 2);
                        }
                        view.showBranches(stringBuilder.toString());
                    }
                });
        addSubscription(subscriptionBranches, BRANCHES_KEY);

        Subscription subscriptionContributors = dataRepository.getRepoContributors(filter)
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
                        StringBuilder stringBuilder = new StringBuilder();
                        Observable.from(list)
                                .subscribe(s -> stringBuilder.append(s.getName()).append(", "));

                        if (stringBuilder.length() > 1) {
                            stringBuilder.setLength(stringBuilder.length() - 2);
                        }
                        view.showContributors(stringBuilder.toString());
                    }
                });
        addSubscription(subscriptionContributors, CONTRIBUTORS_KEY);
    }

    public void setFilter(RepoFilter filter) {
        this.filter = filter;
    }

}
