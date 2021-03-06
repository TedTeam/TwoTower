package tedteam.twotowers.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Ez az osztaly felelos a szekvenciak megfelelo formatumu kilistazasaert.
 */
public class Logger {
	public static int count = 0;
	public String param = "";
	

	/**
	 * Itt taruljuk a kiirando sorokat.
	 */
	private static ArrayList<String> lines = new ArrayList<String>();

	/**
	 * Metodusok belepesi pontja.
	 * @param obj: az objektum neve
	 * @param method: a hivott fuggveny
	 * @param param1: elso parameter, ha nincs akkor ures string
	 * @param param2: masodik parameter, ha nincs akkor ures string
	 */
	public static void enter(String obj, String method, String param1,
			String param2) {
		count++;
		String param = checkParam(param1, param2);

		
		StringBuilder actualLine = new StringBuilder();
		
		for (int i = 1; i < count; i++) {
			actualLine.append("   ");

		}

		actualLine.append("-> " + obj + "." + method + "(" + param + ")");
		
		lines.add(actualLine.toString());

	}
	
	/**
	 * Metodusok kilepesi pontja.
	 * @param retType: a metodus visszateresi erteke
	 */
	public static void exit(String retType) {
		count--;
		
		StringBuilder actualLine = new StringBuilder();
		
		for (int i = 0; i < count; i++) {
			actualLine.append("   ");
			
		}

		actualLine.append("<- " + retType);
		
		lines.add(actualLine.toString());

	}

	/**
	 * Ezzel a metodussal ellenorizzuk a parametereket a listazashoz.
	 * @param param1: elso parameter, ha nincs akkor ures string
	 * @param param2: masodik parameter, ha nincs akkor ures string
	 * @return parameter ertekeknek megfelelo string
	 */
	public static String checkParam(String param1, String param2) {
		String param;
		if (param1.equals("") & param2.equals("")) {
			param = "";
		} else if (param2.equals("")) {
			param = param1;
		} else {
			param = param1 + "," + param2;
		}
		return param;
	}
	
	/**
	 *  Parameterkent kapott kerdest kiirja, illetve visszater 
	 * a felhasznalo altal megadott valasszal.
	 * @param question: a kerdes, mely a kepernyore kiirodik
	 * @return felhasznalo IGEN-nel valaszolt --> true, NEM-mel --> false
	 */
	public static boolean question(String question) {
		System.out.println(question+"\n I/N?");
		boolean correct = false;
		while(!correct) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				String s1 = br.readLine();
				if(s1.equals("I") || s1.equals("i"))
					return true;
				else if(s1.equals("N") || s1.equals("n"))
					return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Az eltarolt sorok torlese
	 */
	public static void resetLines() {
		lines.clear();
	}
	
	/**
	 * Kiirja az eltarolt sorokat.
	 */
	public static void printLines() {
		for(String s : lines) {
			System.out.println(s);
		}
	}

}
