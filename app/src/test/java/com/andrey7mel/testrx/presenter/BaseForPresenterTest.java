package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.testrx.presenter.mappers.UserReposMapper;
import com.andrey7mel.testrx.presenter.vo.Branch;
import com.andrey7mel.testrx.presenter.vo.Contributor;
import com.andrey7mel.testrx.presenter.vo.Repository;

import org.junit.Before;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

@Ignore
public class BaseForPresenterTest extends BaseTest {

    protected List<Repository> repoList;
    protected List<Contributor> contributorList;
    protected List<Branch> branchList;

    protected List<RepositoryDTO> repositoryDTOs;
    protected List<ContributorDTO> contributorDTOs;
    protected List<BranchDTO> branchDTOs;
    @Inject
    protected RepoBranchesMapper branchesMapper;
    @Inject
    protected RepoContributorsMapper contributorsMapper;
    @Inject
    protected UserReposMapper userReposMapper;
    @Inject
    DataRepository dataRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        RepositoryDTO[] repositoryDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class);
        ContributorDTO[] contributorDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/contributors"), ContributorDTO[].class);
        BranchDTO[] branchDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/branches"), BranchDTO[].class);

        repositoryDTOs = Arrays.asList(repositoryDTOArray);
        contributorDTOs = Arrays.asList(contributorDTOArray);
        branchDTOs = Arrays.asList(branchDTOArray);

        repoList = userReposMapper.call(repositoryDTOs);
        contributorList = contributorsMapper.call(contributorDTOs);
        branchList = branchesMapper.call(branchDTOs);
    }


}