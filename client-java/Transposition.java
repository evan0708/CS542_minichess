/**
 * Created by evanchu on 6/3/16.
 */
public class Transposition {
    int score;
    int depth;
    boolean valid;
    chess.Bound bound;

    public Transposition() {
        this.score = 0;
        this.depth = 0;
        this.valid = true;
        this.bound = chess.Bound.EXACT;
    }

    public Transposition(int score, int depth, chess.Bound bound) {
        this.score = score;
        this.depth = depth;
        this.valid = true;
        this.bound = bound;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getScore() {
        return this.score;
    }

    public chess.Bound getBound() {
        return this.bound;
    }

}
