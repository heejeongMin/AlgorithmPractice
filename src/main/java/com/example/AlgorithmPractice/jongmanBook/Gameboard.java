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
        String file = "C:\\Users\\민희정\\Downloads\\gameboard.txt";
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

    private static int[][][] coverType = new int[][][]{ {{0,0},{1,0},{0,1}},
                                                        {{0,0},{0,1},{1,1}},
                                                        {{0,0},{1,0},{1,1}},
                                                        {{0,0},{1,0},{1,-1}}
                                                };

    public static void coverChk(){
        boolean isAllCovered = true; /*기저베이스 : 다 덮였는지 확인*/
        int x = 0;
        int y = 0;

        Loop :
        for(int h=0; h<height; h++){
            for(int w=0; w<width; w++){
                if(!board[h][w]) {
                    x = h;
                    y = w;
                    isAllCovered = false;
                    break Loop;
                }
            }
        }

        if(isAllCovered) {
            ++answer;
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
                coverChk();
                board[x][y] = false;
                board[x+(coverType[i][1][0])][y+(coverType[i][1][1])] = false;
                board[x+(coverType[i][2][0])][y+(coverType[i][2][1])] = false;
            }
        }
    }
}
