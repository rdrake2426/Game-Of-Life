package gameoflife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Interface {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][] grid = null;
		
		
		
		boolean run = true;
		
		while (run) { // menu loop
			run = false;
			System.out.println("Choose an Option:\n1: Grid from file\n2: Random Grid: ");
			int option = scan.nextInt();
			
			switch (option) {
				case 1:
				try {
					grid = fileGrid(); // create grid from file "grid.txt"
				} catch (IOException e) {
					e.printStackTrace();
				}
					break;
					
				case 2:
					System.out.print("\nEnter size x: ");
					int sizex = scan.nextInt();
					System.out.print("\nEnter size y: ");
					int sizey = scan.nextInt();
					grid = randomGrid(sizex, sizey); //Creating random grid of set sizes
					break;
					
				default:
					System.out.print("Invalid\n");
					run = true;
			}
		}
		
		
		
		System.out.print("\nEnter Iteration amount: ");
		int count = scan.nextInt();
						
		System.out.print("Iteration 0:\n");
		print(grid);
		
		for (int i = 1; i <= count; i++) {
			grid = runlife(grid);
			System.out.print("\nIteration " + i + ":\n");
			print(grid);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		scan.close();
	}
	
	public static int[][] fileGrid() throws IOException{
		String fileName = "grid.txt";
		ArrayList<String> gridArray = new ArrayList<String>();
		
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null){
		   gridArray.add(line);
		}
		br.close();
		int x = gridArray.get(1).length();
		int y = gridArray.size();
		
		int[][] grid = new int[y+2][x+2];
		for (int i = 1; i < y+1; i++) {		
			for (int j = 1; j < x+1; j++) {
				grid[i][j] = Character.getNumericValue(gridArray.get(i-1).charAt(j-1));
			}
		}
		return grid;	
	}
	
	public static int[][] randomGrid(int x, int y){
		Random ran = new Random();
		int[][] grid = new int[y+2][x+2];
		for (int i = 1; i < y+1; i++) {		
			for (int j = 1; j < x+1; j++) {
				grid[i][j] = ran.nextInt(2);
			}
		}
		return grid;	
	}
	
	public static int[][] runlife(int[][] life){
		int[][] newlife = new int[life.length][life[1].length];

		for (int i = 1; i < life.length-1; i++) {
			for (int j = 1; j < life[i].length-1; j++) {
				for (int y = i-1; y <= i+1; y++) {
					for (int x = j-1; x <= j+1; x++) {
						if (life[y][x] != 0) {
							newlife[i][j]++;
						}
					}
				}
				if (life[i][j] == 1)
					newlife[i][j]--;
			}
			
		}
		
		for (int i = 1; i < life.length-1; i++) {
			for (int j = 1; j < life[i].length-1; j++) {
				if ((newlife[i][j] >=2 && newlife[i][j] <=3 && life[i][j] == 1) || (life[i][j] == 0 && newlife[i][j] == 3)) {
					newlife[i][j] = 1;
				}
				else
					newlife[i][j] = 0;
			}
		}
		return newlife;	
	}
	
	
	public static void print(int[][] grid){
		System.out.print("\n");
		for (int i = 1; i < grid.length-1; i++) {
			for (int j = 1; j < grid[i].length-1; j++) {
				if (grid[i][j] == 1)
					System.out.print(" #");
				else
					System.out.print(" -");
			}
			System.out.print("\n");
		}
	}

}
