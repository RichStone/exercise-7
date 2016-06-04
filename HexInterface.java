import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class HexInterface extends UserInterface {
	
	private JPanel buttonPanelHex;
	private JPanel radioPanel;
	private JRadioButton rad;
	
	public HexInterface(CalcEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	

	protected void makeFrame() {
		
		super.makeFrame();
		
		JPanel contentPaneHex = (JPanel)frame.getContentPane();

		buttonPanelHex = new JPanel(new GridLayout(4, 4));
		
		addButton(buttonPanelHex, "A");
		addButton(buttonPanelHex, "B");
		addButton(buttonPanelHex, "C");
		addButton(buttonPanelHex, "D");
		addButton(buttonPanelHex, "E");
		addButton(buttonPanelHex, "F");
		
		contentPaneHex.add(buttonPanelHex, BorderLayout.SOUTH);
		
		rad = new JRadioButton("hex");
		rad.setSelected(true);
		UserInterface.buttonPanel.add(rad, BorderLayout.SOUTH );
		
		rad.addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent event)
	    {
		 	
		 	super.actionPerformed(event);
		 	
	        String command = event.getActionCommand();
	        
	        if (	 command.equals("A") ||
	        		 command.equals("B") ||
	        		 command.equals("C") ||
	        		 command.equals("D") ||
	        		 command.equals("E") ||
	        		 command.equals("F")) {
        	calc.hexPressed(command);
	        }
	        
	        else if(command.equals("hex")){
	        	processSwitchGUI();
	        }

	        super.redisplay();
	    }
	
	private void processSwitchGUI() {
		if(rad.isSelected()) {
			
			buttonPanelHex.setVisible(true);
		}
		else {
			buttonPanelHex.setVisible(false);
		}
	}
}
