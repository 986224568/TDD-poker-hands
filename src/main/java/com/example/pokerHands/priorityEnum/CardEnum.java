package com.example.pokerHands.priorityEnum;


import lombok.Data;

public enum CardEnum {
    J(11,"J"),
    Q(12,"Q"),
    K(13,"K"),
    A(14,"A");

    private Integer priority;
    private String character;

    CardEnum(int priority, String character) {
        this.priority = priority;
        this.character = character;
    }

    public int getPriority() {
        return priority;
    }
}
