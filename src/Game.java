import org.xml.sax.ErrorHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.security.Key;
import java.util.logging.ErrorManager;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 800, HEIGHT = 600;
    public static String title = "Zombie Game";

    private boolean isRunning = false;
    private Thread thread;

    //Instances
    private Handler handler;
    private KeyInput input;
    private MouseInput mouseInput;


    public Game() throws InterruptedException {
        //constructor
        new Window(WIDTH, HEIGHT, title, this);
        start();

        init();
    }

    private void init() throws InterruptedException {
        handler = new Handler();
        input = new KeyInput();
        mouseInput = new MouseInput(handler);
        this.addKeyListener(input);
        this.addMouseListener(mouseInput);

        handler.add(new Player(100, 100, ID.Player, input));
//        thread.wait(1000);
        mouseInput.findPlayer();
//        wait(1000);

    }

    private synchronized void start() throws InterruptedException {
        if (isRunning) return;

        thread = new Thread(this);
        thread.start();
//        thread.sleep(10000);
        isRunning = true;
    }


    private synchronized void stop(){
        if (!isRunning) return;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ammountOfTricks = 60.0;
        double ns = 1000000000 / ammountOfTricks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        // updating the game
        handler.tick();
    }

    private void render() {
        // creating frames
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Meat and bones of rendering
        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        Toolkit.getDefaultToolkit().sync();


        handler.render(g);

        bs.show();
        g.dispose();
    }

    public static void main(String[] args) throws InterruptedException {
        new Game();
    }
}
