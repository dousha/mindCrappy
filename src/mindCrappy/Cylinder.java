package mindCrappy;

import java.awt.Font;
import java.io.*;

import java.util.*;

public class Cylinder {
	private String username;
	private int memSize = 1024;
	private String OSType;
	private String javaPath = "java";
	private boolean is64bit = false;
	private Font font;
	private String version = "14y5a";
	private boolean inSetting = false;
	private boolean inGame = false;
	private String fontType;
	private File gamePath;
	
	public Cylinder(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String newName) {
		this.username = newName;
	}

	public int getMemSize() {
		return memSize;
	}

	public void setMemSize(int newSize) {
		this.memSize = newSize;
	}

	public String getOSType() {
		if (OSType == null)
			this.checkOSType();
		return OSType;
	}

	public void checkOSType() {
		Properties props = System.getProperties();
		this.OSType = props.getProperty("os.name");
	}

	public void readSettings() {
		try {
			File f = new File("./configure/configure.vf");
			if(f.exists()){
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream("./configure/configure.vf")));
				String readOut = br.readLine();
				if(readOut == null){
					br.close();
					return;
				}
				String[] originalData;
				originalData = readOut.split(":");
				if(originalData.length == 5){
					this.username = originalData[0];
					this.javaPath = originalData[1];
					this.memSize = Integer.parseInt(originalData[2]);
					this.is64bit = new Boolean(originalData[3]).booleanValue();
					this.gamePath = new File(originalData[4]);
				}
				br.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

	public int saveSettings() {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("./configure/configure.vf")));
			String writeIn = "";
			if (is64bit) {
				writeIn = (new StringBuilder(String.valueOf(username)))
						.append(":").append(javaPath).append(":")
						.append(memSize).append(":").append("true").append(":").append(gamePath).toString();
			} else {
				writeIn = (new StringBuilder(String.valueOf(username)))
						.append(":").append(javaPath).append(":")
						.append(memSize).append(":").append("false").append(":").append(gamePath).toString();
			}
			bw.write(writeIn);
			System.out.println("Written >> " + writeIn);
			bw.flush();
			bw.close();
			return 0;
		} catch (IOException ex) {
			ex.printStackTrace();
			return 1;
		}
	}
	
	public void init() {
		File dir = new File("./configure");
		File config = new File("./configure/configure.vf");
		if (!dir.exists()) {
			dir.mkdir();
			if (!config.exists()) {
				try {
					config.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.readSettings();
	}
	
	public String getBase(){
		File base = this.getGamePathRaw();
		File ba = new File(base.getParent());
		String b = ba.getParent();
		return b;
	}
	
	public Font getFont() {
		if (font == null)
			this.setFont();
		return font;
	}
	
	public String getFontType(){
		if(this.fontType == null)
			this.setFontType();
		return fontType;
	}
	
	public void setFont(){
		// "font" is a READ ONLY value!
		if(this.getOSType() == "Linux")
			// use Linux Safety Font, like WenQuanYi ZenHei
			this.font = new Font("Droid Sans",Font.PLAIN,16);
		else
			// use Standard Font
			this.font = new Font("SansSerif", 0, 16);
	}
	
	public void setFontType(){
		if(this.getOSType() == "Linux")
			this.fontType = "Droid Sans";
		else
			this.fontType = "SimSun";
	}
	
	public String getVersion(){
		return version;
	}
	
	public void setJavaPath(){
		// auto get java path, or just use "java" command
		if((this.getOSType() == "Linux") || (this.getOSType().toLowerCase() == "osx"))
			// act like Linux or Mac
			this.javaPath = "java";
		if(this.getOSType().toLowerCase() == "windows")
			this.javaPath = System.getProperty("java.home") + "\\bin\\javaw.exe";
	}
	
	public void setJavaPath(String newPath){
		// set it manually
		this.javaPath = newPath;
	}
	
	public String getJavaPath(){
		return javaPath;
	}

	public boolean isInSetting() {
		return inSetting;
	}

	public void setSettingMode(boolean newMode) {
		this.inSetting = newMode;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setGameMode(boolean inGame) {
		this.inGame = inGame;
	}
	
	public void set64bitMode(boolean newMode){
		this.is64bit = newMode;
	}
	
	public boolean get64bitMode(){
		return is64bit;
	}
	
	public void setGamePath(String newPath){
		this.gamePath = new File(newPath);
	}
	
	public String getGamePath(){
		return gamePath.toString();
	}
	
	public File getGamePathRaw(){
		return gamePath;
	}
	
}
