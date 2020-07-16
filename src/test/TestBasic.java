package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Dungeon;

class TestBasic {
    @Test
    public void blahTest2(){
        Dungeon d = new Dungeon(1, 2);
        assertEquals(d.getWidth(), 1);
    }
}