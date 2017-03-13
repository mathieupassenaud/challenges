package fr.mathieupassenaud.busroutechallenge.decoder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import fr.mathieupassenaud.busroutechallenge.model.Routes;

public class InputFileDecoder {

	private BufferedReader br;
	
	public InputFileDecoder(String fileName) throws IOException{

		InputStream fis = new FileInputStream(fileName);
	    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	    br = new BufferedReader(isr);
	    decode();
	}

	public int getNumberOfRoutes() throws IOException{
		// la première ligne est le nombre de routes
		String numberOfBusRoutes = br.readLine();
        numberOfBusRoutes = numberOfBusRoutes.replaceAll("\\s+","");
        return new Integer(numberOfBusRoutes);
	}
	
	public void decode() throws IOException{
		String line;
		int numberOfRoutes = getNumberOfRoutes();
		for(int i = 0 ; i < numberOfRoutes ; i++){
			//read one line
			line = br.readLine();
			
			String[] lineAsStringArray = line.split(" ");
			// le premier caractère représente le numéro de la route
			int routeNumber = new Integer(lineAsStringArray[0]);
			
			
			// build a table with nodes names
			int[] nodes = new int[lineAsStringArray.length -1];
			// chaque caratère suivant représente un numéro de station
			for(int j = 1 ; j <  lineAsStringArray.length ; j++){
				// here we got nodes
				nodes[j-1] = new Integer(lineAsStringArray[j]);
			}
			//with this table, build all routes possible
			new Routes().buildRoutes(nodes);
			
		}
	}
}
