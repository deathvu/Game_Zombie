import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 1200, HEIGHT = 600;
    public static String title = "Zombie Game";

    private boolean isRunning = false;
    private Thread thread;

    public Game(){
        //constructor
        new Window(WIDTH, HEIGHT, title, this);
        start();
    }

    private synchronized void start(){
        if (isRunning) return;

        thread = new Thread(this);
        thread.start();
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
        g.setColor(Color.red);
        g.fillRect(0, 0, WIDTH, HEIGHT);



        bs.show();
        g.dispose();
    }

    public static void main(String[] args) {
        new Game();
    }
}
