package com.cs2340team7.project.models;

public class EnemyFactory {
    public static Enemy generateEnemy (int x, int y, Enemy.EnemyType type) {
        switch(type) {
            case BUZZ:
                return new BuzzEnemy(x, y);
            case FRESHMEN:
                return new FreshmenEnemy(x, y);
            case SENIOR:
                return new SeniorEnemy(x, y);
            case TA:
                return new TAEnemy(x, y);
            default:
                return null;
        }
    }
}
