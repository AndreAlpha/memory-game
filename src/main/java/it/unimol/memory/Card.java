package it.unimol.memory;

import java.util.Objects;

/**
 * Rappresenta una singola carta del gioco Memory.
 * Mantiene lo stato di visibilità e accoppiamento.
 *
 * @author Studente
 * @version 1.0
 */
public class Card {

    private final String id;
    private boolean visible;
    private boolean matched;

    /**
     * Crea una nuova carta con un identificativo specifico.
     *
     * @param id L'identificativo della carta (es. "A", "B").
     * Non deve essere nullo o vuoto.
     */
    public Card(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("L'ID della carta non può essere nullo o vuoto");
        }
        this.id = id;
        this.visible = false;
        this.matched = false;
    }

    /**
     * Restituisce l'ID della carta.
     *
     * @return L'identificativo.
     */
    public String getId() {
        return id;
    }

    /**
     * Verifica se la carta è attualmente visibile (scoperta).
     *
     * @return true se visibile, false altrimenti.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Verifica se la carta è stata accoppiata definitivamente.
     *
     * @return true se accoppiata.
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * Imposta la visibilità della carta.
     * Non ha effetto se la carta è già accoppiata.
     *
     * @param visible true per scoprire la carta, false per coprirla.
     */
    public void setVisible(boolean visible) {
        if (!this.matched) {
            this.visible = visible;
        }
    }

    /**
     * Segna la carta come accoppiata.
     * Una carta accoppiata rimane sempre visibile.
     */
    public void setMatched() {
        this.matched = true;
        this.visible = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}