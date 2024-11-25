package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MealtotalJSONFile {
    private static Long ctotal =(long) 0;
    public static long total(String[] meal)
    {
        ctotal =(long)0;
        JSONParser parser = new JSONParser();
        try
        {
            Object obj1 = parser.parse(new FileReader("meal1.json"));
            JSONArray jsonObject1 = (JSONArray) obj1; 
            Object obj2 = parser.parse(new FileReader("meal2.json"));
            JSONArray jsonObject2 = (JSONArray) obj2; 
            Object obj3 = parser.parse(new FileReader("meal3.json"));
            JSONArray jsonObject3 = (JSONArray) obj3; 
            Object obj4 = parser.parse(new FileReader("meal4.json"));
            JSONArray jsonObject4 = (JSONArray) obj4; 
            
            jsonObject1.contains(jsonObject2);
            jsonObject1.contains(jsonObject3);
            jsonObject1.contains(jsonObject4);

            for(int i =0; i<meal.length;i++){
                for(int j =0; j<jsonObject1.size();i = i+2){
                    if(meal[i].equals(jsonObject1.get(j))){
                        ctotal = ctotal + (long) jsonObject1.get(j+1);
                    }
                }  
            }
            
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return (long) 0;
    }
    
    public static JSONArray Read(String table)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("RestaurantSemiAutomatedSystemProject/"+table));
            JSONArray jsonObject = (JSONArray) obj; 
            
            return jsonObject;
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return null;
    }
    
    public static void UpdateJSON(JSONArray obj ,String table)
    {
        try(FileWriter file= new FileWriter("RestaurantSemiAutomatedSystemProject/"+table))
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