package com.example.AlgorithmPractice.jongmanBook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Gameboard {

    private static int noOfWaysToCover = 0;
    private static int[][][] coverType = new int[][][]{ {{0,0},{1,0},{0,1}},
                                                        {{0,0},{0,1},{1,1}},
                                                        {{0,0},{1,0},{1,1}},
                                                        {{0,0},{1,0},{1,-1}}
                                                       };

    public static void main(String args[]) throws Exception {

        String file = "C:\\Users\\hjmin\\Downloads\\gameboard.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        int noOfGames = Integer.parseInt(br.readLine()); //게임의 수
        String[] arr = br.readLine().split(" ");
        int height = Integer.parseInt(arr[0]); //가로
        int width = Integer.parseInt(arr[1]); //세로
        boolean[][] board = new boolean[height][width];

        int whiteSpaceCnt = 0;
        for(int i=0; i<height; i++){
            String[] pos = br.readLine().split("");
            for(int j=0; j<width; j++){
                if(pos[j].equals("#")){
                    board[i][j] = true;
                } else {
                    ++whiteSpaceCnt;
                }
            }
        }

        if(whiteSpaceCnt%3 != 0){
            System.out.println(noOfWaysToCover);
        } else {

        }
    }

    public static boolean coverChk(){
        //게임판을 넘어가는지 확인 넘어가면 게임 오버

        return false;
    }
}
