package com.example.AlgorithmPractice.programmers;

public class Carpet {

    private int yellow;
    private int brown;

    public int[] solution2(int brown, int yellow) {
        this.yellow = yellow;
        this.brown = brown;
        return measure(yellow, 1);
    }

    //재귀사용
    private int[] measure(int x, int y){ //x 가로, y 세로
        int[] answer;
        if((x*2) + (y * 2) + 4 == brown) { //기저베이스로 모서리를 포함 둘레가 brown이랑 같은경우 return으로 재귀 빠져나옴
            return new int[]{x+2, y+2};
        } else {
            do{
                ++y;
            }while(yellow % y != 0); //약수 찾은 수
            return measure(yellow/y, y); //재귀 파라미터에 가로/세로 담기
        }
    }

    //for문사용
    public int[] solution1(int brown, int yellow) {
        int[] answer = new int[2];

        for (int i=1; i<=yellow/i; i++){
            if(yellow%i == 0){ //약수인 경우
                int x = yellow/i;//가로 (가로가 항상 세로보다 크거나 같은 제약조건)
                int y = i; //세로
                int size = (x*2) + (y*2) + 4; //모서리 수까지 합한 둘레

                if(size == brown) { //모서리 수까지 합한 둘레가 brown 조각 수와 같다면
                    answer[0] = x+2;//가로
                    answer[1] = i+2;//세로
                    break;
                }
            }
        }
        return answer;
    }


}
