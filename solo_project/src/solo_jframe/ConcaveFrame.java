package solo_jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ConcaveFrame extends JFrame implements ActionListener{
	private Container container = getContentPane();
	private JPanel topPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JButton btnStart = new JButton("시작");
	private JButton btnclose = new JButton("종료");
	//검은 체스말
	private ImageIcon[] blackImages = {
			new ImageIcon("images/BLook.png"),new ImageIcon("images/BKnight.png"),
			new ImageIcon("images/BBishop.png"),new ImageIcon("images/BQueen.png"),
			new ImageIcon("images/BKing.png"),new ImageIcon("images/BPawn.png")
			
	};
	private JLabel[] blackLabel = new JLabel[16];
	private ImageIcon[] blackChess = new ImageIcon[blackLabel.length];
	//하얀 체스말
	private ImageIcon[] whiteImages = {
			new ImageIcon("images/WLook.png"),new ImageIcon("images/WKnight.png"),
			new ImageIcon("images/WBishop.png"),new ImageIcon("images/WQueen.png"),
			new ImageIcon("images/WKing.png"),new ImageIcon("images/WPawn.png")
			
	};
	private JLabel[] whiteLabel = new JLabel[16];
	private ImageIcon[] whiteChess = new ImageIcon[whiteLabel.length];
	//체스판 패널
	private JPanel[] panels = new JPanel[64];
	public ConcaveFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("체스");
		setSize(500,500);
		setUi();
		setListener();
		setVisible(true);
	}
	public void setListener() {
		btnStart.addActionListener(this);
		for(int i = 0; i < blackLabel.length;i++) {
			blackLabel[i].addMouseListener(new MyMouseAdapter());
		}
		for(int x = 0; x < panels.length;x++) {
			panels[x].addMouseListener(new MyMouseAdapter());
		}
	}
	
	public void setUi() {
		setCenterPanel();
		setTopPanel();
	}
	public void setTopPanel() {
		topPanel.setBackground(Color.RED);
		topPanel.add(btnStart);
		topPanel.add(btnclose);
		container.add(topPanel, BorderLayout.NORTH);
	}
	public void setChessImage() {
			Image blackImage[] = new Image[blackLabel.length];
			Image whiteImage[] = new Image[whiteLabel.length];
		for(int z = 0; z < blackLabel.length; z++) {
			if(z <= 4) {
				blackImage[z] = blackImages[z].getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
				whiteImage[z] = whiteImages[z].getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
				blackChess[z] = new ImageIcon(blackImage[z]);
				whiteChess[z] = new ImageIcon(whiteImage[z]);
			} else if(z > 4 && z <= 7) {
				switch(z) {
					case 5:
						blackChess[z] = new ImageIcon(blackImage[2]);
						whiteChess[z] = new ImageIcon(whiteImage[2]);
					break;
					case 6:
						blackChess[z] = new ImageIcon(blackImage[1]);
						whiteChess[z] = new ImageIcon(whiteImage[1]);
						break;
					case 7:
						blackChess[z] = new ImageIcon(blackImage[0]);
						whiteChess[z] = new ImageIcon(whiteImage[0]);
						break;
				}
			}else if(z >= 8) {
				blackChess[z] = new ImageIcon(blackImages[5].getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
				whiteChess[z] = new ImageIcon(whiteImages[5].getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
			}
		}
		for(int x = 0; x < whiteLabel.length; x++) {
			whiteLabel[x] = new JLabel();
			whiteLabel[x].setIcon(whiteChess[x]);
			panels[63-x].add(whiteLabel[x]);
			whiteLabel[x].setVisible(false);
		}
		for(int i = 0; i < blackLabel.length;i++) {
			blackLabel[i] = new JLabel();
			blackLabel[i].setIcon(blackChess[i]);
			panels[i].add(blackLabel[i]);
			blackLabel[i].setVisible(false);
		}
	}
	public void setCenterPanel() {
		centerPanel.setLayout(new GridLayout(8,8));
		Color color1 = Color.GRAY;
		Color color2 = Color.WHITE;
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			if(i % 2 == 0) {
				panels[i].setBackground(color1);
				centerPanel.add(panels[i]);
			}else {
				panels[i].setBackground(color2);
				centerPanel.add(panels[i]);
			}
			
			if((i + 1) % 8 == 0 && i != 0) {
				Color test = color1;
				color1 = color2;
				color2 = test;
			}
		}
		setChessImage();
		container.add(centerPanel,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new ConcaveFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnStart)) {
			for(int i = 0; i < whiteLabel.length;i++) {
				whiteLabel[i].setVisible(true);
				blackLabel[i].setVisible(true);
			}
		}
	}
	
	class MyMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getSource());
		}
	}
}
