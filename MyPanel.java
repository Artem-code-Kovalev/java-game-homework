

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel {
	int x = 400;
	int y = 220;
	Image deposit;
	Image fon;
	Image life;
	int slogn;
	Timer timerDraw, timerChip;
	Image chips[];
	Random r = new Random();
	int time;
	
	int count;//life col
	int ball = 0;//ochki
	int xf[] = new int[6];
	int yf[] = new int[6];
	int col1;
	
	
	public MyPanel(int slogn) throws IOException {
		
		
		deposit = ImageIO.read(new File("deposit.png"));
		fon = ImageIO.read(new File("fon.jpg"));
		life = ImageIO.read(new File("life.png"));
		chips = new Image[] {ImageIO.read(new File("5chip.png")),
				 ImageIO.read(new File("10chip.png")),
				 ImageIO.read(new File("15chip.png")),
				 ImageIO.read(new File("25chip.png")),
				 ImageIO.read(new File("50chip.png")),
				 ImageIO.read(new File("75chip.png"))};
		
		int col = slogn;
		count = 6 - slogn;
		for(int i=0; i<col; i++) {
			xf[i] = r.nextInt(590);
			yf[i] = -r.nextInt(300);
			
		}
			col1 = col;
			
		
		
		timerDraw = new Timer(50,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<col; i++) {
					yf[i] += 5;
					if(yf[i]>270) {
						count--;
						yf[i] = 0;
						xf[i] = r.nextInt(570);
					}
				}
				
				repaint();
				
			}
		});
		
		timerDraw.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(fon, 0, 0, null);
		g.drawImage(deposit, x, y, null);	
		for(int i=0; i<col1; i++) {
			if(xf[i]>x-30 && xf[i]<x+128 && yf[i]>y-40 && yf[i]<270){
				yf[i] = -r.nextInt(300);
				xf[i] = r.nextInt(570);
				ball++;
			
			}	   
		    
			g.drawImage(chips[i], xf[i] ,yf[i],null);
			
			
		}
		
		for(int i=0; i<=count; i++) {
			g.drawImage(life, i*40 ,0, null);
			
		}
		if(count==0)
			try {
				timerDraw.stop();
				stop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	private void stop() throws IOException {
		
		 JOptionPane.showMessageDialog(null,"Вы набрали " + ball +"очков.");	
		 
		 System.exit(0);
		 
		 
	}
}

