package mindCrappy;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Framework extends JFrame{
	public framework frame = new framework();
	public DialogSetting dlgSetting;
	private static final long serialVersionUID = 1L;
	
	public GraphicsEnvironment GE;
	public GraphicsDevice GD;
	public DisplayMode DM;
	static Point origin = new Point();
	
	public BtnAbout btnAbout;
	public BtnSetting btnSetting;
	public BtnStart btnStart;
	public BtnExit btnExit;
	
	public WebIO web;
	
	public Framework(){
		// setup
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setBounds(0, 0, 720, 450);
		GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GD = GE.getDefaultScreenDevice();
		DM = GD.getDisplayMode();
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		this.setLayout(null);
		this.add(frame);
		frame.setBounds(0, 0, 720, 450);
		this.setVisible(true);
		// make window goto the center of the screen
		this.setLocation((DM.getWidth() - getWidth()) / 2, (DM.getHeight() - getHeight()) / 2);
		// make the window able to drag
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				Point p = getLocation();
				setLocation(p.x+e.getX()-origin.x, p.y+e.getY() - origin.y);
			}
		});
	}
	
	public class framework extends JPanel{
		private static final long serialVersionUID = 1L;
		public JPanel blockInfo = new JPanel();
		public JLabel lblInfo = new JLabel();
		public JLabel lblBdcast = new JLabel();
		public JLabel lblOnGoing = new JLabel();
		public JLabel lblTasks = new JLabel();
		public String bdcast; // !d
		public String onGoing; // !d
		public String tasks; // !d
		public JTabbedPane bdpane = new JTabbedPane();
		public framework(){
			lblBdcast.setText(bdcast);
			lblOnGoing.setText(onGoing);
			lblTasks.setText(tasks);
			bdpane.addTab("\u516c\u544a\u677f", lblBdcast);
			bdpane.addTab("\u5f53\u524d\u4efb\u52a1", lblOnGoing);
			bdpane.addTab("\u8ba1\u5212\u4efb\u52a1", lblTasks);
			this.setLayout(null);
			lblInfo.setText(fetchInfo());
			lblInfo.setBounds(487, 229, 206, 115);
			blockInfo.setBorder(BorderFactory.createTitledBorder("\u7528\u6237\u4fe1\u606f"));
			blockInfo.setBounds(477, 213, 226, 141);
			btnSetting = new BtnSetting("\u8bbe\u7f6e",477,364,85,31);
			btnAbout = new BtnAbout("\u5173\u4e8e",477,404,85,26);
			btnStart = new BtnStart("\u542f\u52a8\u6e38\u620f",572,364,131,66);
			btnExit = new BtnExit("X",670,12,34,34);
			bdpane.setBounds(12, 198, 455, 231);
			
			this.add(btnStart);
			this.add(btnAbout);
			this.add(btnSetting);
			this.add(blockInfo);
			this.add(bdpane);
			this.add(btnExit);
			blockInfo.add(lblInfo);
			this.setVisible(true);
			this.repaint();
		}
		
		public String fetchInfo(){
			Cylinder data = new Cylinder();
			String builder = new String();
			data.readSettings();
			builder = "<html>\u7528\u6237\u540d:" + 
			data.getUsername() +
			"<br />"+
			"\u5185\u5b58\u5927\u5c0f:" + 
			data.getMemSize() + 
			"<br />" +
			"64\u4f4d\u652f\u6301:" +
			data.get64bitMode() +
			"<br />" + 
			"Java\u8def\u5f84:" + 
			data.getJavaPath() +
			"</html>";
			return builder;
		}
		
		public void update(){
			
		}
	}
	
	public static void main(String[] args){
		new Framework();
	}
	
}
