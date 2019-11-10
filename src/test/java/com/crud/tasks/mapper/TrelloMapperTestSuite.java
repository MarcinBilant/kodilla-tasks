package com.crud.tasks.mapper;

import com.crud.tasks.controller.TrelloListDto;
import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToLists() {
        //given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "TrelloDtoList", true));

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //then
        assertEquals(1, trelloLists.size());
        assertTrue(trelloLists.contains(new TrelloList("1", "TrelloDtoList", true)));
    }

    @Test
    public void mapToListsDto() {
        //given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "TrelloList", false));

        //when
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //then
        assertEquals(1, trelloListsDto.size());
        assertTrue(trelloListsDto.contains(new TrelloListDto("2", "TrelloList", false)));

    }

    @Test
    public void mapToBoards() {
        //given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "TrelloListDto", true);
        trelloListsDto.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "TrelloBoardDto", trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);

        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //then
        assertEquals(1, trelloBoards.size());
        assertTrue(trelloBoards.contains(new TrelloBoard("1", "TrelloBoardDto",
                Arrays.asList(new TrelloList("1", "TrelloListDto", true)))));
    }

    @Test
    public void mapToBoardsDto() {
        //given
        TrelloList trelloList = new TrelloList("2", "TrelloList", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("2", "TrelloBoard", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //when
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //then
        assertEquals(1, trelloBoardsDto.size());
        assertTrue(trelloBoardsDto.contains(new TrelloBoardDto("2", "TrelloBoard",
                Arrays.asList(new TrelloListDto("2", "TrelloList", true)))));
    }

    @Test
    public void mapToCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "description1", "pos1", "id1");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        assertEquals(new TrelloCard("card1", "description1", "pos1", "id1"), trelloCard);
    }

    @Test
    public void mapToCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard("card2", "description2", "pos2", "id2");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //then
        assertEquals(new TrelloCardDto("card2", "description2", "pos2", "id2"), trelloCardDto);
    }

}