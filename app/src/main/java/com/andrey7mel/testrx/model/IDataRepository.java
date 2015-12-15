package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.filters.UserRepoFilter;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import java.util.List;

import rx.Observable;

public interface IDataRepository {

    Observable<List<RepositoryVO>> getRepoList(UserRepoFilter filter);

    Observable<List<BranchVO>> getRepoBranches(RepoFilter filter);

    Observable<List<ContributorVO>> getRepoContributors(RepoFilter filter);

}
