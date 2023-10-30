package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Enemy {
    private int pos_x;
    private int pos_y;
    private static final int size_x = 160;
    private static final int size_y = 160;
    private int health;
    private int damage;
    private GameDataModel model;
    private Player player;

    public Enemy(int x, int y) {
        this.pos_x = x;
        this.pos_y = y;
        if (model.getDifficulty() == "Easy") {
            this.health = 25;
        } else if (model.getDifficulty() == "Medium") {
            this.health = 50;
        } else {
            this.health = 100;
        }
        if (model.getDifficulty() == "Easy") {
            this.damage = 10;
        } else if (model.getDifficulty() == "Medium") {
            this.damage = 15;
        } else {
            this.damage = 25;
        }
        this.model = GameDataModel.getData();
        this.player = Player.getPlayer();
    }

    private boolean hasCollided(int location_x, int location_y, int size_x, int size_y){
        //x,y start at bottom left corner
        //bottom left side check
        if (((this.pos_x >= location_x) && (this.pos_x <= location_x +size_x)) && ((this.pos_y >= location_y) && (this.pos_y <= location_y +size_y))){
            return true;
        }
        //top left check
        if (((this.pos_x >= location_x) && (this.pos_x <= location_x +size_x)) && ((this.pos_y + this.size_y >= location_y) && (this.pos_y + this.size_y <= location_y +size_y))){
            return true;
        }
        //bottom right check
        if (((this.pos_x +this.size_x >= location_x) && (this.pos_x +this.size_x <= location_x +size_x)) && ((this.pos_y >= location_y) && (this.pos_y <= location_y +size_y))){
            return true;
        }
        //top left check
        if (((this.pos_x +this.size_x >= location_x) && (this.pos_x +this.size_x <= location_x +size_x)) && ((this.pos_y +this.size_y >= location_y) && (this.pos_y +this.size_y <= location_y +size_y))){
            return true;
        }
        return false;
    }

    public void updatePlayerPosition(int location_x, int location_y, int size_x, int size_y) {
        Gdx.app.log("MOVEMENT", "updatePlayeer Pos called, player x y are");
        Gdx.app.log("MOVEMENT", String.valueOf(location_x));
        Gdx.app.log("MOVEMENT", String.valueOf(location_y));
        if (hasCollided(location_x, location_y, size_x, size_y)) {
            model.setCurrentHealth(model.getCurrentHealth() - damage);
            Gdx.app.log("MOVEMENT", "set max health called");
        }
    }

    public static Enemy generateEnemy (int x, int y, TiledMap map, EnemyType type) {
        switch(type) {
            case BUZZ:
                return new BuzzEnemy(x, y, map);
            case FRESHMEN:
                return new FreshmenEnemy(x, y, map);
            case SENIOR:
                return new SeniorEnemy(x, y, map);
            case TA:
                return new TAEnemy(x, y, map);
            default:
                return null;
        }
    }

    public int getPos_x(){ return pos_x;}
    public int getPos_y(){return pos_y;}
}
