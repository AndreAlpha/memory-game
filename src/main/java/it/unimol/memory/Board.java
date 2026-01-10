package it.unimol.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rappresenta il tavolo di gioco (la griglia).
 * Gestisce la creazione del mazzo, il mescolamento e l'accesso alle carte.
 *
 * @author Studente
 * @version 1.0
 */
public class Board {

    private final List<Card> cards;
    private final int size;

    /**
     * Costruisce un tavolo di gioco di dimensioni specificate.
     * Genera automaticamente le coppie di carte e le mescola.
     *
     * @param rows Numero di righe.
     * @param cols Numero di colonne.
     * @throws IllegalArgumentException se il numero totale di carte è dispari.
     */
    public Board(int rows, int cols) {
        this.size = rows * cols;
        if (this.size % 2 != 0) {
            throw new IllegalArgumentException("Il numero totale di carte deve essere pari");
        }
        this.cards = new ArrayList<>(size);
        initializeDeck();
    }

    private void initializeDeck() {
        int pairs = size / 2;
        // Usiamo caratteri ASCII partendo da 'A' per i simboli
        for (int i = 0; i < pairs; i++) {
            String symbol = String.valueOf((char) ('A' + i));
            // Aggiungiamo la coppia
            cards.add(new Card(symbol));
            cards.add(new Card(symbol));
        }
        Collections.shuffle(cards);
    }

    /**
     * Restituisce la carta in una specifica posizione (indice lineare).
     *
     * @param index L'indice della carta (da 0 a size-1).
     * @return L'oggetto Card.
     */
    public Card getCard(int index) {
        if (index < 0 || index >= cards.size()) {
            throw new IndexOutOfBoundsException("Indice carta non valido: " + index);
        }
        return cards.get(index);
    }

    /**
     * Restituisce il numero totale di carte sul tavolo.
     *
     * @return La dimensione del mazzo.
     */
    public int getSize() {
        return size;
    }

    /**
     * Verifica se tutte le carte sono state accoppiate.
     *
     * @return true se il gioco è finito, false altrimenti.
     */
    public boolean isGameOver() {
        return cards.stream().allMatch(Card::isMatched);
    }
}