import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {

    private float _acc = 1f;
    private float _dcc = 0.5f;
    private KeyInput input;


    public Player(float x, float y, ID id, KeyInput input){
        super(x, y, id);

        this.input = input;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        // horizontal movement logic
        // keys 0 = true : right
        // keys 1 = true : left
        if (input.keys[0]) velX += _acc;
        else if (input.keys[1]) velX -= _acc;
        else if (!input.keys[0] && !input.keys[1]) {
            if (velX > 0) velX -= _dcc;
            else if (velX < 0) velX += _dcc;
        }

        // vertical movement logic
        // keys 2 = true : up
        // keys 3 = true : down
        if (input.keys[2]) velY -= _acc;
        else if (input.keys[3]) velY += _acc;
        else if (!input.keys[2] && !input.keys[3]) {
            if (velY > 0) velY -= _dcc;
            else if (velY < 0) velY += _dcc;
        }

        velX = clamp(velX, 5, -5);
        velY = clamp(velY, 5, -5);
    }

    private float clamp (float value, float x, float y) {
        if (value >= x) value = x;
        else if (value <= y) value = y;

        return value;
    }

    public void render(Graphics g) {
        try{
            // System.out.println(new File(".").getAbsolutePath());
            BufferedImage image = ImageIO.read(new File("src/kan.jpg"));
            g.drawImage(image, (int)x, (int)y, 60, 60,  null);
        }catch (IOException e){
            e.printStackTrace();
        }
        //g.setColor(Color.blue);
        //g.fillRect((int) x, (int)y, 32, 32);
        Toolkit.getDefaultToolkit().sync();
    }
}
