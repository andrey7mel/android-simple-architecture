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
import com.andrey7mel.testrx.presenter.UserReposPresenter;
import com.andrey7mel.testrx.presenter.filters.UserRepoFilter;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.adapters.RepoListAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoListFragment extends BaseFragment implements IRepoListView {


    UserReposPresenter presenter = new UserReposPresenter(this);


    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;


    @Bind(R.id.edit_text)
    EditText editText;

    @Bind(R.id.button_search)
    Button searchButton;

    private RepoListAdapter adapter;
    private List<RepositoryVO> lastList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new RepoListAdapter(presenter);
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    presenter.setFilter(new UserRepoFilter(text));
                    presenter.loadData();
                }

            }
        });

        //Fix save state on replace fragment
        if (lastList == null) {
            presenter.setFilter(new UserRepoFilter(editText.getText().toString()));
            presenter.loadData();
        } else {
            setData();
        }


        return view;
    }


    private void setData() {
        if (lastList != null) {
            adapter.setRepos(lastList);
        }
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
    public void showList(List<RepositoryVO> vo) {
        lastList = vo;
        setData();
    }

    @Override
    public void showEmptyList() {
        makeToast("Empty List!");
    }


}
