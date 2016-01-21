package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.testrx.presenter.mappers.UserReposMapper;
import com.andrey7mel.testrx.presenter.vo.Branch;
import com.andrey7mel.testrx.presenter.vo.Contributor;
import com.andrey7mel.testrx.presenter.vo.Repository;

import org.junit.Before;
import org.junit.Ignore;

import java.util.List;

import javax.inject.Inject;

@Ignore
public class BaseForPresenterTest extends BaseTest {


    @Inject
    protected RepoBranchesMapper branchesMapper;

    @Inject
    protected RepoContributorsMapper contributorsMapper;

    @Inject
    protected UserReposMapper userReposMapper;
    protected List<Repository> repoList;
    protected List<Contributor> contributorList;
    protected List<Branch> branchList;
    @Inject
    DataRepository dataRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        repoList = userReposMapper.call(repositoryDTOs);
        contributorList = contributorsMapper.call(contributorDTOs);
        branchList = branchesMapper.call(branchDTOs);
    }


}