import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

public class Main1 
{
	public static void main(String[] argv)throws IOException, TikaException
	{
      System.out.println("The Directory is traversed.");
      File dir = new File("/home/sumedha_12/Downloads/TIKA");
      visitAllDirsAndFiles(dir);
   }
   
   public static void visitAllDirsAndFiles(File dir) throws FileNotFoundException
   {
      System.out.println(dir);
      if (dir.isDirectory())
      {
    	  File[] listOfFiles = dir.listFiles(); 
    	  for (int i = 0; i < listOfFiles.length; i++)
    	  {
    		  visitAllDirsAndFiles(new File(dir, listOfFiles[i].toString()));
    		  PdfParse(listOfFiles[i]);
         }
      }
   }

   public static void PdfParse(File file) throws FileNotFoundException 
   {
	      BodyContentHandler handler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      FileInputStream inputstream = new FileInputStream(new File(file.toString()));
	      ParseContext pcontext = new ParseContext();
	      PDFParser pdfparser = new PDFParser(); 
	      try
	      {
	      pdfparser.parse(inputstream, handler, metadata,pcontext);
	      }
	      catch(Exception e) { System.out.println("ERROR!!");
	    	  
	      }
	      System.out.println("Contents of the PDF :" + handler.toString());
	      System.out.println("Metadata of the PDF:");
	      String[] metadataNames = metadata.names();
	      
	      for(String name : metadataNames)
	      {
	         System.out.println(name+ " : " + metadata.get(name));
	      }
	   }
}
   
