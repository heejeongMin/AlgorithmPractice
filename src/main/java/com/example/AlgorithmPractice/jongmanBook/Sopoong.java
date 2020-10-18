package com.example.AlgorithmPractice.jongmanBook;


public class Sopoong {

    private int noOfStudents;
    private boolean[] takenStudents;
    private int cases = 0;
    private boolean[][] areFirends = new boolean[10][10];

    public Sopoong() {
        noOfStudents = 6;
        takenStudents = new boolean[noOfStudents];
        areFirends[0][1] = true;
        areFirends[0][2] = true;
        areFirends[1][2] = true;
        areFirends[1][3] = true;
        areFirends[1][4] = true;
        areFirends[2][3] = true;
        areFirends[2][4] = true;
        areFirends[3][4] = true;
        areFirends[3][5] = true;
        areFirends[4][5] = true;
    }

    public void test(){
        int ret = recursiveFindPairs(takenStudents);
        System.out.println(ret);
    }

    public int recursiveFindPairs(boolean[] takenStudents) {
        //기저베이스 1. 모든 짝을 찾은 경우
        boolean allMatched = true;

        int studentIdx = 0;
        for (int i = 0; i < noOfStudents; i++) {
            if (!takenStudents[i]) {//아직 짝을 찾지 못한 학생이 있다면 false
                allMatched = false;
                studentIdx = i;
                break;
            }
        }

        if(allMatched) { //모두 짝을 찾았으면 ..
            return 1;
        }

        int ret = 0 ;
        for (int i = studentIdx + 1; i < noOfStudents; i++) { //짝을 찾지 못한 학생 부터 다시 시작
            if (!takenStudents[i]) {//1. 그 다음 idx 친구는 짝이 이미 된 친구인지 확인
                if (areFirends[studentIdx][i]) {//2. taken이 안되었다면 둘이 친구인지 확인하고 맞음면 매칭 시켜서 taken 처리 해줌
                    takenStudents[studentIdx] = true;
                    takenStudents[i] = true;
                    ret += recursiveFindPairs(takenStudents);
                    takenStudents[studentIdx] = false;
                    takenStudents[i] = false;
                }
            }
        }

        return ret;
    }
}
