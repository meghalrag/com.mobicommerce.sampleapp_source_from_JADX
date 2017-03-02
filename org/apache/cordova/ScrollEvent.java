package org.apache.cordova;

import android.view.View;

public class ScrollEvent {
    public int f952l;
    public int nl;
    public int nt;
    public int f953t;
    private View targetView;

    ScrollEvent(int nx, int ny, int x, int y, View view) {
        this.f952l = x;
        y = this.f953t;
        this.nl = nx;
        this.nt = ny;
        this.targetView = view;
    }

    public int dl() {
        return this.nl - this.f952l;
    }

    public int dt() {
        return this.nt - this.f953t;
    }

    public View getTargetView() {
        return this.targetView;
    }
}
