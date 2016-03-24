package parkingPlace;

import java.util.HashMap;

public class Token extends HashMap<Integer, Integer>{
    private int lotNo;
    private int position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        if (lotNo != token.lotNo) return false;
        return position == token.position;
    }

    public Token(int lotNo, int position) {
        this.put(lotNo, position);
    }
}
