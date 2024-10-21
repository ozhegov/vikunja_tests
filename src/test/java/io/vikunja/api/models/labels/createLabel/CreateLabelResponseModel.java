package io.vikunja.api.models.labels.createLabel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vikunja.api.models.labels.CreatedByModel;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CreateLabelResponseModel {
    private int id;
    private String title;
    private String description;
    @JsonProperty("hex_color")
    private String hexColor;
    @JsonProperty("created_by")
    private CreatedByModel createdBy;
    private String created;
    private String updated;
}
