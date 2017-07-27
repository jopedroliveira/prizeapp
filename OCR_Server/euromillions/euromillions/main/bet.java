package euromillions.main;

import java.util.ArrayList;

public class bet {
	private int num1;
	private int num2;
	private int num3;
	private int num4;
	private int num5;
	private int star1;
	private int star2;
	private ArrayList<Integer> splitedcontentInt = new ArrayList<Integer>();
	
	public bet(String content){
		String[] splitedcontentString = content.split(" ");
		
		for(String b : splitedcontentString){
			splitedcontentInt.add(Integer.valueOf(b));
		}
		this.num1 = splitedcontentInt.get(0);
		this.num2 = splitedcontentInt.get(1);
		this.num3 = splitedcontentInt.get(2);
		this.num4 = splitedcontentInt.get(3);
		this.num5 = splitedcontentInt.get(4);
		this.star1 = splitedcontentInt.get(5);
		this.star2 = splitedcontentInt.get(6);
	}

	

	public void setStar2(int star2) {
		this.star2 = star2;
	}

	public ArrayList<Integer> getNumbers(){
		ArrayList<Integer> n = new ArrayList<Integer>();
		n.add(num1);
		n.add(num2);
		n.add(num3);
		n.add(num4);
		n.add(num5);
		return n;
		
	}
	
	public ArrayList<Integer> getStars(){
		ArrayList<Integer> st = new ArrayList<Integer>();
		st.add(star1);
		st.add(star2);
		return st;
	}
	
	public String toString(){
		String rs = "N: "+num1+" "+num2+" "+num3+" "+num4+" "+num5+" S: "+star1+" "+star2;
		return rs;
	}

}
