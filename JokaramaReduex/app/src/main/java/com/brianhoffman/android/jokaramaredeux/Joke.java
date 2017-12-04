package com.brianhoffman.android.jokaramaredeux;

import java.util.UUID;

/**
 * Created by brianhoffman on 12/2/17.
 */

public class Joke {
    private UUID mId;
    private String mName;
    private String mLine1;
    private String mLine2;
    private String mLine3;
    private String mLine4;
    private String mLine5;
    private boolean mCompleted;

    public Joke() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLine1() {
        return mLine1;
    }

    public void setLine1(String line1) {
        mLine1 = line1;
    }

    public String getLine2() {
        return mLine2;
    }

    public void setLine2(String line2) {
        mLine2 = line2;
    }

    public String getLine3() {
        return mLine3;
    }

    public void setLine3(String line3) {
        mLine3 = line3;
    }

    public String getLine4() {
        return mLine4;
    }

    public void setLine4(String line4) {
        mLine4 = line4;
    }

    public String getLine5() {
        return mLine5;
    }

    public void setLine5(String line5) {
        mLine5 = line5;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }
}
