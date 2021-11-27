package top.k.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class T1_TwoSum {
    @Test
    public void twoSumTest() {
        Q1_TwoSum twoSum = new Q1_TwoSum();
        int[] nums1 = {1,2,3,4};
        int target1 = 3;
        int[] output1 = {0,1};
        assertEquals(twoSum.twoSum(nums1, target1), output1);
    }
}
