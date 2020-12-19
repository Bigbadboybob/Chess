/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated.  Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework.  This
 * framework is very effective for turn-based games.  We STRONGLY 
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:  
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with 
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Chess c; // model for the game
    private JLabel status; // current status text
    private Pieces piece; //reusable piece which can be drawn in different forms for different pieces
    private SaveLoad sl;

    // Game constants
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        c = new Chess(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        piece = new Pieces(60, 60, 800, 800);
        sl = new SaveLoad();

        /*
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
        
        //TODO: create a piece selected mode.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                
                // updates the model given the coordinates of the mouseclick
                c.playTurn(p.x / 100, p.y / 100);
                
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        c.reset();
        updateStatus();
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    public void undo() {
        if (c.undo()) {
            updateStatus();
            repaint();
        }

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    public void redo() {
        if (c.redo()) {
            updateStatus();
            repaint();
        }

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    public void save() {
        String name = JOptionPane.showInputDialog("Name Save");
        if (name != null) {
            sl.save(name, c);
        }
    }
    
    public void load() {
        //code from https://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html
        JFileChooser chooser = new JFileChooser("saves/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "txt Files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(new JPanel());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            c = sl.load(chooser.getSelectedFile());
            updateStatus();
            repaint();
        }
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (c.getCurrentPlayer()) {
            status.setText("White's Turn");
        } else {
            status.setText("Black's Turn");
        }
        
        int winner = c.checkWinner();
        System.out.println(winner);
        if (winner == 1) {
            status.setText("White wins!!!");
        } else if (winner == 2) {
            status.setText("Black wins!!!");
        } else if (winner == 3) {
            status.setText("Stalemate.");
        }
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board.  This approach
     * will not be sufficient for most games, because it is not 
     * modular.  All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper 
     * methods.  Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draws board grid
        Color b = Color.getHSBColor(0.28f, 0.4f, 0.6f);
        Color w = Color.getHSBColor(0.14f, 0.17f, 0.85f);
        for (int i = 0; i < 8; i++) {
            if (g.getColor() == b) {
                g.setColor(w);
            } else {
                g.setColor(b);
            }
            
            for (int j = 0; j < 8; j++) {
                if (g.getColor() == b) {
                    g.setColor(w);
                } else {
                    g.setColor(b);
                }
                g.fillRect(i*100, j*100, 100, 100);
            }
        }
        
        // Draws Chess pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int state = c.getCell(j, i);
                switch(state) {
                    case 1:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.PAWN_W);
                        break;
                    case 2:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.ROOK_W);
                        break;
                    case 3:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.BISHOP_W);
                        break;
                    case 4:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.KNIGHT_W);
                        break;
                    case 5:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.QUEEN_W);
                        break;
                    case 6:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.KING_W);
                        break;
                    case 7:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.PAWN_B);
                        break;
                    case 8:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.ROOK_B);
                        break;
                    case 9:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.BISHOP_B);
                        break;
                    case 10:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.KNIGHT_B);
                        break;
                    case 11:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.QUEEN_B);
                        break;
                    case 12:
                        piece.draw(g, 20 + 100*j, 20 + 100*i, PieceType.KING_B);
                        break;
                
                }
            }
        }
        //Highlight possible moves
        if (c.getMode()) {
            for (Move m : c.getSelection()) {
                int col = m.getC2();
                int row = m.getR2();
                Color highlight = new Color(150, 150, 0, 127);
                g.setColor(highlight);
                g.fillRect(col*100, row*100, 100, 100);
            }
        }
        
        
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}