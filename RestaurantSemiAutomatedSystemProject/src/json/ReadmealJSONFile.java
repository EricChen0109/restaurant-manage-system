package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadmealJSONFile {
        private static Arrays list;
        static ArrayList<String> n = new ArrayList<String>();
        static ArrayList<String> n1 = new ArrayList<String>();
        static ArrayList<Long> n2 = new ArrayList<Long>();
    public static void main(String[] args) {
//        System.out.println(Read(1));
//        System.out.println(readMeal(1));
        System.out.println(readMealsStatisticsName());
        System.out.println(readMealsStatisticsAmount());
    }
   
    public static JSONArray Read(int table)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("meal"+table+".json"));
            JSONArray jsonObject = (JSONArray) obj; 
            
            return jsonObject;
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return null;
    }
    
    public static ArrayList<String> readMeal(int table)
    {
        JSONParser parser = new JSONParser();
        n.clear();
        try
        {
            Object obj = parser.parse(new FileReader("meal"+table+".json"));
            JSONArray jsonObject = (JSONArray) obj; 
            
            for(int i = 0;i<jsonObject.size();i++){
                JSONObject a =(JSONObject) jsonObject.get(i);
                n.add((String)a.get("name")) ;
            }

            return n;
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return n;
    }
    
    public static ArrayList<String> readMealsStatisticsName(){
        n1.clear();
        ArrayList<String> temAL = readMeal(1);
        for (int i = 1; i < temAL.size(); i++) {
        	n1.add(temAL.get(i));
        }
        temAL = readMeal(2);
        for (int i = 1; i < temAL.size(); i++) {
        	n1.add(temAL.get(i));
        }
        temAL = readMeal(3);
        for (int i = 1; i < temAL.size(); i++) {
        	n1.add(temAL.get(i));
        }
        temAL = readMeal(4);
        for (int i = 1; i < temAL.size(); i++) {
        	n1.add(temAL.get(i));
        }
        return n1;
    }
    
    public static ArrayList<Long> readMealsStatisticsAmount(){
    	JSONParser parser = new JSONParser();
        n2.clear();
        ArrayList<String> temAL = readMealsStatisticsName();
        try
        {
            Object obj = parser.parse(new FileReader("mealsstatistics.json"));
            JSONObject jsonObject = (JSONObject) obj; 
            for (String mealName : temAL) {
            	n2.add((Long) jsonObject.get(mealName)) ;
            }

            return n2;
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return n2;
    }
    
}