package com.lightquark.contest.upwork.test;

import com.lightquark.contest.upwork.matrix.MatrixUtils;
import com.lightquark.fuf.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Light Quark.
 */
public class MatrixTest
{
    private static final Logger LOG = LogManager.getLogger();

    private int[][] makeArray(final int N)
    {
        Random r = new Random();
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = r.nextInt(100);
        return a;
    }

    private int[][] cloneArray(int[][] m)
    {
        final int N = m.length;
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++)
            System.arraycopy(m[i], 0, a[i], 0, N);
        return a;
    }

    private void checkArray(int[][] a, int[][] b)
    {
        LOG.debug("Check matrix");
        final int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Assert.assertTrue(a[i][j] == b[i][j]);
    }

    @Test
    public void testMatrix()
    {
        //test rotate clockwise
        for (int size = 1; size < 100; size++)
        {
            int[][] arr1 = makeArray(size);
            LOG.debug("source = " + CommonUtils.convertToString(arr1));

            int[][] arr2 = cloneArray(arr1);
            arr1 = MatrixUtils.rotateCW(arr1);
            arr2 = MatrixUtils.rotateCWEx(arr2);

            LOG.debug("arr1 = " + CommonUtils.convertToString(arr1));
            LOG.debug("arr2 = " + CommonUtils.convertToString(arr2));

            checkArray(arr1, arr2);
        }

        //test rotate counterclockwise
        for (int size = 1; size < 100; size++)
        {
            int[][] arr1 = makeArray(size);
            LOG.debug("source = " + CommonUtils.convertToString(arr1));

            int[][] arr2 = cloneArray(arr1);
            arr1 = MatrixUtils.rotateCCW(arr1);
            arr2 = MatrixUtils.rotateCCWEx(arr2);

            LOG.debug("arr1 = " + CommonUtils.convertToString(arr1));
            LOG.debug("arr2 = " + CommonUtils.convertToString(arr2));

            checkArray(arr1, arr2);
        }
    }
}