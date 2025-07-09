package com.spamalot.panes;

import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.spamalot.panes.gui.LogArea;
import com.spamalot.panes.gui.PanesFrame;
import com.spamalot.panes.gui.PanesMenu;

/*
 * TODO: Sound
 * TODO: Hints - Suggest a move
 * TODO: Resize Panes
 * TODO: Clean up GUI - Borders, Colors
 * TODO: JPGs for Panes
 * TODO: Image Buttons
 * TODO: Support Depth First Search
 */
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

  static void init() {

    try {
      JFrame f;

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

//      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

      f = new JFrame("Panes");
      f.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

      Container cp = f.getContentPane();
      cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

      cp.add(Box.createVerticalGlue());

      final PanesFrame b = new PanesFrame();
      cp.add(b);
      cp.add(Box.createVerticalGlue());
      LogArea textarea = new LogArea();
      PaneManipulator.setLogArea(textarea);

      if (Constants.DEBUG) {
        cp.add(textarea);
        cp.add(Box.createVerticalGlue());
      }
      f.setJMenuBar(PanesMenu.makeMenuBar());

      f.pack();

      f.setVisible(true);
      PaneManipulator.init();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }
}
