package it.unimol.memory.gui;

import it.unimol.memory.Board;
import it.unimol.memory.Card;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * La finestra principale del gioco.
 * Visualizza la griglia delle carte.
 */
public class GameFrame extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(47, 79, 79); // Dark Slate Gray
    private static final Color TITLE_COLOR = new Color(255, 255, 240); // Ivory

    private final Board board;
    private final List<MemoryButton> buttons;
    private JLabel movesLabel;

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

        // Header con titolo
        JLabel titleLabel = new JLabel("MEMORY GAME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setForeground(TITLE_COLOR);
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(BACKGROUND_COLOR);
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        gridPanel.setLayout(new GridLayout(4, 4, 15, 15)); // 15px di spazio

        GameController controller = new GameController(board, this);

        for (int i = 0; i < board.getSize(); i++) {
            MemoryButton button = new MemoryButton(i);
            button.addActionListener(controller);
            buttons.add(button);
            gridPanel.add(button);
        }

        this.add(gridPanel, BorderLayout.CENTER);

        // Footer con contatore mosse
        movesLabel = new JLabel("Mosse: 0", SwingConstants.CENTER);
        movesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        movesLabel.setForeground(TITLE_COLOR);
        movesLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        movesLabel.setOpaque(true);
        movesLabel.setBackground(BACKGROUND_COLOR);
        this.add(movesLabel, BorderLayout.SOUTH);
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

    /**
     * Aggiorna il contatore mosse visualizzato.
     *
     * @param moves Il numero di mosse corrente.
     */
    public void updateMoves(int moves) {
        movesLabel.setText("Mosse: " + moves);
    }
}