package io.vikunja.api.models.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskBodyModel {
    private String title;
}
