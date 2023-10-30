package com.cs2340team7.project.models;

public class EnemyFactory {
    public static Enemy generateEnemy(Enemy.EnemyType enemyType, int pos_x, int pos_y){
        // add more types here when they are made
        switch (enemyType){
            case LazySenior:
                return new LazySenior(pos_x,pos_y);
            default: return null;
        }
    }
}
