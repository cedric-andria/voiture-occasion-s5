package com.ced.app.model;

import java.util.HashMap;
import java.util.Map;

public class RequeteImage {
    private String image;
    public RequeteImage(String image) {
        this.image = image;
    }
    public RequeteImage() {
        // setHeader(new HashMap<>());

    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    // public String getHeader() {
    //     return header;
    // }
    // public void setHeader(Map<String, String> header) {
    //     this.header = header;
    // }
}
