package com.brianhoffman.android.jokaramaredeux;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by brianhoffman on 12/3/17.
 */

public class JokeListFragment extends Fragment {

    private RecyclerView mJokeRecyclerView;
    private JokeAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_list, container, false);
        mJokeRecyclerView = (RecyclerView) view.findViewById(R.id.joke_recycler_view);
        mJokeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        updateNumJokes();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_joke_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_jokes:
                resetJokesCompleted();
                updateUI();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetJokesCompleted() {
        JokeRepo jokeRepo = JokeRepo.get(getActivity());
        List<Joke> mJokes = jokeRepo.getJokes();
        for (Joke joke : mJokes) {
            joke.setCompleted(false);
        }
    }

    private void updateNumJokes() {
        JokeRepo jokeRepo = JokeRepo.get(getActivity());
        int jokeCount = jokeRepo.getJokes().size();
        int jokesCompleted = jokeRepo.getCompleted();
        String numJokesSubtitle = String.format(getString(R.string.num_jokes_subtitle), jokeCount, jokesCompleted);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(numJokesSubtitle);
    }

    private void updateUI() {
        JokeRepo jokeRepo = JokeRepo.get(getActivity());
        List<Joke> jokes = jokeRepo.getJokes();
        if (mAdapter == null) {
            mAdapter = new JokeAdapter(jokes);
            mJokeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        updateNumJokes();
    }

    /*** JokeHolder class ***/
    private class JokeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Joke mJoke;
        private TextView mNameTextView;

        public JokeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_joke, parent, false));
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.joke_name);
        }

        public void bind(Joke joke) {
            mJoke = joke;
            mNameTextView.setText(mJoke.getName());
        }

        @Override
        public void onClick(View view) {
            Intent intent = JokeActivity.newIntent(getActivity(), mJoke.getId());
            startActivity(intent);
        }
    }

    /*** JokeAdapter class ***/
    private class JokeAdapter extends RecyclerView.Adapter<JokeHolder> {
        private List<Joke> mJokes;

        public JokeAdapter(List<Joke> jokes) {
            mJokes = jokes;
        }

        @Override
        public JokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new JokeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(JokeHolder holder, int position) {
            Joke joke = mJokes.get(position);
            holder.bind(joke);
            if (joke.isCompleted()) {
                holder.itemView.setBackgroundColor(0xFF00FF00);
            }
        }

        @Override
        public int getItemCount() {
            return mJokes.size();
        }
    }
}
