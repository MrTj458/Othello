package view;

import javax.swing.JFrame;

import controller.OthelloController;

/**
 * Main JFrame for the application
 * 
 * @author Trevor Hodsdon
 *
 */
public class GUIFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GUIPanel basePanel;

	public GUIFrame(OthelloController baseController) {
		basePanel = new GUIPanel(baseController);
		setupFrame();
	}

	private void setupFrame() {
		this.setContentPane(basePanel);
		this.setResizable(false);
		this.setSize(800, 800);
		this.setTitle("Othello");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public GUIPanel getPanel() {
		return basePanel;
	}
}
