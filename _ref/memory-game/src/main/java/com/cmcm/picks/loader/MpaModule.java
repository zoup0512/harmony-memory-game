package com.cmcm.picks.loader;

import java.util.ArrayList;
import java.util.List;

public class MpaModule {
    private List<Mpa> mpa = new ArrayList();

    public void setMpa(List<Mpa> mpa) {
        this.mpa = mpa;
    }

    public List<Mpa> getMpa() {
        return this.mpa;
    }
}
