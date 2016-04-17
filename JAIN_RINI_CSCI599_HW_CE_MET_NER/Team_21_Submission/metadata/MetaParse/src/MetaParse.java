

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.serialization.JsonMetadataSerializer;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MetaParse
{
	//static float avg;
	static int sum;
      int j;
  public static void main(String[] args)throws IOException, TikaException
   {
         System.out.println("The Directory is traversed.");
         File dir1 = new File("/Users/Rini/Desktop/sumedha_Data");
         String[] names = dir1.list();
         HashMap<String, Integer> objMap = new HashMap<String, Integer>();
         for(String name : names)
         {
             if (new File("/Users/Rini/Desktop/sumedha_Data/" + name).isDirectory())
             {
            	 
                 //System.out.println(name);
            	 File dir2 = new File("/Users/Rini/Desktop/sumedha_Data/" + name);
            	 //int ag=(int)avg;
            	 
            	 visitAllDirsAndFiles(dir2);
            	 objMap.put(name, sum);
            	 
            	 
            	 
             }
         }
            
    		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("MetadataQuality.json", true))))
 	     {
 	    	 
 	 	     // System.out.println(avg);
 	 	     
 	    	 JSONObject json = new JSONObject();
 	    json.putAll(objMap);
 	   //Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	  //String jsonOutput = gson.toJson(json);
 	    out.printf( "%s",json);
 
   	   }
 	     
        	    	 
   	    
   	   
 	catch (IOException e) {
   	    //exception handling left as an exercise for the reader
   	}
         }
        // visitAllDirsAndFiles(dir1);
// 		HashMap<String, Integer> objMap = new HashMap<String, Integer>();
//   		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dhruv.json", true))))
//	     {
//	    	 
//	 	     // System.out.println(avg);
//	 	     objMap.put("rss", avg);
//	    	 JSONObject json = new JSONObject();
//	    json.putAll( objMap);
//	   //Gson gson = new GsonBuilder().setPrettyPrinting().create();
//	  //String jsonOutput = gson.toJson(json);
//	    out.printf( "%s",json);
//
//  	   }
//	     
//       	    	 
//  	    
//  	   
//	catch (IOException e) {
//  	    //exception handling left as an exercise for the reader
//  	}
     
  public static void visitAllDirsAndFiles(File dir1) throws FileNotFoundException
    {
         System.out.println(dir1);
         
	     
         if (dir1.isDirectory())
         {
       	 File[] listOfFiles = dir1.listFiles(); 
       	for (int i = 0; i < listOfFiles.length; i++)
       	 {
       		  visitAllDirsAndFiles(new File(dir1, listOfFiles[i].toString()));
       		  MetaParserss(listOfFiles[i]);
    	    
            }
         }
      }

     // @SuppressWarnings("resource")
	public static void MetaParserss(File file) throws FileNotFoundException 
      {
   	      BodyContentHandler handler = new BodyContentHandler();
   	      Metadata metadata = new Metadata();
   	      FileInputStream inputstream = new FileInputStream(new File(file.toString()));
   	      ParseContext pcontext = new ParseContext();
   	      AutoDetectParser pdfparser = new  AutoDetectParser(); 
 	    
   	      try
   	      {
   	    	/* File file1 = new File("out.txt");
   	   		 FileOutputStream fos = new FileOutputStream(file1);
   	   		 PrintStream ps = new PrintStream(fos);
   	   		 System.setOut(ps);*/

   	      pdfparser.parse(inputstream, handler, metadata,pcontext);
   	      }
   	      catch(Exception e) { System.out.println("ERROR!!");
   	    	  
   	      }
   	      //out.println("Contents of the PDF :" + handler.toString());
   	      
   	  // PrintStream out = new PrintStream(new FileOutputStream(
	   	          //"OutFile.txt"));
   	   
   		System.out.println("Metadata Quality score of each file:");
 	      String[] metadataNames = metadata.names();
 	     int score=2;
 	   sum=0;

 	    ArrayList<String> store = new ArrayList<String>();
 	  
 	  
 	      for(String name : metadataNames)
   	      {
 	    	 System.out.println(name+ " : " + metadata.get(name));
   	      
 	    	 
 	    	  store.add(metadata.get(name));
 	    	if(name=="description"||name=="title"||name=="version")//(a) measuring objects intended use
  	    	 {
 	    		System.out.println(name+ " : " + metadata.get(name));
  	    		 score=score+1;
  	    	 }
 	    	if (name=="alias")//(b) checks if it supports interoperability
 	    	{
 	    		score=score+1;
 	    	}

   	    	if(name=="dc:source"||name=="dc:coverage"||name=="dc:rights"||name=="dc:format"||name=="dc:identifier"||name=="dc:subject"||name=="dc:language"||name=="dc:title" || name=="dc:creator"|| name=="dc:format" || name=="dc:creator"||name=="dc:subject"||name=="dc:publisher"||name=="dc:contributor"||name=="dc:date"||name=="dc:type")/*(c) Uses standard controlled vocabularies*/
	    	  {
   	    		System.out.println(name+ " : " + metadata.get(name));
   	   		    score=score+1;
	    	  }
   	    	if(name.startsWith("cp:"))
   	    	{
   	    		System.out.println(name+ " : " + metadata.get(name));
   	   		    score=score+1;
   	    	}
   	    	if(name.startsWith("xmp:"))
   	    	{
   	    		System.out.println(name+ " : " + metadata.get(name));
   	   		    score=score+1;
   	    	}
   	    	if(name.startsWith("lom:"))
   	    	{
   	    		System.out.println(name+ " : " + metadata.get(name));
   	   		    score=score+1;
   	    	}
   	    	if(name.startsWith("xmpTpg:"))
   	    	{
   	    		System.out.println(name+ " : " + metadata.get(name));
   	   		    score=score+1;
   	    	}
   	    	if(name.startsWith("dcterms:"))
   	    	{
   	    		System.out.println(name+ " : " + metadata.get(name));
   	   		    score=score+1;
   	    	}
   	    	if(name=="License")//(d)  identifies its license
   	    	{
   	    		System.out.println(name+ " : " + metadata.get(name));
   	    		score=score+1;
   	    	 } 
   	    	sum=sum+score;
	    	 }
 	     //avg=sum/j;
 	     
 	    
 	// System.out.println(score);
	
      }
   }
   	   
   
