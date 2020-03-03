import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {

    public CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();


    public Iterator<GameObject> iterator_toDo() {
        Iterator <GameObject> it = objects.iterator();
        return it;
    }

    public void tick() {
        Iterator<GameObject> it =  iterator_toDo();
        while (it.hasNext()){
            it.next().tick();
        }
    }

    public void render(Graphics g) {
        Iterator<GameObject> it =  iterator_toDo();
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
