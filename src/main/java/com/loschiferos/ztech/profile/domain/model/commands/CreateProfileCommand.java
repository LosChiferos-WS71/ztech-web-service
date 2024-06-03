package com.loschiferos.ztech.profile.domain.model.commands;

public record CreateProfileCommand(String firstName, String lastName, String email, String password, String street, String number, String city, String zipCode, int phone, String photo) {
}
