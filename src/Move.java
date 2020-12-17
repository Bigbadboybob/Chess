
public class Move {
    
    private int c1;
    private int r1;
    private int c2;
    private int r2;
    private PieceType p;
    private boolean castle;
    
    //if a piece is taken
    private int c3;
    private int r3;
    private PieceType dead;
    
    public Move(int c1, int r1, int c2, int r2, PieceType p, int c3, int r3, PieceType dead, boolean castle) {
        this.c1 = c1;
        this.r1 = r1;
        this.c2 = c2;
        this.r2 = r2;
        this.p = p;
        
        this.c3 = c3;
        this.r3 = r3;
        this.dead = dead;
        this.castle = castle;
    }
    
    public String toString() {
        int piece = p.getPeice();
        String start = Integer.toString(c1) + "," + Integer.toString(r1);
        String end = Integer.toString(c2) + "," + Integer.toString(r2);
        String castleStr = Boolean.toString(castle);
        
        int d = dead.getPeice();
        String death = Integer.toString(c3) + "," + Integer.toString(r3);
        return piece + "," + start + "," + end +"," + d + "," + death + "," + castleStr + ",;";
    }
    
    public static Move fromString(String s) {
        
        int comma = -1;
        
        int nComma = s.indexOf(",", comma+1);
        String piece = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String c1Str = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String r1Str = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String c2Str = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String r2Str = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String dead = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String c3Str = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String r3Str = s.substring(comma+1, nComma);
        comma = nComma;
        
        nComma = s.indexOf(",", comma+1);
        String castleStr = s.substring(comma+1, nComma);
        comma = nComma;
        
        PieceType p = PieceType.valueOf(new Integer(piece));
        int c1 = new Integer(c1Str);
        int r1 = new Integer(r1Str);
        int c2 = new Integer(c2Str);
        int r2 = new Integer(r2Str);
        PieceType d = PieceType.valueOf(new Integer(dead));
        int c3 = new Integer(c3Str);
        int r3 = new Integer(r3Str);
        boolean castle = Boolean.valueOf(castleStr);
        
        
        Move m = new Move(c1, r1, c2, r2, p, c3, r3, d, castle);
        return m;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public PieceType getP() {
        return p;
    }

    public void setP(PieceType p) {
        this.p = p;
    }

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public int getR3() {
        return r3;
    }

    public void setR3(int r3) {
        this.r3 = r3;
    }

    public PieceType getDead() {
        return dead;
    }

    public void setDead(PieceType dead) {
        this.dead = dead;
    }
    
    public boolean getCastle() {
        return castle;
    }

    public void setCastle(boolean castle) {
        this.castle = castle;
    }
    
    

}
