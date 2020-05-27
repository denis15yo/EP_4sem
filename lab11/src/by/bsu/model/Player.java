package by.bsu.model;

public class Player {
   private final String name;
   private int scores;

    public Player(String name) {
        this.name = name;
        scores = 0;
    }

    public String getName() {
        return name;
    }
    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }
}
