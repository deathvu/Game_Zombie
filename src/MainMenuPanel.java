import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainMenuPanel extends JFrame{


    public MainMenuPanel () {
        super("Zombie Kan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 200, 200, 800);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Zombie Kan");
        JButton newGameButton = new JButton("New game");
        JButton settingsButton = new JButton("Settings");
        JButton creditsButton = new JButton("Credits");
        JButton exitButton = new JButton("Exit");

        panel.add(label);
        panel.add(newGameButton);
        panel.add(settingsButton);
        panel.add(creditsButton);
        panel.add(exitButton);

        add(panel);

        exitButton.addActionListener(new exitButtonEventListner());
        newGameButton.addActionListener(new newGameButtonEventListner());
    }

    class exitButtonEventListner implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            String message = "Exit pressed";
            dispose();
        }
    }

    class newGameButtonEventListner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            dispose();
            executorService.submit(new Game());
            executorService.submit(new MusicPlayer("track"));
        }
    }
}
