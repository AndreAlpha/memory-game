package it.unimol.memory.gui;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unimol.memory.Board;
import it.unimol.memory.Card;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Gestisce la logica di interazione tra l'utente (View) e il gioco (Model).
 * Implementa le regole del turno e il controllo delle coppie.
 */
public class GameController implements ActionListener {

    private final Board board;

    // L'annotazione zittisce SpotBugs: sappiamo che GameFrame è mutabile,
    // ma in MVC il controller DEVE avere un riferimento alla view.
    @SuppressFBWarnings("EI_EXPOSE_REP2")
    private final GameFrame view;

    private Integer firstCardIndex = null;
    private boolean inputBlocked = false;

    /**
     * Costruisce il controller.
     *
     * @param board Il modello del gioco.
     * @param view  L'interfaccia grafica.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public GameController(Board board, GameFrame view) {
        this.board = board;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inputBlocked) {
            return; // Ignora i click durante l'animazione di errore
        }

        MemoryButton button = (MemoryButton) e.getSource();
        int index = button.getIndex();
        Card clickedCard = board.getCard(index);

        // Ignora click su carte già visibili o accoppiate
        if (clickedCard.isVisible() || clickedCard.isMatched()) {
            return;
        }

        // Logica di gioco
        clickedCard.setVisible(true);
        view.updateGrid(); // Aggiorna la grafica subito

        if (firstCardIndex == null) {
            // Primo click del turno
            firstCardIndex = index;
        } else {
            // Secondo click del turno
            handleSecondClick(index);
        }
    }

    private void handleSecondClick(int secondIndex) {
        Card firstCard = board.getCard(firstCardIndex);
        Card secondCard = board.getCard(secondIndex);

        if (firstCard.getId().equals(secondCard.getId())) {
            // MATCH!
            firstCard.setMatched();
            secondCard.setMatched();
            firstCardIndex = null; // Reset per il prossimo turno
            view.updateGrid();
            checkVictory();
        } else {
            // NO MATCH - Errore
            inputBlocked = true; // Blocca i click

            // Timer per nascondere le carte dopo 1 secondo
            Timer timer = new Timer(1000, event -> {
                firstCard.setVisible(false);
                secondCard.setVisible(false);
                firstCardIndex = null;
                inputBlocked = false; // Sblocca i click
                view.updateGrid();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkVictory() {
        if (board.isGameOver()) {
            Object[] options = { "Nuova partita", "Esci" };
            int choice = JOptionPane.showOptionDialog(
                    view,
                    "Complimenti! Hai vinto!",
                    "Vittoria!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == JOptionPane.YES_OPTION) {
                // Riavvia il gioco
                view.restartGame();
            } else {
                // Chiudi l'applicazione
                view.dispose();
            }
        }
    }
}