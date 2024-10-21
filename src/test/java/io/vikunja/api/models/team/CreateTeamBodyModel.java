package io.vikunja.api.models.team;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTeamBodyModel {
    private String name;
}
