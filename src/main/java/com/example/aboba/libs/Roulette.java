package com.example.aboba.libs;

import java.util.*;
import java.util.stream.IntStream;

public class Roulette {
    private Random rand = new Random();
    private int MAX_ITER = 1000;
    private Map<String,List<Integer>> numbers = new HashMap<>();
    private Map<String,Integer> coefs = new HashMap<>();
    private Map<String,Integer> balances = new HashMap<>();
    private Map<String,Integer> bets = new HashMap<>();
    private String colors[] = new String[37];
    {
        colors[0]= "GREEN";
        List<String> br = Arrays.asList("RED", "BLACK");
        int idx = 0;
        for (int i = 1; i < 37; i++) {
            colors[i] = br.get(idx);
            if (i != 10 && i != 18 && i != 28) {
                idx = 1 - idx;
            }
        }
    }

    public int getBalance(String user) {
        return balances.get(user);
    }

    public int GetNumber() {
        int iter = 0;
        int num = rand.nextInt(MAX_ITER);
        while (iter < num) {
            num = rand.nextInt(MAX_ITER);
            iter++;
        }
        return num;
    }

    private List<Integer> ConvertBet(String type, Integer num) {
        List<Integer> arr = new ArrayList<>();
        if (type.equals("RED") || type.equals("BLACK") || type.equals("GREEN")) {
            arr = IntStream.range(0, 37).filter(i -> colors[i].equals(type)).boxed().toList();;
        } else if (type.equals("SINGLE")) {
            arr = Collections.singletonList(num);
        } else if (Objects.equals(type, "SPLIT HORIZONTAL")) {
            arr = Arrays.asList(num, num + 1);
        } else if (type.equals("SPLIT VERTICAL")) {
            arr = Arrays.asList(num, num + 3);
        } else if (type.equals("STREET")) {
            arr = Arrays.asList(num, num + 1, num + 2);
        } else if (type.equals("SQUARE")) {
            arr = Arrays.asList(num, num + 1, num + 3, num + 4);
        } else if (type.equals("DOUBLE STREET")) {
            arr = Arrays.asList(num, num + 1, num + 2, num + 3, num + 4, num + 5);
        } else if (type.equals("BUSKET")) {
            arr = Arrays.asList(0, num, num + 1);
        } else if (type.equals("FIRST FOUR")) {
            arr = Arrays.asList(0, 1, 2, 3);
        } else if (type.equals("HALF")) {
            if (num == 1) {
                arr = IntStream.range(1, 19).boxed().toList();;
            } else {
                arr = IntStream.range(19, 37).boxed().toList();;
            }
        } else if (type.equals("EVEN")) {
            arr = IntStream.range(1, 37).filter(i -> i % 2 == 0).boxed().toList();
        } else if (type.equals("ODD")) {
            arr = IntStream.range(1, 37).filter(i -> i % 2 == 1).boxed().toList();;
        } else if (type.equals("DOZEN")) {
            arr = IntStream.range(num, num + 12).boxed().toList();;
        } else if (type.equals("COLUMN")) {
            arr = IntStream.range(1, 37).filter(i -> i % 3 == num).boxed().toList();;
        }
        return arr;
    }

    private Integer GetCoef(String user) {
        if (numbers.get(user).size() == 18) {
            return 1;
        } else if (numbers.get(user).size() == 12) {
            return 2;
        } else if (numbers.get(user).size() == 6) {
            return 5;
        } else if (numbers.get(user).size() == 1) {
            return 35;
        } else if (numbers.get(user).size() == 2) {
            return 17;
        } else if (numbers.get(user).size() == 3) {
            return 11;
        } else if (numbers.get(user).size() == 4) {
            return 8;
        } else if (numbers.get(user).size() == 5) {
            return 6;
        }
        return 0;
    }

    public void AddMoneyToBalance(String user, Integer money) {
        balances.put(user, balances.getOrDefault(user, 0) + money);
    }

    public void SetBet(String user, Integer bet, String type, Integer number) {
        int to_bet = bet;
        if (to_bet > balances.get(user)) {
            to_bet = balances.get(user);
        }
        bets.put(user, to_bet);
        numbers.put(user, ConvertBet(type, number));
        coefs.put(user, GetCoef(user));
    }

    public void PostGame(Integer win_num) {
        for (String user : coefs.keySet()) {
            if (numbers.get(user).contains(win_num)) {
                balances.replace(user, balances.get(user) + coefs.get(user) * bets.get(user));
            } else {
                balances.replace(user, balances.get(user) - bets.get(user));
            }
        }
    }
}