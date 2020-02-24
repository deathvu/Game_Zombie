import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick() {
        for (GameObject tmpObj : objects){
            tmpObj.tick();
        }
    }

    public void render(Graphics g) {
        for (GameObject tmpObj : objects){
            tmpObj.render(g);
        }
    }

    public void add(GameObject tmpObj){
        objects.add(tmpObj);
    }

    public void remove(GameObject tmpObj){
        objects.remove(tmpObj);
    }

}