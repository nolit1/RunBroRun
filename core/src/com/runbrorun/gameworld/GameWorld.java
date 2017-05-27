package com.runbrorun.gameworld;

import com.runbrorun.Helpers.AssetLoader;
import com.runbrorun.gameobjects.ScrollHandler;
import com.runbrorun.gameobjects.Bro;


import static com.runbrorun.Helpers.AssetLoader.music;
import static com.runbrorun.Helpers.AssetLoader.prefs;
import static com.runbrorun.gameworld.GameRenderer.MIN_HEIGHT;


public class GameWorld {

    private Bro bro;
    private ScrollHandler scroller;
    private int score = 0;

    private enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    private GameState currentState;
    public int midPointY;



    public GameWorld(int midPointY) {
        prefs.clear();
        currentState = GameState.READY;
        music.play();
        music.setVolume(0.4f);
        AssetLoader.music.setLooping(true);
        bro = new Bro(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, MIN_HEIGHT);
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public void updateRunning(float delta) {

        if (delta > .30f) {
            delta = .30f;
        }

        bro.updateMotion();
        bro.update(delta);
        scroller.update(delta);


        if (scroller.collides(bro) && bro.isAlive()) {
            bro.die();
            scroller.stop();
            music.stop();
            AssetLoader.death.play();
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            } else currentState = GameState.GAMEOVER;
        }
    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }


    private void updateReady(float delta) {
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bro.onRestart();
        scroller.onRestart();
        currentState = GameState.READY;

    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public void addScore(int increment) {
        score += increment;
        if (score < 0) score = 0;
    }


    public int getScore() {
        return score;
    }

    public Bro getBro() {
        return bro;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

}
