package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Integer> computer = computerSelect();
        System.out.println("숫자 야구 게임을 시작합니다.");
        while (true) {
            List<Integer> client = new ArrayList<>();
            String num = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("숫자를 입력해주세요 : ");
            try {
                num = String.valueOf(br.readLine());
                if (num.length() != 3 || !num.matches("\\d{3}")) {
                    throw new IllegalArgumentException("게임 종료");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
            for (int i = 0; i < num.length(); i++) {
                client.add(i, Integer.parseInt(num.substring(i, i + 1)));
                try{
                    if(client.get(i).equals(0)){
                        throw new IllegalArgumentException("게임 종료");
                    }
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
            try {
                if (client.get(0).equals(client.get(1)) || client.get(0).equals(client.get(2)) || client.get(1).equals(client.get(2))) {
                    throw new IllegalArgumentException("게임 종료");
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                return;
            }
            int strike = 0;
            int ball = 0;
            for (int i = 0; i < client.size(); i++) {
                if (computer.get(i).equals(client.get(i))) {
                    strike++;
                }
                for (Integer client_ : client) {
                    if (computer.get(i).equals(client_)) {
                        ball++;
                    }
                }
            }
            if (ball != 0) {
                ball -= strike;
            }
            result(ball, strike);

            if (strike == 3) {
                int finalGame = 0;
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                try {
                    finalGame = Integer.parseInt(br.readLine());
                    if (finalGame != 1 && finalGame != 2) {
                        throw new IllegalArgumentException("오류가 발생해 게임을 종료합니다.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }

                if (finalGame == 2) {
                    System.out.println("게임 종료");
                    break;
                }else{
                    computer=computerSelect();
                }
            }
        }
    }


    private static void result(int ball, int strike) {
        if (ball == 0 && strike == 0) {
            System.out.println("낫싱");
        } else if (strike == 0) {
            System.out.println(ball + "볼");
        } else if (ball == 0) {
            System.out.println(strike + "스트라이크");
        } else {
            System.out.println(ball + "볼 " + strike + "스트라이크");
        }
    }


    private static List<Integer> computerSelect() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }
}
