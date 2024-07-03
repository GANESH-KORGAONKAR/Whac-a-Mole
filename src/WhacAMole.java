import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class WhacAMole {
    // Game panel dimensions
    int boardwidth = 600;
    int boardlength = 640; // 40px for the text panel on top

    JFrame frame = new JFrame("Mario : Whac A Mole");

    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardJPanel = new JPanel();

    JButton[] board = new JButton[9];

    ImageIcon moleIcon;
    ImageIcon plantIcon;

    JButton currMoleTile;
    JButton currPlantTile;

    Random random = new Random();
    Timer setMoleTimer;
    Timer setPlantTimer;

    int score = 0;
    int highScore = 0; // Variable to keep track of the high score

    WhacAMole() {
        // Set up the main frame properties
        frame.setSize(boardwidth, boardlength);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set up the text label to display the score
        textLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: 00");
        textLabel.setOpaque(true);

        // Add the text label to the text panel
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Set up the board panel with a 3x3 grid layout
        boardJPanel.setLayout(new GridLayout(3, 3));
        frame.add(boardJPanel);

        // Load and scale images for the mole and plant
        Image plantImg = new ImageIcon(getClass().getResource("./piranha.png")).getImage();
        plantIcon = new ImageIcon(plantImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

        Image moleImg = new ImageIcon(getClass().getResource("./monty.png")).getImage();
        moleIcon = new ImageIcon(moleImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

        // Initialize the game board with buttons
        for (int i = 0; i < 9; i++) {
            JButton tile = new JButton();
            board[i] = tile;
            boardJPanel.add(tile);
            tile.setFocusable(false);

            // Add action listener to each tile button
            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton tile = (JButton) e.getSource();
                    if (tile == currMoleTile) {
                        score += 10;
                        textLabel.setText("Score: " + Integer.toString(score));
                    } else if (tile == currPlantTile) {
                        // Update high score if current score is greater
                        if (score > highScore) {
                            highScore = score;
                        }
                        textLabel.setText("Game Over: " + Integer.toString(score) + " High Score: " + highScore);
                        setMoleTimer.stop();
                        setPlantTimer.stop();
                        for (int i = 0; i < 9; i++) {
                            board[i].setEnabled(false);
                        }
                        // Ask the player if they want to restart the game
                        int response = JOptionPane.showConfirmDialog(frame, "Game Over. Do you want to restart?", "Restart", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            restartGame();
                        }
                    }
                }
            });
        }

        // Timer for setting moles on the board
        setMoleTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove mole icon from current tile
                if (currMoleTile != null) {
                    currMoleTile.setIcon(null);
                    currMoleTile = null;
                }

                // Randomly select another tile for the mole
                int num = random.nextInt(9); // 0-8
                JButton tile = board[num];

                // Skip tile if occupied by plant
                if (currPlantTile == tile) return;

                // Set tile to mole
                currMoleTile = tile;
                currMoleTile.setIcon(moleIcon);
            }
        });

        // Timer for setting plants on the board
        setPlantTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove plant icon from current tile
                if (currPlantTile != null) {
                    currPlantTile.setIcon(null);
                    currPlantTile = null;
                }

                // Randomly select another tile for the plant
                int num = random.nextInt(9); // 0-8
                JButton tile = board[num];

                // Skip tile if occupied by mole
                if (currMoleTile == tile) return;

                // Set tile to plant
                currPlantTile = tile;
                currPlantTile.setIcon(plantIcon);
            }
        });

        // Start the timers
        setMoleTimer.start();
        setPlantTimer.start();
        frame.setVisible(true);
    }

    // Method to restart the game
    private void restartGame() {
        score = 0;
        textLabel.setText("Score: 00");
        for (int i = 0; i < 9; i++) {
            board[i].setEnabled(true);
            board[i].setIcon(null);
        }
        setMoleTimer.start();
        setPlantTimer.start();
    }
}
