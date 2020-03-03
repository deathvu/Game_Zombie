public class Camera {
    private int x, y;
    private Handler handler;
    private GameObject tmpPlayer = null;

    public Camera(int x, int y, Handler handler){
        this.x = x;
        this.y = y;
        this.handler = handler;

        findPlayer();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void findPlayer(){
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                tmpPlayer = handler.objects.get(i);
                break;
            }
        }
    }

    public void tick() {
        if(tmpPlayer != null) {
            x = (int)tmpPlayer.x - Game.WIDTH/2;
            y = (int)tmpPlayer.y - Game.HEIGHT/2;
        }else findPlayer();
    }
}
