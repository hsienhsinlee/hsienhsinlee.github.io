import java.io.*;
import java.util.*;

class Papers {
	public String id;
	public String year;
	public String where;
	public String url;
	public String type;
	public String field;
	public String author;
	public String title;
	public String bookTitle;
	public String bookDetails;
	public String etc;
	public int pdf;
	public int slide;
}

class PublicationManager {
	static Vector allList = new Vector();
	static Vector thesisList = new Vector();
	static String pdfPath = "../pub/";
	static String slidePath = "../present/";

	static int NONE = 0;
	static int PDF = 1;
	static int PPS = 2;
	static int PPT = 3;
	static int PPTX = 4;

        static int BS = 10;
        static int MS = 100;
        static int MSPhD = 500; // used if the student has both MS and PhD thesis
        static int PhD = 1000;

	static String titleAll = "All Refereed Papers";
	static String titleJournal = "Refereed Journal Articles";
	static String titleConference = "Refereed Conference Papers";
	static String titleWorkshop = "Refereed Workshop Papers";
	static String titlePoster = "Refereed Poster Presentations";
	static String titleWorkshopPoster = "Refereed Workshop Papers / Poster Presentations";
	static String titleBookChapter = "Book Chapters";
	static String titleThesis = "Theses";

	public static void main(String[] args) throws IOException {

		buildDataBase();

		generateByDate( "all" );

		generateByField( "ml", "ml" );
		generateByField( "sustainability", "sustainability" );
		generateByField( "uarch", "uarch" );
		generateByField( "secure", "secure" );
		generateByField( "embed", "embed" );
		generateByField( "lpower", "lpower" );
		generateByField( "fpga", "fpga" );
		generateByField( "soc", "soc" );
		generateByField( "parallel", "parallel" );
		generateByField( "gfx", "gfx" );

		generateByProject( "research-sec", "research-sec" );
		generateByProject( "research-pod", "research-pod" );
		generateByProject( "research-multicore", "research-multicore" );
		generateByProject( "research-medical", "research-medical" );
		generateByProject( "research-introspective", "research-introspective" );
		generateByProject( "3d", "3d" );
		generateByProject( "research-memory", "research-memory" );

		generateByName( "Joonho", "Baek", "Joonho", MS );
		generateByName( "Chinnakrishnan", "Ballapuram", "Chinnak", PhD );
		generateByName( "Nishank", "Chandawala", "Nishank", MS );
		generateByName( "Mrinmoy", "Ghosh", "Mrinmoy", PhD );
		generateByName( "Nob", "Kladjarern", "Nob", MS );
		generateByName( "Dean", "Lewis", "Dean", PhD );
		generateByName( "Fayez", "Mohamood", "Fayez", MS);
		generateByName( "Weidong", "Shi", "Weidong", PhD);
		generateByName( "Taeweon", "Suh", "Taeweon", PhD);
		generateByName( "Dong Hyuk", "Woo", "Dong_Hyuk", PhD);
		generateByName( "Richard", "Yoo", "Richard", MS);
		generateByName( "Eric", "Fontaine", "Eric", PhD);
		generateByName( "Ahmad", "Sharif", "Ahmad", PhD);
		generateByName( "Pratik", "Marolia", "Pratik", MS);
		generateByName( "Vikas", "Vasisht", "Vikas", MS);
		generateByName( "Nak Hee", "Seong", "Nak_Hee", PhD);
		generateByName( "Jen-Cheng", "Huang", "Jen-Cheng", MS);
		generateByName( "Sungkap", "Yeo", "Sungkap", PhD);
		generateByName( "Tzu-Wei", "Lin", "Tzu-Wei", MS);
		generateByName( "Andrei", "Bersatti", "Andrei", MS);
		generateByName( "Mohammad", "Hossain", "Hossain", MS);
		generateByName( "Abderrahim", "Benquassmi", "Ali", MS);
		generateByName( "Manoj", "Athreya", "Manoj", MS);
		generateByName( "Lifeng", "Nai", "Lifeng", PhD);
        generateByName( "Guanhao", "Shen", "Guanhao", PhD);
        generateByName( "Ilya", "Khorosh", "Ilya", MS);


	}

	private static void generateByDate( String filenameKeyword ) {
		/*
		date( "Refereed Journal Papers", "journal", filenameKeyword+"-journal" );
		date( "Refereed Conference Papers", "conference", filenameKeyword+"-conf" );
		*/
		date( titleAll, "journal+conference+workshop+poster+bookchapters", filenameKeyword + "_all" );
		date( titleThesis, "phd+ms", filenameKeyword + "_all" );

		date( titleJournal, "journal", filenameKeyword + "_journal" );
		date( titleConference, "conference", filenameKeyword + "_conference" );
		date( titleWorkshopPoster, "workshop+poster", filenameKeyword + "_workshop+poster" );
		date( titleBookChapter, "bookchapters", filenameKeyword + "_bookchapters" );
		date( titleThesis, "phd+ms", filenameKeyword + "_thesis" );

	}
	private static void generateByName( String firstName, String lastName, String filenameKeyword, int thesis_type ) {
		/*
		author( "Refereed Journal Papers", firstName, lastName, "journal", filenameKeyword );
		author( "Refereed Conference Papers", firstName, lastName, "conference", filenameKeyword );
	        author( "Refereed Workshop Papers", firstName, lastName, "workshop", filenameKeyword );
		author( "Refereed Poster Presentations", firstName, lastName, "poster", filenameKeyword );
		*/
		author( titleAll, firstName, lastName, "journal+conference+workshop+poster+bookchapters", filenameKeyword );
                if (thesis_type == PhD || thesis_type == MSPhD) {
		    author( "Ph.D. Dissertation", firstName, lastName, "phd", filenameKeyword );
		}
		if (thesis_type == MS || thesis_type == MSPhD) {
		    author( "M.S. Thesis", firstName, lastName, "ms", filenameKeyword );
		}
	}
	private static void generateByField( String fieldName, String filenameKeyword ) {
		/*
		field( "Refereed Journal Papers", fieldName, "journal", filenameKeyword );
		field( "Refereed Conference Papers", fieldName, "conference", filenameKeyword );
                field( "Refereed Workshop Papers", fieldName, "workshop", filenameKeyword );
		field( "Refereed Poster Presentations", fieldName, "poster", filenameKeyword );
                field( "Theses", fieldName, "phd+ms", filenameKeyword );
		*/
		field( titleAll, fieldName, "journal+conference+workshop+poster+bookchapters", filenameKeyword + "_all" );
		field( titleThesis, fieldName, "phd+ms", filenameKeyword + "_thesis" );

		field( titleJournal, fieldName, "journal", filenameKeyword + "_journal" );
		field( titleConference, fieldName, "conference", filenameKeyword + "_conference" );
                field( titleWorkshopPoster, fieldName, "workshop+poster", filenameKeyword + "_workshop+poster" );
		field( titleBookChapter, fieldName, "bookchapters", filenameKeyword + "_bookchapters" );
                field( titleThesis, fieldName, "phd+ms", filenameKeyword + "_thesis" );
	}
	private static void generateByProject( String projectName, String filenameKeyword ) {
		project( titleJournal, projectName, "journal", filenameKeyword);
		project( titleConference, projectName, "conference", filenameKeyword );
		//project( "Refereed Journal and Conference Papers", projectName, "journal+conference", filenameKeyword );
                project( titleWorkshop, projectName, "workshop", filenameKeyword );
		project( titleBookChapter, projectName, "bookchapters", filenameKeyword );
		project( titlePoster, projectName, "poster", filenameKeyword );
                project( titleThesis, projectName, "phd+ms", filenameKeyword );
	}

	private static void buildDataBase() {

		String line;
		int startIndex;

		try {

			BufferedReader br = new BufferedReader( new FileReader( "mars.bib" ) );
			String currentYear = new String();

			while( ( line = br.readLine() ) != null ) {

				if ( line.startsWith( "Year " ) ) {
					currentYear = line.substring( 5 );
					br.readLine();
				}
				else {
					int i;
					Papers paper = new Papers();
					paper.id = line;
					paper.year = currentYear.trim();
					String where = br.readLine().trim();
					if ( ( startIndex = where.indexOf( '(' ) ) >= 0 ) {
						paper.where = where.substring( 0, startIndex );
						paper.url = where.substring( startIndex+1, where.indexOf( ')' ) );
					}
					else {
						paper.where = where;
						paper.url = "";
					}
					paper.type = br.readLine().trim();
					paper.field = br.readLine().trim();
					paper.author = br.readLine().trim();
					paper.title = br.readLine().trim();
					paper.bookTitle = br.readLine().trim();
					paper.bookDetails = br.readLine().trim();
					paper.etc = br.readLine().trim();

					File pdf = new File( pdfPath + paper.id + ".pdf" );
					File pps = new File( slidePath + paper.id + ".pps" );
					File ppt = new File( slidePath + paper.id + ".ppt" );
					File pptx = new File( slidePath + paper.id + ".pptx" );
					File pdf_slide = new File( slidePath + paper.id + ".pdf" );
					if ( pdf.exists() ) {
						paper.pdf = PDF;
					}
					else paper.pdf = NONE;

					if ( pps.exists() ) {
						paper.slide = PPS;
					}
					else if ( ppt.exists() ) {
						paper.slide = PPT;
					}
					else if ( pptx.exists() ) {
						paper.slide = PPTX;
					}
					else if ( pdf_slide.exists() ) {
						paper.slide = PDF;
					}
					else paper.slide = NONE;

					allList.add( paper );
					if ( paper.type.equals( "phd" ) || paper.type.equals( "ms" ) ) {
						thesisList.add( paper );
					}
					br.readLine();
				}
			}
			br.close();

		}
		catch(Exception e) {
			System.err.println( "build error: " + e );
		}
	}



	private static void date(String title, String typeName, String fileName) {
		StringBuffer sb = new StringBuffer();
		Papers paper;
		int count = 0;
		String currentYear = new String();

		sb.append( "<!-- start of generated html code-->\n" );

		try {

			sb.append( "<tr><td align=center><span class=mars4t><b><br><br><br>" )
			  .append( title )
			  .append( "<br></b></span></td></tr>\n" )
			  .append( "<tr><td><table width=100%>\n" );

			{

				for ( int i = 0; i < allList.size(); i++ ) {
					paper = (Papers) allList.elementAt(i);
					if ( typeName.indexOf( paper.type ) >= 0 ) {

						if ( !paper.year.equals( currentYear ) ) {
							currentYear = paper.year;
							sb.append( "<tr><td colspan=2 align=center><span class=mars4t><br><b>" )
							  .append( currentYear )
							  .append( "</b><br><br></span></td></tr>\n" );

						}

						count++;
						sb.append( "<tr><td width=15% valign=top><span class=mars4_>" );
						if ( paper.url.length() > 1 ) {
							sb.append( "<a href='" + paper.url + "' target=_blank>" )
							  .append( paper.where )
							  .append( "</a>" );
						}
						else
							sb.append( paper.where );

						sb.append( "</span></td>" )
						  .append( "<td align='justify'><span class=mars4_>" )
						  .append( paper.author )
						  .append( ". \"<b>" )
						  .append( paper.title )
						  .append( "</b>.\" " )
						  .append( bookTitle(paper) );

						if ( paper.bookDetails.length() > 1 ) {
							sb.append( ", " )
							  .append( paper.bookDetails );
						}

						sb.append( "." );
						if ( paper.etc.length() > 1 ) {
							sb.append( " (" )
							  .append( "<font color=red>" )
							  .append( paper.etc )
							  .append( "</font>)");
						}
						if ( paper.pdf == PDF ) sb.append( "<br>[<a href='/MARS/pub/" + paper.id + ".pdf'>pdf</a>]");
						if ( paper.slide == PPS ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pps'>slides</a>]");
						}
						else if ( paper.slide == PPT ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".ppt'>slides</a>]");
						}
						else if ( paper.slide == PPTX ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pptx'>slides</a>]");
						}
						else if ( paper.slide == PDF ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pdf'>slides</a>]");
						}
						sb.append( "</span></td></tr>\n" );
					}
				}
			}
			sb.append( "</table></td></tr>\n" )
			  .append( "<!-- end of generated html code-->\n" );

			//BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".html"));
			FileWriter fw = new FileWriter(fileName+".html", true);
			if ( count > 0 ) fw.write( sb.toString(), 0, sb.toString().length() );
			fw.close();
		}
		catch(Exception e) {
			System.err.println( e );
		}
	}

	private static void project(String title, String project, String typeName, String fileName) {
		StringBuffer sb = new StringBuffer();
		Papers paper;
		String id;
		int count = 0;

		try {
			sb.append( "<!-- start of generated html code-->\n" );
			sb.append( "<tr><td align=left><span class=mars3t><b><br><br><br>" )
			  .append( title )
			  .append( "<br><br></b></span></td></tr>\n" )
			  .append( "<tr><td><table width=100%>\n" );

			{
				for ( int i = 0; i < allList.size(); i++ ) {
					paper = (Papers) allList.elementAt(i);
					if ( ( paper.field.indexOf( project ) >= 0 ) && ( typeName.indexOf( paper.type ) >= 0 ) ) {
						count++;
						sb.append( "<tr><td width=15% valign=top><span class=mars3_>" );
						if ( paper.url.length() > 1 ) {
							sb.append( "<a href='" + paper.url + "' target=_blank>" )
							  .append( paper.where )
							  .append( "</a>" );
						}
						else
							sb.append( paper.where );

						sb.append( "</span></td>" )
						  .append( "<td align='justify'><span class=mars3_>" )
						  .append( paper.author )
						  .append( ". \"<b>" )
						  .append( paper.title )
						  .append( "</b>.\" " )
						  .append( bookTitle(paper) );

						if ( paper.bookDetails.length() > 1 ) {
							sb.append( ", " )
							  .append( paper.bookDetails );
						}

						sb.append( "." );

						if ( paper.etc.length() > 1 ) {
							sb.append( " (" )
							  .append( "<font color=red>" )
							  .append( paper.etc )
							  .append( "</font>)");
						}
						if ( paper.pdf == PDF ) sb.append( "<br>[<a href='/MARS/pub/" + paper.id + ".pdf'>pdf</a>]"); /* Hsien-Hsin Sean Lee changed the root directory */
						if ( paper.slide == PPS ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pps'>slides</a>]"); /* Hsien-Hsin Sean Lee changed the root directory */
						}
						else if ( paper.slide == PPT ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".ppt'>slides</a>]");
						}
						else if ( paper.slide == PPTX ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pptx'>slides</a>]");
						}
						else if ( paper.slide == PDF ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pdf'>slides</a>]");
						}
						sb.append( "</span></td></tr>\n" );
					}
				}
			}
			sb.append( "</table></td></tr>\n" )
			  .append( "<!-- end of generated html code-->\n" );

			FileWriter fw = new FileWriter(fileName+".html", true);
			if ( count > 0 ) fw.write( sb.toString(), 0, sb.toString().length() );
			fw.close();
		}
		catch(Exception e) {
			System.err.println( e );
		}

	}


	private static void field(String title, String field, String typeName, String fileName) {
		StringBuffer sb = new StringBuffer();
		Papers paper;
		String id;
		int count = 0;

		try {
			sb.append( "<!-- start of generated html code-->\n" );
			sb.append( "<tr><td align=center><span class=mars4t><b><br><br><br>" )
			  .append( title )
			  .append( "<br><br></b></span></td></tr>\n" )
			  .append( "<tr><td><table width=100%>\n" );

			{
				for ( int i = 0; i < allList.size(); i++ ) {
					paper = (Papers) allList.elementAt(i);
					if ( ( paper.field.indexOf( field ) >= 0 ) && ( typeName.indexOf( paper.type ) >= 0 ) ) {
						count++;
						sb.append( "<tr><td width=15% valign=top><span class=mars4_>" );
						if ( paper.url.length() > 1 ) {
							sb.append( "<a href='" + paper.url + "' target=_blank>" )
							  .append( paper.where )
							  .append( "</a>" );
						}
						else
							sb.append( paper.where );

						sb.append( "</span></td>" )
						  .append( "<td align='justify'><span class=mars4_>" )
						  .append( paper.author )
						  .append( ". \"<b>" )
						  .append( paper.title )
						  .append( "</b>.\" " )
						  .append( bookTitle(paper) );

						if ( paper.bookDetails.length() > 1 ) {
							sb.append( ", " )
							  .append( paper.bookDetails );
						}

						sb.append( "." );

						if ( paper.etc.length() > 1 ) {
							sb.append( " (" )
							  .append( "<font color=red>" )
							  .append( paper.etc )
							  .append( "</font>)");
						}
						if ( paper.pdf == PDF ) sb.append( "<br>[<a href='/MARS/pub/" + paper.id + ".pdf'>pdf</a>]");
						if ( paper.slide == PPS ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pps'>slides</a>]");
						}
						else if ( paper.slide == PPT ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".ppt'>slides</a>]");
						}
						else if ( paper.slide == PPTX ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pptx'>slides</a>]");
						}
						else if ( paper.slide == PDF ) {
							if ( paper.pdf == NONE ) sb.append( "<br>" );
							sb.append( " [<a href='/MARS/present/" + paper.id + ".pdf'>slides</a>]");
						}
						sb.append( "</span></td></tr>\n" );
					}
				}
			}
			sb.append( "</table></td></tr>\n" )
			  .append( "<!-- end of generated html code-->\n" );

			FileWriter fw = new FileWriter(fileName+".html", true);
			if ( count > 0 ) fw.write( sb.toString(), 0, sb.toString().length() );
			fw.close();
		}
		catch(Exception e) {
			System.err.println( e );
		}

	}

	private static void author(String title, String firstName, String lastName, String typeName, String fileName) {
		StringBuffer sb = new StringBuffer();
		Papers paper;
		int startIndex, endIndex;
		int count = 0;

		try {
			sb.append( "<!-- start of generated html code-->\n" );
			sb.append( "<tr><td align=center><span class=mars2t><b><br><br><br>" )
			  .append( title )
			  .append( "<br><br></b></span></td></tr>\n" )
			  .append( "<tr><td><table width=100%>\n" );
			for ( int i = 0; i < allList.size(); i++ ) {
				paper = (Papers) allList.elementAt(i);
				if ( ( ( startIndex = paper.author.indexOf( firstName ) ) >= 0 ) && ( paper.author.indexOf( lastName ) >= 0 ) && ( typeName.indexOf( paper.type ) >= 0 ) ) {
					count++;

					endIndex = paper.author.indexOf( lastName, startIndex ) + lastName.length();

					sb.append( "<tr><td width=15% valign=top><span class=mars2_>" );
					if ( paper.url.length() > 1 ) {
						sb.append( "<a href='" + paper.url + "' target=_blank>" )
						  .append( paper.where )
						  .append( "</a>" );
					}
					else
						sb.append( paper.where );

					sb.append( "</span></td>" )
					  .append( "<td align='justify'><span class=mars2_>" )
					  .append( paper.author.substring( 0, startIndex ) )
					  .append( "<b>" );

					if ( endIndex > 0 ) {
						sb.append( paper.author.substring( startIndex, endIndex ) )
						  .append( "</b>" )
						  .append( paper.author.substring( endIndex ) );
					}
					// the last author
					else {
						sb.append( paper.author.substring( startIndex ) )
						  .append( "</b>" );
					}
					sb.append( ". \"" )
					  .append( paper.title )
					  .append( ".\" " )
					  .append( bookTitle(paper) );

					if ( paper.bookDetails.length() > 1 ) {
						sb.append( ", " )
						  .append( paper.bookDetails );
					}

					sb.append( "." );

					if ( paper.etc.length() > 1 ) {
						sb.append( " (" )
						  .append( "<font color=red>" )
						  .append( paper.etc )
						  .append( "</font>)");
					}
					if ( paper.pdf == PDF ) sb.append( "<br>[<a href='/MARS/pub/" + paper.id + ".pdf'>pdf</a>]");
					if ( paper.slide == PPS ) {
						if ( paper.pdf == NONE ) sb.append( "<br>" );
						sb.append( " [<a href='/MARS/present/" + paper.id + ".pps'>slides</a>]");
					}
					else if ( paper.slide == PPT ) {
						if ( paper.pdf == NONE ) sb.append( "<br>" );
						sb.append( " [<a href='/MARS/present/" + paper.id + ".ppt'>slides</a>]");
					}
					else if ( paper.slide == PPTX ) {
						if ( paper.pdf == NONE ) sb.append( "<br>" );
						sb.append( " [<a href='/MARS/present/" + paper.id + ".pptx'>slides</a>]");
					}
					else if ( paper.slide == PDF ) {
						if ( paper.pdf == NONE ) sb.append( "<br>" );
						sb.append( " [<a href='/MARS/present/" + paper.id + ".pdf'>slides</a>]");
					}
					sb.append( "</span></td></tr>\n" );
				}
			}
			sb.append( "</table></td></tr>\n" )
			  .append( "<!-- end of generated html code-->\n" );

			FileWriter fw = new FileWriter(fileName+".html", true);
			if ( count > 0 ) fw.write( sb.toString(), 0, sb.toString().length() );
			fw.close();
		}
		catch(Exception e) {
			System.err.println( e );
		}

	}

	private static String bookTitle(Papers paper) {
		StringBuffer sb = new StringBuffer();
		int i;

		if ( paper.bookTitle.startsWith( "To appear in " ) ) {
			sb.append( "To appear in <i>" );
			sb.append( paper.bookTitle.substring( 13 ) )
			  .append( "</i>" );
		}
		else if ( paper.bookTitle.startsWith( "Accepted by " ) ) {
			sb.append( "Accepted by <i>" );
			sb.append( paper.bookTitle.substring( 12 ) )
			  .append( "</i>" );
		}
		else if ( paper.bookTitle.startsWith( "In Proceedings of " ) ) {
			sb.append( "In Proceedings of <i>" );
			sb.append( paper.bookTitle.substring( 18 ) )
			  .append( "</i>" );
		}
		else if ( paper.bookTitle.startsWith( "In " ) ) {
			sb.append( "In <i>" );
			sb.append( paper.bookTitle.substring( 3 ) )
			  .append( "</i>" );
		}
		else {
			sb.append( paper.bookTitle );
		}

		return sb.toString();
	}
}
