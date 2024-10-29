package Card;

import java.util.ArrayList;
import java.util.List;

import javax.management.DescriptorKey;

public class Card {
    private Suit suit;
    private CardValue value;
    

    public Card(CardValue cardValue, Suit suit) {
        this.value = cardValue;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue cardValue) {
        this.value = cardValue;
        // return new Card(this.suit, cardValue)
    }

    // public Card drawCard() {
    //     List<Card> deck = new ArrayList<>();
    //     return deck.remove(deck.size() - 1);
    // }
    

    @Override
    public String toString() {
        return "[" + value + " of " + suit + "]";
    }
}
