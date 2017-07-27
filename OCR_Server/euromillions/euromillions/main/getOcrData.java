package euromillions.main;
/*
 * ----------------------------------------------
 * This class is ready to run an OCR - Optical Digit Recognition -. 97% of accuracy was the last performed result.
 * This operation is based on Tesseract API (https://github.com/tesseract-ocr) an open source API that contains an OCR engine, trained and validated, 
 * ready to run. 
 * This is a JAVA integration of Tesseract OCR which allow the main program to obtain all bets from an Euromillion ticket. It returns an ArrayList<bet>.
 * See bet description
 * 
 * Developed by J. Pedro Oliveira  
 * 		j.pedrodiasoliveira@gmail.com
 * 		2015
 * ----------------------------------------------

 */


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSeparatorUI;

import net.sourceforge.tess4j.*;

public class getOcrData {

	public static ArrayList<bet> getOcrData(File imageFile){


		ArrayList<bet> bets = new ArrayList<bet>();	
		Tesseract instance = (new Tesseract()).getInstance();

		try {

			String result = instance.doOCR(imageFile);
			result = result.replaceAll("\\r|\\n", "");
			result = result.replaceAll("[-+.^:,'‘~]","");
			result = result.replaceAll("E |E| E | E"," E ");
			result = result.substring(result.indexOf("N"));

			String[] totalBets = result.split("(?=N)"); 


			for(String str : totalBets){

				String superBet = str.substring(str.indexOf("N")+1,((int) str.indexOf("E") +7));

				superBet = superBet.replaceAll(" E ", " ");
				superBet = superBet.replaceAll("O|o", "0");
				superBet = superBet.substring(1, superBet.length());

				bet bt = new bet(superBet);
				bets.add(bt);

			}

		} catch (TesseractException e) {
			System.err.println(e.getMessage());
			bets = null;
		}
		return bets;

		
	}

	
}