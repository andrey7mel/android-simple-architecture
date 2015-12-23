package com.andrey7mel.testrx.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.presenter.BasePresenter;
import com.andrey7mel.testrx.presenter.RepoInfoPresenter;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.adapters.BranchesAdapter;
import com.andrey7mel.testrx.view.adapters.ContributorsAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoInfoFragment extends BaseFragment implements IRepoInfoView {

    public static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";
    @Bind(R.id.repo_info)
    TextView info;
    @Bind(R.id.recycler_view_branches)
    RecyclerView branchesRecyclerView;
    @Bind(R.id.recycler_view_contributors)
    RecyclerView contributorsRecyclerView;
    @Bind(R.id.linear_layout)
    View layout;
    private RepoInfoPresenter presenter;

    public static RepoInfoFragment newInstance(RepositoryVO repositoryVO) {
        RepoInfoFragment myFragment = new RepoInfoFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, repositoryVO);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    private RepositoryVO getRepositoryVO() {
        return (RepositoryVO) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_info, container, false);
        ButterKnife.bind(this, view);

        String infoText = getRepositoryVO().getRepoName() + " (" + getRepositoryVO().getOwnerName() + ")";
        info.setText(infoText);

        branchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contributorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter = new RepoInfoPresenter(this, getRepositoryVO());
        presenter.onCreate(savedInstanceState);

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }


    @Override
    public void showError(Throwable e) {
        makeToast(e.getMessage());
    }


    private void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showContributors(List<ContributorVO> contributors) {
//        List<String> names = Observable.from(contributors)
//                .map(ContributorVO::getName)
//                .toList()
//                .toBlocking()
//                .first();
        branchesRecyclerView.setAdapter(new ContributorsAdapter(contributors));
    }

    @Override
    public void showBranches(List<BranchVO> branches) {
//        List<String> names = Observable.from(branches)
//                .map(BranchVO::getName)
//                .toList()
//                .toBlocking()
//                .first();
        contributorsRecyclerView.setAdapter(new BranchesAdapter(branches));

    }

}
