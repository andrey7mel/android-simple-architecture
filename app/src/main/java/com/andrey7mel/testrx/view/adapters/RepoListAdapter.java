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
    private List<RepositoryVO> Repos = new ArrayList<>();

    public RepoListAdapter(RepoListPresenter presenter) {
        this.presenter = presenter;
    }

    public void setRepos(List<RepositoryVO> Repos) {
        this.Repos = Repos;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final RepositoryVO repo = Repos.get(i);
        viewHolder.name.setText(repo.getRepoName());
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickRepo(repo);
//                EventBus.getDefault().post(new ReplaceFragmentEvent(RepoInfoFragment.newInstance(repo)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return Repos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}