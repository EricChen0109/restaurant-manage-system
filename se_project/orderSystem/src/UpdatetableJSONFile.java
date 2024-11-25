import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdatetableJSONFile {

    public static void AddTime(String table,Integer tablenumber, int time)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("Table.json"));
            JSONObject jsonObject = (JSONObject) obj; 
            JSONArray TableObject = (JSONArray) jsonObject.get("tabledata");
            JSONObject jsoObject= (JSONObject) TableObject.get(tablenumber-1);
            JSONArray TablObject = (JSONArray) jsoObject.get(table);

            TablObject.add(time);
            jsoObject.put(table,TablObject);
            TableObject.set(tablenumber-1, jsoObject);
            jsonObject.put("tabledata",TableObject);

            UpdateJSON(jsonObject);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }
    
    public static void EditTableNumber(int time)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("Table.json"));
            JSONObject jsonObject = (JSONObject) obj; 
            Long TableObject = (Long) jsonObject.get("tableamount");

            TableObject = (long) time ;

            jsonObject.put("tableamount",TableObject);

            UpdateJSON(jsonObject);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }
    
    public static void EditWaiter(Integer tablenumber,String name)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("Table.json"));
            JSONObject jsonObject = (JSONObject) obj; 
            JSONArray TableObject = (JSONArray) jsonObject.get("tabledata");
            JSONObject jsoObject= (JSONObject) TableObject.get(tablenumber-1);
            String TablObject = (String) jsoObject.get("waiter");

            TablObject = name;
            jsoObject.put("waiter",TablObject);
            TableObject.set(tablenumber-1, jsoObject);
            jsonObject.put("tabledata",TableObject);

            UpdateJSON(jsonObject);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }

    public static void UpdateJSON(JSONObject obj)
    {
        try(FileWriter file= new FileWriter("Table.json"))
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