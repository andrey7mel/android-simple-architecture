package com.andrey7mel.testrx.model.dataprovider;

import com.andrey7mel.testrx.model.mappers.UserReposMapper;
import com.andrey7mel.testrx.presenter.filters.UserRepoFilter;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import java.util.List;

import rx.Observable;

public class UserDataProvider extends BaseDataProvider {

    UserReposMapper userReposMapper = new UserReposMapper();

    public Observable<List<RepositoryVO>> getUserRepositories(UserRepoFilter filter) {
        return apiInterface.getRepositories(filter.getName())
                .map(userReposMapper);
    }
}
