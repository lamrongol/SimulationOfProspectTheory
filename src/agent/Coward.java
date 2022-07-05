package agent;

import question.Question;

/**
 * This agent always chooses a fixed points option
 */
public class Coward implements Agent {
    private int point = FIRST_POINT;
    private boolean dead = false;

    @Override
    public void choose(Question question) {
        point = point + question.fixedPoint();
        return;
    }

    @Override
    public int point() {
        return point;
    }

    @Override
    public void die() {
        dead = true;
    }

    @Override
    public boolean dead() {
        return dead;
    }

    @Override
    public String toString() {
        return "point=" + point;
    }
}
