package structures;

import java.util.Timer;
import java.util.TimerTask;

public class Flower {

	//Flower fields
	String name;
	int duration;
	public boolean hasGrown;
	public Flower next;
	
	//Flower type constructor
	public Flower(){
		
	}
	
	public Flower (String name, int duration, boolean hasGrown, Flower next){
		this.name = name;
		this.duration = duration;
		this.hasGrown = hasGrown;
		this.next = next;
	}
	
	//Timer fields
	Timer timer;
	int seconds = 0;
	
	//Timer "constructor"
	/*
	public void setTimer(int seconds){
		this.duration = seconds;
	}
	*/
	
	public void startTimer(){
		System.out.println("Your " + name + " is now growing!");
		
		timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
            	seconds++;
                startCountdown();
            }
        }, 0, 1000);
	}
	
	private void startCountdown(){
		if (seconds == duration){
        	timer.cancel();
        	this.hasGrown = true;
        	System.out.println();
        	System.out.println("HEY! Your " + name + " is now done!");
		}
	}
	public int getCountdown(){
		int secondsLeft = duration - seconds;
		if (secondsLeft < 0){
			System.out.println("error check statement");
		}
		return secondsLeft;
	}
	
	public void stopTimer(){
		timer.cancel();
	}
	
	
	
	
	
}
