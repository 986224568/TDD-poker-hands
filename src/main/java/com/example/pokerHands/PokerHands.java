package com.example.pokerHands;


import java.util.*;
import java.util.stream.Collectors;

public class PokerHands {

    public int play(String[] play1, String[] play2) {
        if (compare(getPokerList(play1), getPokerList(play2)) > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    private static List<Poker> getPokerList(String[] pokerStr) {
        List<Poker> pokers = new ArrayList<>();
        Arrays.stream(pokerStr).forEach(poker -> pokers.add(new Poker(poker)));
        return pokers;
    }

    private int compare(List<Poker> pokers, List<Poker> pokers1) {
        Collections.sort(pokers);
        Collections.sort(pokers1);
        Poker poker1 = pokers.get(0);
        Poker poker2 = pokers1.get(0);
        if (isExistPair(pokers)) {
            return 1;
        } else if (isExistPair(pokers1)){
            return -1;
        }
        if (poker1.getValue() == poker2.getValue()) {
            return poker1.getCharacter() - poker2.getCharacter();
        }
        return poker1.getValue() - poker2.getValue();
    }

    private static boolean isExistPair(List<Poker> pokers) {
        Set<Poker> pokerSet = new HashSet<>(pokers);
        return pokerSet.size() != pokers.size();
    }


}
