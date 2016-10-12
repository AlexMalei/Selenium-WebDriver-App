import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;

/**
 * Created by a.maley on 12.10.2016.
 */
public class JSONReader {
    private JSONObject jsonObject;


    public JSONReader(String fileConfigPath) {
        this.jsonObject = readJSONFile(fileConfigPath);
    }

    private static JSONObject readJSONFile(String fileConfigPath){
        File file = new File(fileConfigPath);
        FileInputStream fis;
        String filePropContent;
        byte[] data = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(data);
            fis.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        filePropContent = new String(data);
        return new JSONObject(filePropContent);
    }

    public String getWebSite(){
        return (String) jsonObject.get("URL");
    }

    public String getBrowserName(){
        return (String) jsonObject.get("Browser");
    }

    public String getLoginOnliner(){
        return (String) jsonObject.get("login_onliner");
    }

    public String getPasswordOnliner(){
        return (String) jsonObject.get("password_onliner");
    }






}
