package com.runbrorun.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.runbrorun.gameworld.GameRenderer;

import java.util.Random;

public class Thorns extends Scrollable {

    private Random r;
    private Rectangle thornsUp, thornsDown;

    private static final int VERTICAL_GAP = GameRenderer.rang; //высота м/д шипами
    private static final int THORNS_WIDTH = 24;
    private static final int THORNS_HEIGHT = 11;
    private float groundY;
    private Boolean isScored = false;

    Thorns(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);

        r = new Random();
        thornsUp = new Rectangle();
        thornsDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {

        //вызываем метод из родительского класса Scrollable
        super.update(delta);


        thornsUp.set(position.x - (THORNS_WIDTH - width) / 2, position.y + height
                - THORNS_HEIGHT, THORNS_WIDTH, THORNS_HEIGHT);
        thornsDown.set(position.x - (THORNS_WIDTH - width) / 2, position.y + height + VERTICAL_GAP,
                THORNS_WIDTH, THORNS_HEIGHT);
    }


    @Override
    public void reset(float newX) {
        super.reset(newX);
        // Изменяет высоту на случайное значение
        height = r.nextInt(90) + 15;
        isScored = false;
    }

    boolean collides(Bro bro) {

        return position.x < bro.getX() + bro.getWidth()
                && (Intersector.overlaps(bro.getBoundingRectangle(), thornsUp)
                || Intersector.overlaps(bro.getBoundingRectangle(), thornsDown));
    }

    boolean isScored() {
        return isScored;
    }

    void setScored(boolean b) {
        isScored = b;
    }

    void onRestart(float x, float scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }

    public Random getR() {
        return r;
    }

    public Rectangle getThornsUp() {
        return thornsUp;
    }

    public Rectangle getThornsDown() {
        return thornsDown;
    }

}
