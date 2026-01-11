package it.unimol.memory.gui;

import it.unimol.memory.Board;
import it.unimol.memory.Card;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * La finestra principale del gioco.
 * Visualizza la griglia delle carte.
 */
public class GameFrame extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(47, 79, 79); // Dark Slate Gray

    private final Board board;
    private final List<MemoryButton> buttons;

    /**
     * Inizializza la finestra di gioco.
     *
     * @param board Il modello del gioco da visualizzare.
     */
    public GameFrame(Board board) {
        this.board = board;
        this.buttons = new ArrayList<>();

        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        // Creiamo una griglia 4x4 (assumendo board da 16 carte)
        gridPanel.setLayout(new GridLayout(4, 4, 15, 15)); // 15px di spazio

        // Passaggio critico: Creiamo il controller PRIMA di aggiungere i bottoni
        // Ma il controller ha bisogno della view... usiamo this.
        GameController controller = new GameController(board, this);

        for (int i = 0; i < board.getSize(); i++) {
            MemoryButton button = new MemoryButton(i);
            button.addActionListener(controller); // Collega il bottone al cervello
            buttons.add(button);
            gridPanel.add(button);
        }

        this.add(gridPanel, BorderLayout.CENTER);
    }

    /**
     * Aggiorna lo stato visivo di tutti i bottoni in base al modello.
     * Chiamato dal Controller quando cambia qualcosa.
     */
    public void updateGrid() {
        for (int i = 0; i < board.getSize(); i++) {
            Card card = board.getCard(i);
            MemoryButton button = buttons.get(i);

            if (card.isMatched()) {
                button.setText(card.getId());
                button.setMatched();
                button.setEnabled(false);
            } else if (card.isVisible()) {
                button.setText(card.getId());
                button.setRevealed();
                button.setEnabled(false);
            } else {
                button.setText("");
                button.setCovered();
                button.setEnabled(true);
            }
        }
    }
}