import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class UndoRedoTest {

    @Test
    public void undoTest() {
        Chess c = new Chess();
        Chess c2 = new Chess(c);
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m);
        c.undo();
        assertEquals(c, c2);
    }
    
    @Test
    public void undoToStartTest() {
        Chess c = new Chess();
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m);
        c.undo();
        Chess c2 = new Chess();
        assertArrayEquals(c.getBoard(), c2.getBoard());
        assertEquals(c.getCurrentPlayer(), c2.getCurrentPlayer());
        assertEquals(c.getMode(),c2.getMode());
        assertEquals(c.getMoveHistory(), c2.getMoveHistory());
        assertEquals(c.getNumTurns(), c2.getNumTurns());
        assertEquals(c.getSelection(), c2.getSelection());
    }
    
    @Test
    public void redoTest() {
        Chess c = new Chess();
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m);
        Chess c2 = new Chess(c);
        c.undo();
        c.redo();
        assertEquals(c, c2);
    }
    
    @Test
    public void redoUndoTest() {
        Chess c = new Chess();
        Chess c2 = new Chess(c);
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m);
        c.undo();
        c.redo();
        c.undo();
        assertEquals(c, c2);
    }
    
    @Test
    public void fourUndoTwoRedoTest() {
        Chess c = new Chess();
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m);
        Move m2 = new Move(2, 1, 2, 2, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m2);
        
        Chess c2 = new Chess(c);
        
        Move m3 = new Move(4, 1, 4, 2, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m3);
        Move m4 = new Move(2, 2, 2, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m4);
        c.undo();
        c.undo();
        c.undo();
        c.undo();
        c.redo();
        c.redo();
        assertEquals(c, c2);
    }
    
    @Test
    public void undoWithDeathTest() {
        Chess c = new Chess();
        Chess c2 = new Chess(c);
        //Note: this move is technically impossible
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, 2, 6, PieceType.PAWN_B, false);
        c.executeMove(m);
        c.undo();
        assertEquals(c, c2);
    }
    
    @Test
    public void redoWithDeathTest() {
        Chess c = new Chess();
        //Note: this move is technically impossible
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, 2, 6, PieceType.PAWN_B, false);
        c.executeMove(m);
        Chess c2 = new Chess(c);
        c.undo();
        c.redo();
        assertEquals(c, c2);
    }
    
    @Test
    public void undoEmptyTest() {
        Chess c = new Chess();
        Chess c2 = new Chess(c);
        c.undo();
        assertEquals(c, c2);
    }
    
    @Test
    public void redoEmptyTest() {
        Chess c = new Chess();
        Chess c2 = new Chess(c);
        c.redo();
        assertEquals(c, c2);
    }
    
    @Test
    public void doubleUndoEmptyTest() {
        Chess c = new Chess();
        Chess c2 = new Chess(c);
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m);
        c.undo();
        c.undo();
        assertEquals(c, c2);
    }
    
    @Test
    public void doubleRedoEmptyTest() {
        Chess c = new Chess();
        Move m = new Move(3, 1, 3, 3, PieceType.PAWN_W, -1, -1, PieceType.EMPTY, false);
        c.executeMove(m);
        Chess c2 = new Chess(c);
        c.undo();
        c.redo();
        c.redo();
        assertEquals(c, c2);
    }
}
