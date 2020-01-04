package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	protected void setInputLayoutConstraints(JComponent object) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		if (object instanceof JTextField) {
			constraints.gridy=1;
			constraints.gridheight=2;
			this.containerLayout.setConstraints(object, constraints);
		} else {
			constraints.gridy=1;
			constraints.gridheight=2;
			this.containerLayout.setConstraints(object, constraints);
		}
	}
}
