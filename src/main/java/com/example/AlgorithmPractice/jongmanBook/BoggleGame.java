package com.example.AlgorithmPractice.jongmanBook;


public class BoggleGame {

    /* 차례대로 게임판, 테스트할 글자, 예상되는 정답 */
    private String[][] board = {{"U", "R", "L", "P", "M"}, {"X", "P", "R", "E", "T"}, {"G", "I", "A", "E", "T"}, {"X", "T", "N", "Z", "Y"}, {"X", "O", "Q", "R", "S"}};
    private String[] words =  {"PRETTY", "GIRL", "REPEAT", "KARA", "PANDORA", "GIAZAPX"};
    private String[] answers = {"YES", "YES", "YES", "NO", "NO", "YES"};

    /* 주변 8개 */
    private final int[] xCoord = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] yCoord = {-1, 0, 1, -1, 1, -1, 0, 1};

    /* 재귀로 한글자씩 떼어서 첫글자가 X, Y에 잇는지 확인 PRETTY -> RETTY -> ETTY ...*/
    public boolean recursiveFindWord(int x, int y, String word) {

        if (!inRange(x, y)) return false; //기저베이스 1. 범위를 벗어난 경우 false
        if (!board[x][y].equals(word.split("")[0])) return false; //기저베이스 2. 해당 좌표에 찾는 글자가 원하는 글자가 아닌경우 false
        if (word.length() == 1)
            return true; //기저베이스 3. 찾는 글자가 1글자 이면 true. 기저베이스 2 번을 통과하고, 1글자만 찾는경우라면 그 자리에 글자가 맞다는 뜻!

        //해당 좌표에 찾는 글자가 원하는 글자가 맞는 경우 true
        for (int i = 0; i < 8; i++) {
            int nextX = x + xCoord[i];
            int nextY = y + yCoord[i];

            if (recursiveFindWord(nextX, nextY, word.substring(1))) {//재귀를 돌면서 글자가 한글자씩 줄어들게 되니까
                return true;
            }
        }
        return false;
    }

    /* 보드판을 벗어나는지 검사하기 위한 메서드 */
    public boolean inRange(int x, int y) {
        if (x < 0 || x >= 5) return false;
        if (y < 0 || y >= 5) return false;
        return true;
    }


    public void test() {

        boolean isExist = false;

        for (int n = 0; n < words.length; n++) {
            System.out.print(words[n] + " : ");
            String firstLetter = words[n].substring(0, 1);

            LoopBoard:
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j].equals(firstLetter)) {
                        if (recursiveFindWord(i, j, words[n])) {
                            isExist = true;
                            break LoopBoard;
                        }
                    }
                }
            }

            if (isExist) {
                System.out.println("YES");
                isExist = false; //초기화
            } else {
                System.out.println("NO");
            }
        }
    }
}
