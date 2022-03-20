package com.crud.tasks.domain;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Trello {

    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;
}
