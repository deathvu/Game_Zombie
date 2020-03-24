import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Obstacle extends GameObject {
    private BufferedImage image;
    public Obstacle(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g)
    {
//        try{
//            // System.out.println(new File(".").getAbsolutePath());
//            image = ImageIO.read(new File("src/cat.jpg"));
//            g.drawImage(image, (int)x, (int)y, 50, 200,  null);
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, 50, 200);
    }
}
