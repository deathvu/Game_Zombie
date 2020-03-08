import java.awt.*;

public class Zombie extends GameObject {
    private Handler handler;
    private GameObject tmpPlayer = null;

    private float _acc = 0.09f;
    private float _dcc = 0.09f;

    public Zombie(float x, float y, ID id, GameObject tmpPlayer) {

        super(x, y, id);
        this.tmpPlayer = tmpPlayer;
    }


    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x > tmpPlayer.getX()) velX -= _acc;
        if (x < tmpPlayer.getX()) velX += _acc;
        if (y < tmpPlayer.getY()) velY += _acc;
        if (y > tmpPlayer.getY()) velY -= _acc;
        if (x == tmpPlayer.getX()) velX -= _dcc;
        if (y == tmpPlayer.getY()) velY -= _dcc;

//        float dx = x - tmpPlayer.getVelX();
//        float dy = y - tmpPlayer.getVelY();
//
//        doule angle = Math.atan2((tmpPlayer.y - y), (tmpPlayer.x - x));
//        double norm = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
//        velX += _acc * Math.cos(angle);
//        velY += _acc * Math.sin(angle);
//        double nx = (dx / norm);
//        double ny = (dy / norm);
//        x = (float) (_acc * nx);
//        y = (float) (_acc * ny);

    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int)y, 32, 32);
        Toolkit.getDefaultToolkit().sync();
    }
}
