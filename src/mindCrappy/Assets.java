package mindCrappy;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Assets {
	public Image bkgnd; // background
	public Image icon; // icon
	public Image btnE; // exit
	public String path;
	public Random random;
	public int randomnum;
	public int count = 0;
	public int randomCount[] = new int[5];
	
	public Assets(){
		genPath();
		try{
			icon = ImageIO.read(new File("./assets/icon.png"));
			btnE = ImageIO.read(new File("./assets/btn_exit.png"));
			bkgnd = ImageIO.read(new File(path));
		}
		catch(IOException ex){
			ex.printStackTrace();
			icon = null;
			btnE = null;
			bkgnd = null;
		}
	}
	
	public void genPath(){
		if(this.count == 5){
			this.path = "./assets/backgrounds/bg0.jpg";
			return;
		}
		else
			this.count++;
		random = new Random(System.currentTimeMillis());
		randomnum = Math.abs(random.nextInt(15));
		String pathA = "./assets/backgrounds/bg" + randomnum + ".jpg";
		if(new File(pathA).exists()){
			this.path = pathA;
			return;
		}
		else{
			try {
				Thread.currentThread();
				Thread.sleep(75);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			genPath();
		}
	}
}

