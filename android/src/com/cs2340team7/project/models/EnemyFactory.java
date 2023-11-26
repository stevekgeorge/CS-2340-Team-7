package com.cs2340team7.project.models;

/**
 * The EnemyFactory class is responsible for generating different types of enemies
 * based on the specified EnemyType. It provides a static method to create and
 * configure enemy instances with the given coordinates.
 */
public class EnemyFactory {

    /**
     * Generates an enemy based on the provided type and coordinates.
     *
     * @param x The x-coordinate of the enemy.
     * @param y The y-coordinate of the enemy.
     * @param type The type of enemy to generate (BUZZ, FRESHMEN, SENIOR, TA).
     * @return An instance of the generated enemy.
     */
    public static Enemy generateEnemy(int x, int y, Enemy.EnemyType type) {
        switch (type) {
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
