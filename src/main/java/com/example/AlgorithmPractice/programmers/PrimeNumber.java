package com.example.AlgorithmPractice.programmers;

import java.util.*;

public class PrimeNumber {

    public static void main(String[] args) {

//        System.out.println(solution("17"));
        System.out.println(solution("011"));
        System.out.println(solution("1207"));

    }

    private static String[] str = null;
    private static Set<String> numberSet = new TreeSet<>();

    public static String dfs(String s, int addCnt, List<Integer> idxList){
        if(addCnt == 0) return s;

        for(int i=0; i<str.length; i++){
            if(idxList.contains(i)) continue;

            s += str[i]; // 숫자를 붙인다.
            idxList.add(i);

            s = dfs(s, addCnt-1, idxList);//dfs
            idxList.remove(Integer.valueOf(i));
            numberSet.add(s);

            s = s.substring(0, s.length()-1);
        }

        return s;
    }


    public static int solution(String numbers) {
        str = numbers.split("");// 0, 1, 1 --> 1 11 011 101 110

        for(int i=0; i<str.length; i++){
            List<Integer> idxList = new ArrayList<>();
            String s = str[i];
            idxList.add(i);
            for(int j=0; j<str.length; j++){
               s = dfs(s, j, idxList);
               numberSet.add(s);
            }
        }

        //소수인지 판별 
        Set<String> answer = new HashSet<>();
        for(String s : numberSet) {
            if (isPrimeNumber(Integer.parseInt(s))) {
                answer.add(s);
            }
        }

        //앞에 0 시작하는애 빼고 숫자 세기
        int res = 0;
        for(String s : answer){
            if(s.startsWith("0") && answer.contains(s.substring(1))){
                continue;
            }
            res++;
        }

        return res;
    }


    public static boolean isPrimeNumber(int n){
        boolean isPrimeNumber = true;

        if (n != 1 && n!=0){
            for(int i=2; i<n; i++){
                if(n%i==0) isPrimeNumber = false;
            }
        } else {
            isPrimeNumber = false;
        }


        return isPrimeNumber;
    }
}
