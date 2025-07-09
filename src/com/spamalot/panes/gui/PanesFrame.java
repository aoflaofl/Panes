package com.spamalot.panes.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.spamalot.panes.PaneGrid;
import com.spamalot.panes.PaneManipulator;

/**
 * Define the basic layout of the game.
 * 
 * @author gej
 * 
 */
public class PanesFrame extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public PanesFrame() {
    // Container cp = this.getContentPane();
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    add(Box.createHorizontalStrut(7));
    final PaneGrid b = new PaneGrid();
    PaneManipulator.setPaneGrid(b);

    add(b);

    // add(Box.createHorizontalGlue());
    add(Box.createHorizontalStrut(5));
    add(Box.createHorizontalGlue());
    add(new ControlPanel());
    add(Box.createHorizontalStrut(7));

  }
}
