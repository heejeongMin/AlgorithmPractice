package com.example.AlgorithmPractice.backjoon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SevenGirls {

    public static int answer = 0;

    public static void main(String[] args) throws Exception {
        String file = "C:\\Users\\hjmin\\Downloads\\sevenGirls.txt";
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        boolean[][] girls = new boolean[5][5];
        List<Integer[]> pos = new LinkedList<>();

        /*
         5*5
         조건 1 : S가 4개 이상 (반대로 Y는 3개 이하)
         조건 2 : 가로세로 인접 (대각선 안되는거 같음)
         */

        /* 문제 읽으면서 S가 있는 곳만 true 표시 및 해당 positing 모으기 */
        for(int i=0; i<5; i++){
            String[] line = br.readLine().split("");

            for(int j=0; j<5; j++){
                if(line[j].equals("S")) {
                    girls[i][j] = true;
                    pos.add(new Integer[]{i, j}); //S의 위치 저장
                }
            }
        }

        Deque<Boolean> group = new ArrayDeque<>();
        Deque<String> posList = new ArrayDeque<>();
        for(int i=0; i<pos.size(); i++){
            Integer[] currentPos = pos.get(i);
            group.add(girls[currentPos[0]][currentPos[1]]);
            posList.add(String.valueOf(currentPos[0])+","+currentPos[1]);
            addGirl(currentPos[0], currentPos[1], group, posList, girls);
            group = new ArrayDeque<>();
            posList = new ArrayDeque<>();
        }

        System.out.println(answer);

    }
//SY
    /* S의 위치를 중심으로 경우의 수 찾기 - 현재위치를 기준으로 대각선을 바로 볼 순 없으나 상하좌우만 탐색 */
    public static int[][] check = new int[][] {{0, -1},{-1, 0},{0, 1},{1, 0}}; //좌,상,우,하 순으로

    public static void addGirl(int x, int y, Deque<Boolean> group, Deque<String> posList, boolean[][] girls){
        //기저베이스
        if(group.size() == 7) {
            answer++;
            return;
        }


        for(int i=0; i<4; i++){
            int[] pos = check[i];
            int newX = x+pos[0];
            int newY = y+pos[1];

            boolean isPossible = true;

            if(newX >= 5 || newX < 0 || newY >= 5 || newY < 0) {//판을 넘는지 확인한다.
                isPossible = false;
            } else if(posList.contains(String.valueOf(newX) + "," + newY)) {//범위 안이라면 이미 본 자리인지 검사
                isPossible = false;
            } else { //그것도 아니지만 추가하였을 때 Y가 3개 초과인지 본고 group에 넣어주던가 continue시킨다. 
                if(!girls[newX][newY]) {
                    int yCnt = 0;
                    for(boolean isX : group) { //문제를 읽을 때 y이면 false를 넣어놨음
                        if(!isX) yCnt++;
                    }
                    if(yCnt >= 3) {
                        isPossible = false;
                    }
                }
            }//end 유효성 체크

            if(isPossible){
                group.add(girls[newX][newY]); //group에 추가를 일단하고,
                posList.add(String.valueOf(newX) + "," + newY); //넣은 사람의 position을 저장하고 재귀
                addGirl(newX, newY, group, posList, girls);
                group.pollLast();
                posList.pollLast();
            }
        }

        return;

    }
}
