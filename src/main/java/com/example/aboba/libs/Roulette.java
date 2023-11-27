package com.example.aboba.libs;

import java.util.*;
import java.util.stream.IntStream;
import com.example.aboba.*;

public class Roulette {
    private Random rand = new Random();
    private int MAX_ITER = 1000;
    private Map<String,User> users = new HashMap<>();
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

    public int getBalance(String username) {
        AddUser(username);
        return users.get(username).GetBalance();
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

    public void AddUser(String username) {
        if (!users.containsKey()) {
            users.put(username, User(username));
        }
    }

    public void AddMoneyToBalance(String username, Integer money) {
        AddUser(username);
        users.get(username).AddMoneyToBalance(money);
    }

    public void SetBet(String username, Integer bet, String type, Integer number) {
        AddUser(username);
        users.get(username).AddBet(bet, type, number);
    }

    public void PostGame(Integer win_num) {
        for (User user : users.valueSet()) {
            user.ComputePostGame(win_num);
        }
    }
}