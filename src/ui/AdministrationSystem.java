package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import model.*; 

public class AdministrationSystem {
	
	private Scanner sca; 
	private BillboardList bList; 
	
	public AdministrationSystem() throws ClassNotFoundException, IOException {
		sca = new Scanner(System.in);
		bList = new BillboardList(); 
		bList.deserializar();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
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
	
	@SuppressWarnings("unused")
	public void menu(int optionUser) throws IOException, ClassNotFoundException {
		
		switch(optionUser){
		case 0: 
			System.out.println("****Bye****");
			bList.serializar();
			break; 
		case 1: 
			String fileImport = dataImport();
			bList.loadBillboards(fileImport); 
			System.out.println("Data import with successfully ^-^\n");
			break; 
		case 2:
			String data =dataBillboard();
			Billboard obj =bList.creatBillboard(data);
			bList.addBillboard(obj);
			System.out.println("Billboard add with successfully ^-^\n");
			break; 
		case 3: 
			System.out.println(bList.listBillboards());
			
			break; 
		case 4: 
			String report = bList.reportBillboard();
			System.out.println(report);
			bList.writeReport(report);
			System.out.println("\n\n¡Report saved with successfully ^-^!\n");
			
			break; 
		default: 
			System.out.println("Incorrect option");
			break; 
		}
	}
	/**
	 * dataImport method
	 * Este método recibe la url del CVS a importar.
	 *  
	 * */
	public String dataImport() {
		String  url = ""; 
 
		System.out.println("Please, enter the url of the file to import");
		
		System.out.println("For example, enter this path: .\\\\files\\\\Datos1.csv\n");
		
		url = sca.nextLine(); 		
		
		return url;
	}
	
	/**
	 * dataBillboard
	 * Este método recibe los datos del Billboard a registrar.
	 * */
	public String dataBillboard() {
		String info = "";
		
		System.out.println("Enter the following data with sepatation ++ betwwen them\n"+
		"for example: heigt++width++inUse++brand");
		
		info = sca.nextLine(); 
		return info; 
	}
	
	
}
