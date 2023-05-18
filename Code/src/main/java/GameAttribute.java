public class GameAttribute {
    private int gameLevel;
    private int numOfVaccines;
    // easy: 5 intermediate: 7 challenge: 10

    public final int levelEasy = 0;
    public final int levelIntermediate = 1;
    public final int levelChallenge = 2;

    /**
     * initialize game attribute with default level
     * @param defaultLvl
     */
    GameAttribute(int defaultLvl) {
        setGameLevel(defaultLvl);
    }

    public void setGameLevel(int n) {
        if(n >= 0 && n <= 2) {
            gameLevel = n;
        }else {
            gameLevel = 0;
        }
        setGameAttributes();
    }
    /**
     * sets attributes of game(game level, number of vaccines required for the level)
     */
    private void setGameAttributes() {
        switch (gameLevel) {
            case levelEasy:
                numOfVaccines = 5;
                break;
            case levelIntermediate:
                numOfVaccines = 7;
                break;
            case levelChallenge:
                numOfVaccines = 10;
                break;
            default:
                gameLevel = levelEasy;
                numOfVaccines = 5;
                break;
        }
    }
    public int getGameLevel() {
        return gameLevel;
    }
    public int getNumOfVaccines() {
        return numOfVaccines;
    }
}
