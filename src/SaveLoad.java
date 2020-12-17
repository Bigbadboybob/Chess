import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SaveLoad {
    
    public SaveLoad() {
    }
    
    public boolean save(String name, Chess c) {
        File file = new File("saves/"+name+".txt");
        try {
            if (!file.createNewFile()) {
                System.out.println("file already exists");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter saver = new PrintWriter(fw);
            //board
            for (int[] row : c.getBoard()) {
                String line = "";
                for (int col : row) {
                    line = line + col + ",";
                }
                saver.println(line);
            }
            //numTurns
            saver.println(c.getNumTurns());
            //white
            saver.println(c.isWhite());
            //gameOver
            saver.println(c.isGameOver());
            //mode
            saver.println(c.getMode());
            //selection
            if (c.getMode()) {
                String selection = "";
                for (Move m : c.getSelection()) {
                    selection = selection + m.toString();
                }
                //selection = selection.substring(0, selection.length()-1);
                saver.println(selection);
            } else {
                saver.println(" ");
            }
            //moveHistory
            String moveHistory = "";
            for (Move m : c.getMoveHistory()) {
                moveHistory = moveHistory + m.toString();
            }
            if (c.getMoveHistory().isEmpty()) {
                saver.println(" ");
            } else {
                //moveHistory = moveHistory.substring(0, moveHistory.length()-1);
                saver.println(moveHistory);
            }
            //redoMoves
            String redoMoves = "";
            for (Move m : c.getRedoMoves()) {
                redoMoves = redoMoves + m.toString();
            }
            if (c.getRedoMoves().isEmpty()) {
                saver.println(" ");
            } else {
                //redoMoves = redoMoves.substring(0, redoMoves.length()-1);
                saver.println(redoMoves);
            }
            
            saver.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        return false;
    }
    
    public Chess load(File file) {
        Chess chess = new Chess();
        BufferedReader fr;
        try {
            fr = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
        
        //board
        int[][] b = new int[8][8];
        for (int r = 0; r < 8; r++) {
            String line = null;
            try {
                line = fr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int comma = -1;
            for (int c = 0; c < 8; c++) {
                int nComma = line.indexOf(",", comma+1);
                String status = line.substring(comma+1, nComma);
                b[r][c] = new Integer(status);
                comma = nComma;
            }
            
        }
        chess.setBoard(b);
        //numTurns
        try {
            chess.setNumTurns(new Integer(fr.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //white
        try {
            chess.setWhite(Boolean.valueOf(fr.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //gameOver
        try {
            chess.setGameOver(Boolean.valueOf(fr.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mode
        try {
            chess.setMode(Boolean.valueOf(fr.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selection
        String sLine = null;
        try {
            sLine = fr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<Move> selection = new LinkedList<Move>();
        int semi = -1;
        while (sLine.indexOf(";", semi+1) != -1) {
            int nSemi = sLine.indexOf(";", semi+1);
            String status = sLine.substring(semi+1, nSemi);
            selection.add(Move.fromString(status));
            semi = nSemi;
        }
        chess.setSelection(selection);
        
      //moveHistory
        String mLine = null;
        try {
            mLine = fr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        LinkedList<Move> moves = new LinkedList<Move>();
        semi = -1;
        while (mLine.indexOf(";", semi+1) != -1) {
            int nSemi = mLine.indexOf(";", semi+1);
            String status = mLine.substring(semi+1, nSemi);
            moves.add(Move.fromString(status));
            semi = nSemi;
        }
        chess.setMoveHistory(moves);
        
      //redoMoves
        String rLine = null;
        try {
            rLine = fr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        LinkedList<Move> redos = new LinkedList<Move>();
        semi = -1;
        while (rLine.indexOf(";", semi+1) != -1) {
            int nSemi = rLine.indexOf(";", semi+1);
            String status = rLine.substring(semi+1, nSemi);
            redos.add(Move.fromString(status));
            semi = nSemi;
        }
        chess.setRedoMoves(redos);
        
        return chess;
    }

}
