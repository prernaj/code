package crash.course;

/**
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 * Question
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 * The ith package on the conveyor belt has a weight of weights[i]. 
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights). 
 * We may not load more weight than the maximum weight capacity of the ship.
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 */
/**
 * Input Output
 * weights = [1,2,3,4,5,6,7,8,9,10], days = 5 => 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

 * weights = [3,2,2,4,1,4], days = 3 => 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

 * weights = [1,2,3,1,1], days = 4 => 3
 * Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
 */
/**
 * Thought process
 * total weight = 45
 * weight per day = 45/5 = 9
 */
/**
 * Improvements
 * The key is left = max(weights), right = sum(weights),which are the minimum and maximum possible weight capacity of the ship.
 * Therefore the question becomes Binary Search to find the minimum weight capacity of the ship between left and right.
 * We start from 
 * mid = (left + right) / 2 as our current weight capacity of the ship.
 * need = days needed == 1
 * cur = current cargo in the ship == 0
 * Start put cargo into ship in order.
 * when need > D, it means the current ship is too small, we modify left = mid + 1 and continue
 * If all the cargo is successfully put into ships, we might have a chance to find a smaller ship, so let right = mid and continue.
 * Finally, when our left == right, we reach our answer
 */
public class Q44_CapacityToShipPackageInDDays {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int i = 0; i < weights.length; i++) {
            int w = weights[i];
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;   
            }
            if (need > days) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
