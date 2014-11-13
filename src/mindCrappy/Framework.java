package mindCrappy;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class Framework extends JFrame {
	public Assets assets = new Assets();
	public framework frame = new framework(assets, this);
	public DialogSetting dlgSetting;
	public Cylinder data = new Cylinder();
	private static final long serialVersionUID = 1L;
	// Dragging around
	public GraphicsEnvironment GE;
	public GraphicsDevice GD;
	public DisplayMode DM;
	static Point origin = new Point();
	// and these are buttons
	public BtnAbout btnAbout;
	public BtnSetting btnSetting;
	public BtnStart btnStart;
	public BtnExit btnExit;
	// and this is the lock file
	public File dump = new File("./cache/isRunning");
	// and the color of the text
	private String colorName = "white";
	public Framework() {
		// setup
		this.setIconImage(assets.icon);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setBounds(0, 0, 720, 450);
		GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GD = GE.getDefaultScreenDevice();
		DM = GD.getDisplayMode();
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setLayout(null);
		this.add(frame);
		frame.setBounds(0, 0, 720, 450);
		this.setVisible(true);
		// make window goto the center of the screen
		this.setLocation((DM.getWidth() - getWidth()) / 2,
				(DM.getHeight() - getHeight()) / 2);
		// make the window able to drag
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
						- origin.y);
			}
		});
		try {
			dump.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class framework extends JPanel {
		private static final long serialVersionUID = 1L;
		public JPanel blockInfo = new JPanel();
		public JLabel lblInfo = new JLabel();
		public Assets assets;
		public BdcastPane bdpane = new BdcastPane(12, 198, 455, 231);
		public boolean isHidden = false;
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(assets.bkgnd, 0, 0, getWidth(), getHeight(), null);
		}
		
		public framework(Assets as, Framework fmwk) {
			assets = as;
			this.setLayout(null);
			lblInfo.setText(fetchInfo(colorName));
			lblInfo.setBounds(487, 229, 206, 115);
			blockInfo.setBorder(BorderFactory
					.createTitledBorder("<html><font color = white>\u7528\u6237\u4fe1\u606f</font></html>"));
			blockInfo.setBounds(477, 213, 226, 141);
			blockInfo.setOpaque(false);
			btnSetting = new BtnSetting("\u8bbe\u7f6e", 477, 364, 85, 31, fmwk);
			btnAbout = new BtnAbout("\u5173\u4e8e", 477, 404, 85, 26);
			btnStart = new BtnStart("\u542f\u52a8\u6e38\u620f", 572, 364, 131,
					66);
			btnExit = new BtnExit("", 670, 12, 34, 34, this);
			lblInfo.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if(colorName == "white")
						colorName = "black";
					else
						colorName = "white";
					refreshInfo();
				}
			});
			this.add(btnStart);
			this.add(btnAbout);
			this.add(btnSetting);
			this.add(blockInfo);
			this.add(btnExit);
			this.add(bdpane);
			blockInfo.add(lblInfo);
			this.setVisible(true);
			this.repaint();
		}

		public String fetchInfo(String colorName) {
			Cylinder data = new Cylinder();
			String builder = new String();
			data.readSettings();
			builder = "<html><font color = " + colorName +">\u7528\u6237\u540d:" + data.getUsername()
					+ "<br />" + "\u5185\u5b58\u5927\u5c0f:"
					+ data.getMemSize() + "<br />" + "64\u4f4d\u652f\u6301:"
					+ data.get64bitMode() + "<br />" + "</font></html>";
			return builder;
		}
		
		public void refreshInfo(){
			lblInfo.setText(fetchInfo(colorName));
		}

	}

	public static void main(String[] args) {
		new Cylinder().init();
		new Framework();
	}
	
}
