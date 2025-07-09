package com.spamalot.panes.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.spamalot.panes.PaneManipulator;

/**
 * The drop down menu for Panes.
 * 
 * @author gej
 * 
 */
public final class PanesMenu {
  /**
   * Don't instantiate.
   */
  private PanesMenu() {
  }

  public static JMenuBar makeMenuBar() {
    JMenuBar menuBar;
    JMenu menu;

    menuBar = new JMenuBar();

    /*
     * The Game Menu.
     */
    menu = new JMenu("Game");
    menu.setMnemonic(KeyEvent.VK_G);
    menuBar.add(menu);
    JMenuItem menuItem;
    menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(new ActionListener() {

      public void actionPerformed(final ActionEvent e) {
        PaneManipulator.newGame();
      }
    });
    menu.add(menuItem);

    menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
    menuItem.addActionListener(new ActionListener() {

      public void actionPerformed(final ActionEvent e) {
        PaneManipulator.exit();
      }
    });
    menu.add(menuItem);

    /*
     * The Moves menu.
     */
    menu = new JMenu("Moves");
    menu.setMnemonic(KeyEvent.VK_M);
    menuBar.add(menu);

    menuItem = new JMenuItem("Take Back Move", KeyEvent.VK_T);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(new ActionListener() {

      public void actionPerformed(final ActionEvent e) {
        PaneManipulator.takeBackMove();
      }
    });

    menu.add(menuItem);

    return menuBar;
  }

}
