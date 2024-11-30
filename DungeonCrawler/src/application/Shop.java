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
	Items items = new Items();
	Items.Clinic clinic = new Items.Clinic();
	Items.FortifyingSupplements fortifyingSupplements = new Items.FortifyingSupplements();
	Items.RabbitsFoot rabbitsFoot = new Items.RabbitsFoot();
	Items.SmellingSalts smellingSalts = new Items.SmellingSalts();
	Items.StrengtheningTonic strengtheningTonic = new Items.StrengtheningTonic();
	Items.Whetstone whetstone = new Items.Whetstone();
	
	
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
	
	
	ArrayList<Items> getRandomItems(int amount) {
		ArrayList<Items> pullList = new ArrayList<Items>();
		pullList.add(fortifyingSupplements);
		pullList.add(rabbitsFoot);
		pullList.add(smellingSalts);
		pullList.add(strengtheningTonic);
		pullList.add(whetstone);
		pullList.add(clinic);
		
		ArrayList<Items> tempPullList = new ArrayList<Items>();
		
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
