package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;

class TestBasic {
    @Test
    public void blahTest2(){
        Dungeon d = new Dungeon(1, 2);
        assertEquals(d.getWidth(), 1);
    }
}