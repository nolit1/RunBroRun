package com.runbrorun.Helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.runbrorun.gameobjects.Bro;
import com.runbrorun.gameworld.GameWorld;

//здесь прогам понимает все действия игрока, даже если он бьётся головой об экран телефона
//или клавиатуру

public class InputHandler implements InputProcessor {

    private Bro myBro;
    private GameWorld myWorld;

    public InputHandler(GameWorld myWorld) {
        this.myWorld = myWorld;
        myBro = myWorld.getBro();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.W:
                myBro.setUpMove(true);
                break;
            case Input.Keys.S:
                myBro.setDownMove(true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode)
        {
            case Input.Keys.W:
                myBro.setUpMove(false);
                break;
            case Input.Keys.S:
                myBro.setDownMove(false);
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (myWorld.isReady()) {
            myWorld.start();
        }

        myBro.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {

            myWorld.restart();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
