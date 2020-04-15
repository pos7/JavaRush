package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
//        char[][] crossword = new char[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> arrayWords = new ArrayList<Word>();

        int[] dx = new int[] {-1,  0,  1, 1, 1, 0, -1, -1};
        int[] dy = new int[] {-1, -1, -1, 0, 1, 1,  1,  0};

        for (String wrd: words) {
            char[] chars = wrd.toCharArray();
            for (int y= 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[y].length; x++) {
                    if (crossword[y][x] == chars[0]) {
                        for (int i = 0; i < dx.length; i++) {
                            int charPos=1; int yy=y; int xx=x;
                            while (((xx+=dy[i])<crossword[0].length) && (xx>=0) && ((yy+=dx[i])<crossword.length) && (yy>=0) && (charPos <= chars.length) && (crossword[yy][xx] == chars[charPos])){
                                if (++charPos == chars.length) {
                                    Word curWord = new Word(wrd);
                                    curWord.setStartPoint(x, y);
                                    curWord.setEndPoint(xx, yy);
                                    arrayWords.add(curWord);
                                    System.out.println(curWord);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayWords;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
