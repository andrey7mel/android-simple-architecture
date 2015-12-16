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
import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.adapters.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class RepoInfoFragment extends BaseFragment implements IRepoInfoView {

    public static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";
    public static final String BUNDLE_INFO_KEY = "BUNDLE_INFO_KEY";
    public static final String BUNDLE_BRANCHES_KEY = "BUNDLE_BRANCHES_KEY";
    public static final String BUNDLE_CONTRIBUTORS_KEY = "BUNDLE_CONTRIBUTORS_KEY";


    @Bind(R.id.repo_info)
    TextView info;

    @Bind(R.id.recycler_view_branches)
    RecyclerView branchesRecyclerView;

    @Bind(R.id.recycler_view_contributors)
    RecyclerView contributorsRecyclerView;

    @Bind(R.id.linear_layout)
    View layout;

    private List<ContributorVO> contributorList;
    private List<BranchVO> branchList;

    private RepoInfoPresenter presenter;


    public static RepoInfoFragment newInstance(RepositoryVO vo) {
        RepoInfoFragment myFragment = new RepoInfoFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, vo);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    private RepositoryVO getRepoVO() {
        return (RepositoryVO) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_info, container, false);
        ButterKnife.bind(this, view);


        String infoText = getRepoVO().getRepoName() + " (" + getRepoVO().getOwnerName() + ")";
        info.setText(infoText);

        branchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contributorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        if (savedInstanceState != null) {
            contributorList = (List<ContributorVO>) savedInstanceState.getSerializable(BUNDLE_CONTRIBUTORS_KEY);
            branchList = (List<BranchVO>) savedInstanceState.getSerializable(BUNDLE_BRANCHES_KEY);
        }

        if (contributorList == null || branchList == null) {
            initPresenter();
        } else {
            showContributors();
            showBranches();
        }

        return view;
    }


    private void initPresenter() {
        presenter = new RepoInfoPresenter(this);
        presenter.setFilter(new RepoFilter(getRepoVO().getOwnerName(), getRepoVO().getRepoName()));
        presenter.loadData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_INFO_KEY, info.getText().toString());
        if (contributorList != null)
            outState.putSerializable(BUNDLE_CONTRIBUTORS_KEY, new ArrayList<>(contributorList));
        if (branchList != null)
            outState.putSerializable(BUNDLE_BRANCHES_KEY, new ArrayList<>(branchList));


    }


    @Override
    public void showError(Throwable e) {
        makeToast(e.getMessage());
    }


    private void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setContributors(List<ContributorVO> contributors) {
        contributorList = contributors;
        showContributors();
    }

    @Override
    public void setBranches(List<BranchVO> branches) {
        branchList = branches;
        showBranches();

    }

    private void showContributors() {
        List<String> names = Observable.from(contributorList)
                .map(ContributorVO::getName)
                .toList()
                .toBlocking()
                .first();
        branchesRecyclerView.setAdapter(new SimpleAdapter(names));
    }

    private void showBranches() {
        List<String> names = Observable.from(branchList)
                .map(BranchVO::getName)
                .toList()
                .toBlocking()
                .first();
        contributorsRecyclerView.setAdapter(new SimpleAdapter(names));
    }


}
