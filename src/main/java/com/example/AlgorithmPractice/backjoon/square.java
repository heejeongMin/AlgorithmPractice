package com.example.AlgorithmPractice.backjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class square {

    public static void main(String[] args) throws Exception {
        String file = "C:\\Users\\hjmin\\Downloads\\square.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        String[] sizeRec = br.readLine().split(" ");
        int n = Integer.parseInt(sizeRec[0]);
        int m = Integer.parseInt(sizeRec[1]);

        /* 문제읽어서 char 배열에 담기*/
        char[][] rectangle = new char[n][m];
        for(int i=0; i<n; i++){
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<m; j++){
                rectangle[i][j] = line[j];
            }
        }

        int answer = 1;
        for (int i = 0; i < n-1;i++){/* for문 돌면서 검사 */
            for(int j=0; j<m; j++){
                int size = findSquare(rectangle, j, i, m);
                if(size > answer) {
                    answer = size;
                }
            }
        }

        System.out.println(answer);
    }

    public static int findSquare(char[][] rectangle, int idx, int n, int m){
        int size = 1;
        char c = '\u0000';
        char[] rec = rectangle[n];

        for (int i=idx; i<m; i++){
            if(c == '\u0000'){ //숫자하나를 뽑고
                c = rec[i];
            } else { // 뽑앗으면 비교
                int x = i-idx + n;
                if(x > rectangle.length-1) break;
                if(c == rec[i]){ // 같은 숫자를 찾으면 갭과같은 행에서 찾기
                    if(c == rectangle[x][idx] && c == rectangle[x][i]){
                        int tempSize = (i-idx+1)*(i-idx+1);
                        if(tempSize>size) size = tempSize;
                    }
                }
            }
        }

        return size;
    }
}

