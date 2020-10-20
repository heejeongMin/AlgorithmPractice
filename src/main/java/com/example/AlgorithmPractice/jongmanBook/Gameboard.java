package com.example.AlgorithmPractice.jongmanBook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Gameboard {

    private static int noOfWaysToCover = 0;
    private static boolean[][] board ;

    public static void main(String args[]) throws Exception {

        String file = "C:\\Users\\hjmin\\Downloads\\gameboard.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        /* 문제 읽기 */
        int noOfGames = Integer.parseInt(br.readLine()); //게임의 수

        for(int i=0; i<noOfGames; i++){
            String[] arr = br.readLine().split(" ");
            int height = Integer.parseInt(arr[0]); //가로
            int width = Integer.parseInt(arr[1]); //세로
            board = new boolean[height][width];

            int whiteSpaceCnt = 0;

            /* 문제의 보드판 만들기 */
            for(int j=0; j<height; j++){
                String[] pos = br.readLine().split("");
                for(int k=0; k<pos.length; k++){
                    if(pos[k].equals("#")){
                        board[j][k] = true;
                    } else {
                        ++whiteSpaceCnt;
                    }
                }
            }
            
            /* 로직 시작 */
            int total = 0;
            if(whiteSpaceCnt%3 != 0){
                System.out.println(noOfWaysToCover);
            } else {
                total += coverChk(height, width);
            }
        }


    }

    public static int coverChk(int height, int width){
        if(Arrays.stream(board).distinct().count() > 1){
            return 1;
        }

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(!board[i][j]){ //하얀 칸이면
                    if(checkRange(i,j)) { //ㄴ 자 블럭을 놓을 수 있는 지 확인
                        board[i][j] = true;
                        coverChk(i+1, j+1);
                    } else {
                        board[i][j] = false;
                    }
                }
            }
        }

        return 0;
    }

    private static int[][][] coverType = new int[][][]{ {{0,0},{1,0},{0,1}},
                                                        {{0,0},{0,1},{1,1}},
                                                        {{0,0},{1,0},{1,1}},
                                                        {{0,0},{1,0},{1,-1}}
                                                        };

    public static boolean checkRange(int x, int y){

        boolean withinRange = true;
        for(int i=0; i < coverType.length; i++){
            for(int j=1; j < coverType[i].length; j++){
                int newX = x + coverType[i][j][0];
                int newY = y + coverType[i][j][1];


                /* 판을 넘어가는지, 이미 덮여 있거나, 검은 판이지 확인 */
                if((newX < 0 || newX >= board.length)
                    || (newY < 0 || newY >= board[i].length)
                    || !board[newX][newY]){
                    withinRange = false;
                } else{
                    withinRange = true;
                }
            }
        }

        return withinRange;
    }


}
