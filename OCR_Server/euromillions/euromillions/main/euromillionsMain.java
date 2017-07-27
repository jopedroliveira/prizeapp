package euromillions.main;

import java.io.File;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.json.JSONObject;

public class euromillionsMain {
	public static void main(String args[]){

		//Get user bet ticket info
		File imageFile = new File("IMG/euromPT_2.png");
		ArrayList<bet> userBets = getOcrData.getOcrData(imageFile);


		//Get current raffle results | roll roll roll
		getCurrentRaffle currentResults = new getCurrentRaffle();

		//Get numbers and stars   
		ArrayList<Integer> cr_n = currentResults.getRaffleNumbers();
		ArrayList<Integer> cr_s = currentResults.getRaffleStars();
		//Get prizes combination  
		ArrayList<JSONObject> prizes = currentResults.getPrizes();


		//Get each bet combinations
		ArrayList<Integer[]> finalResults = new ArrayList<Integer[]>();

		for(bet ub : userBets){
			int nIn = 0;
			int sIn = 0;
				
			System.out.println(ub.toString());
			
			for(int ynum : ub.getNumbers()){
				if(cr_n.contains(ynum)){
					nIn++;
				}
			}

			for(int yst :ub.getStars()){
				if(cr_s.contains(yst)){
					sIn++;
				}
			}

			Integer[] atual = {nIn,sIn};
			finalResults.add(atual);
		}



		//Each bet prize 
		ArrayList<Integer> prizeeCombinationIndex = new ArrayList<Integer>();

		for(Integer[] comb : finalResults){
			int prize1 = 13;
			if(comb[0]==5 && comb[1]==2){
				prize1 = 0;				
			} else if(comb[0]==5 && comb[1]==1){
				prize1 = 1;				
			} else if(comb[0]==5 && comb[1]==0){
				prize1 = 2;				
			} else if(comb[0]==4 && comb[1]==2){
				prize1 = 3;				
			} else if(comb[0]==4 && comb[1]==1){
				prize1 = 4;
			} else if(comb[0]==4 && comb[1]==0){
				prize1 = 5;
			} else if(comb[0]==3 && comb[1]==2){
				prize1 = 6;
			} else if(comb[0]==2 && comb[1]==2){
				prize1 = 7;
			} else if(comb[0]==3 && comb[1]==1){
				prize1 = 8;
			} else if(comb[0]==3 && comb[1]==0){
				prize1 = 9;
			} else if(comb[0]==1 && comb[1]==2){
				prize1 = 10;
			} else if(comb[0]==2 && comb[1]==1){
				prize1 = 11;
			} else if(comb[0]==2 && comb[1]==0){
				prize1 = 12;
			}
			prizeeCombinationIndex.add(prize1);

		}

		System.out.println("************************************");
		System.out.println("N:"+currentResults.getRaffleNumbers()+" S:"+currentResults.getRaffleStars());
		System.out.println("************************************\n");
		Double totalWon = 0.0;

		for(int prize = 0; prize<prizeeCombinationIndex.size();prize++){
			if(prizeeCombinationIndex.get(prize) < prizes.size()){

				System.out.println(userBets.get(prize).toString());
				JSONObject pr = prizes.get(prizeeCombinationIndex.get(prize));
				System.out.println("You won: "+ pr.get("Prize") + "€. "
						+"You hit "+ pr.get("Numbers") +" numbers, "
						+" "+ pr.get("Stars") + " stars," 
						+" and there were other "+ pr.get("Winners") +" winners.");
				totalWon = totalWon + Double.parseDouble(pr.get("Prize").toString());

			} else {
				// Do nothing
			}
		}
		System.out.println("\n\nTotal amount: "+totalWon);


 
	}
}

