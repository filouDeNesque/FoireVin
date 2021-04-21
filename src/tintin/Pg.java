package tintin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

public class Pg {

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
	public static int position = 0;
	public static String cheminSource = "c:/TP/VINStp.DON";
	public static String cheminCible = "c:/TP/fichierCible.DON"; // modifier
	public static String[] stringstr = null;

	// ------------------------------------------------------------

	public static void main(String[] args) {
		// initialisation des variables
		FileReader fileVins = null;
		FileReader fileVins2 = null;
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedReader br3 = null;
		RandomAccessFile raf = null;
		RandomAccessFile raf2 = null;
		RandomAccessFile raf3 = null;
		// Mise en forme du fichier structure
		// -------------------------------------------------------------------
		try {
			// Declarations des variables br/file
			fileVins = new FileReader(cheminSource);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(cheminSource), "CP1252"));
			raf = new RandomAccessFile(cheminSource, "rw");
			String chaine = null;
			// lecture du fichier avec le BR
			countTab(br);
			stringstr = new String[nmbLine];
			fileVins2 = new FileReader(cheminSource);
			br2 = new BufferedReader(new InputStreamReader(new FileInputStream(cheminSource), "CP1252"));
			raf2 = new RandomAccessFile(cheminCible, "rw");
			// Ecrit le fichier non trié
			while (br2.ready()) {
				String phrase     = br2.readLine();
				newString(phrase, raf2);  
			}
			// lecture du fichier non trier
			for (int i = 0; i < nmbLine; i++) {
				lecture(raf2, 98 * i);
			}
			// -------------------------- trie
			trieAlphabetique();
			// ------------recherche par dicotomi
			
			// !!!!!  Tentative echouer pour le moment !!!!!!!!!!
			String champDeRecherchereference = "Georges Duboeuf";
			// determiner la placedu pivot
			int pivot = nmbLine / 2 * 98;
			int fin = nmbLine;
			int debut = 0;
			System.out.println("pivot : " + pivot);
			// si reference est superieur (nmbLine/2)*98
			chercheEngine(champDeRecherchereference, pivot, fin, debut, 98);
			// allez dans la partie haute du tableau
			// diviser le tabelau restant /2
			// si plus grand que le point mediant
			// sinon allez dans la partie basse
		}
		// -----------
		catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				fileVins.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} // accolade fermante Main

	private static void chercheEngine(String champDeRecherchereference, int pivot, int fin, int debut, int compare)
			throws UnsupportedEncodingException, IOException {
		BufferedReader br3;
		RandomAccessFile raf3;
		System.out.println("pivotTitle:" + pivot);
		if (compare != 0) {
			try {
				br3 = new BufferedReader(new InputStreamReader(new FileInputStream(cheminCible), "CP1252"));
				raf3 = new RandomAccessFile(cheminCible, "rw");
				byte[] domaineLecture = new byte[domaineTaille];
				raf3.seek(pivot);
				raf3.read(domaineLecture);
				String domaineLectureStr = new String(domaineLecture);
				compare = domaineLectureStr.compareTo(champDeRecherchereference);
				
				System.out.println("compareto : " + domaineLectureStr.compareTo(champDeRecherchereference));
				System.out.println("Domaine : " + domaineLectureStr);
				
				if (domaineLectureStr.compareTo(champDeRecherchereference) < 0) {
					debut = debut + fin / 2;
					pivot = debut;
				
					System.out.println("au dessus");
					System.out.println("debut: " + debut);
					System.out.println("FIN: " + debut);
					System.out.println("pivot: " + pivot);
					
					chercheEngine(champDeRecherchereference, pivot, fin, debut, compare);
				} else if (domaineLectureStr.compareTo(champDeRecherchereference) > 0) {
					fin = fin / 2;
					pivot = pivot * fin;
					raf3.seek(pivot);
					raf3.read(domaineLecture);
					domaineLectureStr = new String(domaineLecture);
					
					System.out.println("En dessous");
					System.out.println("Fin: " + fin);
					System.out.println(domaineLectureStr);
					System.out.println("pivot:" + pivot);
					
					chercheEngine(champDeRecherchereference, pivot, fin, debut, compare);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private static void trieAlphabetique() throws UnsupportedEncodingException, IOException {
		BufferedReader br3;
		RandomAccessFile raf3;
		try {
			br3 = new BufferedReader(new InputStreamReader(new FileInputStream(cheminCible), "CP1252"));
			raf3 = new RandomAccessFile(cheminCible, "rw");
			for (int i = 0; i < nmbLine; i++) {
				for (int j = 0; j < nmbLine; j++) {
					raf3.seek(98 * j);
					byte[] domaineLecture = new byte[98];
					raf3.read(domaineLecture);
					String domaineLectureStr = new String(domaineLecture);

					raf3.seek(98 * (j + 1));
					byte[] domaineComparer = new byte[98];
					raf3.read(domaineComparer);
					String domainecomparerstr = new String(domaineComparer);

					if (domaineLectureStr.compareTo(domainecomparerstr) > 0) {
						raf3.seek(98 * j);
						byte[] b = domainecomparerstr.getBytes();
						raf3.write(b);
						raf3.seek(98 * (j + 1));
						byte[] c = domaineLectureStr.getBytes();
						raf3.write(c);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void countTab(BufferedReader br) throws IOException {
		String chaine;
		while ((chaine = br.readLine()) != null) {
			nmbLine++; // compte les lignes // 389
			// determination des tailles colonnes MAX
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
				standTaille = str[5].length();
			}
			chaine = "";
			domaine = new char[domaineTaille]; // 46 domaine = char[46]
			appellation = new char[appellationTaille]; // 23
			region = new char[regionTaille]; // 20
			ref = new char[refTaille]; // 3
			surface = new char[surfaceTaille]; // 3
			stand = new char[standTaille]; // 3
		}
	}

	// ------------------------------------------------------

	public static void newString(String phrase, RandomAccessFile raf) throws IOException {
		String[] phrase2 = phrase.split("\t");

		// domaine
		String domaineStr = phrase2[0];

		while (domaineStr.length() < domaineTaille) {

			domaineStr += " ";
		}
		if (domaineStr.length() == domaineTaille) {
			byte[] b = domaineStr.getBytes();
			raf.write(b);
		}
		// appellation
		String appellationStr = phrase2[1];

		while (appellationStr.length() < appellationTaille) {

			appellationStr += " ";
		}
		if (appellationStr.length() == appellationTaille) {
			byte[] b = appellationStr.getBytes();
			raf.write(b);
		}

		// region

		String regionStr = phrase2[2];

		while (regionStr.length() < regionTaille) {

			regionStr += " ";
		}
		if (regionStr.length() == regionTaille) {
			byte[] b = regionStr.getBytes();
			raf.write(b);
		}
		// ref
		String refStr = phrase2[3];

		while (refStr.length() < refTaille) {

			refStr += " ";
		}
		if (refStr.length() == refTaille) {
			byte[] b = refStr.getBytes();
			raf.write(b);
		}
		// surface
		String surfaceStr = phrase2[4];

		while (surfaceStr.length() < surfaceTaille) {

			surfaceStr += " ";
		}
		if (surfaceStr.length() == surfaceTaille) {
			byte[] b = surfaceStr.getBytes();
			raf.write(b);
		}
		// stand
		String standStr = phrase2[5];

		while (standStr.length() < standTaille) {

			standStr += " ";
		}
		if (standStr.length() == standTaille) {
			byte[] b = standStr.getBytes();
			raf.write(b);
		}

	}

	// ---------------------------------------------

	public static void lecture(RandomAccessFile raf, int position) throws IOException {

		raf.seek(position);
		byte[] domaineLecture = new byte[domaineTaille];
		raf.read(domaineLecture);
		String domaineLectureStr = new String(domaineLecture);

		raf.seek(position + domaineTaille);
		byte[] appellationLecture = new byte[appellationTaille];
		raf.read(appellationLecture);
		String appellationLectureStr = new String(appellationLecture);

		raf.seek(position + domaineTaille + appellationTaille);
		byte[] regionLecture = new byte[regionTaille];
		raf.read(regionLecture);
		String regionLectureStr = new String(regionLecture);

		raf.seek(position + domaineTaille + appellationTaille + regionTaille);
		byte[] refLecture = new byte[refTaille];
		raf.read(refLecture);
		String refLectureStr = new String(refLecture);

		raf.seek(position + domaineTaille + appellationTaille + regionTaille + refTaille);
		byte[] surfaceLecture = new byte[surfaceTaille];
		raf.read(surfaceLecture);
		String surfaceLectureStr = new String(surfaceLecture);

		raf.seek(position + domaineTaille + appellationTaille + regionTaille + refTaille + surfaceTaille);
		byte[] standLecture = new byte[standTaille];
		raf.read(standLecture);
		String standLectureStr = new String(standLecture);

		int numLigne = position / 98;
		stringstr[numLigne] = domaineLectureStr;
	}

} // accolade de class
