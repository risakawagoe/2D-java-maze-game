public class GameState {
    private State gameState;
    private int page;// controls page for tutorial and narration display, 0 - maxPage
    private final int maxPageTutorial = 3;// 4 pages(0-3)
    private final int maxPageNarration = 10;// 11 pages(0-10)
    private enum State {
        TITLE,
        CHANGE_LEVEL,
        TUTORIAL,
        NARRATION,
        PLAY,
        END
    }

    public final int titleState = 0;
    public final int changeLevelState = 1;
    public final int tutorialState = 2;
    public final int narrationState = 3;
    public final int playState = 4;
    public final int endState = 5;


    GameState() {
        gameState = State.TITLE;
        page = 0;
    }

    /**
     * change game states
     */
    public void toTitleState() {gameState = State.TITLE;}
    public void toPlayState() {gameState = State.PLAY;}
    public void toChangeLevelState() {gameState = State.CHANGE_LEVEL;}
    public void toTutorialState() {gameState = State.TUTORIAL;}
    public void toNarrationState() {gameState = State.NARRATION;}
    public void toEndState() {gameState = State.END;}

    /**
     * get current game states
     */
    public int getGameState() {
        switch (gameState) {
            case TITLE: return 0;
            case CHANGE_LEVEL: return 1;
            case TUTORIAL: return 2;
            case NARRATION: return 3;
            case PLAY: return 4;
            case END: return 5;
            default: toTitleState(); return 0;
        }
    }
    public int getPage() {
        checkPageValid();
        return page;
    }
    /**
     * switch to previous screen in tutorial state or narration state
     */
    public void prevPage() {
        if(gameState == State.TUTORIAL) {
            if(page > 0 && page <= maxPageTutorial) {
                page--;
            }
        }else if(gameState == State.NARRATION) {
            if(page > 0 && page <= maxPageNarration) {
                page--;
            }
        }
        checkPageValid();
    }
    /**
     * switch to next screen in tutorial state or narration state
     * last page of tutorial state will switch to first page of narration state
     */
    public void nextPage() {
        if(gameState == State.TUTORIAL) {
            if(page >= 0 && page < maxPageTutorial) {
                page++;
            }else if(page < 0) {// exception
                page = 0;
            }else {
                page = 0;
                toNarrationState();
            }
        }else if(gameState == State.NARRATION) {
            if(page >= 0 && page < maxPageNarration) {
                page++;
            }else if(page < 0) {// exception
                page = 0;
            }else {
                page = maxPageNarration;
            }
        }
        checkPageValid();
    }
    public void skipTutorial() {
        if(gameState == State.TUTORIAL) {
            page = 0;
            toNarrationState();
        }
    }
    public void skipNarration() {
        if(gameState == State.NARRATION) {
            page = 0;
            toPlayState();
        }
    }
    /**
     * check if page is within the maximum number of pages for that screen(tutorial/narration)
     * tutorial: 0-3
     * narration: 0-10
     */
    private void checkPageValid() {
        if(page < 0) {
            page = 0;
        }else if(gameState == State.TUTORIAL && page > maxPageTutorial) {
            page = 0;
        }else if(gameState == State.NARRATION && page > maxPageNarration) {
            page = 0;
        }
    }

}
