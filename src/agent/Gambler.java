package agent;

import question.Candidate;
import question.Question;

/**
 * This agent always chooses a probabilistic points option
 */
public class Gambler implements Agent {
    private int point = FIRST_POINT;
    private boolean dead = false;

    @Override
    public void choose(Question question) {
        var r = RANDOM.nextDouble();
        var probabilitySum = 0.0;
        for (Candidate candidate : question.candidateList()) {
            probabilitySum += candidate.probability();
            if (r < probabilitySum) {
                point = point + candidate.point();
                return;
            }
        }
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