package it.unimol.memory;

import it.unimol.memory.gui.GameFrame;
import javax.swing.SwingUtilities;

/**
 * Entry point del gioco Memory.
 */
public class Main {
    /**
     * Metodo principale.
     * Avvia l'interfaccia grafica.
     *
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        // Avviamo la GUI nel thread corretto di Swing
        SwingUtilities.invokeLater(() -> {
            // 1. Creiamo il Modello (4x4 = 16 carte)
            Board board = new Board(4, 4);
            
            // 2. Creiamo la View (che crea internamente il Controller)
            GameFrame frame = new GameFrame(board);
            
            // 3. Mostriamo la finestra
            frame.setVisible(true);
        });
    }
}