package mindCrappy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LibraryReader {
	public Cylinder data = new Cylinder();
	
	public String read(){
			data.readSettings();
			String readIn = "";
			String line = null;
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(data.getGamePath() + "/" + data.getGamePath().substring(data.getGamePath().lastIndexOf("/")) + ".json")));
				while((line = br.readLine()) != null){
					readIn = readIn.concat(line); 
				}
				br.close();
			}catch(FileNotFoundException enf){
				JOptionPane
				.showConfirmDialog(
						null,
						"File Not Found! Please select the JAR dirctory!",
						"FileNotFoundException", JOptionPane.INFORMATION_MESSAGE);
				enf.printStackTrace();
			}catch(IOException eio){
				eio.printStackTrace();
			}
			JSONObject json = JSONObject.fromObject(readIn);
			Object id = json.get("id");
			// Object time = json.get("time");
			// Object releaseTime = json.get("releaseTime");
			// Object type = json.get("type");
			Object minecraftArg = json.get("minecraftArguments");
			Object mainClass = json.get("mainClass");
			String rawLibs = "";
			JSONArray arr = json.getJSONArray("libraries");
			JSONObject rows = null;
			Object[] name = new Object[arr.size()];
			for(int i = 0 ;i < arr.size();i++){
				rows = arr.getJSONObject(i);
				name[i] = rows.get("name");
			}
			for(int i = 0; i < name.length; i++){
				String parts[] = name[i].toString().split(":",3);
				name[i] = String.format("%s/%s/%s", new Object[]{
					parts[0].replaceAll("\\.", "/"),parts[1],parts[2]	
				});
				String result = String.format("%s-%s.jar", new Object[]{
						parts[1],parts[2]
				});
				name[i] = name[i] + "/" + result;
				name[i] = data.getBase() + "/libraries/" + name[i] + ":";
				rawLibs += name[i];
			}
			StringBuffer libs = new StringBuffer();
			libs.append(rawLibs);
			libs.append(data.getGamePath() + "/" + data.getGamePathRaw().getName() + ".jar");
			minecraftArg = minecraftArg.toString().replace("${auth_player_name}", data.getUsername());
			minecraftArg = minecraftArg.toString().replace("${version_name}", id.toString());
			minecraftArg = minecraftArg.toString().replace("${game_directory}", data.getBase());
			minecraftArg = minecraftArg.toString().replace("${game_assets}", data.getBase() + "/assets");
			minecraftArg = minecraftArg.toString().replace("${auth_uuid}", "{}");
			minecraftArg = minecraftArg.toString().replace("${auth_access_token}","{}");
			String cmd = data.getJavaPath() + " -Xmx" + data.getMemSize() + "M -Djava.library.path=" + data.getBase() + "/natives " + "-cp " + libs.toString() + " " + mainClass.toString() + " " + minecraftArg.toString();
			return cmd;
	}

}
