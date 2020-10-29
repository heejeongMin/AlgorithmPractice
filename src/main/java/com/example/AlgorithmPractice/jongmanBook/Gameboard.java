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
                for(int h=0; i<height; i++){

                }
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
                    int[] pos = checkRange(i,j);
                    coverChk();

                    if(pos.length != 0) {
                        board[i][j] = false;
                        board[i + coverType[pos[0]][pos[1]-1][0]][j + coverType[pos[0]][pos[1]-1][1]] = false;
                        board[i + coverType[pos[0]][pos[1]][0]][j + coverType[pos[0]][pos[1]][1]] = false;
                        return;
                    }

                }
            }
        }
    }

    private static int[][][] coverType = new int[][][]{ {{0,0},{1,0},{0,1}},
                                                        {{0,0},{0,1},{1,1}},
                                                        {{0,0},{1,0},{1,1}},
                                                        {{0,0},{1,0},{1,-1}}
                                                        };

    public static boolean checkShapeIfCanCover(int x, int y, int shape) {
        for( int i = 0; i < 3; i++){
            int newX = x + coverType[shape][i][0];
            int newY = y + coverType[shape][i][1];
            if((newX < 0 || newX > height-1) || (newY < 0 || newY > width-1)){
                if(!board[newX][newY]){
                    return true;
                }
            }
        }
        return false;
    }


    public static int[] checkRange(int x, int y){

        int[] pos = new int[2];
        int cnt = 1;
        Loop :
        for(int i=0; i < coverType.length; i++){
            for(int j=1; j < coverType[i].length; j++){
                int newX = x + coverType[i][j][0];
                int newY = y + coverType[i][j][1];

                /* 판을 넘어가는지, 이미 덮여 있거나, 검은 판이지 확인 */
                if((newX < 0 || newX > height-1) || (newY < 0 || newY > width-1)){
                    break;
                } else {
                    if(!board[newX][newY]){
                        ++cnt;
                        if(cnt == 3) {
                            board[x][y] = true;
                            board[x+coverType[i][j-1][0]][y+coverType[i][j-1][1]] = true;
                            board[newX][newY] = true;
                            pos[0] = i;
                            pos[1] = j;
                            break Loop;
//                            coverChk();
//                            board[x][y] = false;
//                            board[x+coverType[i][j-1][0]][y+coverType[i][j-1][1]] = false;
//                            board[newX][newY] = false;
                        }
                    }
                }
            }
            cnt = 1;
        }

        return pos;
    }


}
