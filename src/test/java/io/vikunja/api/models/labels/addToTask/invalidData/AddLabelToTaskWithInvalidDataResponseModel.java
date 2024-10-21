package io.vikunja.api.models.labels.addToTask.invalidData;

import lombok.Data;

@Data
public class AddLabelToTaskWithInvalidDataResponseModel {
    private String code;
    private String message;
}
