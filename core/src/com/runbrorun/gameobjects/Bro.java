package com.runbrorun.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.runbrorun.gameworld.GameRenderer;


public class Bro {

    private Vector2 position;
    private Vector2 velocity; //скорость
    private Rectangle boundingRectangle; //+

    private float rotation; //когда умирает, то падает вниз более динамично чем просто движение вниз
    private int width;
    private int height;
    private boolean isAlive;

    private boolean UpMove;
    private boolean DownMove;

    public boolean isLeft;


    public Bro(float x, float y, int width, int height) {
        isAlive = true;
        this.width = width;
        this.height = height;
        position = new Vector2(x, y); // позиция
        velocity = new Vector2(0, 0); // скорость без нашего ведома
        boundingRectangle = new Rectangle();
        isLeft = false;
    }

    public void update(float delta) {

        // проверяем столкновение с верхом
        if (position.y < 2) {
            position.y = 2;
        }

        // проверяем столкновение с низом
        if (position.y > GameRenderer.midPointY + 66) {
            position.y = GameRenderer.midPointY + 66;
        }

        boundingRectangle.set(position.x, position.y, 17, 12 );

    }

    //начальное положение перса
    public void onRestart() {
        rotation = 0;
        position.y = GameRenderer.midPointY;
        velocity.x = 0;
        velocity.y = 0;
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void onClick() {
    }

    public void die() {
        isAlive = false;
        velocity.x = -69;
    }


    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }


    public void updateMotion() {
        if (isAlive) {
            if (UpMove) {
                position.y -= 120 * Gdx.graphics.getDeltaTime();
            }
            if (DownMove) {
                position.y += 120 * Gdx.graphics.getDeltaTime();
            }
        }
    }

    public void setUpMove(boolean t) {
        if (DownMove && t) DownMove = false;
        UpMove = t;
    }

    public void setDownMove(boolean t) {
        if (UpMove && t) UpMove = false;
        DownMove = t;
    }

}


