package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Getter
@Component
public class TrelloConfig {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.user}")
    private String trelloUser;
}