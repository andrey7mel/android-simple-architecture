package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.filters.RepoListFilter;

import java.util.List;

import rx.Observable;

public interface IDataRepository {

    Observable<List<RepositoryDTO>> getRepoList(RepoListFilter filter);

    Observable<List<BranchDTO>> getRepoBranches(RepoFilter filter);

    Observable<List<ContributorDTO>> getRepoContributors(RepoFilter filter);

}
