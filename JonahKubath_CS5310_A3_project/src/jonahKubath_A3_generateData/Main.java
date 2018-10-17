package jonahKubath_A3_generateData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Generating Data");
		int numFiles = 10;
		int increment = 10;
		Random rand = new Random();
		
		for(int i = 0; i < numFiles; i++) {
			File f = new File("./data/data_" + i + ".txt");
			FileWriter fw = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(fw);
			
			for(int j = 0; j < (increment * (i + 1)); j++) {
				String temp = j + "," + rand.nextInt(increment * (i + 1));
				//System.out.println(temp);
				writer.write(temp + "\n");
			}
			writer.close();
		}
		
		System.out.println("Done");
	}

}
