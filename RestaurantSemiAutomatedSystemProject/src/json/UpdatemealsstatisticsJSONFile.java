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

public class UpdatemealsstatisticsJSONFile {
    private static ArrayList<String> list = new ArrayList<String>() ;
    private static String[] list1;

    public static boolean Add(ArrayList<String> meals)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("mealsstatistics.json"));
            JSONObject jsonObject = (JSONObject) obj; 

            for(int i = 0;i<meals.size();i++){
                if(jsonObject.get(meals.get(i)) == null){
                    jsonObject.put((String) meals.get(i), (long) 0);
                }
                jsonObject.put((String) meals.get(i), (long) jsonObject.get(meals.get(i))+(long) 1);
            }

            UpdateJSON(jsonObject);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return true;
    }
    
    public static String[] Read()
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("mealsstatistics.json"));
            JSONObject jsonObject = (JSONObject) obj; 
            String n= jsonObject.toString();
            list1 = n.split(",");
            for(int i =0;i<list1.length;i++){
                list1[i].split(":");
            }
            return list1;
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return null;
    }

    public static void UpdateJSON(JSONObject obj)
    {
        try(FileWriter file= new FileWriter("mealsstatistics.json"))
        {
            file.write(obj.toString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //System.out.println(obj);
    }
}