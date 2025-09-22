// Lab 4, CardShuffler.java
// Simple Swing program to display 52 card images from a "cards" folder
// Shuffle them when a button is clicked


import javax.swing.*;                     // Swing GUI components
import java.awt.*;                        // Layouts, Dimensions, Colors
import java.awt.event.*;                  // ActionListener (button clicks)
import java.io.File;                      // File handling
import java.io.IOException;               // IO exception for image reading
import java.util.*;                       // ArrayList, HashMap, Collections

public class CardShuffler {
    // Attributes
    private static final String CARDS_FOLDER = "cards"; // folder where card image files are stored

    // desired width and height to scale images to (px)
    private static final int CARD_WIDTH = 90;
    private static final int CARD_HEIGHT = 125;
    
    private static final int COLUMNS = 13; // display grid columns: 13 columns produces 4 rows

    //  Runtime fields 
    private final ArrayList<String> deck = new ArrayList<>();  // list of filenames for the deck, "SA.jpg", "H10.jpg"
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
    // Build and show the GUI
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Card Shuffler — Simple (no preload)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8, 8)); // small gaps around components

        // Compute rows (52 cards / 13 columns -> 4 rows)
        int rows = Math.max(1, (deck.size() + COLUMNS - 1) / COLUMNS);
        cardPanel = new JPanel(new GridLayout(rows, COLUMNS, 6, 6));
        cardPanel.setBackground(Color.GREEN); // makes gaps visible

        // Create a JLabel for each card spot (icons will be loaded and set in updateDisplay())
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

        // Display images in initial (unshuffled) order
        updateDisplay();

        // Put cardPanel inside a JScrollPane in case window is small
        JScrollPane scroll = new JScrollPane(cardPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scroll, BorderLayout.CENTER);

        // Bottom panel with Shuffle button
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton shuffleBtn = new JButton("Shuffle");
        shuffleBtn.addActionListener(e -> {
            // Shuffle the filenames and reload the icons into labels
            Collections.shuffle(deck);
            updateDisplay();
        });
        bottom.add(shuffleBtn);
        frame.add(bottom, BorderLayout.SOUTH);

        // Set a reasonable window size and show
        frame.setPreferredSize(new Dimension(Math.min(1400, COLUMNS * (CARD_WIDTH + 14)),
                                             Math.min(800, rows * (CARD_HEIGHT + 32))));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    public void shuffle() {
        // use collections built in shuffle to shuffle deck
        Collections.shuffle(deck);
    }

    public void updateDisplay() {

    }
    // Main Function
    public static void main(String[] args){
    }

}
