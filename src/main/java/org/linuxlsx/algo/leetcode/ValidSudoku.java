package org.linuxlsx.algo.leetcode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution of <a href='https://leetcode.com/problems/valid-sudoku/'> Valid Sudoku</a>
 * @author linuxlsx
 * @date 2019-03-25
 */
public class ValidSudoku {


    /**
     * 算法复杂度 O(n^2) 这个是固定的，必须遍历完整个数组才能知道是否符合要求。
     * 运行时间 14ms. beats 77%
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {

        //比较暴力的做法，通过一个双层循环来搞定。
        //通过坐标替换做到一遍完成横和竖的遍历。
        //同时通过坐标的运算可以得到每个3x3的内容。


        //比较挫，预先定义好9个 3x3的内容
        char[] rect0 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect1 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect2 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect3 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect4 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect5 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect6 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect7 = {'.','.','.','.','.','.','.','.','.'};
        char[] rect8 = {'.','.','.','.','.','.','.','.','.'};

        Map<String, char[]> maps = new HashMap<>();
        maps.put("0_0", rect0);
        maps.put("0_1", rect1);
        maps.put("0_2", rect2);
        maps.put("1_0", rect3);
        maps.put("1_1", rect4);
        maps.put("1_2", rect5);
        maps.put("2_0", rect6);
        maps.put("2_1", rect7);
        maps.put("2_2", rect8);

        for (int i = 0; i < 9; i++) {
            char[] hor_array = {'.','.','.','.','.','.','.','.','.'};
            char[] ver_array = {'.','.','.','.','.','.','.','.','.'};
            for (int j = 0; j < 9; j++) {
                char hor_char = board[i][j];
                char ver_char = board[j][i];

                //如果第一次出现，则把对应的位置替换为对应的内容
                //如果第二次出现，那么内容就不是初始值了，可以认定是invalid
                if(hor_char != '.'){
                    if(hor_array[hor_char - '0'] == '.'){
                        hor_array[hor_char - '0'] = hor_char;
                    }else {
                        return false;
                    }

                    //计算当前字符属于哪个3x3
                    String key = (i / 3) + "_" + (j / 3);
                    //int index = (i % 3) * 3 + j % 3;
                    char[] rect = maps.get(key);
                    if (rect[hor_char - '0'] == '.') {
                        rect[hor_char - '0'] = hor_char;
                    }else {
                        return false;
                    }
                }

                if(ver_char != '.'){
                    if(ver_array[ver_char - '0'] == '.'){
                        ver_array[ver_char - '0'] = ver_char;
                    }else {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    /**
     * 最快的版本。 10ms  beats 100%。
     * 对于每个横、竖和3x3 进行 `|` 操作，这样通过 `&`操作就能知道这个数字是否重复过。
     * @param board
     * @return
     */
    public boolean isValidSudokuFastest(char[][] board) {

        int [] vset = new int [9];
        int [] hset = new int [9];
        int [] bckt = new int [9];
        int idx = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    idx = 1 << (board[i][j] - '0') ;
                    if ((hset[i] & idx) > 0 ||
                            (vset[j] & idx) > 0 ||
                            (bckt[(i / 3) * 3 + j / 3] & idx) > 0) return false;
                    hset[i] |= idx;
                    vset[j] |= idx;
                    bckt[(i / 3) * 3 + j / 3] |= idx;
                }
            }
        }
        return true;

    }


    public static void main(String[] args) {

        String input = "[\n" +
                "  [\"8\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],\n" +
                "  [\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"],\n" +
                "  [\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"],\n" +
                "  [\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"],\n" +
                "  [\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"],\n" +
                "  [\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"],\n" +
                "  [\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"],\n" +
                "  [\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"],\n" +
                "  [\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]\n" +
                "]";

        input = "[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"],[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"],[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"],[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"],[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"],[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"],[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"],[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]";

        Type type = new TypeToken<char[][]>(){}.getType();

        char[][] inputArgs = new Gson().fromJson(input, type);


        ValidSudoku validSudoku = new ValidSudoku();
        System.out.println(validSudoku.isValidSudoku(inputArgs));
    }
}
