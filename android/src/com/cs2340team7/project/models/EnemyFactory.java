package com.cs2340team7.project.models;

public class EnemyFactory {
    public static Enemy generateEnemy (int x, int y, Enemy.EnemyType type) {
        switch(type) {
            case BUZZ:
               BuzzEnemy buzzEnemy = new BuzzEnemy(x, y);
               Player.getPlayer().addPlayerPositionSubscribers(buzzEnemy);
               return buzzEnemy;
            case FRESHMEN:
                FreshmenEnemy freshmenEnemy = new FreshmenEnemy(x, y);
                Player.getPlayer().addPlayerPositionSubscribers(freshmenEnemy);
                return freshmenEnemy;
            case SENIOR:
                SeniorEnemy seniorEnemy = new SeniorEnemy(x, y);
                Player.getPlayer().addPlayerPositionSubscribers(seniorEnemy);
                return seniorEnemy;
            case TA:
                TAEnemy taEnemy = new TAEnemy(x, y);
                Player.getPlayer().addPlayerPositionSubscribers(taEnemy);
                return taEnemy;
            default:
                return null;
        }
    }
}
