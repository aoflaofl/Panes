package com.spamalot.panes;

import com.spamalot.panes.gui.LogArea;
import com.spamalot.panes.gui.PanesFrame;
import com.spamalot.panes.gui.PanesMenu;
import java.awt.Container;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Panes - A game of world domination. No, really just a puzzle game.
 * 
 * @author gej
 * 
 */
final class Panes {

	private Panes() {
	}

	public static void main(final String[] arg) {
		init();
	}

	/**
	 * Initializes the application by setting up the look and feel, creating the
	 * main frame, and initializing the game logic.
	 */
	static void init() {
		try {
			setLookAndFeel();
			JFrame frame = createMainFrame();
			addComponents(frame);
			frame.setVisible(true);
			PaneManipulator.init();
		} catch (Exception e) {
			showErrorDialog("Failed to initialize Panes application.", e);
		}
	}

	/**
	 * Sets the system look and feel for the application.
	 * 
	 * @throws ClassNotFoundException          if the look and feel class is not
	 *                                         found
	 * @throws InstantiationException          if the look and feel cannot be
	 *                                         instantiated
	 * @throws IllegalAccessException          if the look and feel cannot be
	 *                                         accessed
	 * @throws UnsupportedLookAndFeelException if the look and feel is not supported
	 */
	private static void setLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// Optionally, use a different look and feel:
		// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	}

	/**
	 * Creates and configures the main application frame.
	 * 
	 * @return the configured JFrame
	 */
	private static JFrame createMainFrame() {
		JFrame frame = new JFrame("Panes");
		frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		frame.setJMenuBar(PanesMenu.makeMenuBar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

	/**
	 * Adds UI components to the main frame.
	 * 
	 * @param frame the main application frame
	 */
	private static void addComponents(JFrame frame) {
		Container cp = frame.getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

		cp.add(Box.createVerticalGlue());

		final PanesFrame panesFrame = new PanesFrame();
		cp.add(panesFrame);
		cp.add(Box.createVerticalGlue());

		LogArea textarea = new LogArea();
		PaneManipulator.setLogArea(textarea);

		if (Constants.DEBUG) {
			cp.add(textarea);
			cp.add(Box.createVerticalGlue());
		}

		frame.pack();
	}

	/**
	 * Shows a user-friendly error dialog when initialization fails.
	 * 
	 * @param message the error message to display
	 * @param e       the exception that was thrown
	 */
	private static void showErrorDialog(String message, Exception e) {
		JOptionPane.showMessageDialog(null, message + "\n" + e.getMessage(), "Initialization Error",
				JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
}
