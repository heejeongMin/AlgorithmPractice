package com.example.AlgorithmPractice.programmers;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PrimeNumber {

    public static void main(String[] args) {

//        System.out.println(solution("17"));
        System.out.println(solution("011"));
        System.out.println(solution("1207"));

    }


    /*
     * 1. 문제를 자른다.
     * 2. 0으로 시작하는 지를 체크 ?
     * 3. 소수인지 체크
     * 4. 소수 이면 answer ++ 하기
     *
     */
    public static int solution(String numbers) {
        String[] str = numbers.split("");
        Set<String> numberSet = new TreeSet<>();

        //경우의 조합을 만들고
        for(int i=0; i<str.length; i++){
            String s = str[i];
            numberSet.add(s);
                 //0, 1 빼고 한자리 넣기

            for (int j = 0; j < str.length; j++) {
                for (int k = 0; k <= j; k++) {
                    if (i != k) {
                        s += str[k];
                    }
                }

                numberSet.add(s);
                s = str[i];
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

        return answer.size();
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
