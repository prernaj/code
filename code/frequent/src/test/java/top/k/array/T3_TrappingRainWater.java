package top.k.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class T3_TrappingRainWater {
    @Test
    public void trappingRainWaterTest() {
        Q3_TrappingRainWater trappingRainWater = new Q3_TrappingRainWater();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        assertEquals(6, trappingRainWater.getTrappingRainWater(height));
    }
}
