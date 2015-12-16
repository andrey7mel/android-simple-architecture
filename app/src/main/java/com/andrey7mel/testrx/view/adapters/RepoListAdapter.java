package com.andrey7mel.testrx.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import java.util.ArrayList;
import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {


    RepoListPresenter presenter;
    private List<RepositoryVO> repoList = new ArrayList<>();

    public RepoListAdapter(RepoListPresenter presenter) {
        this.presenter = presenter;
    }

    public void setRepoList(List<RepositoryVO> Repos) {
        this.repoList = Repos;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final RepositoryVO repo = repoList.get(i);
        viewHolder.name.setText(repo.getRepoName());
        viewHolder.name.setOnClickListener(v -> presenter.clickRepo(repo));
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}