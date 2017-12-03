package com.brianhoffman.android.jokaramaredeux;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import java.util.UUID;

public class JokeActivity extends SingleFragmentActivity {

    private static final String EXTRA_JOKE_ID = "com.brianhoffman.android.jokaramredeux.joke_id";

    public static Intent newIntent(Context packageContext, UUID jokeId) {
        Intent intent = new Intent(packageContext, JokeActivity.class);
        intent.putExtra(EXTRA_JOKE_ID, jokeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID jokeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_JOKE_ID);
        return JokeFragment.newInstance(jokeId);
    }
}
