package com.example.aboba.domain;

import java.util.*;
import java.util.stream.IntStream;

public class User {
    private List<Bet> _bets = new ArrayList<>();
    private Integer _balance = 0;
    private String _name = "";
    
    public User(String name) {
        _name = name;
    }

    public void AddBet(Integer bet, String type, Integer number) {
        int to_bet = bet;
        if (to_bet > _balance) {
            to_bet = _balance;
        }
        _balance -= to_bet;
        _bets.add(new Bet(type, number, to_bet));
    }

    public void AddMoneyToBalance(Integer money) {
        _balance += money;
    }

    public void ComputePostGame(Integer win_num) {
        for (Bet bet : _bets) {
            _balance += bet.GetWinMoney(win_num);
        }
        _bets.clear();
    }

    public Integer GetBalance() {
        return _balance;
    }
}