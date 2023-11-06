package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy implements PlayerPositionSubscriber {
    public static enum EnemyType {
        LazySenior
    }
    int size_x;
    int size_y;
    int damage;
    GameDataModel model;
    Player player;

    Sprite sprite;

    public Enemy(){
        this.model = GameDataModel.getData();
        this.player = Player.getPlayer();
        player.addPlayerPositionSubscribers(this);

    }
    public abstract void move();

    public void updatePlayerPosition(Rectangle player_rect){
        if (sprite.getBoundingRectangle().overlaps((player_rect))){
            model.setCurrentHealth(model.getCurrentHealth() - damage);
            Gdx.app.log("MOVEMENT", "set max health called");
        }
        //add more to this method if you want the enemy to do different things when
        //the player moves
    }
    public Sprite getSprite(){
        return sprite;
    }
    public int getPos_x(){ return (int) sprite.getX();}
    public int getPos_y(){return (int) sprite.getY();}

}
//
//private boolean hasCollided(int location_x, int location_y, int size_x, int size_y){
//        //x,y start at bottom left corner
//        //bottom left side check
//        if (((this.getPos_x() >= location_x) && (this.getPos_x() <= location_x +size_x)) && ((this.getPos_y() >= location_y) && (this.getPos_y() <= location_y +size_y))){
//        return true;
//        }
//        //top left check
//        if (((this.getPos_x() >= location_x) && (this.getPos_x() <= location_x +size_x)) && ((this.getPos_y() + this.size_y >= location_y) && (this.getPos_y() + this.size_y <= location_y +size_y))){
//        return true;
//        }
//        //bottom right check
//        if (((this.getPos_x() +this.size_x >= location_x) && (this.getPos_x() +this.size_x <= location_x +size_x)) && ((this.getPos_y() >= location_y) && (this.getPos_y() <= location_y +size_y))){
//        return true;
//        }
//        //top left check
//        if (((this.getPos_x() +this.size_x >= location_x) && (this.getPos_x() +this.size_x <= location_x +size_x)) && ((this.getPos_y() +this.size_y >= location_y) && (this.getPos_y() +this.size_y <= location_y +size_y))){
//        return true;
//        }
//        return false;
