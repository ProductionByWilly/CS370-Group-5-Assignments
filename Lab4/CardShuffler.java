import javax.swing.*; // Needed for GUI
import java.awt.*; // Needed for GUI
import java.awt.event.*; // Needed for clicking a button in the GUI
import java.util.ArrayList; // Needed for Array
import java.util.Collections; // Needed for Shuffle functionality

// Lab 4 CardShuffler Program
public class CardShuffler {
    // Attributes
    private ArrayList<String> deck; // ArrayList named deck to store

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
