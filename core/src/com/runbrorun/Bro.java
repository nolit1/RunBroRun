package com.runbrorun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.runbrorun.Screens.GameScreen;
import com.runbrorun.Helpers.AssetLoader;

public class Bro extends Game {

	@Override
	public void create() {
		Gdx.app.log("RunBro", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
