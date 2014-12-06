/**
 * @(#)FinalProject.java
 *
 * FinalProject Applet application
 *
 * @author 
 * @version 1.00 2014/11/26
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class FinalProject extends JApplet implements ActionListener {
	JLabel[] theLabels = {new JLabel("enter initial speed"), new JLabel("enter initial angle"), new JLabel("enter mass of the projectile"), new JLabel("enter the range of the motion"), new JLabel("enter the maximum height of the projectile")};
	JTextField[] theFields = {new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
	JPanel inputPanel;
	JButton goButton;
	MyJPanel displayPanel;
	
	public void init() {
		setSize(1000,500);
		goButton = new JButton("GO!");
		goButton.addActionListener(this);
		setLayout(new BorderLayout());
		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		inputPanel.add(theLabels[0]);
		inputPanel.add(theFields[0]);
		inputPanel.add(theLabels[1]);
		inputPanel.add(theFields[1]);
		inputPanel.add(theLabels[2]);
		inputPanel.add(theFields[2]);
		inputPanel.add(theLabels[3]);
		inputPanel.add(theFields[3]);
		inputPanel.add(theLabels[4]);
		inputPanel.add(theFields[4]);
		inputPanel.add(goButton);
		displayPanel = new MyJPanel();
		displayPanel.setPreferredSize(new Dimension(500,500));
		add(inputPanel, BorderLayout.SOUTH);
		add(displayPanel, BorderLayout.CENTER);
		}
		
		public void actionPerformed(ActionEvent click){
			Object src = click.getSource();
			if(src == goButton){
				displayPanel.setValues(Double.parseDouble(theFields[0].getText()),Double.parseDouble(theFields[1].getText()));
				displayPanel.validate();
				displayPanel.repaint();
				}
			
	}
    
	
}
	


class MyJPanel extends JPanel{
	double speed;
	double theta;
	int width = getWidth();
	int tall = getHeight();
	
	public void setValues(double s, double t){
		speed = s;
		theta = Math.toRadians(t);
	}
	
	public double getMaxHeight(double s, double t){
		double h = (s*s*Math.sin(t)*Math.sin(t))/(2*9.8);
		return h;
	}
	
	public double getMaxRange(double s, double t){
		double r = (s*s*Math.sin(2*t))/(9.8);
		return r;
	}
		
	
	protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        double range = getMaxRange(speed, theta);
        double height = getMaxHeight(speed, theta);
        QuadCurve2D.Double curve = new QuadCurve2D.Double(10,400,(range/2)+10,400-height,range+10,400);
        g2.draw(curve);
	}
	
}
