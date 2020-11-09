package com.example.AlgorithmPractice.hackerrank;

import java.util.*;

public class MeshKorea {

    public static void main(String[] args) {

//        System.out.println(powerJump("11111"));
//        System.out.println(powerJump("11101"));
//        System.out.println(powerJump("10101"));

//        System.out.println(bitPattern(Arrays.asList(1, 2, 3, 2, 1)).toString());
        System.out.println(bitPattern(Arrays.asList(1, 3, 2, 3, 4, 1)).toString());
    }


    public static List<String> bitPattern(List<Integer> num) {
        int len = num.size();
//        int half = (int)Math.ceil((double)len/2);
        List<String> res1 = new ArrayList<>();
//        String[] res1 = new String[len]; //답안 1 어디에 중복 숫자가 있는 자리에 표시
        String[] res2 = new String[len]; //답안 2 뒤에 중복 숫자가 있으면 현재에 표시

        Deque<Integer> deque1 = new LinkedList<>(num);//첫번째 답안용
        for (int i = 0; i <len; i++) {
            if(!res1.contains(null)) break;
            if(res1.get(i) == null) {//확인 안한자리만 확인
                res1.add("0");

                res1 = markPresent(res1, i+1, deque1.pollFirst(), (LinkedList<Integer>) deque1);
            }
        }

        Deque<Integer> deque = new LinkedList<>(num);
        for (int i = 0; i < len; i++) {
            res2[i] = (deque.contains(deque.pollFirst()))? "1" : "0";
        }

        List<String> answer = new ArrayList<>();
//        answer.add(getAnswerString(res1));
        answer.add(getAnswerString(res2));

        return answer;
    }

    public static String getAnswerString(String[] res){
        StringBuffer sb = new StringBuffer();
        for(String s : res) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static List<String> markPresent(List<String> res, int pos, int curNum, LinkedList<Integer> num) {
        int size = num.size();
        for (int i = 0; i < size; i++) {
            if (num.get(i) == curNum){
                res.add(i+pos, "1");
            }
        }

        return res;
    }


    public static int powerJump(String game) {
        int power = 1;
        char designatedTile = game.charAt(game.length() - 1);
        char[] tiles = game.toCharArray();
        int len = tiles.length;

        for (int i = 0; i < len; i++) {
            if (tiles[i] != designatedTile) {
                int gap = findGap(i, len, designatedTile, tiles);
                power = (gap > power) ? gap : power;
            }
        }

        return power;
    }

    // 1 0 1 0 1
    public static int findGap(int idx, int len, char curChar, char[] tiles) {
        int gap = 1;
        for (int i = idx; i < len; i++) {
            if (curChar == tiles[i]) {
                break;
            } else {
                ++gap;
            }
        }
        return gap;
    }

}
