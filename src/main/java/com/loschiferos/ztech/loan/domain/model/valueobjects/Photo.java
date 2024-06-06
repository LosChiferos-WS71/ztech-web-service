package com.loschiferos.ztech.loan.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public class Photo {
    private String photoUrl;

    public Photo() {
        this.photoUrl = null;
    }

    public Photo(String photoUrl) {
        if (photoUrl == null || photoUrl.trim().isEmpty() || !isValidURL(photoUrl)) {
            throw new IllegalArgumentException("Invalid photo URL");
        }
        this.photoUrl = photoUrl;
    }

    private boolean isValidURL(String photoUrl) {
        // Use a simple regex to validate URL (this can be enhanced as per requirements)
        String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(photoUrl).matches();
    }
    public String getPhoto() {
        return photoUrl;
    }
}
