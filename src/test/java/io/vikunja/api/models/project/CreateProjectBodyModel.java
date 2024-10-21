package io.vikunja.api.models.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectBodyModel {
    private String title;
}
