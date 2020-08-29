package com.example.pokerHands;

import com.example.pokerHands.priorityEnum.CardEnum;
import com.example.pokerHands.priorityEnum.CharacterEnum;
import lombok.Data;

import java.util.Objects;

@Data
public class Poker implements Comparable<Poker> {
    private String pokerName;
    private int value;
    private int character;

    public Poker(String pokerName) {
        this.pokerName = pokerName;
        if (pokerName.length() == 2) {
            this.character = CharacterEnum.valueOf(pokerName.charAt(1) + "").getPriority();
            this.value = Character.isDigit(pokerName.charAt(0)) ? Integer.parseInt(pokerName.charAt(0) + "")
                    : CardEnum.valueOf(pokerName.charAt(0) + "").getPriority();
        } else if (pokerName.length() == 3) {
            this.character = CharacterEnum.valueOf(pokerName.charAt(2) + "").getPriority();
            this.value = 10;
        }
    }

    @Override
    public int compareTo(Poker poker) {
        if (this.value == poker.value) {
            return poker.character - this.character;
        }
        return  poker.value - this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poker poker = (Poker) o;
        return value == poker.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
