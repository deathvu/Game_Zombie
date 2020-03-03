import java.awt.*;

public class SimpleBullet extends GameObject {
    public SimpleBullet(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, 6,6);
        Toolkit.getDefaultToolkit().sync();
    }
}
