package com.example.pokerHands;

import com.example.pokerHands.priorityEnum.CardEnum;
import com.example.pokerHands.priorityEnum.CharacterEnum;
import lombok.Data;

@Data
public class Poker implements Comparable<Poker> {
    private String pokerName;
    private int value;
    private int character;

    public Poker(String pokerName) {
        this.pokerName = pokerName;
        this.character = CharacterEnum.valueOf(pokerName.charAt(1) + "").getPriority();
        this.value = Character.isDigit(pokerName.charAt(0)) ? Integer.parseInt(pokerName.charAt(0) + "")
                : CardEnum.valueOf(pokerName.charAt(0) + "").getPriority();
    }

    @Override
    public int compareTo(Poker poker) {
        if (this.value == poker.value) {
            return poker.character - this.character;
        }
        return  poker.value - this.value;
    }
}
