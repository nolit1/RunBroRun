package com.runbrorun.gameobjects;


import com.runbrorun.Helpers.AssetLoader;
import com.runbrorun.gameworld.GameRenderer;
import com.runbrorun.gameworld.GameWorld;

import static com.runbrorun.Helpers.AssetLoader.music;


public class ScrollHandler {
    // ScrollHandler создаст все необходимые нам объекты
    private Thorns columnThorns1, columnThorns2, columnThorns3;
    private GameWorld gameWorld;

    public static final int SCROLL_SPEED = -69;
    public static final int THORNS_GAP = 49;

    public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;

        columnThorns1 = new Thorns(210, 0, 22, 60, SCROLL_SPEED, yPos);
        columnThorns2 = new Thorns(columnThorns1.getTailX() + THORNS_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
        columnThorns3 = new Thorns(columnThorns2.getTailX() + THORNS_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
    }

    public void update(float delta) {

        columnThorns1.update(delta);
        columnThorns2.update(delta);
        columnThorns3.update(delta);

        // проверяем кто из объектов за левой границей
        // и соответственно сбрасываем параметры этого объекта
        if (columnThorns1.isScrolledLeft()) {
            columnThorns1.reset(columnThorns3.getTailX() + THORNS_GAP);
        } else if (columnThorns2.isScrolledLeft()) {
            columnThorns2.reset(columnThorns1.getTailX() + THORNS_GAP);

        } else if (columnThorns3.isScrolledLeft()) {
            columnThorns3.reset(columnThorns2.getTailX() + THORNS_GAP);
        }
    }

    public void stop() {
        columnThorns1.stop();
        columnThorns2.stop();
        columnThorns3.stop();
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    // вернуть True если касание
    public boolean collides(Bro bro) { //отвечает за смерть и не только
        if (!columnThorns1.isScored()
                && columnThorns1.getX() + (columnThorns1.getWidth() / 2) < bro.getX()
                + bro.getWidth()) {
            addScore(1);
            columnThorns1.setScored(true);
            if (GameRenderer.rang > 13) GameRenderer.rang -= 1;
            AssetLoader.coin.play();

        } else if (!columnThorns2.isScored()
                && columnThorns2.getX() + (columnThorns2.getWidth() / 2) < bro.getX()
                + bro.getWidth()) {
            addScore(1);
            columnThorns2.setScored(true);
            if (GameRenderer.rang > 13) GameRenderer.rang -= 1;
            AssetLoader.coin.play();

        } else if (!columnThorns3.isScored()
                && columnThorns3.getX() + (columnThorns3.getWidth() / 2) < bro.getX()
                + bro.getWidth()) {
            addScore(1);
            columnThorns3.setScored(true);
            if (GameRenderer.rang > 13) GameRenderer.rang -= 1;
            AssetLoader.coin.play();

        }

        return (columnThorns1.collides(bro) || columnThorns2.collides(bro) || columnThorns3.collides(bro));
    }

    public void onRestart() {
        music.play();
        GameRenderer.rang = 50;
        columnThorns1.onRestart(210, SCROLL_SPEED);
        columnThorns2.onRestart(columnThorns1.getTailX() + THORNS_GAP, SCROLL_SPEED);
        columnThorns3.onRestart(columnThorns2.getTailX() + THORNS_GAP, SCROLL_SPEED);
    }


    public Thorns getColumnThorns1() {
        return columnThorns1;
    }

    public Thorns getColumnThorns2() {
        return columnThorns2;
    }

    public Thorns getColumnThorns3() {
        return columnThorns3;
    }
}
