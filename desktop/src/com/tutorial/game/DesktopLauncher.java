package com.tutorial.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tutorial.game.MemoryManagement.ExampleObjectPool;
import com.tutorial.game.ui.ExampleLayoutWidgets;
import com.tutorial.game.ui.WidgetsExample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static boolean enableGL32 = false;
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.GL32,3,2);

		config.setForegroundFPS(60);
		config.setWindowedMode(1080,720);
		config.setTitle("Tutorial Game");
		new Lwjgl3Application(new ExampleObjectPool(), config);
	}

	private static void readConsole(){
		try{
			System.out.println("Please select the GL version.");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String message = reader.readLine();
			if (message.equals("32")) enableGL32 = true;
		}catch (Exception exception){
			exception.printStackTrace();
		}
	}
}
