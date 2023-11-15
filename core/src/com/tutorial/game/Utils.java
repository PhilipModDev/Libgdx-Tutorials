package com.tutorial.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Utils {

    public static FileHandle getClasspath(String filepath){
        return Gdx.files.classpath(filepath);
    }


    public static FileHandle getInternalPath(String filepath){
        return Gdx.files.internal(filepath);
    }


    public static FileHandle getLocalPath(String filepath){
        return Gdx.files.local(filepath);
    }

    public static FileHandle getExternalPath(String filepath){
        return Gdx.files.external(filepath);
    }
}
