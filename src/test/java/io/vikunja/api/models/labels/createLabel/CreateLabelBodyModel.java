package io.vikunja.api.models.labels.createLabel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateLabelBodyModel {
    @Builder.Default
    String description = "";
    @JsonProperty("hex_color")
    @Builder.Default
    String hexColor = "";
    private String title;
}
