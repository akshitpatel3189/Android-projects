package com.example.mho;

public class Uploadpdf {

    public String fname;
    public String url;

    public Uploadpdf() {
    }

    public Uploadpdf(String fname, String url) {
        this.fname = fname;
        this.url = url;
    }

    public String getFname() {
        return fname;
    }

    public String getUrl() {
        return url;
    }
}
