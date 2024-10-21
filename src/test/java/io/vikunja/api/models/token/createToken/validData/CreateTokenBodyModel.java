package io.vikunja.api.models.token.createToken.validData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CreateTokenBodyModel {
    private String title;
    private Map<String, List<String>> permissions;
    @JsonProperty("expires_at")
    private String expiresAt;
}
