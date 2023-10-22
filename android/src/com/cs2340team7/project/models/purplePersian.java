//package com.cs2340team7.project.models;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.Game;
//import com.cs2340team7.project.views.Klaus;
//import com.cs2340team7.project.views.Skiles;
//import com.cs2340team7.project.views.TechGreen;
//
//public class purplePersian implements movementStrategy {
//
//
//    private static int SPEED = 1;
//    Player player;
//    GameDataModel dataModel;
//    movementStrategy origin;
//
//
//
//    @Override
//    public void move(Object object) {
//
//        if(object instanceof TechGreen) {
//            TechGreen origin = (TechGreen) object;
//        } else if (object instanceof Skiles) {
//            Skiles origin = (Skiles) object;
//        } else if (object instanceof Klaus) {
//            Klaus origin = (Klaus) object;
//        }
//        if (Gdx.input.isKeyPressed(Keys.UP)) {
//            origin.setSprite(player.getX(), player.getY() + SPEED);
//        } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
//            player.updatePosition(player.getX(), player.getY() - SPEED);
//        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
//            player.updatePosition(player.getX() + SPEED, player.getY());
//        } else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
//            player.updatePosition(player.getX() - SPEED, player.getY());
//        }
//    }
//
////    public void move(KeyEvent keyEvent) {
////        int keyCode = keyEvent.getKeyCode();
////        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
////            player.updatePosition(player.getX(), player.getY() + SPEED);
////        } else if (keyCode == keyEvent.KEYCODE_DPAD_DOWN) {
////            player.updatePosition(player.getX(), player.getY() - SPEED);
////        } else if (keyCode == keyEvent.KEYCODE_DPAD_RIGHT) {
////            player.updatePosition(player.getX() + SPEED, player.getY());
////        } else if (keyCode == keyEvent.KEYCODE_DPAD_LEFT) {
////            player.updatePosition(player.getX() - SPEED, player.getY());
////        }
////    }
//}
