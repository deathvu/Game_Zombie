import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Camera camera;
    private GameObject tmpPlayer = null;

    public void findPlayer(){
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                tmpPlayer = handler.objects.get(i);
                break;
            }
        }
    }

    public MouseInput(Handler handler, Camera camera) {
        this.handler = handler;
        this.camera = camera;
    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if (tmpPlayer != null) {
            GameObject tmpBullet = handler.add(new SimpleBullet(tmpPlayer.x + 16, tmpPlayer.y + 16, ID.SimpleBullet));

            float angle = (float) Math.atan2(my - tmpPlayer.y - 16 + camera.getY(), mx - tmpPlayer.x - 16 + camera.getX());
            int bulletVelocity = 10;
            tmpBullet.velX = (float) ((bulletVelocity) * Math.cos(angle));
            tmpBullet.velY = (float) ((bulletVelocity) * Math.sin(angle));

        } else findPlayer();
    }
}
