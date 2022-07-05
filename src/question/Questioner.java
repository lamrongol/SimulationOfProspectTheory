package question;

import java.util.ArrayList;
import java.util.Random;

public class Questioner {
    private static final Random RANDOM = new Random();

    public static Question question() {
        int absOfExpectedValue = (int) (10 + 10 * (RANDOM.nextDouble() - 0.5));
        //Profitable options with a 50% chance
        if (RANDOM.nextDouble() < 0.50) {
            final ArrayList<Candidate> PROFIT_CANDIDATE_LIST = new ArrayList<>() {
                {
                    add(new Candidate(0.50, absOfExpectedValue * 2));
                    add(new Candidate(0.50, 0));
                }
            };
            return new Question(Kind.PROFIT, absOfExpectedValue, PROFIT_CANDIDATE_LIST);
        } else {
            final ArrayList<Candidate> LOSS_CANDIDATE_LIST = new ArrayList<>() {
                {
                    add(new Candidate(0.50, -absOfExpectedValue * 2));
                    add(new Candidate(0.50, 0));
                }
            };

            return new Question(Kind.LOSS, -absOfExpectedValue, LOSS_CANDIDATE_LIST);
        }
    }
}
