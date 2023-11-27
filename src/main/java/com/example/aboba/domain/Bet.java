package com.example.aboba.domain;

import java.util.*;
import java.util.stream.IntStream;

public class Bet {
    private List<Integer> _fields = new ArrayList<>();
    private String _user;
    private Integer _coef;
    
    public Bet(String type, Integer num, String user) {
        _user = user;
        if (type.equals("RED") || type.equals("BLACK") || type.equals("GREEN")) {
            _fields = IntStream.range(0, 37).filter(i -> colors[i].equals(type)).boxed().toList();;
        } else if (type.equals("SINGLE")) {
            _fields = Collections.singletonList(num);
        } else if (Objects.equals(type, "SPLIT HORIZONTAL")) {
            _fields = Arrays.asList(num, num + 1);
        } else if (type.equals("SPLIT VERTICAL")) {
            _fields = Arrays.asList(num, num + 3);
        } else if (type.equals("STREET")) {
            _fields = Arrays.asList(num, num + 1, num + 2);
        } else if (type.equals("SQUARE")) {
            _fields = Arrays.asList(num, num + 1, num + 3, num + 4);
        } else if (type.equals("DOUBLE STREET")) {
            _fields = Arrays.asList(num, num + 1, num + 2, num + 3, num + 4, num + 5);
        } else if (type.equals("BUSKET")) {
            _fields = Arrays.asList(0, num, num + 1);
        } else if (type.equals("FIRST FOUR")) {
            _fields = Arrays.asList(0, 1, 2, 3);
        } else if (type.equals("HALF")) {
            if (num == 1) {
                _fields = IntStream.range(1, 19).boxed().toList();;
            } else {
                _fields = IntStream.range(19, 37).boxed().toList();;
            }
        } else if (type.equals("EVEN")) {
            _fields = IntStream.range(1, 37).filter(i -> i % 2 == 0).boxed().toList();
        } else if (type.equals("ODD")) {
            _fields = IntStream.range(1, 37).filter(i -> i % 2 == 1).boxed().toList();;
        } else if (type.equals("DOZEN")) {
            _fields = IntStream.range(num, num + 12).boxed().toList();;
        } else if (type.equals("COLUMN")) {
            _fields = IntStream.range(1, 37).filter(i -> i % 3 == num).boxed().toList();;
        }
        _coef = InitCoef();
    }

    private Integer InitCoef() {
        if (_fields.size() == 18) {
            return 1;
        } else if (_fields.size() == 12) {
            return 2;
        } else if (_fields.size() == 6) {
            return 5;
        } else if (_fields.size() == 1) {
            return 35;
        } else if (_fields.size() == 2) {
            return 17;
        } else if (_fields.size() == 3) {
            return 11;
        } else if (_fields.size() == 4) {
            return 8;
        } else if (_fields.size() == 5) {
            return 6;
        }
        return 0;
    }
}