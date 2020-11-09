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

                for(int x=0; x<height; x++){
                    for(int y=0; y<width; y++){
                       int answer = coverChk(x, y);
                    }
                }
                System.out.println(answer);
            }
            answer = 0;
        }


    }

    private static int[][][] coverType = new int[][][]{ {
                                                            {0,0},{1,0},{0,1}
                                                        },
                                                        {{0,0},{0,1},{1,1}},
                                                        {{0,0},{1,0},{1,1}},
                                                        {{0,0},{1,0},{1,-1}}
                                                };

    public static int coverChk(int x, int y){
        int answer = 0;
        /*기저베이스 : 다 덮였는지 확인*/
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
            return 1;
        }

        /* 반복의 조각 */
        for(int i=0; i<4; i++) {
            boolean canBePlaced = true;

            /* 블럭 모양 대로 놓을 수 있는지 먼저 보고*/
            for(int j=1; j<3; j++){
                /* 놓으려는 곳이 판 안인지 + 아직 안 놓였는지 확인 */
                int newX = coverType[i][j][0];
                int newY = coverType[i][j][1];

                if((x+newX) >= height || (x+newX) < 0 || (y+newY) >= width || (y+newY) < 0) {
                    continue;
                } else {
                    if(board[x+newX][y+newY]) { //비었는지 보고
                        canBePlaced = false;
                        break;
                    }
                }
            }

            /* 놓을 수 있으면 true로 변경 하고 그 다음 놓을 수 있는지 보기 */
            if(canBePlaced) {
                board[x][y] = true;
                board[x+(coverType[i][1][0])][y+(coverType[i][1][1])] = true;
                board[x+(coverType[i][2][0])][y+(coverType[i][2][1])] = true;
                answer += coverChk(x, y+1);
                board[i][0] = board[i][1] = board[i][2] = false;
            } else {
                break;
            }
        }

        return 0;
    }



//    public static boolean checkShapeIfCanCover(int x, int y, int shape) {
//        for( int i = 0; i < 3; i++){
//            int newX = x + coverType[shape][i][0];
//            int newY = y + coverType[shape][i][1];
//            if((newX < 0 || newX > height-1) || (newY < 0 || newY > width-1)){
//                if(!board[newX][newY]){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


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
