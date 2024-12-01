package application;

import java.util.ArrayList;
import java.util.Collections;

import application.Items.Clinic;
import application.Items.FortifyingSupplements;
import application.Items.RabbitsFoot;
import application.Items.SmellingSalts;
import application.Items.StrengtheningTonic;
import application.Items.Whetstone;

public class Shop {
	Items items = new Items("1", "2", "3", "4", 1);
	Items.Clinic clinic = items.new Clinic();
	Items.FortifyingSupplements fortifyingSupplements = items.new FortifyingSupplements();
	Items.RabbitsFoot rabbitsFoot = items.new RabbitsFoot();
	Items.SmellingSalts smellingSalts = items.new SmellingSalts();
	Items.StrengtheningTonic strengtheningTonic = items.new StrengtheningTonic();
	Items.Whetstone whetstone = items.new Whetstone();
	
	
	//all items in a array to pull at random
	//ArrayList<Items> pullList = new ArrayList<Items>();
	//pullList.add(fortifyingSupplements);
	//pullList.add(rabbitsFoot);
	//pullList.add(smellingSalts);
	//pullList.add(strengtheningTonic);
	//pullList.add(wheatstone);
	//pullList.add(clinic);
	
	
	//all items in a array to pull at random for excluding clinic for if clinic is suppose to appear each time
	//ArrayList<Items> pullList = new ArrayList<Items>();
	//pullList.add(fortifyingSupplements);
	//pullList.add(rabbitsFoot);
	//pullList.add(smellingSalts);
	//pullList.add(strengtheningTonic);
	//pullList.add(wheatstone);
	
	public ArrayList<Items> createShopList() {

		ArrayList<Items> tempList = new ArrayList<Items>();
		
		tempList.add(fortifyingSupplements);
		tempList.add(rabbitsFoot);
		tempList.add(smellingSalts);
		tempList.add(strengtheningTonic);
		tempList.add(whetstone);
		tempList.add(clinic);
		
		
		return tempList;
	}
	
	
	ArrayList<Items> getRandomItems(int amount) {
		ArrayList<Items> pullList = createShopList();
		
		ArrayList<Items> tempPullList = new ArrayList<Items>();
		
		for (Items item : pullList) {
			System.out.println(item.getName());
		}
		int tempSize = pullList.size() - amount;
		//will remove items = to the difference between the size and the amount wanted this ensures no duplicates
		for(int i = 0; i < tempSize; i++) {
			Collections.shuffle(pullList);
			Items item = pullList.remove(0);
			tempPullList.add(item);
		}
		
		return tempPullList;
	}
}
