package com.example;

import com.example.pokerHands.PokerHands;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {
    private static PokerHands pokerHands;

    @BeforeAll
    public static void setUp() throws Exception {
        pokerHands = new PokerHands();
    }

    @Test
    void should_return_true_with_player2_win_when_playing_porkerHands_compare_by_poker_value_given_2_player_cards() {
        //given
        String[] player1 = {"2H", "3D", "4S", "5C", "7H"};
        String[] player2 = {"AH", "3S", "4D", "5S", "7D"};
        //when
        int result = pokerHands.play(player1, player2);
        //then
        assertEquals(2, result);
    }

    @Test
    void should_return_true_with_player1_win_when_playing_porkerHands_compare_by_poker_value_and_character_given_2_player_cards() {
        //given
        String[] player1 = {"AS", "3D", "4S", "5C", "7H"};
        String[] player2 = {"AH", "3S", "4D", "5S", "7D"};
        //when
        int result = pokerHands.play(player1, player2);
        //then
        assertEquals(1, result);
    }

    @Test
    void should_return_true_with_player2_win_when_playing_porkerHands_compare_by_poker_value_and_character_and_pair_given_2_player_cards() {
        //given
        String[] player1 = {"AS", "3D", "4S", "5C", "7H"};
        String[] player2 = {"AH", "3S", "AD", "5S", "7D"};
        //when
        int result = pokerHands.play(player1, player2);
        //then
        assertEquals(2, result);
    }

    @Test
    void should_return_true_with_player1_win_when_playing_porkerHands_compare_by_poker_value_and_character_and_pair_given_2_player_cards_both_exist_pair() {
        //given
        String[] player1 = {"AS", "3D", "4S", "4C", "7H"};
        String[] player2 = {"AH", "3S", "3D", "5S", "7D"};
        //when
        int result = pokerHands.play(player1, player2);
        //then
        assertEquals(1, result);
    }

    @Test
    void should_return_true_with_player1_win_when_playing_porkerHands_compare_by_poker_value_and_character_and_pair_given_player1_has_2pair_and_player2_has_1pair() {
        //given
        String[] player1 = {"AS", "3D", "3S", "4C", "4H"};
        String[] player2 = {"AH", "AD", "5D", "6S", "7D"};
        //when
        int result = pokerHands.play(player1, player2);
        //then
        assertEquals(1, result);
    }

    @Test
    void should_return_true_with_player2_win_when_playing_porkerHands_compare_by_poker_value_and_character_and_pair_and_three_of_kind_given_cards_has_pair() {
        //given
        String[] player1 = {"2S", "3D", "3S", "4C", "4H"};
        String[] player2 = {"AH", "AD", "AS", "6S", "7D"};
        //when
        int result = pokerHands.play(player1, player2);
        //then
        assertEquals(1, result);
    }
}