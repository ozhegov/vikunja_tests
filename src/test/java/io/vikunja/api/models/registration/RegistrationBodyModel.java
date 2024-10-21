package io.vikunja.api.models.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegistrationBodyModel {
    private String email;
    private String password;
    private String username;
}
