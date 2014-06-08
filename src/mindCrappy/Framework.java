package mindCrappy;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

public class Framework extends JFrame {
	public framework frame = new framework();
	public DialogSetting dlgSetting;
	public Cylinder data = new Cylinder();
	private static final long serialVersionUID = 1L;

	public GraphicsEnvironment GE;
	public GraphicsDevice GD;
	public DisplayMode DM;
	static Point origin = new Point();

	public BtnAbout btnAbout;
	public BtnSetting btnSetting;
	public BtnStart btnStart;
	public BtnExit btnExit;

	public File dump = new File("./cache/isRunning");

	public Framework() {
		// setup
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
		public JLabel lblBdcast = new JLabel();
		public JLabel lblOnGoing = new JLabel();
		public JLabel lblTasks = new JLabel();
		public JTabbedPane bdpane = new JTabbedPane();

		public framework() {
			lblBdcast.setText("Loading...CLICK ME to refresh!");
			lblOnGoing.setText("Coming Soon!");
			lblTasks.setText("Coming PRETTY, PRETTY Soon!");
			bdpane.addTab("\u516c\u544a\u677f", lblBdcast);
			bdpane.addTab("\u5f53\u524d\u4efb\u52a1", lblOnGoing);
			bdpane.addTab("\u8ba1\u5212\u4efb\u52a1", lblTasks);
			this.setLayout(null);
			lblInfo.setText(fetchInfo());
			lblInfo.setBounds(487, 229, 206, 115);
			blockInfo.setBorder(BorderFactory
					.createTitledBorder("\u7528\u6237\u4fe1\u606f"));
			blockInfo.setBounds(477, 213, 226, 141);
			btnSetting = new BtnSetting("\u8bbe\u7f6e", 477, 364, 85, 31);
			btnAbout = new BtnAbout("\u5173\u4e8e", 477, 404, 85, 26);
			btnStart = new BtnStart("\u542f\u52a8\u6e38\u620f", 572, 364, 131,
					66);
			btnExit = new BtnExit("X", 670, 12, 34, 34);
			bdpane.setBounds(12, 198, 455, 231);

			lblBdcast.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					lblBdcast.setText(lblBdcast.getText() + "...REFRESHING...");
					getBdcast();
				}
			});

			lblTasks.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("You Found A Easter Egg! Woo!");
					lblTasks.setText("<html>"
							+ "<p>\u6211\u5f88\u611f\u8c22\u5927\u5bb6\u5728\u8fd9\u6bb5\u65f6\u95f4\u7ed9\u6211\u7684\u9f13\u52b1\u548c\u5173\u6000\uff01\u611f\u8c22\u5927\u5bb6\u80fd\u591f\u62bd\u51fa35\u79d2\u7684\u65f6\u95f4\u6765\u95ee\u6211\u76ee\u524d\u7684\u8fdb\u5ea6\u600e\u6837\uff0c\u4e5f\u5f88\u611f\u8c22\u5927\u5bb6\u5bf9\u6211\u7684\u652f\u6301\u3002</p><p>\u6211\u77e5\u9053\u6ca1\u4eba\u80fd\u591f\u7406\u89e3\u4e00\u4e2ageek\u7684\u601d\u7ef4\uff0c\u56e0\u6b64\u6211\u5728\u53d1\u5e03\u4e00\u4e9b\u4e1c\u897f\u770b\u5230\u7fa4\u91cc\u9762\u51b7\u7684\u4e00\u7247\u7684\u65f6\u5019\u6211\u4e5f\u5e76\u4e0d\u60ca\u8bb6\uff0c\u4e5f\u5e76\u4e0d\u6028\u6068\u5927\u5bb6\u3002\u8fd9\u662f\u6211\u5df2\u7ecf\u4e60\u4ee5\u4e3a\u5e38\u7684\u4e8b\u60c5\u3002\u4e0d\u8fc7\uff0c\u611f\u8c22\u5927\u5bb6\u7ed9\u4e88\u6211\u7684\u70ed\u60c5\uff01</p>"
							+ "<p>\u4f60\u627e\u5230\u4e86\u4e00\u4e2a\u5f69\u86cb\uff01\u606d\u559c\u4f60\uff01\u8981\u77e5\u9053\u53ef\u80fd\u6709\u66f4\u591a\u7684\u60ca\u559c\u5728\u7b49\u7740\u4f60\u3002\u8fd9\u4e2a\u542f\u52a8\u5668\u91cc\u57cb\u85cf\u4e86\u6211\u5f88\u591a\u7684\u5fc3\u8840\uff0c\u6211\u4e3a\u4e86\u5b9e\u73b0\u8de8\u5e73\u53f0\u7279\u610f\u51b3\u5b9a\u5b66\u4e60Java\uff0c\u82b1\u4e864\u4e2a\u6708\u3002\u6211\u5f88\u62b1\u6b49\u8fd94\u4e2a\u6708\u7684\u5b66\u4e60\u65f6\u95f4\u6211\u65e0\u826f\u8df3\u7968\u4e86\u591a\u6b21\uff0c\u5728\u6b64\u7ed9\u5927\u5bb6\u9053\u6b49\u3002</p>"
							+ "<p>\u8fd9\u4e2a\u5f69\u86cb\u662f\u65e9\u671f\u5f00\u53d1\u7248\u672c\u72ec\u6709\u7684\uff0c\u5982\u679c\u559c\u6b22\u8bf7\u622a\u56fe\u7559\u5ff5233333333\u3002</p>"
							+ "<p>\u987a\u81f4\u6765\u81ea\u80cc\u666f\u566a\u58f0\u6536\u96c6\u5668\u7684\u95ee\u5019\u3002</p>"
							+ "<p>dousha,</p>"
							+ "<p>dousha Studio Creative & Program Department.</p>"
							+ "</html>");
				}
			});
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

		public void getBdcast() {
			new Thread(new getBdcast(this)).start();
		}

		public String fetchInfo() {
			Cylinder data = new Cylinder();
			String builder = new String();
			data.readSettings();
			builder = "<html>\u7528\u6237\u540d:" + data.getUsername()
					+ "<br />" + "\u5185\u5b58\u5927\u5c0f:"
					+ data.getMemSize() + "<br />" + "64\u4f4d\u652f\u6301:"
					+ data.get64bitMode() + "<br />" + "Java\u8def\u5f84:"
					+ data.getJavaPath() + "</html>";
			return builder;
		}

	}

	public static void main(String[] args) {
		new Cylinder().init();
		new Framework();
	}

	public class getBdcast implements Runnable {
		framework fmwk;

		public getBdcast(framework f) {
			this.fmwk = f;
		}

		public void run() {
			StringBuffer buff = new StringBuffer();
			try {
				URL url = new URL(
						"https://raw.githubusercontent.com/dousha/mindCrappy/master/update/motd.md");
				URLConnection conn = url.openConnection();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					buff.append(line);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			fmwk.lblBdcast.setText(buff.toString());
		}
	}

}
