package apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FPDriver {
	
	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	
	private static void ifExit(String response){
		if (response.equals("e")){
			System.out.println("Goodbye!");
			System.exit(0);
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		String response; //holds any response by the user
		boolean isFinished = false;
		
		System.out.println("Welcome to the Flower Pot v2! Would you like to plant some flowers (y/n)?");
		response = keyboard.readLine();
		response.toLowerCase();
		if (!response.equals("y")){
			ifExit("e");
		}
		
		FlowerBed bed = new FlowerBed();
		
		//Main game loop
		while (!isFinished){
			System.out.println();
			
			System.out.println("You can do the following:");
			System.out.println("(V)iew available seeds in your seed pouch.");
			System.out.println("(P)lant a flower.");
			System.out.println("(R)emove or pick a flower.");
			System.out.println("(C)heck your flower bed.");
			System.out.println("(E)xit your flower bed.");
			
			response = keyboard.readLine();
			response.toLowerCase();
			
				if (response.equals("v")){
					//view the seeds
					bed.printFlowersList();
					continue;
				}
				
				else if (response.equals("p")){
					System.out.println("These are the flowers you can plant:");
					bed.printFlowersList();
					System.out.println("Which flower would you like to plant?");
					response = keyboard.readLine();
					//error check
					ifExit(response); //Check if the user wants to exit
					while (bed.checkFlowersList(response) == false){ //Check if the user inputed a valid flower to plant
						System.out.println("Please type in a valid flower.");
						response = keyboard.readLine();
						response.toLowerCase();
						ifExit(response); //check again
					}
					bed.plantFlower(response);
					
				}
				else if (response.equals("r")){
					if (bed.numFlowers == 0){
						System.out.println("There are no flowers in your flower bed to remove!");
						continue;
					}
					System.out.println("Which flower from your flower bed would you like to remove?");
					bed.printFlowerBed();
					response = keyboard.readLine();
					response.toLowerCase();
					ifExit(response);
					while (bed.checkFlowerBed(response) == false){
						System.out.println("Please type in a different flower.");
						response = keyboard.readLine();
						response.toLowerCase();
						ifExit(response); //check again
					}
					bed.removeFlower(response);
				}
				
				else if (response.equals("c")){
					bed.printFlowerBed();
				}
				
				else if (response.equals("e")){
					ifExit(response);
				}
				
				else{
					System.out.println("Please input one of the following letters in parathesis!");
				}	
		}
	}
}
