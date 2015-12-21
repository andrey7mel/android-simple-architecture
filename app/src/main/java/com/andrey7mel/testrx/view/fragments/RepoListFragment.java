package com.andrey7mel.testrx.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.presenter.BasePresenter;
import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.MainActivity;
import com.andrey7mel.testrx.view.adapters.RepoListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoListFragment extends BaseFragment implements IRepoListView {

    public static final String BUNDLE_REPO_LIST_KEY = "BUNDLE_REPO_LIST_KEY";


    RepoListPresenter presenter = new RepoListPresenter(this);


    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;


    @Bind(R.id.edit_text)
    EditText editText;

    @Bind(R.id.button_search)
    Button searchButton;

    private RepoListAdapter adapter;
    private List<RepositoryVO> repoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new RepoListAdapter(presenter);
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> loadData());

        if (savedInstanceState != null) {
            repoList = (List<RepositoryVO>) savedInstanceState.getSerializable(BUNDLE_REPO_LIST_KEY);
        }

        //Fix save state on replace fragment
        if (repoList == null) {
            loadData();
        } else {
            showList();
        }

        return view;
    }

    private void loadData() {
        String userName = editText.getText().toString();
        if (!TextUtils.isEmpty(userName)) {
            presenter.loadData(userName);
        }
    }

    private void showList() {
        if (repoList != null)
            adapter.setRepoList(repoList);
    }


    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void showError(Throwable e) {
        makeToast(e.getMessage());
    }

    @Override
    public void setRepoList(List<RepositoryVO> vo) {
        repoList = vo;
        showList();
    }

    @Override
    public void startRepoInfoFragment(RepositoryVO repositoryVO) {
        ((MainActivity) getActivity()).startRepoInfoFragment(repositoryVO);
    }

    @Override
    public void showEmptyList() {
        makeToast("Empty List!");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (repoList != null)
            outState.putSerializable(BUNDLE_REPO_LIST_KEY, new ArrayList<>(repoList));
    }

}
