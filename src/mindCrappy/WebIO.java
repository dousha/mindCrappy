package mindCrappy;

import java.io.*;
import java.net.*;

public class WebIO{
	public class checkUpdate extends Thread{
		@Override
		public void run() {
			StringBuffer buff = new StringBuffer();
		try{
			// TODO find a nice server!
			URL addr = new URL("http://someweb.example.com/update/version.vf");
			InputStream inBuff = new BufferedInputStream(addr.openStream());
			InputStreamReader buffReader = new InputStreamReader(inBuff, "UTF-8");
			int c;
			while((c = buffReader.read()) != -1){
				buff.append((char) c);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(buff.toString() == new Cylinder().getVersion())
			return;
		// TODO download update
		new downloadUpdateScript();
		}
	}
	
	public class downloadUpdateScript implements Runnable{
		@Override
		public void run() {
			try {
				URL addr = new URL("http://someweb.example.com/update/update.sc");
				HttpURLConnection conn = (HttpURLConnection) addr.openConnection();
				InputStream in = conn.getInputStream();
				String localFileName = addr.getFile().substring(addr.getFile().lastIndexOf("/")+1);
				FileOutputStream out = new FileOutputStream(localFileName);
				int legth = 0;
				int total = 0;
				byte[] buf = new byte[1024];
				while((legth = in.read(buf))!= -1){
					out.write(buf, 0, legth);
					total = total + legth;
				}
				in.close();
				out.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
