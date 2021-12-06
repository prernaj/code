package crash.course;

public class Q54_MoveZeros {
    public void moveZeroes(int[] nums) {
        Integer zp = 0;
        Integer np = 0;
        int n = nums.length;
        // initialize
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zp = i;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                np = i;
                break;
            }
        }
        while (np < n && zp < n) {
            // swap zp and np
            if (zp < np && np < n && zp < n) {
                int temp = nums[zp];
                nums[zp] = nums[np];
                nums[np] = temp;    
            } else {
                np++;
            }
            
            // move zp to next zero
            while(zp < n && nums[zp] != 0) {
                zp++;
            }
            // move np to next non-zero
            while(np < n && nums[np] == 0) {
                np++;
            }
        }
    }
}
