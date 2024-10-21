package io.vikunja.api.models.login;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginBodyModel {
    @JsonProperty("long_token")
    @Builder.Default
    boolean longToken = true;
    private String password;
    private String username;
}
