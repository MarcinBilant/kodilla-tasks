package com.crud.tasks.trello.facade;

import com.crud.tasks.controller.TrelloListDto;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;


    @Test
    public void shouldFetchTrelloBoards() {
        //given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "Trello_list", false));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1", "Trello_board", trelloListsDto));

        List<TrelloList> TrelloLists = new ArrayList<>();
        TrelloLists.add(new TrelloList("1", "Trello_list", false));

        List<TrelloBoard> TrelloBoards = new ArrayList<>();
        TrelloBoards.add(new TrelloBoard("1", "Trello_board", TrelloLists));

        when(trelloService.fetchTrelloBoard()).thenReturn(trelloBoardsDto);
        when(trelloMapper.mapToBoards(trelloBoardsDto)).thenReturn(TrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoardsDto);
        when(trelloValidator.validateTrelloBoards(TrelloBoards)).thenReturn(TrelloBoards);

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoard();

        //then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("Trello_board", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("Trello_list", trelloListDto.getName());
                assertEquals(false, trelloListDto.isClosed());
            });
        });
    }




}
