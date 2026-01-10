package it.unimol.memory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitari per la logica del tavolo di gioco.
 */
class BoardTest {

    @Test
    void testBoardInitializationSize() {
        // Creiamo una board 4x4 (16 carte)
        Board board = new Board(4, 4);
        assertEquals(16, board.getSize(), "La board dovrebbe avere 16 carte");
    }

    @Test
    void testOddDimensionsThrowException() {
        // Una board 3x3 ha 9 carte -> impossibile fare coppie
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(3, 3);
        }, "Dovrebbe lanciare eccezione per numero dispari di carte");
    }

    @Test
    void testGameIsNotOverAtStart() {
        Board board = new Board(2, 2);
        assertFalse(board.isGameOver(), "Il gioco non dovrebbe essere finito all'inizio");
    }
    
    @Test
    void testPairsAreCreated() {
        Board board = new Board(2, 1); // 2 carte: A e A
        Card c1 = board.getCard(0);
        Card c2 = board.getCard(1);
        
        // Essendo solo due carte e generate come coppie, devono avere lo stesso ID
        assertEquals(c1.getId(), c2.getId(), "Le uniche due carte dovrebbero essere una coppia");
    }
}