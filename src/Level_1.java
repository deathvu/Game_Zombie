import java.util.ArrayList;

public class Level_1 {
    public ArrayList<Obstacle> obstacles;
    public ArrayList<Zombie> zombies;

    public Level_1(Player player) {
        obstacles = new ArrayList<>();
        zombies = new ArrayList<>();

        obstacles.add(new Obstacle(100, 1000, ID.Block));
        obstacles.add(new Obstacle(100, 800, ID.Block));
        obstacles.add(new Obstacle(100, 600, ID.Block));
        obstacles.add(new Obstacle(100, 400, ID.Block));
        obstacles.add(new Obstacle(100, 200, ID.Block));

        zombies.add(new Zombie(100, 200, ID.Zombie, player));
        zombies.add(new Zombie(200, 200, ID.Zombie, player));
        zombies.add(new Zombie(300, 200, ID.Zombie, player));
        zombies.add(new Zombie(150, 200, ID.Zombie, player));
    }
}
