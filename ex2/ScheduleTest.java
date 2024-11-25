
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkScheduleTest {

    private WorkSchedule ws;

    public int getRequiredWorkers(WorkSchedule ws, int time)
    {
        return ws.readSchedule(time).requiredNumber;
    }
    @BeforeEach
    public void setUp()
    {
        ws = new WorkSchedule(10);
    }

    @Test
    public void test_Schedule_StartTimeGreaterThanEndTime()
    {
        int reqWorkers = getRequiredWorkers(ws, 2);
        assertEquals(0, reqWorkers);
        ws.setRequiredNumber(1, 8, 2);
        assertEquals(0, getRequiredWorkers(ws, 2));
    }

    @Test
    public void test_Schedule_nemployeGreaterThanWorkers()
    {
        ws.setRequiredNumber(2, 0, 9);
        assertEquals(2, getRequiredWorkers(ws, 0));

        ws.addWorkingPeriod("worker1", 0, 9);
        ws.addWorkingPeriod("worker2", 0, 9);

        ws.setRequiredNumber(3, 0, 9);
        assertEquals(3, getRequiredWorkers(ws, 0));
        assertEquals(2, ws.readSchedule(0).workingEmployees.length);
    }

    @Test
    public void test_Schedule_DiscardingWorkers()
    {
        ws.setRequiredNumber(2, 0, 9);
        ws.addWorkingPeriod("wroker1", 0, 9);
        ws.addWorkingPeriod("worker2", 0, 9);

        String[] workingWorkersBeforeFunctionCall = ws.readSchedule(0).workingEmployees;
        assertEquals(2, workingWorkersBeforeFunctionCall.length);

        ws.setRequiredNumber(1, 0, 9);
        String[] workingWorkersAfterFunctionCall = ws.readSchedule(0).workingEmployees;
        assertEquals(1, workingWorkersAfterFunctionCall.length);
    }
    @Test
    public void test_Schedule_StartTimeEqualsEndTime() {
        ws.setRequiredNumber(1, 5, 5);
        assertEquals(1, getRequiredWorkers(ws, 5));
    }

    @Test
    public void test_Schedule_allHoursFilled()
    {
        ws.setRequiredNumber(1, 0, 9);
        for(int i = 0; i < 10; i++)
        {
            ws.addWorkingPeriod("worker"+i, i, i+1);
        }
        assertEquals(-1, ws.nextIncomplete(0));
    }
    
    @Test
    public void test_Schedule_hourNotFilled()
    {
        WorkSchedule work = new WorkSchedule(3);
        work.setRequiredNumber(1, 0, 2);
        work.addWorkingPeriod("worker1", 0, 2);
        assertEquals(2, work.nextIncomplete(0));
    }
    
    @Test
    public void test_Schedule_firstHour()
    {
        WorkSchedule work = new WorkSchedule(3);
        work.setRequiredNumber(1, 0, 2);
        work.addWorkingPeriod("worker1", 1, 2);
        assertEquals(0, work.nextIncomplete(0));
    }
    
    @Test
    public void test_Schedule_middleHour()
    {
        WorkSchedule work = new WorkSchedule(5);
        work.setRequiredNumber(1, 0, 4);
        work.addWorkingPeriod("worker1", 0, 1);
        assertEquals(2, work.nextIncomplete(0));
    }
    
    @Test
    public void test_MinValues()
    {
        WorkSchedule work = new WorkSchedule(1);
        work.setRequiredNumber(1, 0, 0);
        assertEquals(0, work.nextIncomplete(0));
    }

    @Test
    public void test_Schedule_HighValues
    {
        WorkSchedule work = new WorkSchedule(5);
        work.setRequiredNumber(1, 0, 4);
        assertEquals(4, work.nextIncomplete(4));

    @Test
    public void test_Schedule_workEmpty()
    {
        WorkSchedule work = new WorkSchedule(3);
        work.setRequiredNumber(1, 0, 2);
        assertEquals(0, work.nextIncomplete(0));
    }

    @Test
    public void test_Schedule_noneRequired()
    {
        WorkSchedule work = new WorkSchedule(3);
        work.setRequiredNumber(0, 0, 2);
        assertEquals(-1, work.nextIncomplete(0));
    }

}
