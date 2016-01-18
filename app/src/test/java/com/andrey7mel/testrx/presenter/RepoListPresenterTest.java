package com.andrey7mel.testrx.presenter;

import android.os.Bundle;

import com.andrey7mel.testrx.other.TestConst;
import com.andrey7mel.testrx.view.fragments.IRepoListView;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoListPresenterTest extends BaseForPresenterTest {

    private IRepoListView mockView;
    private RepoListPresenter repoListPresenter;


    @Before
    public void setUp() throws Exception {
        super.setUp();

        mockView = mock(IRepoListView.class);
        repoListPresenter = spy(new RepoListPresenter(mockView));

        doAnswer(invocation -> Observable.just(repositoryDTOs))
                .when(dataRepository)
                .getRepoList(TestConst.TEST_OWNER);

        doAnswer(invocation -> TestConst.TEST_OWNER)
                .when(mockView)
                .getInputName();
    }


    @Test
    public void testLoadData() {
        repoListPresenter.onCreate(null);
        repoListPresenter.onStop();

        verify(mockView).setRepoList(repoList);
    }

    @Test
    public void testSubscribe() {
        repoListPresenter.onCreate(null);

        verify(repoListPresenter).addSubscription(any());

        repoListPresenter.onStop();
        assertTrue(repoListPresenter.compositeSubscription.isUnsubscribed());

    }

    @Test
    public void testSaveState() {
        repoListPresenter.onCreate(null);

        Bundle bundle = Bundle.EMPTY;
        repoListPresenter.onSaveInstanceState(bundle);
        repoListPresenter.onStop();

        repoListPresenter.onCreate(bundle);

        verify(mockView, times(2)).setRepoList(repoList);

        verify(dataRepository).getRepoList(TestConst.TEST_OWNER);
    }
}