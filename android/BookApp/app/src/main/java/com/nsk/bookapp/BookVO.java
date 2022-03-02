package com.nsk.bookapp;

public class BookVO {
    private String title;
    private String info;
    private String thumnail;
    private String link;

    public BookVO(String title, String info, String thumnail, String link) {
        this.title = title;
        this.info = info;
        this.thumnail = thumnail;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    @Override
    public String toString() {
        return "BookVO{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", thumnail='" + thumnail + '\'' +
                '}';
    }
}
