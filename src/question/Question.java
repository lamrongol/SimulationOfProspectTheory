package question;

import java.util.ArrayList;

public record Question(Kind kind, int fixedPoint, ArrayList<Candidate> candidateList) {}
