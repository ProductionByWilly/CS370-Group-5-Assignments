// Lab 4, CardShuffler.java
// Simple Swing program to display 52 card images from a "cards" folder
// Shuffle them when a button is clicked

import javax.smartcardio.Card;
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
        JFrame frame = new JFrame("Card Shuffler"); // creates the main window frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets what happens when the user clicks exit
        frame.setLayout(new BorderLayout(8, 8)); // small gaps around components

        // Compute rows 
        int rows = Math.max(1, (deck.size() + COLUMNS - 1) / COLUMNS); // calculates how many rows are needed for the grid
        cardPanel = new JPanel(new GridLayout(rows, COLUMNS, 6, 6)); // creates panel with grid layout, 4 rows, 13 columns, 6 pixel gap 
        cardPanel.setBackground(Color.GREEN); // makes background green


        for (int i = 0; i < deck.size(); i++) {   // loops through all 52 cards to create a JLabel
            JLabel lbl = new JLabel();  //  creates an empty label 
            lbl.setHorizontalAlignment(SwingConstants.CENTER); // centers the image horizontally
            lbl.setVerticalAlignment(SwingConstants.CENTER);   // centers the image vertically
            lbl.setOpaque(true);  // makes the label see through so background color is shown
            lbl.setBackground(Color.WHITE);  // sets background color of each card to white
            lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));  // adds a thin light gray boarder for each card
            cardLabels.add(lbl);  // adds label to our arrayList so we can reference it later
            cardPanel.add(lbl);  // adds the label to the visual grid panel
        }

        updateDisplay();  // loads the card images into each label

        // Put cardPanel inside a JScrollPane in case window is small
        JScrollPane scroll = new JScrollPane(cardPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // wraps the panel in a scroll plane (needed if content is larger than the window)
        frame.add(scroll, BorderLayout.CENTER);  // adds the scroll pane to the center region of the frame's BorderLayout

        // Bottom panel with Shuffle button
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER)); // creates a panel for the bottom area using FlowLayout
        JButton shuffleBtn = new JButton("Shuffle"); // creates a button with the text "shuffle"
        shuffleBtn.addActionListener(e -> {
            Collections.shuffle(deck); // when clicked it shuffles the deck ArrayList randomly, then updates the display to show new order
            updateDisplay();  // shows the updated display
        });
        bottom.add(shuffleBtn); // adds the button to the bottom panel
        frame.add(bottom, BorderLayout.SOUTH); // adds the bottom panel to the south region of the frame

        // Set a reasonable window size and show
        frame.setPreferredSize(new Dimension(Math.min(1400, COLUMNS * (CARD_WIDTH + 14)),
                                             Math.min(800, rows * (CARD_HEIGHT + 32))));  // calculates preferred window size based on card dimensions plus padding
        frame.pack();  // sizes the window to fit its preferred size (calculated above)
        frame.setLocationRelativeTo(null);  // centers the window on the screen
        frame.setVisible(true); // makes the window visible to the user
    }


    public void updateDisplay() {

        for (int i = 0; i < deck.size(); i++) { // loop through the deck
            String fileName = deck.get(i); // create filename with contents from deck
            File imgFile = new File(CARDS_FOLDER, fileName); // create imgFile with fileName from deck
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());

                Image scaled = icon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                cardLabels.get(i).setIcon(new ImageIcon(scaled));
            }
            else {
                cardLabels.get(i).setText("X");
            }
            cardPanel.revalidate();
            cardPanel.repaint();
        }
    }
    // Main Function
    public static void main(String[] args){
        CardShuffler d = new CardShuffler();
        d.createAndShowGUI();
    }

}
