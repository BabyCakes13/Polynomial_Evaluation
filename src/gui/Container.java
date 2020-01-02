package gui;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Container {
	protected JFrame frame;
	protected JPanel container;
	protected GridBagLayout containerLayout;
	
	public Container(JFrame frame, String containerTitle) {
		this.frame = frame;
		this.container = this.createBasicContainer(containerTitle);
		this.containerLayout = this.setLayout();
		this.container.setLayout(containerLayout);
	}
	
	public JPanel createBasicContainer(String containerTitle) {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder(containerTitle));
		
		return container;
	}
	
	private GridBagLayout setLayout() {
		GridBagLayout gridbag = new GridBagLayout();
		this.container.setLayout(gridbag);
		
		return gridbag;
	}
}
