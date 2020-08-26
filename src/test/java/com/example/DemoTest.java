package com.example;

import
        org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {
    private static PokerHands pokerHands;

    @BeforeAll
    public static void setUp() throws Exception {
        pokerHands = new PokerHands();
    }

    @Test
    void should_return_play1Win_when_playing_porkerHands_given_2_player_cards() {
        //given
        String[] player1 = {"H2", "D3", "S4", "C5", "H7"};
        String[] player2 = {"H1", "S3", "D4", "S5", "D7"};
        //when
        int result = pokerHands.play(player1, player2);
        //then
        assertEquals(2,result);
    }
}