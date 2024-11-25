import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdatestaffJSONFile {

    public static void main(String[] args) {
        Remove("Ban","Ban");
    };
    

    public static boolean Add(String name,String position,Integer hourlywage,String starttime,String account,String password)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("staff.json"));
            JSONArray jsonObject = (JSONArray) obj; 

            for(int i = 0;i<jsonObject.size();i++){
                JSONObject a =(JSONObject) jsonObject.get(i);
                if(a.get("account").equals(account) && a.get("password").equals(password) ){
                    return false;
                };
            }

            JSONObject newinfo = new JSONObject();
            newinfo.put("name",name);
            newinfo.put("position",position);
            newinfo.put("hourlywage",hourlywage);
            newinfo.put("starttime",starttime);
            newinfo.put("account",account);
            newinfo.put("password",password);

            jsonObject.add(newinfo);

            UpdateJSON(jsonObject);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return true;
    }
    
    public static void Edit(String name,String position,Integer hourlywage,String starttime,String account,String password)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("staff.json"));
            JSONArray jsonObject = (JSONArray) obj; 
            
            JSONObject newinfo = new JSONObject();
            newinfo.put("name",name);
            newinfo.put("position",position);
            newinfo.put("hourlywage",hourlywage);
            newinfo.put("starttime",starttime);
            newinfo.put("account",account);
            newinfo.put("password",password);

            jsonObject.add(newinfo);


            UpdateJSON(jsonObject);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }
    
    public static void Remove(String account,String password)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("staff.json"));
            JSONArray jsonObject = (JSONArray) obj; 
            for(int i = 0;i<jsonObject.size();i++){
                JSONObject a =(JSONObject) jsonObject.get(i);
                if(a.get("account").equals(account) && a.get("password").equals(password) ){
                    jsonObject.remove(jsonObject.get(i));
                };
            }

            UpdateJSON(jsonObject);
            System.out.println("Remove Order : " + account);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }

    public static void UpdateJSON(JSONArray obj)
    {
        try(FileWriter file= new FileWriter("staff.json"))
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