package ui;

import java.io.IOException;
import java.util.Scanner;
import model.*; 

public class AdministrationSystem {
	
	private Scanner sca; 
	private BillboardList bList; 
	
	public AdministrationSystem() {
		sca = new Scanner(System.in);
		bList = new BillboardList(); 
	}
	
	public static void main(String[] args){
		
		AdministrationSystem adS = new AdministrationSystem(); 
		
		System.out.println("=========================="); 
		System.out.println("         Welcome          \n");
		
		int option = -1; 
		do {
			option = adS.menuOption(); 
			adS.menu(option);
		}while(option!=0);
		
		
	} 
	
	public int menuOption() {
		int option = 0; 
		
		System.out.println("Enter the option that do you want\n");
		System.out.println("Menu option\n"+
						"(1) Data import\n"+
						"(2) Add billboard\n"+
						"(3) Show billboards\n"+
						"(4) Export hazard report\n"+
						"(0) Close program\n");
		
		try {
			option = sca.nextInt(); 
		}catch(Exception e) {
			e.printStackTrace();
			option = -1;
		}
		sca.nextLine(); 
		return option; 
	}
	
	public void menu(int optionUser) {
		
		switch(optionUser){
		case 0: 
			System.out.println("****Bye****");
			break; 
		case 1: 
			System.out.println("Hola 1");
			break; 
		case 2:
			String data =dataBillboard();
			Billboard obj =bList.creatBillboard(data);
			bList.addBillboard(obj);
		
			break; 
		case 3: 
			System.out.println("Hola 3");
			break; 
		case 4: 
			System.out.println("Hola 4");
			break; 
		default: 
			System.out.println("Incorrect option");
			break; 
		}
	}
	
	public void dataImport() {
		String url = ""; 
		
		System.out.println("");
		
	}
	
	public void registerBillboard() {
		double height = 0.0; 
		double width = 0.0; 
		double area = 0.0; 
		boolean inUse = false;
		String brand = "";
		
		try {
			
			System.out.println("Enter the following data:\n");
			System.out.print("Height: ");
			height = sca.nextDouble(); 
			System.out.print("width: ");
			width = sca.nextDouble();
			//area calculate, in cm2
			area = height*width;
			//billboard state
			inUse = stateBillboard();
			sca.nextLine();
			System.out.print("Brand: ");
			brand = sca.nextLine(); 
			System.out.println("\n");
			bList.addBillboard(height,width,area,inUse,brand);
			System.out.println(bList.getBillboards().get(0).toString());
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public boolean stateBillboard() {
		boolean state = false; 
		int option = 1; 
		boolean flag = false;
		
		do {
		
			try {
				do {
					System.out.println("The billboard is in use?\n"+
								"(1) Yes\n"+
								"(2) No\n");
					sca.nextLine();
					option = sca.nextInt();
					switch(option) {
					case 1:
						state = true;
						break;
					case 2:
						state = false;
						break;
					default:
						System.out.println("Incorrect option");
						break; 
					}
					flag = false;
				}while(option>2||option<1);
			
			}catch(Exception e) {
				e.printStackTrace();
				flag = true;
			}
		}while(flag==true);
		
		return state;
	}
	
	public String dataBillboard() {
		String info = "";
		
		System.out.println("Enter the following data with sepatation ++ betwwen them\n"+
		"for example: heigt++width++inUse++brand");
		
		info = sca.nextLine(); 
		return info; 
	}
	
}
