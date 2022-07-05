import agent.Contrarian;
import agent.Coward;
import agent.Gambler;
import agent.Human;
import question.Questioner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Generate players
        final int FIRST_PLAYER_NUM = (int) Math.pow(2, 24);//16,777,216

        int humanNum = FIRST_PLAYER_NUM;
        int contrarianNum = FIRST_PLAYER_NUM;
        int cowardNum = FIRST_PLAYER_NUM;
        int gamblerNum = FIRST_PLAYER_NUM;

        var humanList = new ArrayList<Human>() {{
            for (int i = 0; i < FIRST_PLAYER_NUM; i++) {
                add(new Human());
            }
        }};

        var contrarianList = new ArrayList<Contrarian>() {{
            for (int i = 0; i < FIRST_PLAYER_NUM; i++) {
                add(new Contrarian());
            }
        }};

        var cowardList = new ArrayList<Coward>() {{
            for (int i = 0; i < FIRST_PLAYER_NUM; i++) {
                add(new Coward());
            }
        }};

        var gamblerList = new ArrayList<Gambler>() {{
            for (int i = 0; i < FIRST_PLAYER_NUM; i++) {
                add(new Gambler());
            }
        }};

        //Simulation
        File survivorNumFile = new File("survivor_num.tsv");
        File pointSumFile = new File("points_sum.tsv");
        try (FileWriter survivorNumFileWriter = new FileWriter(survivorNumFile);
             FileWriter pointSumFileWriter = new FileWriter(pointSumFile)) {
            survivorNumFileWriter.write("Count\tHuman\tContrarian\tCoward\tGambler\n");
            pointSumFileWriter.write("Count\tHuman\tContrarian\tCoward\tGambler\n");

            for (int i = 0; i < 1000; i++) {
                long humanPointSum = 0;
                long contrarianPointSum = 0;
                long cowardPointSum = 0;
                long gamblerPointSum = 0;

                for (int j = 0; j < FIRST_PLAYER_NUM; j++) {
                    if (humanNum > 0) {
                        var human = humanList.get(j);
                        if (!human.dead()) {
                            var question = Questioner.question();
                            human.choose(question);
                            if (human.point() <= 0) {
                                human.die();
                                humanNum--;
                            } else humanPointSum += human.point();
                        }
                    }
                    if (contrarianNum > 0) {
                        var contrarian = contrarianList.get(j);
                        if (!contrarian.dead()) {
                            var question = Questioner.question();
                            contrarian.choose(question);
                            if (contrarian.point() <= 0) {
                                contrarian.die();
                                contrarianNum--;
                            } else contrarianPointSum += contrarian.point();
                        }
                    }
                    if (cowardNum > 0) {
                        var coward = cowardList.get(j);
                        if (!coward.dead()) {
                            var question = Questioner.question();
                            coward.choose(question);
                            if (coward.point() <= 0) {
                                coward.die();
                                cowardNum--;
                            } else cowardPointSum += coward.point();
                        }
                    }
                    if (gamblerNum > 0) {
                        var gambler = gamblerList.get(j);
                        if (!gambler.dead()) {
                            var question = Questioner.question();
                            gambler.choose(question);
                            if (gambler.point() <= 0) {
                                gambler.die();
                                gamblerNum--;
                            } else gamblerPointSum += gambler.point();
                        }
                    }

                    if (humanNum == 0 && contrarianNum == 0 && cowardNum == 0 && gamblerNum == 0)
                        break;
                }
                survivorNumFileWriter.write((i + 1) + "\t" + humanNum + "\t" + contrarianNum + "\t" + cowardNum + "\t" + gamblerNum + "\n");
                pointSumFileWriter.write((i + 1) + "\t" + humanPointSum + "\t" + contrarianPointSum + "\t" + cowardPointSum + "\t" + gamblerPointSum + "\n");
                if (humanNum == 0 && contrarianNum == 0 && cowardNum == 0 && gamblerNum == 0)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
