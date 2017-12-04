package com.brianhoffman.android.jokaramaredeux;

import android.support.v4.app.Fragment;

/**
 * Created by brianhoffman on 12/3/17.
 */

public class JokeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new JokeListFragment();
    }
}
