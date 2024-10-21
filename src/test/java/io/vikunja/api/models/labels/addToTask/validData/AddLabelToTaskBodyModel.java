package io.vikunja.api.models.labels.addToTask.validData;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddLabelToTaskBodyModel {
    @JsonProperty("label_id")
    private int labelId;
}
