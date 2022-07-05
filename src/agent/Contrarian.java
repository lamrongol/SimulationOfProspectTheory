package agent;

import question.Candidate;
import question.Kind;
import question.Question;

/**
 * This agent chooses an option according to the opposite law of Human
 * if there is a possibility of profit, choose a probabilistic points option,
 * and if there is a possibility of loss, choose a fixed points option.
 */
public class Contrarian implements Agent {
    private int point = FIRST_POINT;
    private boolean dead = false;

    @Override
    public void choose(Question question) {
        if (question.kind() == Kind.PROFIT) {
            var r = RANDOM.nextDouble();
            var probabilitySum = 0.0;
            for (Candidate candidate : question.candidateList()) {
                probabilitySum += candidate.probability();
                if (r < probabilitySum) {
                    point = point + candidate.point();
                    return;
                }
            }
        } else {
            point = point + question.fixedPoint();
            return;
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
