import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Architect extends JFrame implements Runnable {
	
	static int KeyCode;
	private int x;
	private int y;
	private int xDir;
	private int yDir;

	static int counter = 0;
	static int speed = 500;
	
	//private String Box_File = "/Users/Cory/Desktop/Workspace/Architect/src/box.png";
	public static Image box;
	public static Thread t1;
	
	public static ArrayList <Rectangle> RectArr = new ArrayList<Rectangle>();
	public static int[] ROW = new int[]{4,4,3,3,2,2,1,1,1};
	
	/////////////////////////////MAKE BOARD//////////////////////////////////
	public Architect(){
		addKeyListener(new KeyADAPT());
		setTitle("Architect");
		setSize(320,400);
		setBackground(Color.BLACK);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//ImageIcon i = new ImageIcon(Box_File);
		//box = i.getImage();
		xDir = 40;
		yDir = 0;
		x = 160;
		y = 360;
	}
	
	public void paint(Graphics g){
		Image dbImg = createImage(getWidth(), getHeight());
        Graphics dbg = dbImg.getGraphics();
        draw(dbg);
        g.drawImage(dbImg, 0, 0, this);
        
	}
	
	public void draw(Graphics g){
			
		g.setColor(Color.RED);
		int temp = 0;
		for(int i = 0; i < ROW[counter]; i++){
			g.fillRect(x +temp , y, 40, 40);
			temp+=40;
		}
			
			//g.setColor(Color.RED);
			//g.fillRect(x,y,40,40);
			
			for(int i = 0; i<RectArr.size(); i++)
				g.fillRect(RectArr.get(i).x,RectArr.get(i).y,RectArr.get(i).width,RectArr.get(i).height);
			
			


			
			repaint();
		}
	////////////////////////////////////////////////////////////////////////
		
	public static void main(String[] args) {
		Architect OurGame = new Architect();
		t1 = new Thread(OurGame);
		t1.start();
		
	}

	public void move(){
		x+=xDir;
		
		
		for(int i = 0; i < ROW.length; i++){
			if(ROW[i] == 4){
				if(xDir > 140)
					xDir = -40;
			}
				
		}
		
		
		
		
		
		
		if(x>260)
			xDir = -40;
		if(x<40)
			xDir = 40;
		
		
	}
	
	@Override
	public void run() {
		
		while(true){
			move();	
		try{
				Thread.sleep(speed);
			
			}catch(Exception e){
				System.out.println("Error");
				
		
				}
			}
		
	}
	
	///////////////////////////////////KEY ADAPTER/////////////////////////////////////////////////
	public class KeyADAPT extends KeyAdapter {

		public void keyPressed(KeyEvent e){
			 KeyCode = e.getKeyCode();
			if(KeyCode == e.VK_SPACE){
			int temp = 0;	
				System.out.println("HELLO");
				System.out.println(KeyCode);
				for(int i = 0; i < ROW[counter];i++){
				RectArr.add(new Rectangle(x + temp,y,40,40));
				
				temp+=40;
				}
				
				 if(counter >= 8)
					speed = 30;
				 else if(speed >125)
				speed -=75;
				
					
				y-=40;
				counter++;
				}
			
			}
			
		}///end key adapter
		
	
	////////////////////////////THREAD CONTROLLER METHOD///////////////////////////////////
	public void setThreadCondition(Thread i, int x){
		if( x == 0)
			i.stop();
		
	}
	
		
		
	}
	


