package euromillions.main;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class tests {

	public static void main(String[] args) throws UnirestException {
		File imageFile = new File("IMG/euromPT_2.png");
		ArrayList<bet> userBets = getOcrData.getOcrData(imageFile);

		for(bet i : userBets){
			System.out.println(i.toString());
		}
	}
}