package fr.eql.ai109.salonVin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.sun.org.apache.xml.internal.security.signature.Reference;

public class vin {

	public static int domaineTaille = 0;
	public static int appellationTaille = 0;
	public static int regionTaille = 0;
	public static int refTaille = 0;
	public static int surfaceTaille = 0;
	public static int standTaille = 0;
	public static int nmbLine = 0;
	public static char[] domaine = null;
	public static char[] appellation = null;
	public static char[] region = null;
	public static char[] ref = null;
	public static char[] surface = null;
	public static char[] stand = null;

	public static void main(String[] args) {

		FileReader fileVins = null;
		FileReader fileVins2 = null;
		try {
			fileVins = new FileReader("c:/TP/VINStp.DON");
			int caractere = 0;
			String chaine = "";
			chaine = determinerTailleChamps(caractere, fileVins);
			fileVins.close();
			compteLigne();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int caractere = 0;
		ajoutTableau(caractere, fileVins2);

	}

	private static String determinerTailleChamps(int caractere, FileReader fileVins) throws IOException {
		String chaine = null;
		while ((caractere = fileVins.read()) != -1) {
			chaine += (char) caractere;

			if (caractere == '\n') {
				String[] str = chaine.split("\t");
				if (domaineTaille < str[0].length()) {
					domaineTaille = str[0].length();
				}
				if (appellationTaille < str[1].length()) {
					appellationTaille = str[1].length();
				}
				if (regionTaille < str[2].length()) {
					regionTaille = str[2].length();
				}
				if (refTaille < str[3].length()) {
					refTaille = str[3].length();
				}
				if (surfaceTaille < str[4].length()) {
					surfaceTaille = str[4].length();
				}
				if (standTaille < str[5].length()) {
					standTaille = str[5].length() - 2;
				}
				chaine = "";

			}
		}
		return chaine;
	}

	private static void compteLigne() {
		FileReader fileVins2;
		try {

			fileVins2 = new FileReader("c:/TP/VINStp.DON");
			int caractere2 = 0;
			String chaine2 = "";
			while ((caractere2 = fileVins2.read()) != -1) {
				chaine2 += (char) caractere2;
				if (caractere2 == '\n') {
					nmbLine++;
				}
			}
			try {
				fileVins2.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void ajoutTableau(int caractere, FileReader fileVins) {
		String chaine = null;
		domaine = new char[domaineTaille];
		appellation = new char[appellationTaille];
		region = new char[regionTaille];
		ref = new char[refTaille];
		surface = new char[surfaceTaille];
		stand = new char[standTaille];

		int flag = nmbLine;

		try {
			fileVins = new FileReader("c:/TP/VINStp.DON");
			while ((caractere = fileVins.read()) != -1) {
				chaine += (char) caractere;
				// System.out.println(chaine);
				if (caractere == '\n') {

					String[] str = chaine.split("\t");
					for (int i = 0; i < str.length; i++) {

						for (int j = 0; j < str[i].length(); j++) {
							if (str[i].equals("")) {
								System.out.print(str[i].charAt(j));

								if (i == 0) {
									domaine[j] = str[i].charAt(j);
								} else if (i == 1) {
									appellation[j] = str[i].charAt(j);
								} else if (i == 2) {
									region[j] = str[i].charAt(j);
								} else if (i == 3) {
									ref[j] = str[i].charAt(j);
								} else if (i == 4) {
									surface[j] = str[i].charAt(j);
								} else if (i == 5) {
									stand[j] = str[i].charAt(j);
								}
							}
						}
						
						System.out.print(" ");
					}
					chaine = "";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println(chaine);
		}

	}
}
