package za.co.wethinkcode.examples.hangman;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void shouldStartWith5Chances() {
        Player player = new Player();
        assertEquals(5, player.getChances());
    }
    @Test
    public void canLoseAChance() {
        Player player = new Player();
        int chances = player.getChances();
        player.lostChance();
        assertEquals(chances - 1, player.getChances());
    }
    @Test
    public void noMoreChances() {
        Player player = new Player();
        int chances = player.getChances();
        for (int i = 0; i < chances; i++) {
            assertFalse(player.hasNoChances());
            player.lostChance();
        }
        assertTrue(player.hasNoChances());
    }
    @Test
    public void cannotLoseChanceIfNoneAvailable() {
        Player player = new Player();
        int chances = player.getChances();
        for (int i = 0; i < chances + 1; i++) {
            player.lostChance();
        }
        assertEquals(0, player.getChances());
    }
}
