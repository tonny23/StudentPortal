package com.example.studentportal;

/**
 * Class to save a URL and title in
 */
public class PortalItem {
    private String url;
    private String title;

    public PortalItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}