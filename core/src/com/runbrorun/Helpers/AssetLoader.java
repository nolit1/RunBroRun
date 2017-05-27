package com.runbrorun.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


//все текстуры

public class AssetLoader {
    public static Texture texture;

    public static TextureRegion bg;

    public static Animation broAnimation;
    public static TextureRegion broTwo, broOne, broThree;

    public static TextureRegion thornsUp, thornsDown;
    public static Sound coin, death;
    public static Music music;

    public static BitmapFont font, shadow;//для шрифтов

    public static Preferences prefs; //эта штука позволяет хранить данные в файле


    public static void load() {

        texture = new Texture(Gdx.files.internal("data/texture3.png"));

        //благодаря этой штуке происходит нормальное масштабирование
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 54); //43
        bg.flip(false, true);


        broOne = new TextureRegion(texture, 136, 0, 17, 12);
        broOne.flip(false, true);

        broTwo = new TextureRegion(texture, 153, 0, 17, 12);
        broTwo.flip(false, true);

        broThree = new TextureRegion(texture, 170, 0, 17, 12);
        broThree.flip(false, true);

        TextureRegion[] bros = {broOne, broTwo, broThree};
        broAnimation = new Animation(0.06f, bros); //<>
        broAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        thornsUp = new TextureRegion(texture, 192, 0, 24, 14);
        thornsDown = new TextureRegion(thornsUp);
        thornsDown.flip(false, true);

        death = Gdx.audio.newSound(Gdx.files.internal("data/death.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt.txt"));
        font.getData().setScale(.25f,-.25f); //.
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt.txt"));
        shadow.getData().setScale(.25f,-.25f);

        music = Gdx.audio.newMusic(Gdx.files.internal("data/musicTheme.mp3"));

        prefs = Gdx.app.getPreferences("RunBro");


        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void dispose() {

        texture.dispose();
        death.dispose();
        coin.dispose();
        font.dispose();
        shadow.dispose();
    }

    // Ролучает на вход значение для hishScore и сохраняет в файл
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }
}
