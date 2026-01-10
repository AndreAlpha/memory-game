package it.unimol.memory.gui;

import javax.swing.JButton;

/**
 * Un bottone personalizzato che rappresenta una carta nella griglia.
 * Memorizza l'indice della carta per facilitare la gestione degli eventi.
 */
public class MemoryButton extends JButton {

    private final int index;

    /**
     * Crea un nuovo bottone per la carta.
     *
     * @param index L'indice della carta nella Board.
     */
    public MemoryButton(int index) {
        this.index = index;
        // Impostazioni grafiche di base
        this.setFocusPainted(false);
        this.setFont(this.getFont().deriveFont(24.0f)); // Font grande
    }

    /**
     * Restituisce l'indice della carta associata.
     *
     * @return l'indice intero.
     */
    public int getIndex() {
        return index;
    }
}