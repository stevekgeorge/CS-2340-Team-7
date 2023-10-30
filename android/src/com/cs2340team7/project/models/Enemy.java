package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Enemy implements PlayerPositionSubscriber {
    public static enum EnemyType {
        LazySenior
    }
    int pos_x;
    int pos_y;
    int size_x;
    int size_y;
    int damage;
    GameDataModel model;
    Player player;

    Texture texture;

    public Enemy(){
        this.model = GameDataModel.getData();
        this.player = Player.getPlayer();
        player.addPlayerPositionSubscribers(this);

    }
    public abstract void move();
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
    public void updatePlayerPosition(int location_x, int location_y, int size_x, int size_y){
        Gdx.app.log("MOVEMENT","updatePlayeer Pos called, player x y are" );
        Gdx.app.log("MOVEMENT", String.valueOf(location_x));
        Gdx.app.log("MOVEMENT", String.valueOf(location_y));
        if (hasCollided(location_x, location_y, size_x, size_y)){
            model.setCurrentHealth(model.getCurrentHealth() - damage);
            Gdx.app.log("MOVEMENT", "set max health called");
        }
        //add more to this method if you want the enemy to do different things when
        //the player moves
    }
    public Texture getTexture(){
        return texture;
    }
    public int getPos_x(){ return pos_x;}
    public int getPos_y(){return pos_y;}

}
