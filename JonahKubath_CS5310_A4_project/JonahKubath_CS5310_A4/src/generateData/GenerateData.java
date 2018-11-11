package generateData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateData {

	public static void main(String[] args) throws IOException {
		
		int weightMax = 100;
		int numberOfNodes = 10;
		int changeOfNodesPerRun = 10;
		int runs = 10;
		
		Random rand = new Random();
		
		String folder = "./data_10_100/";
		String fileBase = "data_";
		String filename = "";
		
		for(int a = 0; a < runs; a++) {
			
			filename = folder + fileBase + a + ".txt";
			System.out.println("File " + filename);
			File f = new File(filename);
			FileWriter fw = new FileWriter(f);
			fw.write(numberOfNodes + (changeOfNodesPerRun * a) + "\n");
			fw.write(1 + "\n");
			
			for(int i = 0; i < numberOfNodes + (changeOfNodesPerRun * a); i++)
			{
				for(int j = 0; j < numberOfNodes + (changeOfNodesPerRun * a); j++)
				{
					if(rand.nextBoolean()) {
						fw.write(rand.nextInt(weightMax) + "");
					}
					if(j < numberOfNodes + (changeOfNodesPerRun * a) - 1) {
						fw.write(",");
					}
				}
				fw.write("\n");
			}
			
			fw.close();
		}
		

	}

}
