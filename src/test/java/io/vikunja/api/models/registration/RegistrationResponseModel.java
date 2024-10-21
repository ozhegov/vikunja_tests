package io.vikunja.api.models.registration;

import lombok.Data;

@Data
public class RegistrationResponseModel {
    private String id;
    private String name;
    private String username;
    private String email;
    private String created;
    private String updated;
}
