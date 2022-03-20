package com.crud.tasks.domain;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentsByType {

    @JsonProperty("trello")
    private Trello trello;

}
