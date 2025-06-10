/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
package GoFish;

import java.util.*;

public class GameOfGoFish extends Game {
    private final List<Player> players = new ArrayList<>();
    private final Deck deck = new Deck();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void play() {
        System.out.println("Welcome to Go Fish!. Get ready to play");

        
        System.out.print("Enter the number of players (2–4): ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter name of Player " + i + ": ");
            players.add(new Player(scanner.nextLine()));
        }
        int cardsToDeal = (players.size() == 2) ? 6 : 3;
        for (int i = 0; i < cardsToDeal; i++) {
            for (Player player : players) {
                player.addCardToHand(deck.drawCard());
            }
        }

        int indexOfCurrentPlayer = 0;

        while (!deck.isEmpty() || !allBooksCompleted()) {
            Player currentPlayer = players.get(indexOfCurrentPlayer);
            System.out.println("\n" + currentPlayer.getName() + "'s turn.");
            showHand(currentPlayer);

            Player playerToTarget = choosePlayer(currentPlayer);
            System.out.print("Ask for a rank: ");
            String rank = scanner.nextLine();

            if (playerToTarget.rankOfCard(rank)) {
                System.out.println(playerToTarget.getName() + " has the cards!");
                List<Card> transferred = playerToTarget.removeCard(rank);
                for (Card c : transferred) {
                    currentPlayer.addCardToHand(c);
                }
            } else {
                System.out.println("Go Fish!");
                Card drawn = deck.drawCard();
                if (drawn != null) {
                    System.out.println("You drew: " + drawn);
                    currentPlayer.addCardToHand(drawn);
                }
            }

            currentPlayer.checkForBooks();

            if (!playerToTarget.rankOfCard(rank)) {
                indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % players.size();
            }
        }

        winnerIsAnnounced();
    }
    private Player choosePlayer(Player currentPlayer) {
        while (true) {
            System.out.print("Choose a player to ask: ");
            String name = scanner.nextLine();
            for (Player p : players) {
                if (!p.equals(currentPlayer) && p.getName().equalsIgnoreCase(name)) {
                    return p;
                }
            }
            System.out.println("Invalid player name. Try again.");
        }
    }

    @Override
    public void winnerIsAnnounced() {
        System.out.println("\nGame Over!");
        Player winner = players.get(0);
        for (Player p : players) {
            System.out.println(p.getName() + " has " + p.getNumberOfBooks() + " books.");
            if (p.getNumberOfBooks() > winner.getNumberOfBooks()) {
                winner = p;
            }
        }
        System.out.println("Winner: " + winner.getName());
    }

    private boolean allBooksCompleted() {
        int totalBooks = 0;
        for (Player p : players) {
            totalBooks += p.getNumberOfBooks();
        }
        return totalBooks == 13;
    }

    private void showHand(Player player) {
        System.out.println("Show Your hand");
        for (Card c : player.getCardInHand()) {
            System.out.println(" The card in hand is: " + c);
        }
    }

    

    public static void main(String[] args) {
        new GameOfGoFish().play();
    }

}

