package model;

import java.util.ArrayList;

public class BillboardList {
	
	ArrayList<Billboard> billboards;
	
	public BillboardList() {
		billboards = new ArrayList<Billboard>();  
	}
	
	public void addBillboard(double height,double width,double area, boolean inUse, String brand) {
		Billboard obj = new Billboard(height,width,area,inUse,brand);
		billboards.add(obj);
	}
	public void addBillboard(Billboard billboard) {
		billboards.add(billboard);
	}
	public ArrayList<Billboard> getBillboards(){
		return billboards; 
	}
	public void setBillboards(ArrayList<Billboard> billboards) {
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
}
