package com.cs2340team7.project.models;

public class EnemyFactory {
    public Enemy generateEnemy (int x, int y, EnemyType type) {
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
