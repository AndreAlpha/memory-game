package it.unimol.memory.gui;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * Un bottone personalizzato che rappresenta una carta nella griglia.
 * Memorizza l'indice della carta per facilitare la gestione degli eventi.
 */
public class MemoryButton extends JButton {

    private final int index;

    // Colori per i diversi stati
    private static final Color COVERED_COLOR = new Color(70, 130, 180); // Steel Blue
    private static final Color REVEALED_COLOR = new Color(255, 255, 240); // Ivory
    private static final Color MATCHED_COLOR = new Color(144, 238, 144); // Light Green
    private static final Color BORDER_COLOR = new Color(47, 79, 79); // Dark Slate Gray
    private static final Color TEXT_COLOR = new Color(25, 25, 112); // Midnight Blue

    /**
     * Crea un nuovo bottone per la carta.
     *
     * @param index L'indice della carta nella Board.
     */
    public MemoryButton(int index) {
        this.index = index;
        // Impostazioni grafiche di base
        this.setFocusPainted(false);
        this.setFont(this.getFont().deriveFont(32.0f));
        this.setBorder(new LineBorder(BORDER_COLOR, 3, true));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setForeground(TEXT_COLOR);

        // Stato iniziale: carta coperta
        setCovered();
    }

    /**
     * Restituisce l'indice della carta associata.
     *
     * @return l'indice intero.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Imposta lo stile per carta coperta.
     */
    public void setCovered() {
        this.setBackground(COVERED_COLOR);
        this.setOpaque(true);
    }

    /**
     * Imposta lo stile per carta scoperta.
     */
    public void setRevealed() {
        this.setBackground(REVEALED_COLOR);
    }

    /**
     * Imposta lo stile per carta accoppiata.
     */
    public void setMatched() {
        this.setBackground(MATCHED_COLOR);
    }
}