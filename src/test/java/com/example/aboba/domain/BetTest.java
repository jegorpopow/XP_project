package com.example.aboba.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {
    @Test
    void singleBet() throws Exception {
        Bet bet1 = new Bet("SINGLE", 15, 1000);
        Bet bet2 = new Bet("SINGLE", 30, 1000);
        Assertions.assertThrows(Exception.class, () -> new Bet("SINGLE", 100, 0));
        Assertions.assertEquals(bet1.GetWinMoney(15), 1000 * 36);
        Assertions.assertEquals(bet2.GetWinMoney(15), 1000 * 0);
    }

    @Test
    void colorBet() throws Exception{
        Bet bet1 = new Bet("RED", 0, 1000);
        Bet bet2 = new Bet("BLACK", 0, 1000);
        Bet bet3 = new Bet("GREEN", 0, 1000);       
        Assertions.assertEquals(bet1.GetWinMoney(15), 1000 * 0);
        Assertions.assertEquals(bet2.GetWinMoney(15), 1000 * 2);
        Assertions.assertEquals(bet3.GetWinMoney(0), 1000 * 36);

    }


}
