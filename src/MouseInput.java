import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private GameObject tmpPlayer = null;

    public void findPlayer(){
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                tmpPlayer = handler.objects.get(i);
                break;
            }
        }
    }

    public MouseInput(Handler handler) {
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if (tmpPlayer != null) {
        GameObject tmpBullet = handler.add(new SimpleBullet(tmpPlayer.x + 16, tmpPlayer.y + 16, ID.SimpleBullet));
        } else findPlayer();
    }
}
