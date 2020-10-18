package com.example.AlgorithmPractice.backjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;

/*
제한 시간 2 초
제한 메모리 128 MB
 */
public class Sopoong {
    private static boolean[][] areFriends;
    private static int studentsTotal;
    private static int studentsToGo;
    private static Set<Integer> myset = new TreeSet();

    public static void main(String[] args) throws FileNotFoundException {
        String file = "C:\\Users\\hjmin\\Downloads\\sopoong.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        try {
            /* 문제 읽기 */
            String[] students = br.readLine().split(" ");
            studentsToGo = Integer.parseInt(students[0]); //1 ≤ K ≤ 62
            studentsTotal = Integer.parseInt(students[1]); //K ≤ N ≤ 900
            int noOfPairs = Integer.parseInt(students[2]); //1 ≤ F ≤ 5,600

            /* 친구 정보 담기 */
            areFriends = new boolean[studentsTotal + 1][studentsTotal + 1];

            for (int i = 0; i < noOfPairs; i++) {
                String[] pairs = br.readLine().split(" ");
                areFriends[Integer.parseInt(pairs[0])][Integer.parseInt(pairs[1])] = true;
                areFriends[Integer.parseInt(pairs[1])][Integer.parseInt(pairs[0])] = true;
            }

            /* 친구 찾기 */
            for (int i = 1; i <= studentsTotal; i++) {
                if (myset.size() == studentsToGo) {
                    break;
                } else {
                    myset = new TreeSet<>();
                }
                test(i);
            }

            if (myset.size() != studentsToGo) System.out.println(-1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 친구 관계인지 확인 */
    public static void test(int studentIdx) {
        if (myset.size() == studentsToGo) {
            for (int i : myset) {
                System.out.println(i);
            }
            return;
        }

        for (int i = studentIdx + 1; i <= studentsTotal; i++) {
            if (areFriends[studentIdx][i] && retest(i)) {
                myset.add(studentIdx);
                myset.add(i);
                test(i);
                if (myset.size() != studentsToGo) myset.remove(i);
            }
        }
    }

    /* 이미 set에 들어간 친구들과 새친구가 친구 관계인지 확인 */
    public static boolean retest(int newStudent) {
        for (int i : myset) {
            if (!areFriends[newStudent][i]) return false;
        }
        return true;
    }
}
