package mindCrappy;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Btn extends JPanel implements MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean isEnable;
	public String title;
	public int status;
	public Cylinder data = new Cylinder();
	public Btn(String name, int x, int y, int width, int height){
		this.status = 0;
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.isEnable = true;
		this.title = name;
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		switch (status)
		{
		case 0:
			g.setColor(Color.GRAY);
			g.fillRoundRect(0, 0, getWidth()-6, getHeight()-6, 2, 2);
			g.setColor(Color.BLACK);
			g.drawRoundRect(0, 0, getWidth()-6, getHeight()-6, 5, 5);
			break;
		case 1:
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(0, 0, getWidth()-6, getHeight()-6, 2, 2);
			g.setColor(Color.BLACK);
			g.drawRoundRect(0, 0, getWidth()-6, getHeight()-6, 5, 5);
			break;
		case 2:
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect(0, 0, getWidth()-6, getHeight()-6, 2, 2);
			g.setColor(Color.BLACK);
			g.drawRoundRect(0, 0, getWidth()-6, getHeight()-6, 5, 5);
			break;
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font(data.getFontType(), 0, 16));
		FontMetrics fm = g.getFontMetrics();
		int stringWidth = fm.stringWidth(title);
		int stringAscent = fm.getAscent();
		int stringDescent = fm.getDescent();
		int x = getWidth() / 2 - stringWidth / 2;
		int y = getHeight() / 2 + (stringAscent - stringDescent) / 2;
		g.drawString(title, x + 1, y + 1);
		if (isEnable)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.LIGHT_GRAY);
		g.drawString(title, x - 1, y - 1);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	public void mouseClicked(MouseEvent e) {
		if (!isEnable)
		{
			return;
		} else
		{
			isClicked(e);
			e.consume();
			return;
		}
	}

	public void mouseEntered(MouseEvent e) {
		if(!isEnable) return;
		if(status != 1){
			status = 1;
			repaint();
		}
		isEntered(e);
		e.consume();
	}

	public void mouseExited(MouseEvent e) {
		if(!isEnable) return;
		if(status == 1){
			status = 0;
			repaint();
		}
		isExited(e);
		e.consume();
	}

	public void mousePressed(MouseEvent e) {
		if(!isEnable) return;
		if(status == 1){
			status = 2;
			repaint();
		}
		isPressed(e);
		e.consume();
	}

	public void mouseReleased(MouseEvent e) {
		if(!isEnable) return;
		if(status == 2){
			status = 0;
			repaint();
		}
		isReleased(e);
		e.consume();
	}
	
	public void isClicked(MouseEvent e){
		
	}
	
	public void isEntered(MouseEvent e){
		
	}
	
	public void isExited(MouseEvent e){
		
	}
	
	public void isPressed(MouseEvent e){
		
	}
	
	public void isReleased(MouseEvent e){
		
	}
}
