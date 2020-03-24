import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Game extends Canvas implements Runnable {

    public static int WIDTH = 800, HEIGHT = 600;
    public static String title = "Zombie Game";

    private boolean isRunning = false;
    private Thread thread;

    //Instances
    private Handler handler;
    private KeyInput input;
    private MouseInput mouseInput;
    private Camera camera;

    public Game(){
        //constructor
        new Window(WIDTH, HEIGHT, title, this);
        // start();
        init();
    }

    private void init(){
        handler = new Handler();
        input = new KeyInput();
        camera = new Camera(0,0, handler);
        mouseInput = new MouseInput(handler, camera);
        this.addKeyListener(input);
        this.addMouseListener(mouseInput);

        Player player = new Player(100, 100, ID.Player, input);

        Level_1 level_1 = new Level_1(player);

        for (Obstacle obstacle: level_1.obstacles) {
            handler.add(obstacle);
        }
        for (Zombie zombie: level_1.zombies) {
            handler.add(zombie);
        }

        handler.add(player);
        mouseInput.findPlayer();

    }

//    private synchronized void start(){
//        if (isRunning) return;
//        thread = new Thread(this);
//        thread.start();
//        isRunning = true;
//    }

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
        isRunning = true;
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
        camera.tick();
    }

    private void render() {
        // creating frames
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

//        try{
//            // System.out.println(new File(".").getAbsolutePath());
//            BufferedImage image = ImageIO.read(new File("src/grass.jpg"));
//            g.drawImage(image, 0, 0, WIDTH, HEIGHT,  null);
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        Toolkit.getDefaultToolkit().sync();

        g2d.translate(-camera.getX(), -camera.getY());

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());


        bs.show();
        g.dispose();
    }

    public static void main(String[] args) {

        MainMenuPanel menu = new MainMenuPanel();
        menu.setVisible(true);
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        // Let the game begin !
//        executorService.submit(new Game());
//        executorService.submit(new MusicPlayer("track"));
    }
}
