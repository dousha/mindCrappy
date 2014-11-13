package mindCrappy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

public class BdcastPane extends JPanel{
	public Cylinder data = new Cylinder();
	public int easter = 0;
	public boolean isRunning = false;
	private static final long serialVersionUID = 1L;
	private JLabel lblBdcast = new JLabel("<html><font color=black size=32>\u70b9\u6211\u66f4\u65b0\u516c\u544a</font></html>");
	public AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
	
	public BdcastPane(int x, int y, int width, int height){
		lblBdcast.setFont(new Font(data.getFontType(),0,14));
		this.setLayout(null);
		this.add(lblBdcast,"North");
		this.setBounds(x, y, width, height);
		lblBdcast.setBounds(0, 0, width, height);
		this.setOpaque(false);
		this.setVisible(true);
		this.repaint();
		lblBdcast.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(easter < 6){
					easter++;
					updateBdcast();
				}
				else if(easter > 6){
					updateBdcast();
					easter = 0;
				}
				else{
					lblBdcast.setText("<html><font color=black>"
							+ "<p>\u6211\u5f88\u611f\u8c22\u5927\u5bb6\u5728\u8fd9\u6bb5\u65f6\u95f4\u7ed9\u6211\u7684\u9f13\u52b1\u548c\u5173\u6000\uff01\u611f\u8c22\u5927\u5bb6\u80fd\u591f\u62bd\u51fa35\u79d2\u7684\u65f6\u95f4\u6765\u95ee\u6211\u76ee\u524d\u7684\u8fdb\u5ea6\u600e\u6837\uff0c\u4e5f\u5f88\u611f\u8c22\u5927\u5bb6\u5bf9\u6211\u7684\u652f\u6301\u3002</p>"
							+ "<p>\u6211\u77e5\u9053\u6ca1\u4eba\u80fd\u591f\u7406\u89e3\u4e00\u4e2ageek\u7684\u601d\u7ef4\uff0c\u56e0\u6b64\u6211\u5728\u53d1\u5e03\u4e00\u4e9b\u4e1c\u897f\u770b\u5230\u7fa4\u91cc\u9762\u51b7\u7684\u4e00\u7247\u7684\u65f6\u5019\u6211\u4e5f\u5e76\u4e0d\u60ca\u8bb6\uff0c\u4e5f\u5e76\u4e0d\u6028\u6068\u5927\u5bb6\u3002\u8fd9\u662f\u6211\u5df2\u7ecf\u4e60\u4ee5\u4e3a\u5e38\u7684\u4e8b\u60c5\u3002\u4e0d\u8fc7\uff0c\u611f\u8c22\u5927\u5bb6\u7ed9\u4e88\u6211\u7684\u70ed\u60c5\uff01</p>"
							+ "<p>\u4f60\u627e\u5230\u4e86\u4e00\u4e2a\u5f69\u86cb\uff01\u606d\u559c\u4f60\uff01\u8981\u77e5\u9053\u53ef\u80fd\u6709\u66f4\u591a\u7684\u60ca\u559c\u5728\u7b49\u7740\u4f60\u3002\u8fd9\u4e2a\u542f\u52a8\u5668\u91cc\u57cb\u85cf\u4e86\u6211\u5f88\u591a\u7684\u5fc3\u8840\uff0c\u6211\u4e3a\u4e86\u5b9e\u73b0\u8de8\u5e73\u53f0\u7279\u610f\u51b3\u5b9a\u5b66\u4e60Java\uff0c\u82b1\u4e864\u4e2a\u6708\u3002\u6211\u5f88\u62b1\u6b49\u8fd94\u4e2a\u6708\u7684\u5b66\u4e60\u65f6\u95f4\u6211\u65e0\u826f\u8df3\u7968\u4e86\u591a\u6b21\uff0c\u5728\u6b64\u7ed9\u5927\u5bb6\u9053\u6b49\u3002</p>"
							+ "<p>\u8fd9\u4e2a\u5f69\u86cb\u662f\u65e9\u671f\u5f00\u53d1\u7248\u672c\u72ec\u6709\u7684\uff0c\u5982\u679c\u559c\u6b22\u8bf7\u622a\u56fe\u7559\u5ff5233333333\u3002</p>"
							+ "<p>\u987a\u81f4\u6765\u81ea\u80cc\u666f\u566a\u58f0\u6536\u96c6\u5668\u7684\u95ee\u5019\u3002</p>"
							+ "<p>dousha,</p>"
							+ "<p>dousha Studio Creative & Program Department.</p>"
							+ "</font></html>");
				}
			}
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(composite);
		g2.setColor(new Color(0xCCCCCC));
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	public void updateBdcast(){
		lblBdcast.setText("<html><font color=black size=32>...\u6b63\u5728\u5237\u65b0...</font></html>");
		if(!isRunning){
			new Thread(new getBdcast()).start();
			isRunning = true;
		}
	}
	
	public class getBdcast implements Runnable {
		
		public getBdcast() {
			// default constructing function
		}

		public void run() {
			StringBuffer buff = new StringBuffer();
			try {
				// and broadcast loads from here
				// you can point it to your server or so
				// this is just a demo
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
			lblBdcast.setText(buff.toString());
			isRunning = false;
		}
	}

}
