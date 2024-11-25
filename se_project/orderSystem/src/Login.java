import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Login {
    public Login(String account,String password){
        readState(account, password);
    }
    public static boolean readState(String account,String password)
    {
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader("staff.json"));
            JSONArray jsonObject = (JSONArray) obj; 
            
            for(int i = 0;i<jsonObject.size();i++){
                JSONObject a =(JSONObject) jsonObject.get(i);
                if(a.get("account").equals(account) && a.get("password").equals(password) ){
                    return true;
                };
            }
        }

        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}

        return false;
    }
    
}
