package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.Texture;

public class Enemy {
    public static enum EnemyType {BUZZ, FRESHMEN, SENIOR, TA }
    private Texture texture;
    private int x;
    private int y;
    private static final int size_x = 160;
    private static final int size_y = 160;
    private int health;
    private int damage;
    private int speed;
    private GameDataModel model;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.model = GameDataModel.getData();
        if (model.getDifficulty() == "Easy") {
            this.health = 25;
            model.setEnemyHealth(25);
        } else if (model.getDifficulty() == "Medium") {
            this.health = 50;
            model.setEnemyHealth(50);
        } else {
            this.health = 100;
            model.setEnemyHealth(100);
        }
        if (model.getDifficulty() == "Easy") {
            this.damage = 10;
            model.setEnemyDamage(10);
        } else if (model.getDifficulty() == "Medium") {
            this.damage = 15;
            model.setEnemyDamage(15);
        } else {
            this.damage = 25;
            model.setEnemyDamage(25);
        }
    }

    public int getX(){ return x;}
    public int getY(){return y;}
    public Texture getTexture() {
        return texture;
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public GameDataModel getGameData() {
        return model;
    }
}
