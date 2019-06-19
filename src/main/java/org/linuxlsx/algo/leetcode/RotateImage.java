package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/rotate-image/'>Rotate Image</a>
 * @author linuxlsx
 * @date 2019-06-19
 */
public class RotateImage {

    /**
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {

        int len = matrix.length - 1;

        for (int i = 0; i < matrix.length / 2; i++) {

            for (int j = i; j < len - i; j++) {

                int a = matrix[i][j];
                int b = matrix[j][len - i];
                int c = matrix[len - i][len -j];
                int d = matrix[len - j][i];

                //starting rotate
                matrix[i][j] = d;
                matrix[j][len - i] = a;
                matrix[len - i][len -j] = b;
                matrix[len - j][i] = c;
            }
        }
    }

    public static void main(String[] args) {

        int len = 5;
        int[][] matrix = new int[len][];

        int x = 1;
        for (int i = 0; i < len; i++) {
            matrix[i] = new int[len];
            for (int j = 0; j < len; j++) {
                matrix[i][j] = x++;
            }
        }


        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(matrix);

        System.out.println(matrix);
    }
}
