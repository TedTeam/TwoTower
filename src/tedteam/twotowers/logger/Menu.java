package tedteam.twotowers.logger;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tedteam.twotowers.main.Generator;


public class Menu {

	public int choice;

	public void handleChoice() {
		Logger.count=0;
		
		switch (choice) {

		case 1:
			Generator g = new Generator();
			g.buildField();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;
		case 11:
			break;

		}
	}

	public void printMenu() throws IOException {

		System.out.println("-------- Menu --------");
		System.out.println();

		System.out.println("(  1 ) P�lya fel�p�t�se");
		System.out.println("(  2 ) J�t�k�ll�s ellen�rz�se");
		System.out.println("(  3 ) Akad�ly �p�t�se");
		System.out.println("(  4 ) Torony �p�t�se");
		System.out.println("(  5 ) K� akad�lyra helyez�se");
		System.out.println("(  6 ) K� toronyra helyez�se");
		System.out.println("(  7 ) Akad�ly hat�sa");
		System.out.println("(  8 ) Torony l�v�s");
		System.out.println("(  9 ) Ellens�g gener�l�s");
		System.out.println("( 10 ) L�ptet�s");
		System.out.println("( 11 ) Ellens�g sebz�s");
		System.out.println();

		System.out.println("K�rlek v�lassz men�pontot: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		try {
			choice = Integer.parseInt(s1);
			if (choice > 11 | choice < 1) {
				System.err.println("Invalid Format!");
				String s2 = br.readLine();
				choice = Integer.parseInt(s2);
			}
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Format!");
		}
		System.out.println(choice);

	}

}
