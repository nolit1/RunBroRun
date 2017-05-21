package com.runbrorun.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.runbrorun.gameobjects.*;
import com.runbrorun.Helpers.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Animation;

public class GameRenderer {

    private GameWorld myWorld;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    public static int midPointY;
    private int gameHeight;

    // Game Objects
    private Bro bro;
    private Thorns columnThorns1, columnThorns2, columnThorns3;
    public static int rang = 50; //расстояние между шипами

    // Game Assets
    private TextureRegion bg;
    private Animation broAnimation;
    private TextureRegion broOne, broTwo, broThree;
    private TextureRegion thornsUp, thornsDown;


    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;


        this.gameHeight = gameHeight;
        GameRenderer.midPointY = midPointY;

        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        //объект
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        // Вызываем вспомогательные методы, чтобы проиницилизировать переменные класса
        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        bro = myWorld.getBro();
        ScrollHandler scroller = myWorld.getScroller();
        columnThorns1 = scroller.getColumnThorns1();
        columnThorns2 = scroller.getColumnThorns2();
        columnThorns3 = scroller.getColumnThorns3();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        broAnimation = AssetLoader.broAnimation;
        broOne = AssetLoader.broTwo;
        broTwo = AssetLoader.broOne;
        broThree = AssetLoader.broThree;
        thornsUp = AssetLoader.thornsUp;
        thornsDown = AssetLoader.thornsDown;
    }

    private void drawThorns() {

        batcher.draw(thornsUp, columnThorns1.getX() - 1,
                columnThorns1.getY() + columnThorns1.getHeight() - 14, 24, 14);
        batcher.draw(thornsDown, columnThorns1.getX() - 1,
                columnThorns1.getY() + columnThorns1.getHeight() + rang, 24, 14); ///////

        batcher.draw(thornsUp, columnThorns2.getX() - 1,
                columnThorns2.getY() + columnThorns2.getHeight() - 14, 24, 14);
        batcher.draw(thornsDown, columnThorns2.getX() - 1,
                columnThorns2.getY() + columnThorns2.getHeight() + rang, 24, 14);

        batcher.draw(thornsUp, columnThorns3.getX() - 1,
                columnThorns3.getY() + columnThorns3.getHeight() - 14, 24, 14);
        batcher.draw(thornsDown, columnThorns3.getX() - 1,
                columnThorns3.getY() + columnThorns3.getHeight() + rang, 24, 14);
    }


    //тут отрисовка
    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);


        // Заливаем задний фон (небо)
        shapeRenderer.setColor(39 / 255.0f, 64 / 255.0f, 94 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Рисуем (низ, нижнее небо что ли назвать)
        shapeRenderer.setColor(52 / 255.0f, 81 / 255.0f, 123 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(bg, 0, midPointY + 23, 136, 43);

        batcher.enableBlending();

        drawThorns();


        //отрисовка перса
        batcher.draw((TextureRegion) broAnimation.getKeyFrame(runTime), bro.getX(),
                bro.getY(), bro.getWidth() / 2.0f,
                bro.getHeight() / 2.0f, bro.getWidth(), bro.getHeight(),
                1, 1, bro.getRotation());

        //отрисока текста
        if (myWorld.isReady()) {
            AssetLoader.shadow.draw(batcher, "Go", (136 / 2) - (10), 76);
            AssetLoader.font.draw(batcher, "Go", (136 / 2) - (10 + 1), 75);
        } else {

            if (myWorld.isGameOver() || myWorld.isHighScore()) {

                if (myWorld.isGameOver()) {
                    AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
                    AssetLoader.font.draw(batcher, "Game Over", 24, 55);

                    AssetLoader.shadow.draw(batcher, "High Score:", 23, 106);
                    AssetLoader.font.draw(batcher, "High Score:", 22, 105);

                    String highScore = AssetLoader.getHighScore() + "";

                    AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
                            - (3 * highScore.length()), 128);
                    AssetLoader.font.draw(batcher, highScore, (136 / 2)
                            - (3 * highScore.length() - 1), 127);
                } else {
                    AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
                    AssetLoader.font.draw(batcher, "High Score!", 18, 55);
                }

                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again?", 24, 75);

                // Конвертируем integer в String
                String score = myWorld.getScore() + "";

                AssetLoader.shadow.draw(batcher, score,
                        (136 / 2) - (3 * score.length()), 12);
                AssetLoader.font.draw(batcher, score,
                        (136 / 2) - (3 * score.length() - 1), 11);

            }

            String score = myWorld.getScore() + "";

            AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
                    - (3 * score.length()), 12);
            AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
                    - (3 * score.length() - 1), 11);

        }

        batcher.end();

    }
}
