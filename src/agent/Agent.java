package agent;

import question.Question;

import java.util.Random;

public interface Agent {
    int FIRST_POINT = 100;

    Random RANDOM = new Random();

    void choose(Question question);

    int point();

    void die();

    boolean dead();
}
