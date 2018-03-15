package apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import structures.Flower;

public class FlowerBed {
	
	private HashMap<String, Integer> flowersList = new HashMap<String, Integer>(); //list of flowers and their growth time
	public HashMap<String, Flower> flowerBed = new HashMap<String, Flower>(); //flower bed where flowers are grown
	int numFlowers = 0; //Number of flowers in the flower bed
	
	
	public FlowerBed() throws FileNotFoundException{
		indexFlowers("flowers.txt");
	}
	
	//creates the list of flowers and their durations and stores them into a hashmap
	private void indexFlowers(String doc) throws FileNotFoundException{
		String word = null;
		int num = 0;
		Scanner scan = new Scanner(new File(doc));
		
		while (scan.hasNext()){
			String temp = scan.next();
			char character = temp.charAt(0);
			if (Character.isLetter(character)){
				word = temp;
			}
			else {
				num = Integer.parseInt(temp);
			}
			if (word != null && num != 0){
				flowersList.put(word, num);
				word = null;
				num = 0;
			}
		}	
	}
	
	//Get the number of flowers
	public int getNumFlowers(){
		return numFlowers; 
	}
	
	//Print flowers in the flower database/check flowers in your seed pouch
	public void printFlowersList(){
		for (String name : this.flowersList.keySet()){
			String key = name.toString();
			String value = flowersList.get(key).toString();
			System.out.println(key + "(s)" + " that take " + value + " seconds to grow." );
		}
	}
	
	public void printFlowerBed(){
		
		if (flowerBed.size() == 0){
			System.out.println("There are no flowers growing in your flower bed!");
			return;
		}
		
		System.out.println("You have " + numFlowers + " These are the flowers currently growing in your flower bed:");
		
		//Traverse through flower bed hashmap to print out each element
		for (String name : this.flowerBed.keySet()){
			String flowerName = name.toString(); //get the name of the flower
			Flower flower = flowerBed.get(name); //get the flower object
			if (flower.next != null){ //there are duplicates. traverse through the linked list
				Flower ref = flower; 
				while (ref != null){
					if (ref.hasGrown == false){
						System.out.println("A " + name + " that will be grown in " + ref.getCountdown() + " seconds.");
					}
					else{
						System.out.println("A " + name + " that is finished growing!");
					}
					ref = ref.next;
				}
			}
			else{
				if (flower.hasGrown == false){
					System.out.println("A " + name + " that will be grown in " + flower.getCountdown() + " seconds.");
				}
				else{
					System.out.println("A " + name + " that is finished growing!");
				}
			}
		}
	}
	
	public boolean checkFlowersList(String name){
		if (flowersList.get(name) == null){
			System.out.println("You do not have this flower!");
			return false;
		}
		else{
			return true;
		}
		
	}
	
	public boolean checkFlowerBed(String name){
		if (flowerBed.containsKey(name) == false){
			System.out.println("You do not have this flower in your flower bed!");
			return false;
		}
		else{
			return true;
		}
	}
	
	public void plantFlower(String flower){
		
		//Create a new flower object
		int duration = flowersList.get(flower);
		Flower newFlower = new Flower(flower, duration, false, null);
		
		
		//Add the flower object to the flowerBed hashmap
		
		//check if there is a duplicate flower growing
		if (flowerBed.get(flower) == null){
			flowerBed.put(flower, newFlower); //No duplicates are growing. Add a new one to the front of the linked list
		}
		else{
			Flower otherFlower = flowerBed.get(flower); //get the link to duplicates already growing
			flowerBed.put(flower, newFlower); //add new flower to the hashmap
			newFlower.next = otherFlower; //create a linked list of flowers and link this new one to the other flowers
		}
		//Let the flower start growing!
		numFlowers++;
		newFlower.startTimer();
	}
	
	public void removeFlower(String flower){
		Flower thisFlower = flowerBed.get(flower);
		if (thisFlower.next == null){ //there is no duplicates to worry about. just delete the object
			flowerBed.remove(flower);
			System.out.println("You removed your " + flower + ".");
			return;
		}
		else{ //there are duplicates of this flower. traverse through the linked list
			Flower reference = thisFlower;
			
			//first flower should be deleted
			if (reference.hasGrown == true){
				reference = reference.next;
				flowerBed.put(flower, reference);
				System.out.println("You removed your " + flower + ".");
				return;
			}
			
			//traverse through linked list to find the correct flower
			Flower prev = null;
			while (reference != null){
				if (reference.hasGrown == true){ //delete the following flower
					prev.next = reference.next;
					System.out.println("You removed your " + flower + ".");
					return;
				}
				else{
					prev = reference;
					reference = reference.next;
				}
			}
			
			//if there are no grown flowers that should be taken out and we should take out any random flower.
			prev.next = reference.next;
			reference = null;
			System.out.println("You removed your " + flower + ".");
		}
	}
	


}
