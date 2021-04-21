package fr.eql.ai109.salonVin2;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;


public class pg {
	
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
		public static int position = 0 ;
		public static String cheminSource = "c:/TP/VINStp.DON" ;
		public static String cheminCible = "c:/TP/fichierCible.DON" ; // � modifier

		//------------------------------------------------------------

		public static void main(String[] args) {
			//initialisation des variables	
			FileReader fileVins = null;
			FileReader fileVins2 = null;
			BufferedReader br = null ;
			BufferedReader br2 = null ;
			RandomAccessFile raf = null ;
			RandomAccessFile raf2 = null ;

			//Mise en forme du fichier structure
			//-------------------------------------------------------------------		
			try {
				//Declarations des variables br/file
				fileVins = new FileReader(cheminSource);
				br = new BufferedReader(new InputStreamReader(new FileInputStream(cheminSource),"CP1252"));
				raf = new RandomAccessFile(cheminSource, "rw") ;
				String chaine = null;
				//lecture du fichier avec le BR	
				while ((chaine = br.readLine()) !=null) {
					nmbLine++; //compte les lignes // 389
					//determination des tailles colonnes MAX
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
						standTaille = str[5].length() ;
					}	
					chaine = "";

					domaine = new char[domaineTaille]; //46 domaine = char[46]
					appellation = new char[appellationTaille]; //23
					region = new char[regionTaille]; //20
					ref = new char[refTaille]; //3
					surface = new char[surfaceTaille]; //3
					stand = new char[standTaille]; //3
				}
				
				fileVins2 = new FileReader(cheminSource) ;
				br2 = new BufferedReader(new InputStreamReader(new FileInputStream(cheminSource),"CP1252"));
				raf2 = new RandomAccessFile(cheminCible, "rw") ;
				
				
				while (br2.ready()) {
							
					String phrase = br2.readLine() ;
					newString(phrase,raf2) ;
					
					
				}
				
				lecture(raf2, 98);
				
			}
			
			
			//-----------
			catch (IOException e) {

				e.printStackTrace();
			} 
			finally {

				try {
					fileVins.close();
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			


		} //accolade fermante Main

	//------------------------------------------------------

		public static void newString(String phrase, RandomAccessFile raf) throws IOException{
	        String[] phrase2 =phrase.split("\t");
	 
	   //domaine     
	        String domaineStr = phrase2[0];
	        
	        
	        while (domaineStr.length() < domaineTaille) {
	        	
	        	domaineStr += " ";
	        }
	        if (domaineStr.length() == domaineTaille) {
	        	byte[] b = domaineStr.getBytes() ;
	        	raf.write(b);
	        }
	  //appellation
	        String appellationStr = phrase2[1];
	        
	        
	        while (appellationStr.length() < appellationTaille) {
	        	
	        	appellationStr += " ";
	        }
	        if (appellationStr.length() == appellationTaille) {
	        	byte[] b = appellationStr.getBytes() ;
	        	raf.write(b);
	        }
	        
	    //region
	        
	        String regionStr = phrase2[2];
	        
	        
	        while (regionStr.length() < regionTaille) {
	        	
	        	regionStr += " ";
	        }
	        if (regionStr.length() == regionTaille) {
	        	byte[] b = regionStr.getBytes() ;
	        	raf.write(b);
	        }
	    //ref
	String refStr = phrase2[3];
	        
	        
	        while (refStr.length() < refTaille) {
	        	
	        	refStr += " ";
	        }
	        if (refStr.length() == refTaille) {
	        	byte[] b = refStr.getBytes() ;
	        	raf.write(b);
	        }
	     //surface
	String surfaceStr = phrase2[4];
	        
	        
	        while (surfaceStr.length() < surfaceTaille) {
	        	
	        	surfaceStr += " ";
	        }
	        if (surfaceStr.length() == surfaceTaille) {
	        	byte[] b = surfaceStr.getBytes() ;
	        	raf.write(b);
	        }
	   //stand 
	String standStr = phrase2[5];
	        
	        
	        while (standStr.length() < standTaille) {
	        	
	        	standStr += " ";
	        }
	        if (standStr.length() == standTaille) {
	        	byte[] b = standStr.getBytes() ;
	        	raf.write(b);
	        }
	        
	        
	    }

	//---------------------------------------------
		
		public static void lecture(RandomAccessFile raf, int position) throws IOException{
				
		
		raf.seek(position);
		byte[] domaineLecture = new byte[domaineTaille];
		raf.read(domaineLecture);
		String domaineLectureStr = new String (domaineLecture) ;
		
		
		
		raf.seek(position+domaineTaille);
		byte[] appellationLecture = new byte[appellationTaille];
		raf.read(appellationLecture);
		String appellationLectureStr = new String(appellationLecture) ;
		
		raf.seek(position+domaineTaille+appellationTaille);
		byte[] regionLecture = new byte[regionTaille];
		raf.read(regionLecture);
		String regionLectureStr = new String(regionLecture) ;
		
		raf.seek(position+domaineTaille+appellationTaille+regionTaille);
		byte[] refLecture = new byte[refTaille];
		raf.read(refLecture);
		String refLectureStr = new String(refLecture) ;
		
		raf.seek(position+domaineTaille+appellationTaille+regionTaille+refTaille);
		byte[] surfaceLecture = new byte[surfaceTaille];
		raf.read(surfaceLecture);
		String surfaceLectureStr = new String(surfaceLecture) ;
		
		raf.seek(position+domaineTaille+appellationTaille+regionTaille+refTaille+surfaceTaille);
		byte[] standLecture = new byte[standTaille];
		raf.read(standLecture);
		String standLectureStr = new String(standLecture) ;

	//affichage finale	
		System.out.println(domaineLectureStr+ " " + appellationLectureStr+ " " +regionLectureStr + " " +refLectureStr+ " " +surfaceLectureStr+ " " +standLectureStr);
		
		
		
		}
		

	} // accolade de class



