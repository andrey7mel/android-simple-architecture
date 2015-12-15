package com.andrey7mel.testrx.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.presenter.BasePresenter;
import com.andrey7mel.testrx.presenter.RepoInfoPresenter;
import com.andrey7mel.testrx.presenter.filters.RepoFilter;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoInfoFragment extends BaseFragment implements IRepoInfoView {

    public static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";
    public static final String BUNDLE_INFO_KEY = "BUNDLE_INFO_KEY";
    public static final String BUNDLE_BRANCHES_KEY = "BUNDLE_BRANCHES_KEY";
    public static final String BUNDLE_CONTRIBUTORS_KEY = "BUNDLE_CONTRIBUTORS_KEY";


    @Bind(R.id.repo_info)
    TextView info;

    @Bind(R.id.repo_branches)
    TextView branches;

    @Bind(R.id.repo_contributors)
    TextView contributors;

    @Bind(R.id.linear_layout)
    View layout;


    RepoInfoPresenter presenter;


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

    public RepositoryVO getRepoVO() {
        return (RepositoryVO) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_info, container, false);
        ButterKnife.bind(this, view);

        presenter = new RepoInfoPresenter(this);

        info.setText(getRepoVO().getRepoName() + " (" + getRepoVO().getOwnerName() + ")");
        if (savedInstanceState == null) {
            presenter.setFilter(new RepoFilter(getRepoVO().getOwnerName(), getRepoVO().getRepoName()));
            presenter.loadData();
        } else {
            info.setText(savedInstanceState.getString(BUNDLE_INFO_KEY));
            branches.setText(savedInstanceState.getString(BUNDLE_BRANCHES_KEY));
            contributors.setText(savedInstanceState.getString(BUNDLE_CONTRIBUTORS_KEY));

        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_INFO_KEY, info.getText().toString());
        outState.putString(BUNDLE_BRANCHES_KEY, branches.getText().toString());
        outState.putString(BUNDLE_CONTRIBUTORS_KEY, contributors.getText().toString());

    }

//    @Override
//    public void inflateData(RepoBranchesVO vo) {
//        List<String> branchesList = vo.getBranches();
//        List<String> contributorsList = vo.getContributors();
//        StringBuilder builder = new StringBuilder();
//        builder.append("Branches: ");
//        for (String name : branchesList) {
//            builder.append(name + ", ");
//        }
//        branches.setText(builder.substring(0, builder.length() - 2)); //delete last ", "
//        builder = new StringBuilder();
//        builder.append("Contributors: ");
//        for (String name : contributorsList) {
//            builder.append(name + ", ");
//        }
//        contributors.setText(builder.substring(0, builder.length() - 2));
//    }

    @Override
    public void showError(Throwable e) {
        makeToast(e.getMessage());
    }


    private void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void showContributors(String contributorsText) {
        contributors.append(contributorsText);

    }

    @Override
    public void showBranches(String branchesText) {
        branches.append(branchesText);
    }
}
