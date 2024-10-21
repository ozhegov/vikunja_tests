package io.vikunja.api.models.labels;

import lombok.Data;

@Data
public class CreatedByModel {
    private String id;
    private String name;
    private String username;
    private String created;
    private String updated;
}
