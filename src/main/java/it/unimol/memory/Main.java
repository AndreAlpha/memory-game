package it.unimol.memory;

import it.unimol.memory.gui.GameFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Entry point del gioco Memory.
 */
public class Main {

    /**
     * Imposta il Look and Feel Nimbus se disponibile.
     */
    private static void setNimbusLookAndFeel() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception ignored) {
                    assert true;
                }
                break;
            }
        }
    }

    /**
     * Metodo principale.
     * Avvia l'interfaccia grafica.
     *
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        setNimbusLookAndFeel();

        SwingUtilities.invokeLater(() -> {
            Board board = new Board(4, 4);
            GameFrame frame = new GameFrame(board);
            frame.setVisible(true);
        });
    }
}