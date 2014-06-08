package mindCrappy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DialogSetting extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUsername = new JLabel();
	private JTextField iptUsername = new JTextField();
	private JLabel lblJavaPath = new JLabel();
	private JTextField iptJavaPath = new JTextField();
	private JButton btnFind = new JButton();
	private JButton btnBws = new JButton();
	private JLabel lblSlider = new JLabel();
	private JSlider slider = new JSlider();
	private JLabel lblMemSize = new JLabel();
	private JLabel lbl64bit = new JLabel();
	private JCheckBox ckbox64bit = new JCheckBox();
	private JButton btnTerm = new JButton();
	private JLabel linkHelp = new JLabel();
	private JButton btnSave = new JButton();
	private JButton btnCancel = new JButton();
	private JLabel lblGamePath = new JLabel();
	private JTextField iptGamePath = new JTextField();
	private JButton btnBrowse = new JButton();
	private boolean i = true;

	public Cylinder data = new Cylinder();
	public LibraryReader jr = new LibraryReader();
	public GraphicsEnvironment GE;
	public GraphicsDevice GD;
	public DisplayMode DM;
	
	public DialogSetting(){
		GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GD = GE.getDefaultScreenDevice();
		DM = GD.getDisplayMode();
		// this.setAlwaysOnTop(true);
		this.setPreferredSize(new Dimension(340,340));
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		data.setSettingMode(true);
		this.setFont(data.getFont());
		this.setLayout(null);
		lblUsername.setText("\u7528\u6237\u540d");
		lblJavaPath.setText("Java\u8def\u5f84");
		iptJavaPath.setBounds(105, 68, 197, 24);
		btnFind.setText("\u81ea\u52a8\u5bfb\u627e");
		btnBws.setText("\u6d4f\u89c8");
		slider.setMaximum(2048);
		slider.setMinimum(512);
		slider.setMajorTickSpacing(256);
		slider.setMinorTickSpacing(256);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		lblSlider.setText("\u5185\u5b58\u5927\u5c0f");
		lblUsername.setBounds(29, 33, 55, 19);
		iptUsername.setBounds(105, 28, 197, 24);
		lblJavaPath.setBounds(29, 72, 66, 20);
		btnFind.setBounds(208, 102, 94, 26);
		btnBws.setBounds(136, 102, 63, 26);
		lblSlider.setBounds(29, 138, 66, 20);
		slider.setBounds(105, 138, 134, 20);
		slider.setValue(1024);
		lblMemSize.setBounds(249, 138, 53, 20);
		lblMemSize.setText(slider.getValue() + "MB");
		lbl64bit.setBounds(29, 174, 66, 20);
		lbl64bit.setText("64\u4f4d\u6a21\u5f0f");
		ckbox64bit.setBounds(105, 171, 103, 26);
		ckbox64bit.setText("\u52fe\u9009\u6211\u5f00\u542f");
		btnTerm.setBounds(209, 171, 93, 26);
		btnTerm.setText("\u5f00\u53d1\u8005\u7ec8\u7aef");
		lblGamePath.setBounds(30, 212, 65, 17);
		lblGamePath.setText("\u6e38\u620f\u8def\u5f84");
		iptGamePath.setBounds(106, 208, 123, 24);
		btnBrowse.setBounds(239, 207, 63, 26);
		btnBrowse.setText("\u6d4f\u89c8");
		linkHelp.setText("<html><font color=blue><u>\u6211\u5e94\u8be5\u4f7f\u7528\u54ea\u79cd\u914d\u7f6e\uff1f</u></font></html>");
		Font fnt = new Font(data.getFontType(),Font.PLAIN,11);
		linkHelp.setFont(fnt);
		linkHelp.setBounds(30, 267, 117, 10);
		btnCancel.setText("\u653e\u5f03");
		btnCancel.setBounds(167, 251, 63, 26);
		btnSave.setText("\u4fdd\u5b58");
		btnSave.setBounds(239, 251, 63, 26);
		
		btnTerm.addMouseListener(new MouseAdapter(){
			// Warning! This button is for developers only!
			// Test your function here!
			public void mouseClicked(MouseEvent e){
				
			}
		});
		
		btnSave.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(iptUsername.getText().length() <= 0){
					Object[] option = {"\u8fd4\u56de\u68c0\u67e5"};
					JOptionPane.showOptionDialog(null, "\u60a8\u4f3c\u4e4e\u5c11\u8f93\u5165\u4e86\u4e00\u4e9b\u4e1c\u897f...", "Oops!", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
					iptUsername.setBackground(Color.RED);
					return;
				}
				data.setUsername(iptUsername.getText());
				if(iptJavaPath.getText().length() <= 0)
					data.setJavaPath();
				else
					data.setJavaPath(iptJavaPath.getText());
				data.setMemSize(slider.getValue());
				if(ckbox64bit.isSelected() == true)
					data.set64bitMode(true);
				else
					data.set64bitMode(false);
				if(iptGamePath.getText().isEmpty()){
					Object[] option = {"\u8fd4\u56de\u68c0\u67e5"};
					JOptionPane.showOptionDialog(null, "\u60a8\u4f3c\u4e4e\u5c11\u8f93\u5165\u4e86\u4e00\u4e9b\u4e1c\u897f...", "Oops!", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
					iptGamePath.setBackground(Color.RED);
					return;
				}
				else
					data.setGamePath(iptGamePath.getText());
				data.saveSettings();
				dispose();
			}
		});
		btnCancel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				dispose();
			}
		});
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				lblMemSize.setText(slider.getValue() + "MB");
			}
		});
		btnFind.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				iptJavaPath.setText("java");
				iptJavaPath.setEnabled(false);
			}
		});
		iptUsername.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(iptUsername.getBackground() == Color.RED)
					iptUsername.setBackground(Color.WHITE);
			}
		});
		btnBrowse.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(i){
					i = false;
					Object[] option = {"\u6211\u77e5\u9053\u4e86"};
					JOptionPane.showOptionDialog(null, "\u8bf7\u76f4\u63a5\u9009\u62e9jar\u6587\u4ef6", "Notice!", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
				}
				try{
					JFileChooser j = new JFileChooser();
					j.setCurrentDirectory(new File("."));
					j.setFileHidingEnabled(true);
					j.showOpenDialog(null);
					String s = j.getSelectedFile().getParentFile().getAbsoluteFile().toString();
					iptGamePath.setText(s);
				}catch(NullPointerException ex){
					// It's a general error when user didn't select any file
					// This exception will be consumed
				}
			}
		});
		add(lblUsername);
		add(iptUsername);
		add(lblJavaPath);
		add(iptJavaPath);
		add(btnFind);
		add(btnBws);
		add(lblSlider);
		add(slider);
		add(lblMemSize);
		add(lbl64bit);
		add(ckbox64bit);
		add(btnTerm);
		add(lblGamePath);
		add(iptGamePath);
		add(btnBrowse);
		add(linkHelp);
		add(btnCancel);
		add(btnSave);
		this.setVisible(true);
		this.pack();
		this.setLocation((DM.getWidth() - getWidth()) / 2, (DM.getHeight() - getHeight()) / 2);
		this.loadSettings();
	}
	
	public void loadSettings(){
		data.readSettings();
		if(data.getUsername().length() <= 0)
			return;
		iptUsername.setText(data.getUsername());
		iptJavaPath.setText(data.getJavaPath());
		slider.setValue(data.getMemSize());
		if(data.get64bitMode())
			ckbox64bit.setSelected(true);
		iptGamePath.setText(data.getGamePath());
	}
	
}
