package com.runbrorun.gameobjects;


import com.runbrorun.Helpers.AssetLoader;
import com.runbrorun.gameworld.GameRenderer;
import com.runbrorun.gameworld.GameWorld;

import static com.runbrorun.Helpers.AssetLoader.music;


public class ScrollHandler {
    // ScrollHandler создаст все необходимые нам объекты
    private Thorns columnThorns1, columnThorns2, columnThorns3;
    private GameWorld gameWorld;

    private static final int SCROLL_SPEED = -79;
    private static final int THORNS_GAP = 49;
    private static final int MIN_RANG = 20; //минимальное расстояние между шипами
    private static final int SCORE_PLUS = 1; //увеличение счёта
    private static final int SCORE_MINUS = -3; //уменьшение счёта
    private static final int RANGE_PLUS = 1; //смещение верхних шипов
    private static final int RANGE_MINUS = -2; //смещение нижних шипов, в 2 раза больше чем верхнее
    private static final int RANGE_BONUS = 10; //прибавляется каждые 5 очков
    private static final int SCORE_FOR_BONUS = 5; //те самые "каждые 5 очков"

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
        if (!columnThorns1.isScored() && columnThorns1.getX() + (columnThorns1.getWidth() / 2) < bro.getX() + bro.getWidth()) {
            if (columnThorns1.getHeight() < bro.getY() && columnThorns1.getHeight() + GameRenderer.rang > bro.getY()) {
                addScore(SCORE_PLUS);
                columnThorns1.setScored(true);
                AssetLoader.coin.play();
                if (GameRenderer.rang >= MIN_RANG) {
                    columnThorns1.height += RANGE_PLUS;
                    columnThorns2.height += RANGE_PLUS;
                    columnThorns3.height += RANGE_PLUS;
                    GameRenderer.rang += RANGE_MINUS;
                }
                if (gameWorld.getScore() > SCORE_FOR_BONUS && gameWorld.getScore() % SCORE_FOR_BONUS == 0) {
                    GameRenderer.rang += RANGE_BONUS;
                    if (GameRenderer.rang > 50) GameRenderer.rang = 50;
                }
            } else {
                addScore(SCORE_MINUS);
                columnThorns1.setScored(true);
            }

        } else if (!columnThorns2.isScored() && columnThorns2.getX() + (columnThorns2.getWidth() / 2) < bro.getX() + bro.getWidth()) {
            if (columnThorns2.getHeight() < bro.getY() && columnThorns2.getHeight() + GameRenderer.rang > bro.getY()) {
                addScore(SCORE_PLUS);
                columnThorns2.setScored(true);
                AssetLoader.coin.play();
                if (GameRenderer.rang >= MIN_RANG) {
                    columnThorns1.height += RANGE_PLUS;
                    columnThorns2.height += RANGE_PLUS;
                    columnThorns3.height += RANGE_PLUS;
                    GameRenderer.rang += RANGE_MINUS;
                }
                if (gameWorld.getScore() > SCORE_FOR_BONUS && gameWorld.getScore() % SCORE_FOR_BONUS == 0) {
                    GameRenderer.rang += RANGE_BONUS;
                    if (GameRenderer.rang > 50) GameRenderer.rang = 50;
                }
            } else {
                addScore(SCORE_MINUS);
                columnThorns2.setScored(true);
                //if (GameRenderer.rang > MIN_RANG) GameRenderer.rang -= 1;
            }

        } else if (!columnThorns3.isScored() && columnThorns3.getX() + (columnThorns3.getWidth() / 2) < bro.getX() + bro.getWidth()) {
            if (columnThorns3.getHeight() < bro.getY() && columnThorns3.getHeight() + GameRenderer.rang > bro.getY()) {
                addScore(SCORE_PLUS);
                columnThorns3.setScored(true);
                AssetLoader.coin.play();
                if (GameRenderer.rang >= MIN_RANG) {
                    columnThorns1.height += RANGE_PLUS;
                    columnThorns2.height += RANGE_PLUS;
                    columnThorns3.height += RANGE_PLUS;
                    GameRenderer.rang += RANGE_MINUS;
                }
                if (gameWorld.getScore() > SCORE_FOR_BONUS && gameWorld.getScore() % SCORE_FOR_BONUS == 0) {
                    GameRenderer.rang += RANGE_BONUS;
                    if (GameRenderer.rang > 50) GameRenderer.rang = 50;
                }
            } else {
                addScore(SCORE_MINUS);
                columnThorns3.setScored(true);
               // if (GameRenderer.rang > MIN_RANG) GameRenderer.rang -= 1;
            }
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
