package com.lightquark.contest.upwork.matrix;

/**
 * Created by Light Quark.
 */
public class MatrixUtils
{
    /**
     * Rotate the matrix <code>m</code> clockwise.
     * This method use additional memory, but it is very simple.
     * Important: There are no checks of the boundaries of the array
     *
     * @param m input matrix NxN
     * @return rotated matrix
     */
    public static int[][] rotateCW(int[][] m)
    {
        final int N = m.length;
        int[][] a = new int[N][N];
        for (int row = 0; row < N; row++)
        {
            int i = N - 1 - row;
            for (int col = 0; col < N; col++)
            {
                a[col][i] = m[row][col];
            }
        }
        return a;
    }

    /**
     * Rotate the matrix <code>m</code> counterclockwise.
     * This method use additional memory, but it is very simple.
     * Important: There are no checks of the boundaries of the array
     *
     * @param m input matrix NxN
     * @return rotated matrix
     */
    public static int[][] rotateCCW(int[][] m)
    {
        final int N = m.length;
        int[][] a = new int[N][N];
        for (int row = 0; row < N; row++)
        {
            int i = N - 1 - row;
            for (int col = 0; col < N; col++)
            {
                a[row][col] = m[col][i];
            }
        }
        return a;
    }

    /**
     * Rotate the matrix <code>m</code> clockwise.
     * This method doesn't use additional memory, and use the idea described on http://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
     * Important: There are no checks of the boundaries of the array
     *
     * @param m input matrix NxN
     * @return rotated matrix (actually, it is the same matrix)
     */
    public static int[][] rotateCWEx(int[][] m)
    {
        final int N = m.length - 1;
        final int HALF_N = N / 2;
        final int REMAINDER = N % 2;
        for (int row = HALF_N; row >= 0; row--)
        {
            int i1 = HALF_N - row;
            int i2 = N - HALF_N + row;

            for (int col = i1; col < HALF_N + row + REMAINDER; col++)
            {
                int i3 = N - col;

                int t = m[i1][col];
                m[i1][col] = m[i3][i1];
                m[i3][i1] = m[i2][i3];
                m[i2][i3] = m[col][i2];
                m[col][i2] = t;
            }
        }
        return m;
    }

    /**
     * Rotate the matrix <code>m</code> counterclockwise.
     * This method doesn't use additional memory, and use the idea described on http://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
     * Important: There are no checks of the boundaries of the array
     *
     * @param m input matrix NxN
     * @return rotated matrix (actually, it is the same matrix)
     */
    public static int[][] rotateCCWEx(int[][] m)
    {
        final int N = m.length - 1;
        final int HALF_N = N / 2;
        final int REMAINDER = N % 2;
        for (int row = HALF_N; row >= 0; row--)
        {
            int i1 = HALF_N - row;
            int i2 = N - HALF_N + row;

            for (int col = i1; col < HALF_N + row + REMAINDER; col++)
            {
                int i3 = N - col;

                int t = m[i1][col];
                m[i1][col] = m[col][i2];
                m[col][i2] = m[i2][i3];
                m[i2][i3] = m[i3][i1];
                m[i3][i1] = t;
            }
        }
        return m;
    }
}
