package io.vikunja.api.models.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskResponseModel {
    private String id;
    private String title;
}
