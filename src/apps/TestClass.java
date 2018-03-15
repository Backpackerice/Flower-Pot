package apps;
import java.util.Timer;
import java.util.TimerTask;

//TIMER WORKS
//QUESTION: HOW CAN WE MAKE MULTIPLE TIMERS GO AT THE SAME TIME? CAN WE JUST CALL THEM?
//WILL THE SYSTEM.OUT(0) CLOSE THE ENTIRE PROGRAM OR JUST THE CURRENT CALL TO THE METHOD?
//HOW DOES THE TIMER CLASS EVEN WORK? TIMER.CANCEL?
//ONCE THE TIMER ENDS, HOW CAN WE MAKE THE FLOWER "DONE GROWING?"

public class TestClass {
    public long myLong = 1234;
    public static int seconds;
    public static Timer timer = new Timer();
    
    
    public static void main(String[] args) {
        final TestClass test = new TestClass(); //creates new object of the testclass 
        
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
            	seconds++;
                test.doStuff();
            }
        }, 0, test.myLong);
    }
  

    public void doStuff(){
        System.out.println("What");
        if (seconds == 5){
        	System.out.println("sup");
        	System.out.println("stopping timer now...");
        	timer.cancel();
        }
    }
}