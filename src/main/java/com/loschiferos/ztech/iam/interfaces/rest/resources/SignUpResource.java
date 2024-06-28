package com.loschiferos.ztech.iam.interfaces.rest.resources;

import java.util.Date;
import java.util.List;

public record SignUpResource(String name, String email, String address, Long phone, String photo, Long dni, Date birthday, String gender, String ruc, List<String> roles) {
}
