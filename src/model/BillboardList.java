package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BillboardList {
	
	List<Billboard> billboards;
	
	private final String FILE_BILLBOARDS = ".\\files\\BillboardData.txt";

	public BillboardList() {
		billboards = new ArrayList<Billboard>();  
	}
	
	public void addBillboard(double height,double width,double area, boolean inUse, String brand) throws IOException {
		Billboard obj = new Billboard(height,width,area,inUse,brand);
		billboards.add(obj);
	}
	public void addBillboard(Billboard billboard) throws IOException {
		billboards.add(billboard);
	}
	public List<Billboard> getBillboards(){
		return billboards; 
	}
	public void setBillboards(List<Billboard> billboards) {
		this.billboards = billboards; 
	}
	
	public Billboard creatBillboard(String info) {
		Billboard obj = null; 
		String separador ="\\+\\+";
		String[] infoArray = info.split(separador); 
		double height = 0.0; 
		double width = 0.0; 
		double area = 0.0; 
		boolean inUse = false;
		String brand = "";
		
		try {
			height = Double.parseDouble(infoArray[0]);
			width = Double.parseDouble(infoArray[1]);
			area = height*width;
			inUse = Boolean.parseBoolean(infoArray[2]);
			brand = infoArray[3];
			obj = new Billboard(height,width,area,inUse,brand);
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	public String prueba() {
		String info = billboards.get(0).toString(); 
		return info; 
	}
	
	public void loadBillboards(String url) throws IOException {
		
		
		File file;  
		FileReader fr = null;
		BufferedReader document = null; 
		try {
			file= new File(url);
			fr = new FileReader(file);
			document = new BufferedReader(fr);
			
			document.readLine();
			
			while(document.ready()) {
				String obj = document.readLine();
				String [] data = obj.split("\\|");
				
				
				double height = Double.parseDouble(data[0]);
				double width = Double.parseDouble(data[1]);
				double area = height*width;
				boolean inUse = Boolean.parseBoolean(data[2]);
				String brand = data[3]; 
				addBillboard(height,width,area,inUse,brand);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
			document.close();
			fr.close();
	}
	
	public void serializar() throws IOException {
		File file = new File(FILE_BILLBOARDS);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(getBillboards());
		
		oos.close();
		fos.close();		
	}
	public void deserializar() throws ClassNotFoundException, IOException {
		File file = new File(".\\files\\BillboardData.txt");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ArrayList<Billboard> obj = (ArrayList<Billboard>)ois.readObject();
		billboards.addAll(obj);
		ois.close();
		fis.close();
	}
	
	public String listBillboards() {
		String answer= "";
		answer+="w		h		inUse		Brand\n";
		for (int i = 0; i<billboards.size(); i++) {
			answer+=(billboards.get(i).getWidth()+"		"+billboards.get(i).getHeight()+"		"+billboards.get(i).getInUse()+"		"+billboards.get(i).getBrand()+"		\n");;
			
		}
		answer+="TOTAL: "+ billboards.size() + " Billboards";
		return answer;
	}
	
	public String reportBillboard() {
		String report = ""; 
		
		report += "===============================\n";
		report += "   DANGEROUS BILLBOARD REPORT  \n";
		report += "===============================\n";
		report += "The dangerous billboard are:\n";
		int j = 1; 
		if(billboards.size()==0) {
			report += "        None               \n";
		}else {
			for(int i = 0; i<billboards.size();i++) {
				if(billboards.get(i).getArea()>=200000) {
					report+= (j) + ". " + "Billboard " + billboards.get(i).getBrand() + " with area " + billboards.get(i).getArea() + " cm2\n";
					j++;
				}
			}
			if(j==1) {
				report += "            None            \n";
			}
		}
		return report; 
	}
	
	public void writeReport(String report) throws IOException {
		File file = new File(".\\files\\ReportDangerousBillboard.txt");
		
		FileWriter fw = new FileWriter(file);
		
		BufferedWriter output = new BufferedWriter(fw);
		
		output.write(report);
		output.newLine();
		output.close();
		fw.close();
	}
	
}
