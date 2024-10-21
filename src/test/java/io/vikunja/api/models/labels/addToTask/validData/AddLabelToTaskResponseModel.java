package io.vikunja.api.models.labels.addToTask.validData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddLabelToTaskResponseModel {
    @JsonProperty("label_id")
    private int labelId;
    private String created;
}
