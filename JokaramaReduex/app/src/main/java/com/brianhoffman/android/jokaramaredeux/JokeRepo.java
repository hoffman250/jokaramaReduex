package com.brianhoffman.android.jokaramaredeux;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by brianhoffman on 12/2/17.
 */

public class JokeRepo {

    private static JokeRepo sJokeRepo;
    private List<Joke> mJokes;

    public static JokeRepo get(Context context) {
        if (sJokeRepo == null) {
            sJokeRepo = new JokeRepo(context);
        }
        return sJokeRepo;
    }

    private JokeRepo(Context context) {
        mJokes = new ArrayList<>();
        for (int i=0; i<20; i++) {
            Joke joke = new Joke();
            joke.setName("Joke " + i);
            joke.setLine1("Android ");
            joke.setLine2("is ");
            joke.setLine3("a major ");
            joke.setLine4("pain in the ");
            joke.setLine5("ass.");
            mJokes.add(joke);
        }
    }

    public List<Joke> getJokes() {
        return mJokes;
    }

    public Joke getJoke(UUID id) {
        for (Joke joke: mJokes) {
            if(joke.getId().equals(id)) {
                return joke;
            }
        }
        return null;
    }

    public int getCompleted() {
        int completed = 0;
        for (Joke joke : mJokes) {
            if (joke.isCompleted()) {
                completed++;
            }
        }
        return completed;
    }

    public void resetCompleted() {
        for (Joke joke : mJokes) {
            joke.setCompleted(false);
        }
    }
}
