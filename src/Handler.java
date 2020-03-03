import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {

    public CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();

    public Iterator <GameObject> it = objects.iterator();

    public void tick() {
        while (it.hasNext()){
            it.next().tick();
        }
    }

    public void render(Graphics g) {
        while (it.hasNext()){
            it.next().render(g);
        }
    }

    public GameObject add(GameObject tmpObj){

        objects.add(tmpObj);
        return tmpObj;
    }

    public void remove(GameObject tmpObj){
        objects.remove(tmpObj);
    }

}
