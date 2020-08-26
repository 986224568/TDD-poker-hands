package com.example.pokerHands;


import java.util.*;

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

        if (getFourOfKindNumber(pokers) != -1 && getFourOfKindNumber(pokers1) != -1) {
            return getThreeOfKindValue(pokers) > getThreeOfKindValue(pokers1) ? 1 : -1;
        }

        if (getFourOfKindNumber(pokers) != -1) {
            return 1;
        } else if (getFourOfKindNumber(pokers1) != -1) {
            return -1;
        }

        if (getStraightValue(pokers) != -1 && getStraightValue(pokers1) != -1) {
            return getStraightValue(pokers) > getStraightValue(pokers1) ? 1 : -1;
        }

        if (getStraightValue(pokers) != -1) {
            return 1;
        } else if (getStraightValue(pokers1) != -1) {
            return -1;
        }

        if (getThreeOfKindNumber(pokers) != -1 && getThreeOfKindNumber(pokers1) != -1) {
            return getThreeOfKindValue(pokers) > getThreeOfKindValue(pokers1) ? 1 : -1;
        }

        if (getThreeOfKindNumber(pokers) != -1) {
            return 1;
        } else if (getThreeOfKindNumber(pokers1) != -1) {
            return -1;
        }
        if (getPairNumbers(pokers) > 0 && getPairNumbers(pokers1) > 0) {
            return (getPairNumbers(pokers) - getPairNumbers(pokers1)) > 0 ? 1 :
                    (getPairNumbers(pokers) == getPairNumbers(pokers1)) ?
                            getMaxPairCardValue(pokers) - getMaxPairCardValue(pokers1) : -1;
        }
        if (getPairNumbers(pokers) > 0) {
            return 1;
        } else if (getPairNumbers(pokers1) > 0) {
            return -1;
        }
        if (poker1.getValue() == poker2.getValue()) {
            return poker1.getCharacter() - poker2.getCharacter();
        }
        return poker1.getValue() - poker2.getValue();
    }

    private static int getPairNumbers(List<Poker> pokers) {
        Set<Poker> pokerSet = new HashSet<>(pokers);
        return pokers.size() - pokerSet.size();
    }

    private static int getMaxPairCardValue(List<Poker> pokers) {
        Collections.sort(pokers);
        for (int i = 0; i < pokers.size(); i++) {
            if (pokers.get(i).getValue() == pokers.get(i + 1).getValue()) {
                return pokers.get(i).getValue();
            }
        }
        return 0;
    }

    private static int getThreeOfKindNumber(List<Poker> pokers) {
        Map<Poker, Integer> map = new HashMap<>();
        for (Poker poker : pokers) {
            map.put(poker, map.getOrDefault(poker, 0) + 1);
            if (map.get(poker) == 3) {
                return poker.getValue();
            }
        }
        return -1;
    }

    private static int getThreeOfKindValue(List<Poker> pokers) {
        Collections.sort(pokers);
        for (int i = 0; i < pokers.size(); i++) {
            if (pokers.get(i).getValue() == pokers.get(i + 1).getValue() &&
                    pokers.get(i + 1).getValue() == pokers.get(i + 2).getValue()) {
                return pokers.get(i).getValue();
            }
        }
        return 0;
    }

    private static int getFourOfKindNumber(List<Poker> pokers) {
        Map<Poker, Integer> map = new HashMap<>();
        for (Poker poker : pokers) {
            map.put(poker, map.getOrDefault(poker, 0) + 1);
            if (map.get(poker) == 4) {
                return poker.getValue();
            }
        }
        return -1;
    }

    private static int getStraightValue(List<Poker> pokers) {
        Collections.sort(pokers);
        for (int i = 0; i < pokers.size(); i++) {
            if (i == 4) {
                return pokers.get(0).getValue();
            }
            if ((pokers.get(i).getValue() - pokers.get(i + 1).getValue()) != 1) {
                return -1;
            }
        }
        return pokers.get(0).getValue();
    }
}
