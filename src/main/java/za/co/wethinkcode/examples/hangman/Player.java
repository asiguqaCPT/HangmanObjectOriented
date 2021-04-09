package za.co.wethinkcode.examples.hangman;

public class Player {
    private int chances = 5;
    public int getChances() {
        return chances;
    }

    public void lostChance() {
        if(!this.hasNoChances())
            this.chances--;
    }

    public boolean hasNoChances() {
        return this.getChances() == 0;
    }
}
