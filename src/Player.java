import java.awt.*;

public class Player extends GameObject {

    public Player(float x, float y, ID id){
        super(x, y, id);

        velX = 1;
        velY = 1;
    }

    @Override
    public void tick() {
        x += velX;
        y = velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x, (int)y, 32, 32);
    }
}
