import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class initGUI{
	int[] number = new int[4];
	int[] check = new int[4];
	public int next = 0;
	int[] pomCheck = new int[4];
	
	JFrame frame = new JFrame("Skocko");  
    JPanel panel = new JPanel();
    Color colorBG = new Color(30, 30, 30);
    Color colorPanel = new Color(80, 80, 80);
    Random rand = new Random();
    int constI = 0;
    int constJ = 0;
    boolean notFinish = true;
    int onPlace = 0;
    int onPl = onPlace;
    int notOnPlace = 0;
    ArrayList<JLabel> list = new ArrayList<JLabel>();
    
    JButton skocko;
    JButton detelina;
    JButton list2;
    JButton srce;
    JButton karo;
    JButton zvezda;
    
    

    public void init() {
	    frame.add(panel);
	    frame.setSize(800, 800);  
	    frame.setLocationRelativeTo(null);  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setVisible(true);  
	    frame.setResizable(false);
	    frame.setIconImage(new ImageIcon(getClass().getResource("/skocko.png")).getImage());
	    
	    for(int i = 0; i<4; i++) {
			check[i] = rand.nextInt(6) +1;
		}
    }
    
	
	
	public void createLabel(Icon icon){ 
		JLabel l = new JLabel();
		panel.add(l);
		
		l.setIcon(icon);
		l.setLocation(frame.getWidth() /2 -320 +constJ *72, 48 +constI *72);
		l.setSize(64, 64);
		l.setBackground(Color.WHITE);
		l.setOpaque(true);
		
		
		if (next > 3){ 
			next = 0;
			
			
			for(int i = 0; i<4; i++) {
				pomCheck[i] = check[i];
				
				if (pomCheck[i] == number[i]) {
					number[i] = -1;
					pomCheck[i] = -1;
					onPlace++;
				}
			}
			
			for (int i = 0; i<4; i++) {
				for (int j = 0; j<4; j++) {
					if ((pomCheck[i] > 0) && (number[j] > 0)) {
						if (pomCheck[i] == number[j]) {
							number[j] = -1;
							pomCheck[i] = -1;
							notOnPlace++;
						}
					}
				}
			}
			
			onPl = onPlace;
			
			for (int i = 0; i<4; i++) {
				JLabel d = new JLabel();
				panel.add(d);
				
				
				if (onPlace > 0) {
					d.setIcon(new ImageIcon(getClass().getResource("/tacno1.png")));
					d.setLocation(frame.getWidth() /2 +48 +i *72, 48 +constI *72);
					onPlace--;
				}else if (notOnPlace > 0) {
					d.setIcon(new ImageIcon(getClass().getResource("/tacno2.png")));
					d.setLocation(frame.getWidth() /2 +48 +i *72, 48 +constI *72);
					notOnPlace--;
				}else {
					d.setIcon(new ImageIcon(getClass().getResource("/tacno3.png")));
					d.setLocation(frame.getWidth() /2 +48 +i *72, 48 +constI *72);
				}
				d.setSize(64, 64);
				d.setBackground(null);
				d.setOpaque(true);
			}
		}
		
		constJ++;
		if (constJ > 3) {
			constI++;
			if (constI > 6) {
				notFinish = false;
			}
			constJ = 0;
		}
		
		System.out.println(constI);
		
		if (onPl == 4 || constI > 6){
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = -1;
			if (onPl == 4){
				dialogResult = JOptionPane.showConfirmDialog (null, "Tacno, da li zelis da igras ponovo?","",dialogButton);
			}else if (constI > 6){
				dialogResult = JOptionPane.showConfirmDialog (null, "Nisi pogodio tacnu kombinaciju, da li zelis da igras ponovo?","",dialogButton);
			}
			if (dialogResult == JOptionPane.YES_OPTION) {
				panel.removeAll();
				panel.setLayout(null);
		        panel.add(skocko);
		        panel.add(detelina);
		        panel.add(srce);
		        panel.add(list2);
		        panel.add(karo);
		        panel.add(zvezda);
		        panel.setBackground(colorBG);
				panel.revalidate();
				panel.repaint();
				list.removeAll(list);
				next = 0;
				constI = 0;
				constJ = 0;
				onPlace = 0;
				onPl = 0;
				notOnPlace = 0;
				notFinish = true;
				for(int i = 0; i<4; i++) {
					check[i] = rand.nextInt(6) +1;
				}
				
			}else {
				frame.dispose();
			}
		}
		
		
	}
	
	
	public void createStuff(){ 
        
        skocko = new JButton();
        skocko.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (notFinish) {
					number[next] = 1;
					next++;
					createLabel(new ImageIcon(getClass().getResource("/skocko.png")));
				}
			}
		});
        detelina = new JButton();
        detelina.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (notFinish) {
					number[next] = 2;
					next++;
					createLabel(new ImageIcon(getClass().getResource("/detelina.png")));
				}
				
			}
		});
        list2 = new JButton();
        list2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (notFinish) {
					number[next] = 3;
					next++;
					createLabel(new ImageIcon(getClass().getResource("/list.png")));
				}
			}
		});
        
        srce = new JButton();
        srce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (notFinish) {
					number[next] = 4;
					next++;
					createLabel(new ImageIcon(getClass().getResource("/srce.png")));
				}
			}
		});
        karo = new JButton();
        karo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (notFinish) {
					number[next] = 5;
					next++;
					createLabel(new ImageIcon(getClass().getResource("/karo.png")));
				}
			}
		});
        zvezda = new JButton();
        zvezda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (notFinish) {
					number[next] = 6;
					next++;
					createLabel(new ImageIcon(getClass().getResource("/zvezda.png")));
				}
			}
		});
        
        JLabel bg = new JLabel();
        
        panel.setLayout(null);
        panel.add(skocko);
        panel.add(detelina);
        panel.add(srce);
        panel.add(list2);
        panel.add(karo);
        panel.add(zvezda);
        panel.add(bg);
        panel.setBackground(colorBG);
        
        
        skocko.setLocation(frame.getWidth() /2 -32 -36*5, frame.getHeight() -112);
        skocko.setSize(64, 64);
        skocko.setIcon(new ImageIcon(getClass().getResource("/skocko.png")));
        skocko.setBackground(Color.WHITE);
        
        detelina.setLocation(frame.getWidth() /2 -32 -36*3, frame.getHeight() -112);
        detelina.setSize(64, 64);
        detelina.setIcon(new ImageIcon(getClass().getResource("/detelina.png")));
        detelina.setBackground(Color.WHITE);
        
        list2.setLocation(frame.getWidth() /2 -32 -36*1, frame.getHeight() -112);
        list2.setSize(64, 64);
        list2.setIcon(new ImageIcon(getClass().getResource("/list.png")));
        list2.setBackground(Color.WHITE);
        
        srce.setLocation(frame.getWidth() /2 -32 +36*1, frame.getHeight() -112);
        srce.setSize(64, 64);
        srce.setIcon(new ImageIcon(getClass().getResource("/srce.png")));
        srce.setBackground(Color.WHITE);
        
        karo.setLocation(frame.getWidth() /2 -32 +36*3, frame.getHeight() -112);
        karo.setSize(64, 64);
        karo.setIcon(new ImageIcon(getClass().getResource("/karo.png")));
        karo.setBackground(Color.WHITE);
        
        zvezda.setLocation(frame.getWidth() /2 -32 +36*5, frame.getHeight() -112);
        zvezda.setSize(64, 64);
        zvezda.setIcon(new ImageIcon(getClass().getResource("/zvezda.png")));
        zvezda.setBackground(Color.WHITE);
        
        /*bg.setSize(448, 432);
        bg.setLocation(panel.getWidth() /2 -224, 32);
        bg.setBackground(colorPanel);
        bg.setOpaque(true);*/
        
    }  
}
