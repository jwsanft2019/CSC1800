//Created by Jake Sanft

package bingoCheck;

import java.io.*;
import java.io.File;
import java.util.*;

public class check {
	//public arrays I'll need in the program
	static int[][] pattern;
	static int[] calledNumbers;
	static int[][] bingoCard;

	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		// 	initial print statements were before program read file correctly
			 System.out.println("pattern");
		pattern = new int[5][5];
		for (int row = 0; row < pattern.length; row++) {
			for (int col = 0; col < pattern.length; col++) {
				pattern[row][col] = scan.nextInt();
			}
		}
			 System.out.println("numbers");
		// have to use string because calledNumbers has different values depending on file
		String s = scan.nextLine();
		calledNumbers = new int[s.length()];
		for (int i = 0; i < calledNumbers.length; i++) {
			calledNumbers[i] = Integer.parseInt(s);
		}

		bingoCard = new int[5][5];
			 System.out.println("bingoNumbers");
		for (int row = 0; row < pattern.length; row++) {
			for (int col = 0; col < pattern.length; col++) {
				bingoCard[row][col] = scan.nextInt();
			}
		}
		scan.close();
		//close scanner

		//check if card has to be run through crazyCheck or bingoCheck
		int check = 0;
		for (int row = 0; row < 5; row ++) {
			for (int col = 0; col < 5; col++) {
				if (pattern[row][col] == 4) {
					check = 1;
				}
			}
		}
		if (check == 1) {
			crazyCheck();
		} else {
			bingoCheck();
		}
		//checking pattern and for debugging purposes
			 for (int row = 0; row < pattern.length; row++) {
				 for (int col = 0; col < pattern.length; col++) {
						 System.out.print(pattern[row][col]);
				 }
			 }
			 System.out.println("///////////////");
			 for (int row = 0; row < pattern.length; row++) {
				 for (int col = 0; col < pattern.length; col++) {
						 System.out.print(bingoCard[row][col]);
				 }
			 }
		System.out.println(bingoCheck());
		if (!(bingoCheck() == false) || (crazyCheck() == false)) {
			System.out.println("Valid Bingo");
		} else {
			System.out.println("No Bingo");
		}
		System.out.println("done");
	}

	private static boolean crazyCheck() {
		// TODO Auto-generated method stub
		//rotate pattern and make it a new file, make each value that is changed equal to four
		for (int rotate = 0; rotate < 3; rotate++) {
			for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 5; col++) {
					for (int i = 0; i < calledNumbers.length; i++) {
						if (calledNumbers[i] == bingoCard[row][col]) {
							bingoCard[row][col] = 4;
						}
					}
				}
			}
			int[][] newPattern = new int[5][5];
			for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 5; col++) {
					newPattern[row][col] = pattern[col][row];
				}
			}
			pattern = newPattern;
		}
		//flag to check if true or false
		boolean flag = false;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if ((pattern[row][col] == 4) && (bingoCard[row][col] != 4)) {
					flag = false;
				} else {
					flag = true;
				}
			}
		}
		return flag;
	}

	private static boolean bingoCheck() {
		// check for regular bingo patterns
		// TODO Auto-generated method stub
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				for (int i = 0; i < calledNumbers.length; i++) {
					if (calledNumbers[i] == bingoCard[row][col]) {
						//make all found values equal to 1
						bingoCard[row][col] = 1;
					}
				}
			}
		}
		// all unidentified values equal 0
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (bingoCard[row][col] != 1) {
					bingoCard[row][col] = 0;
				}
			}
		}
		//check to see if pattern matches card
		boolean flag = false;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if ((pattern[row][col] == 1) && (bingoCard[row][col] != 1)) {
					flag = false;
				} else {
					flag = true;
				}
			}
		}
		return flag;
	}
}
