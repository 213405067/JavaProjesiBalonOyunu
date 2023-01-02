package defaultpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates {
	private int x;
	private int y;
	public Ates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}

public class Oyun extends JPanel implements KeyListener,ActionListener {
	Timer timer = new Timer(0,this);
	private int gecen_sure=0;
	private int harcanan_ates=0;
	
	private BufferedImage image;
	
	private ArrayList<Ates> atesler = new ArrayList<Ates>();
	
	private int atesdirY=1;
	private int balonX =0;
	private int balondirX=2;
	private int silahX= 0;
	private int dirOrtamX=20;
	public boolean kontrolEt() {
		for (Ates ates : atesler) {
			if (new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(balonX,0,20,20))) {
				return true;
				
			}
		}
		return false;
		
	}
	


	public Oyun() {
	try {
		 String workingDir = System.getProperty("user.dir");
		image =ImageIO.read(new FileImageInputStream(new File(workingDir+"/src/silah.png")));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 String workingDir = System.getProperty("user.dir");
		    System.out.println("Current working directory : " + workingDir);
		    e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
	setBackground(Color.BLACK);
	timer.start();
	
	
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		gecen_sure +=5;
		
		g.setColor(Color.red);
		g.fillOval(balonX, 0, 45,45);
		g.drawImage(image, silahX, 490,image.getWidth() / 4,image.getHeight()/4,this);
		for (Ates ates : atesler) {
			if (ates.getY() < 0) {
				atesler.remove(ates);
				
				}
			}
		g.setColor(Color.BLUE);
		for (Ates ates : atesler) {
			g.fillRect(ates.getX(), ates.getY(), 10, 20);
		}
		if (kontrolEt()) {
			timer.stop();
			String message = "Tebrikler, Kazandınız :) \n" + "Harcanan Ateş :  " + harcanan_ates +"\nGeçen Süre: " + gecen_sure /1000.0 + " saniye";
			JOptionPane.showMessageDialog(this,message);
			System.exit(0);
			
			}
			}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Ates ates : atesler) {
			ates.setY(ates.getY()-atesdirY);
		}
		balonX +=balondirX;
		if (balonX >= 750 ) {
			balondirX = -balondirX;
		}
		if (balonX <=0) {
			balondirX= -balondirX;
		}
		repaint();
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
			// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c== KeyEvent.VK_LEFT) {
			if (silahX <=0) {
				silahX=0;
		
			}
			else {
				silahX-= dirOrtamX;
				
			}
		}
		else if (c== KeyEvent.VK_RIGHT) {
			if (silahX >=720) {
				silahX=720;
				
			}
			else {
				silahX+= dirOrtamX;
				
			}
		}
		else if (c== KeyEvent.VK_CONTROL ) {
			atesler.add(new Ates (silahX+5,470));
			harcanan_ates++;
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
