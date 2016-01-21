package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.model.DataRepositoryImplTest;
import com.andrey7mel.testrx.presenter.BaseForPresenterTest;
import com.andrey7mel.testrx.presenter.RepoInfoPresenterTest;
import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapperTest;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapperTest;
import com.andrey7mel.testrx.presenter.mappers.UserReposMapperTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelTestModule.class, PresenterTestModule.class})
public interface TestComponent extends AppComponent {


    void inject(DataRepositoryImplTest dataRepositoryImplTest);

    void inject(RepoInfoPresenterTest repoInfoPresenterTest);

    void inject(BaseForPresenterTest baseForPresenterTest);

    void inject(RepoBranchesMapperTest repoBranchesMapperTest);

    void inject(RepoContributorsMapperTest repoContributorsMapperTest);

    void inject(UserReposMapperTest userReposMapperTest);
}
