package top.k.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class T1_MeetingRoomsTest {
    @Test
    public void minMeetingRoomsTest1() {
        Q1_MeetingRooms solution1 = new Q1_MeetingRooms();
        int[][] input1 = {{0,30},{5,10},{15,20}};
        assertEquals(2, solution1.minMeetingRooms(input1));

        int[][] input2 = {{7,10},{2,4}};
        assertEquals(1, solution1.minMeetingRooms(input2));

    }
}
