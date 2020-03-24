import java.awt.*;
import java.awt.image.BufferStrategy;
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
        handler.add(new Obstacle(100, 500, ID.Block));
        handler.add(new Obstacle(200, 400, ID.Block));
        handler.add(new Obstacle(300, 300, ID.Block));
        handler.add(new Obstacle(400, 200, ID.Block));
        handler.add(player);
        handler.add(new Zombie(100, 200, ID.Zombie, player));
        handler.add(new Zombie(200, 200, ID.Zombie, player));
        handler.add(new Zombie(300, 200, ID.Zombie, player));
        handler.add(new Zombie(150, 200, ID.Zombie, player));
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

        // Meat and bones of rendering
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
