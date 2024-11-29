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
	Clinic clinic;
	FortifyingSupplements fortifyingSupplements;
	RabbitsFoot rabbitsFoot;
	SmellingSalts smellingSalts;
	StrengtheningTonic strengtheningTonic;
	Whetstone whetstone;
	
	
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
	
	
	ArrayList<Items> getRandomItems(int ammount) {
		ArrayList<Items> pullList = new ArrayList<Items>();
		pullList.add(fortifyingSupplements);
		pullList.add(rabbitsFoot);
		pullList.add(smellingSalts);
		pullList.add(strengtheningTonic);
		pullList.add(whetstone);
		pullList.add(clinic);
		
		//will remove items = to the difference between the size and the amount wanted this ensures no duplicates
		for(int i = 0; i < pullList.size() - ammount; i++) {
			Collections.shuffle(pullList);
			pullList.remove(0);
		}
		return pullList;
	}
}
