package com.example.aboba.libs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouletteTest {
    Roulette roulette = new Roulette();

    @Test
    void canAddBet() throws Exception {
        roulette.AddMoneyToBalance("user1", 1000);
        roulette.AddMoneyToBalance("user2", 1000);
        roulette.AddBet("user1", 500, "SINGLE", 7);
        roulette.AddBet("user2", 1500, "EVEN", 7);

        int num = 7;
        roulette.PostGame(num);
        Assertions.assertEquals(500 * 35 + 1000, roulette.getBalance("user1"));
        Assertions.assertEquals(0, roulette.getBalance("user2"));
    }

}