import java.awt.*;

public class Obstacle extends GameObject {
    public Obstacle(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 64, 64);
    }
}
