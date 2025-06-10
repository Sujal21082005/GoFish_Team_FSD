/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
package GoFish;
import GoFish.Card;
import java.util.*;

public class Player {
    private final String name;
    private final List<Card> cardList = new ArrayList<>();
    private final List<String> books = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public boolean rankOfCard(String rank) {
    for (Card card : cardList) {
        if (card.getRank().equals(rank)) {
            return true;
        }
    }
    return false;
}

    public void addCardToHand(Card card) {
        cardList.add(card);
    }

  

    public List<Card> removeCard(String rank) {
        List<Card> removed = new ArrayList<>();
        cardList.removeIf(card -> {
            if (card.getRank().equals(rank)) {
                removed.add(card);
                return true;
            }
            return false;
        });
        return removed;
    }

    public void checkForBooks() {
        List<String> rankCheck = new ArrayList<>();

    for (Card card : new ArrayList<>(cardList)) { 
        String rank = card.getRank();

        if (!rankCheck.contains(rank)) {
            int count = 0;
            for (Card c : cardList) {
                if (c.getRank().equalsIgnoreCase(rank)) {
                    count++;
                }
            }
            if (count == 4 && !books.contains(rank)) {
                books.add(rank);
                cardList.removeIf(c -> c.getRank().equalsIgnoreCase(rank));
                System.out.println(name + " completed a book of " + rank + "s!");
            }
            rankCheck.add(rank);
        }
   
    }
}
    
    public int getNumberOfBooks() {
        return books.size();
    }

    public List<Card> getCardInHand() {
        return cardList;
    }

    public List<String> getCompleteBooks() {
        return books;
    }

}

