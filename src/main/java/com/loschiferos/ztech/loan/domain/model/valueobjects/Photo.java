package com.loschiferos.ztech.loan.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public class Photo {
    private String url;

    public Photo() {
        this.url = null;
    }

    public Photo(String url) {
        if (url == null || url.trim().isEmpty() || !isValidURL(url)) {
            throw new IllegalArgumentException("Invalid photo URL");
        }
        this.url = url;
    }

    private boolean isValidURL(String url) {
        // Use a simple regex to validate URL (this can be enhanced as per requirements)
        String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }
    public String getPhoto() {
        return url;
    }
}
