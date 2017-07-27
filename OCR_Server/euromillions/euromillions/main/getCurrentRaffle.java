package euromillions.main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class getCurrentRaffle {
	private ArrayList<Integer> raffleNumbers;
	private ArrayList<Integer> raffleStars;
	private String currentJackpot;
	private String nextJackpot;
	private String date;
	private ArrayList<JSONObject> prizes;

	public getCurrentRaffle(){


		HttpResponse<JsonNode> response = null;

		try {
			response = Unirest.get("https://euromillions.p.mashape.com/ResultsService/FindLast")
					.header("X-Mashape-Key", "pNthJTO0hImshyiD0c7YELd8dVigp1LjGI4jsnEjxggAHgENuq")
					.header("Accept", "text/plain")
					.asJson();

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject body = response.getBody().getObject();

		this.raffleNumbers = getRaffleNumbers(body);
		this.raffleStars = getRaffleStars(body);
		this.currentJackpot = getCurrentJackpot(body);
		this.nextJackpot = getNextJackpot(body);
		this.date = getRaffleDate(body);
		this.prizes = getPrizeCombination(body);

	}

	private ArrayList<Integer> getRaffleNumbers(JSONObject body){
		ArrayList<Integer> raffleNumbersList= new ArrayList<Integer>();
		raffleNumbersList.add(Integer.parseInt(body.get("Num1").toString()));
		raffleNumbersList.add(Integer.parseInt(body.get("Num2").toString()));
		raffleNumbersList.add(Integer.parseInt(body.get("Num3").toString()));
		raffleNumbersList.add(Integer.parseInt(body.get("Num4").toString()));
		raffleNumbersList.add(Integer.parseInt(body.get("Num5").toString()));

		return raffleNumbersList;
	}

	private ArrayList<Integer> getRaffleStars(JSONObject body){
		ArrayList<Integer> raffleStarList= new ArrayList<Integer>();
		raffleStarList.add(Integer.parseInt(body.get("Star1").toString()));
		raffleStarList.add(Integer.parseInt(body.get("Star2").toString()));

		return raffleStarList;
	}

	private String getCurrentJackpot(JSONObject body){
		return body.get("Jackpot").toString();
	}

	private String getNextJackpot(JSONObject body){
		return body.get("NextJackpot").toString() ;
	}

	private String getRaffleDate(JSONObject body){


		String st = body.get("Date").toString().substring(6, 19);
		BigInteger aa = new BigInteger(st);

		Calendar calendar = Calendar.getInstance();
		long aaa = aa.longValue();
		calendar.setTimeInMillis(aaa);


		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH);
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		int wDay = calendar.get(Calendar.DAY_OF_WEEK);
		String wDayT = null;

		switch(wDay){
		case 1: 
			wDayT = "Sunday";
			break;
		case 2:  
			wDayT = "Monday";
			break;
		case 3: 
			wDayT = "Tuesday";
			break;
		case 4: 
			wDayT = "Wednesday";
			break;
		case 5: 
			wDayT = "Thursday";
			break;
		case 6: 
			wDayT = "Friday";
			break;

		case 7: 
			wDayT = "Saturday";
			break;
		}

		return wDayT+" "+mDay+"/"+mMonth+"/"+mYear;
	}

	private ArrayList<JSONObject> getPrizeCombination(JSONObject body){
		
		String fullstring = body.get("PrizeCombinations").toString();
		fullstring = fullstring.replace("[", "");
		fullstring = fullstring.replace("]", "");
		fullstring = fullstring.replace("},{", "} {");
		String[] allprizes = fullstring.split(" ");
		ArrayList<JSONObject> prizes = new ArrayList<JSONObject>();

		for(String b : allprizes){
			JSONObject pr = new JSONObject(b);
			prizes.add(pr);
			
		}
		return prizes;
	}
	
	public ArrayList<Integer> getRaffleNumbers() {
		return raffleNumbers;
	}

	public ArrayList<Integer> getRaffleStars() {
		return raffleStars;
	}

	public String getCurrentJackpot() {
		return currentJackpot;
	}

	public String getNextJackpot() {
		return nextJackpot;
	}

	public String getDate() {
		return date;
	}

	public String toString(){
		String numbers = "";
		for(int k : raffleNumbers){
			numbers = numbers + " "+k;
		}

		String stars = "";
		for(int kk : raffleStars){
			stars = stars + " "+kk;
		}
		return "Raffle Date: " + date
				+ "\nNumbers: " + numbers 
				+ "\nStars: " + stars
				+ "\nCurrent Jackpot: " + currentJackpot
				+ "\nNext Jackpot: " + nextJackpot;
	}
	public ArrayList<JSONObject> getPrizes() {
		return prizes;
	}
}



