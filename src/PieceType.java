import java.util.TreeMap;
import java.util.Map;

public enum PieceType {
    
    PAWN_W(1),
    ROOK_W(2),
    BISHOP_W(3),
    KNIGHT_W(4),
    QUEEN_W(5),
    KING_W(6),
    
    PAWN_B(7),
    ROOK_B(8),
    BISHOP_B(9),
    KNIGHT_B(10),
    QUEEN_B(11),
    KING_B(12),
    
    EMPTY(0);
    
    private final int val;
    
    PieceType(int v) {
        this.val = v;
    }
    
    public int getPeice() {
        return val;
    }
    
    private static Map<Integer, PieceType> map = new TreeMap<Integer, PieceType>();

    static {
        for (PieceType p : PieceType.values()) {
            map.put(p.val, p);
        }
    }

    public static PieceType valueOf(int i) {
        return map.get(i);
    }
    
    public boolean isBlack() {
        if (val >= 7) {
            return true;
        } else if (val < 0) {
            return false;
        } else {
            new IllegalArgumentException();
            return false;
        }
    }
    public boolean isWhite() {
        if (val < 7 && val > 0) {
            return true;
        } else if (val >= 7) {
            return false;
        } else {
            new IllegalArgumentException();
            return false;
        }
    }
    
    public static boolean isBlack(int i) {
        if (i >= 7) {
            return true;
        } else if (i < 0) {
            return false;
        } else {
            new IllegalArgumentException();
            return false;
        }
    }
    
    
}
