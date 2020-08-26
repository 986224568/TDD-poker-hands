package com.example.pokerHands.priorityEnum;
import lombok.Getter;

@Getter
public enum CharacterEnum {
    D(1, "方块"),
    C(2, "梅花"),
    H(3, "红桃"),
    S(4, "黑桃")
    ;

    private Integer priority;
    private String character;

    CharacterEnum(int priority, String character) {
        this.priority = priority;
        this.character = character;
    }

    public int getPriority() {
        return priority;
    }
}
