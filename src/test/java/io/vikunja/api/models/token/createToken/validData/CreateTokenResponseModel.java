package io.vikunja.api.models.token.createToken.validData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreateTokenResponseModel {
    private int id;
    private String title;
    private String token;
    private Map<String, List<String>> permissions;
    @JsonProperty("expires_at")
    private String expiresAt;
    private String created;
}
