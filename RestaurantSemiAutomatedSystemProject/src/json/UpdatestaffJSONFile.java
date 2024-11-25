package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdatestaffJSONFile {
    private static ArrayList<String> n = new ArrayList<String>();
    

    public static boolean Add(String name,int position,int hourlywage,String starttime,String account,String password)
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
    
    public static ArrayList<String> readname()
    {
        JSONParser parser = new JSONParser();
        n.clear();
        try
        {
            Object obj = parser.parse(new FileReader("staff.json"));
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
    public static ArrayList<String> readbyname(String name)
    {
        JSONParser parser = new JSONParser();
        n.clear();
        try
        {
            Object obj = parser.parse(new FileReader("staff.json"));
            JSONArray jsonObject = (JSONArray) obj; 
            
            for(int i = 0;i<jsonObject.size();i++){
                JSONObject a =(JSONObject) jsonObject.get(i);
                if(a.get("name").equals(name)){
                    n.add((String) a.get("name"));
                    n.add(a.get("position") + "");
                    n.add(a.get("hourlywage") + "");
                    n.add((String) a.get("starttime"));
                    n.add((String) a.get("account"));
                    n.add((String) a.get("password"));
                }
            }

            return n;
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
        return n;
    }
    
    public static void Remove(String name)
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object obj = parser.parse(new FileReader("staff.json"));
            JSONArray jsonObject = (JSONArray) obj; 
            for(int i = 0;i<jsonObject.size();i++){
                JSONObject a =(JSONObject) jsonObject.get(i);
                if(a.get("name").equals(name)){
                    jsonObject.remove(jsonObject.get(i));
                };
            }

            UpdateJSON(jsonObject);
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }
    
    public static String getNameByAccount(String account)
    {
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader("Staff.json"));
            JSONArray jsonObject = (JSONArray) obj; 
            
            for(int i = 0;i<jsonObject.size();i++){
                JSONObject a =(JSONObject) jsonObject.get(i);
                if(a.get("account").equals(account)){
                    return (String) a.get("name");
                }
            }
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}

        return "";
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