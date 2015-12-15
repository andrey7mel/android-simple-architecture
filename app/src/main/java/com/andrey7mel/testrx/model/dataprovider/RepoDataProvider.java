package com.andrey7mel.testrx.model.dataprovider;

import com.andrey7mel.testrx.model.mappers.RepoBranchesMapper;
import com.andrey7mel.testrx.model.mappers.RepoContributorsMapper;
import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;

import java.util.List;

import rx.Observable;


public class RepoDataProvider extends BaseDataProvider {

    RepoBranchesMapper repoBranchesMapper = new RepoBranchesMapper();
    RepoContributorsMapper repoContributorsMapper = new RepoContributorsMapper();

    public Observable<List<BranchVO>> getRepoBranches(RepoFilter filter) {
        return apiInterface.getBranches(filter.getOwner(), filter.getRepo())
                .map(repoBranchesMapper);
    }

    public Observable<List<ContributorVO>> getRepoContributors(RepoFilter filter) {
        return apiInterface.getContributors(filter.getOwner(), filter.getRepo())
                .map(repoContributorsMapper);
    }
}
