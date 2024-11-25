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

public class UpdatetimeJSONFile {
        private static Arrays list;
        
    public static void main(String[] args) {
    	AddTime("staytime.json", 20);
        System.out.println(Read("staytime.json"));
    }
    public static void AddTime(String table, int time)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader(""+table));
            JSONArray jsonObject = (JSONArray) obj; 
            
            jsonObject.add(time);

            UpdateJSON(jsonObject,table);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }
    
    public static JSONArray Read(String table)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader(""+table));
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
        try(FileWriter file= new FileWriter(""+table))
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