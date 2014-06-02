package mindCrappy;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class BtnAbout extends Btn{
	private static final long serialVersionUID = 1L;

	public BtnAbout(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
	}
	
	public void isClicked(MouseEvent e) {
		System.out
				.println("Thanks for clicking About button!\n"
						+ "Oh! Right!\n"
						+ "Do you want to join us?\n"
						+ "Contact me via QQ!\n"
						+ "Account Num: 565910397\n"
						+ "dousha Studio Creative Department & Programming Department.\n");
		JOptionPane
				.showConfirmDialog(
						null,
						"\u7a0b\u5e8f:dousha99    Copyleft (!c) dousha Studio 2013-2014, \u6839\u636eCC BY-NC-SA\u534f\u8bae\u5f00\u6e90",
						"\u5173\u4e8e", JOptionPane.INFORMATION_MESSAGE);
	}

}
