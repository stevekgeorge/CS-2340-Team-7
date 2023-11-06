package com.cs2340team7.project.models;

public class EnemyFactory {
    public static Enemy generateEnemy (int x, int y, Enemy.EnemyType type) {
        switch(type) {
            case BUZZ:
               BuzzEnemy buzzEnemy = new BuzzEnemy(x, y);
               Player.getPlayer().addPlayerPositionSubscribers(buzzEnemy);
               return buzzEnemy;
            case FRESHMEN:
                return new FreshmenEnemy(x, y);
            case SENIOR:
                SeniorEnemy seniorEnemy = new SeniorEnemy(x, y);
            Player.getPlayer().addPlayerPositionSubscribers(seniorEnemy);
            return seniorEnemy;
            case TA:
                return new TAEnemy(x, y);
            default:
                return null;
        }
    }
}
