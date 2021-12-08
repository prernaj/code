package go.to.google;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/logger-rate-limiter/
 * Question
 * Design a logger system that receives a stream of messages along with their timestamps. 
 * Each unique message should only be printed at most every 10 seconds (i.e. a message printed 
 * at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 * Implement the Logger class:
 * Logger() Initializes the logger object.
 * bool shouldPrintMessage(int timestamp, string message) 
 * Returns true if the message should be printed in the given timestamp, otherwise returns false.
 */
/**
 * Input Output
 * ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
[[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
=> [null, true, true, false, false, false, true]
 */
/**
 * Thought process
 * Keep a hashmap of the message, timestamp.
 * if message is not printed yet, put it in the hashmap
 * if message is present and older timestamp + 10 <= current timestamp => update timestamp in map => return true
 * else return false.
 * Time: O(N)
 * Space: O(D) : D is the distinct number of messages.
 * */
public class E1_LoggerRateLimiter {

    HashMap<String, Integer> timeMap;
        
    public E1_LoggerRateLimiter() {
        timeMap = new HashMap<>();    
    }
        
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timeMap.containsKey(message)) {
            if (timestamp >= timeMap.get(message)+10) {
                timeMap.put(message, timestamp);
                return true;
            } 
            return false;
        } else {
            timeMap.put(message, timestamp);
            return true;
        }
    }

}
