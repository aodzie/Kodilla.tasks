package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    void fetchTrelloBoardsTest(){
        //Given
        List<TrelloListDto> listDtos = List.of(new TrelloListDto("1", "list1", true));
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto("1", "name", listDtos));
        //When
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        List<TrelloBoardDto> fetchedTrelloBoardDtoList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals (1, fetchedTrelloBoardDtoList.size());
    }

    @Test
    void createdTrelloCardDtoTest(){
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "createdTrelloCardDto.com");
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        //When
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        CreatedTrelloCardDto newCard = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals(newCard, createdTrelloCardDto);
    }
}
