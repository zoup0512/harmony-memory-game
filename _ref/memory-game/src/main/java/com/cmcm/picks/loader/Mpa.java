package com.cmcm.picks.loader;

public class Mpa {
    private String ac;
    private String mpa_id;
    private String pic_url;
    private String pkg_url;
    private String title;

    public Mpa(String mpa_id, String ac, String title, String pic_url, String pkg_url) {
        this.mpa_id = mpa_id;
        this.ac = ac;
        this.title = title;
        this.pic_url = pic_url;
        this.pkg_url = pkg_url;
    }

    public void setMpa_id(String mpa_id) {
        this.mpa_id = mpa_id;
    }

    public String getMpa_id() {
        return this.mpa_id;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getAc() {
        return this.ac;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public void setPkg_url(String pkg_url) {
        this.pkg_url = pkg_url;
    }

    public String getPkg_url() {
        return this.pkg_url;
    }
}
