// Lab 4, CardShuffler.java
// Simple Swing program to display 52 card images from a "cards" folder
// Shuffle them when a button is clicked


import javax.swing.*;                     // Swing GUI components
import java.awt.*;                        // Layouts, Dimensions, Colors
import java.awt.event.*;                  // ActionListener (button clicks)
import java.io.File;                      // File handling
import java.io.IOException;               // IO exception for image reading
import java.util.*;                       // ArrayList, HashMap, Collections
import javax.imageio.ImageIO;             // Read images from files
import java.awt.image.BufferedImage;      // BufferedImage for image manipulation

public class CardShuffler {
    // Attributes
    private static final String CARDS_FOLDER = "cards"; // folder where your card image files live (relative to working dir)

    // desired width and height to scale images to (px)
    private static final int CARD_WIDTH = 90;
    private static final int CARD_HEIGHT = 130;
    private static final int COLUMNS = 13; 

    //  Runtime fields 
    private final ArrayList<String> deck = new ArrayList<>();  // list of filenames for the deck, e.g. "SA.jpg", "H10.jpg"
    private final Map<String, ImageIcon> iconCache = new HashMap<>();  // simple cache mapping filename
    private final ArrayList<JLabel> cardLabels = new ArrayList<>(); // list of JLabels that show icons in the grid 
    private JPanel cardPanel; // panel that holds the grid of card labels

    // Constructor
    public CardShuffler() {
        deck = new ArrayList<>();
        // create suits array
        String[] suits = {"S", "H", "C", "D"};
        // create value array
        String[] values = {"A", "2", "3","4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // loop through the suits and values and add each as a jpg file to the deck
        for(String suit : suits) {
            for(String value : values) {
                deck.add(suit + value + ".jpg"); // add each image to deck
            }
        }
    }

    // Methods
    // This method creates the GUI, the grid of labels, and the shuffle button
    public void createAndShowGUI() {
        // Create main window
        JFrame frame = new JFrame("Simple Card Shuffler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8, 8));

        // Calculate rows needed (52 cards / 13 columns = 4 rows)
        int rows = Math.max(1, (deck.size() + COLUMNS - 1) / COLUMNS);

        // Create a panel with GridLayout to place card images
        cardPanel = new JPanel(new GridLayout(rows, COLUMNS, 6, 6));
        cardPanel.setBackground(Color.GREEN); 

        // Preload icons into cache so we only do it once
        preloadIcons();

        // Create JLabels and add them to panel and list
        for (int i = 0; i < deck.size(); i++) {
            JLabel lbl = new JLabel();
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setBackground(Color.WHITE);
            lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            cardLabels.add(lbl);
            cardPanel.add(lbl);
        }

        // Show the initial order
        updateDisplay();

        // Put card panel in a scroll pane in case the window is small
        JScrollPane scroll = new JScrollPane(cardPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scroll, BorderLayout.CENTER);

        // Bottom panel with Shuffle button
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton shuffleBtn = new JButton("Shuffle");
        // When clicked, shuffle the deck and refresh the icons
        shuffleBtn.addActionListener(e -> {
            shuffleDeck();
            updateDisplay();
        });
        bottom.add(shuffleBtn);
        frame.add(bottom, BorderLayout.SOUTH);

        // Sets reasonable window size
        frame.setPreferredSize(new Dimension(Math.min(1400, COLUMNS * (CARD_WIDTH + 12)),
                                             Math.min(800, rows * (CARD_HEIGHT + 30))));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    public void shuffle() {
        // use collections built in shuffle to shuffle deck
        Collections.shuffle(deck);
    }

    public void displayCards() {

    }
    // Main Function
    public static void main(String[] args){
    }

}
