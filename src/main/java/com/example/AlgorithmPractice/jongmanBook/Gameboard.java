package com.example.AlgorithmPractice.jongmanBook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Gameboard {

    private static int noOfWaysToCover = 0;
    private static boolean[][] board ;
    private static int height;
    private static int width;
    static int answer = 0;

    public static void main(String args[]) throws Exception {

        String file = "C:\\Users\\hjmin\\Downloads\\gameboard.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        /* 문제 읽기 */
        int noOfGames = Integer.parseInt(br.readLine()); //게임의 수

        for(int i=0; i<noOfGames; i++){
            String[] arr = br.readLine().split(" ");
            height = Integer.parseInt(arr[0]); //가로
            width = Integer.parseInt(arr[1]); //세로
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
            if(whiteSpaceCnt%3 != 0){
                System.out.println(noOfWaysToCover);
            } else {
                 coverChk();
                System.out.println(answer);
            }
            answer = 0;
        }


    }


    public static void coverChk(){
        int cntFalse = 0;
        for(boolean[] row : board){
            for(boolean b : row){
                if(!b) {
                    ++cntFalse;
                    break;
                }

            }
        }

        if(cntFalse == 0) {
            answer++;
            return;
        }

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(!board[i][j]){ //하얀 칸이면 (false 이면)
                    checkRange(i,j);
                }
            }
        }
    }

    private static int[][][] coverType = new int[][][]{ {{0,0},{1,0},{0,1}},
                                                        {{0,0},{0,1},{1,1}},
                                                        {{0,0},{1,0},{1,1}},
                                                        {{0,0},{1,0},{1,-1}}
                                                        };

    public static boolean checkRange(int x, int y){

        boolean withinRange = true;
        int cnt = 1;
        Loop :
        for(int i=0; i < coverType.length; i++){
            for(int j=1; j < coverType[i].length; j++){
                int newX = x + coverType[i][j][0];
                int newY = y + coverType[i][j][1];

                /* 판을 넘어가는지, 이미 덮여 있거나, 검은 판이지 확인 */
                if((newX < 0 || newX > height-1) || (newY < 0 || newY > width-1)){
                    withinRange = false;
                    break;
                } else {
                    if(!board[newX][newY]){
                        withinRange = true;
                        ++cnt;
                        if(cnt == 3) {
                            board[x][y] = true;
                            board[x+coverType[i][j-1][0]][y+coverType[i][j-1][1]] = true;
                            board[newX][newY] = true;
                            coverChk();
                            board[x][y] = false;
                            board[x+coverType[i][j-1][0]][y+coverType[i][j-1][1]] = false;
                            board[newX][newY] = false;
                        }
                    }
                }
            }
            cnt = 1;
        }

        return withinRange;
    }


}
