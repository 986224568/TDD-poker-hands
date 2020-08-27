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
        Integer result = null;

        result = judgeStraightFlush(pokers, pokers1);
        if (result != null) return result;

        result = judgeFourOfKind(pokers, pokers1);
        if (result != null) return result;

        result = judgeFullHouse(pokers, pokers1);
        if (result != null) return result;

        result = judgeFlush(pokers, pokers1);
        if (result != null) return result;

        result = judgeStraight(pokers, pokers1);
        if (result != null) return result;

        result = judgeThreeOfKind(pokers, pokers1);
        if (result != null) return result;

        result = judgePair(pokers, pokers1);
        if (result != null) return result;

        return judgeHighCard(poker1, poker2);
    }

    private int judgeHighCard(Poker poker1, Poker poker2) {
        if (poker1.getValue() == poker2.getValue()) {
            return poker1.getCharacter() - poker2.getCharacter();
        }
        return poker1.getValue() - poker2.getValue();
    }

    private Integer judgePair(List<Poker> pokers, List<Poker> pokers1) {
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
        return null;
    }

    private Integer judgeThreeOfKind(List<Poker> pokers, List<Poker> pokers1) {
        if (getThreeOfKindNumber(pokers) != -1 && getThreeOfKindNumber(pokers1) != -1) {
            return getThreeOfKindValue(pokers) > getThreeOfKindValue(pokers1) ? 1 : -1;
        }

        if (getThreeOfKindNumber(pokers) != -1) {
            return 1;
        } else if (getThreeOfKindNumber(pokers1) != -1) {
            return -1;
        }
        return null;
    }

    private Integer judgeStraight(List<Poker> pokers, List<Poker> pokers1) {
        if (getStraightValue(pokers) != -1 && getStraightValue(pokers1) != -1) {
            return getStraightValue(pokers) > getStraightValue(pokers1) ? 1 : -1;
        }

        if (getStraightValue(pokers) != -1) {
            return 1;
        } else if (getStraightValue(pokers1) != -1) {
            return -1;
        }
        return null;
    }

    private Integer judgeFlush(List<Poker> pokers, List<Poker> pokers1) {
        if (getFlushValue(pokers) != -1 && getFlushValue(pokers1) != -1) {
            return getFlushValue(pokers) > getFlushValue(pokers1) ? 1 : -1;
        }

        if (getFlushValue(pokers) != -1) {
            return 1;
        } else if (getFlushValue(pokers1) != -1) {
            return -1;
        }
        return null;
    }

    private Integer judgeFullHouse(List<Poker> pokers, List<Poker> pokers1) {
        if (getFullHouse(pokers) != -1 && getFullHouse(pokers1) != -1) {
            return getFullHouse(pokers) > getFullHouse(pokers1) ? 1 : -1;
        }

        if (getFullHouse(pokers) != -1) {
            return 1;
        } else if (getFullHouse(pokers1) != -1) {
            return -1;
        }
        return null;
    }

    private Integer judgeFourOfKind(List<Poker> pokers, List<Poker> pokers1) {
        if (getFourOfKindNumber(pokers) != -1 && getFourOfKindNumber(pokers1) != -1) {
            return getThreeOfKindValue(pokers) > getThreeOfKindValue(pokers1) ? 1 : -1;
        }

        if (getFourOfKindNumber(pokers) != -1) {
            return 1;
        } else if (getFourOfKindNumber(pokers1) != -1) {
            return -1;
        }
        return null;
    }

    private Integer judgeStraightFlush(List<Poker> pokers, List<Poker> pokers1) {
        if(getStraightFlushValue(pokers) != -1 && getStraightFlushValue(pokers1) != -1) {
            return getStraightValue(pokers) > getStraightFlushValue(pokers1) ? 1 : -1;
        }

        if (getStraightFlushValue(pokers) != -1) {
            return 1;
        } else if (getStraightFlushValue(pokers1) != -1) {
            return -1;
        }
        return null;
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

    private static int getFlushValue(List<Poker> pokers) {
        Collections.sort(pokers);
        for (int i = 0; i < pokers.size(); i++) {
            if (i == 4) {
                return pokers.get(0).getValue();
            }
            if (pokers.get(i).getCharacter() != pokers.get(i + 1).getCharacter()) {
                return -1;
            }
        }
        return pokers.get(0).getValue();
    }

    private static int getFullHouse(List<Poker> pokers) {
        Collections.sort(pokers);
        Map<Poker, Integer> map = new HashMap<>();
        for (Poker poker : pokers) {
            map.put(poker, map.getOrDefault(poker, 0) + 1);
        }
        for (Poker poker : pokers) {
            if (map.get(poker) == 3 && map.keySet().size() == 2) {
                return poker.getValue();
            }
        }
        return -1;
    }

    private static int getStraightFlushValue(List<Poker> pokers) {
        Collections.sort(pokers);
        if (getFlushValue(pokers) != -1 && getStraightValue(pokers) != -1) {
            return pokers.get(0).getValue();
        }
        return -1;
    }
}
