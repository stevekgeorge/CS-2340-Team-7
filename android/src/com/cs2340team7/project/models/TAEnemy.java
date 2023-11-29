package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TAEnemy extends Enemy { //TA enemies do not move.
    private int speed;
    public TAEnemy(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("taenemy.png");
        Texture texture = new Texture(fileHandle);
        FileHandle attackHandle = Gdx.files.internal("taenemyattacking.png");
        Texture attackTexture = new Texture(attackHandle);
    
        this.setSizeX(160);
        this.setSizeY(160);
        this.setEnemySprite(new Sprite(texture));
        this.setAttackSprite(new Sprite(attackTexture));
        this.getEnemySprite().setX(x);
        this.getEnemySprite().setY(y);
        this.getAttackSprite().setX(x);
        this.getAttackSprite().setY(y);
        this.getEnemySprite().setSize(this.getSizeX(), this.getSizeY());
        this.getAttackSprite().setSize(this.getSizeX(), this.getSizeY());
        this.speed = 0;
    }
    @Override
    public void move(Player.Direction direction) {
        //TAs do not move, you come to the TAs...
    }
}
