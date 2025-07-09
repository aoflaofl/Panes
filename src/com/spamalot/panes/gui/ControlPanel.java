package com.spamalot.panes.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.spamalot.panes.Messages;
import com.spamalot.panes.PaneManipulator;

/**
 * Define the buttons and other elements that the user uses to control the game.
 * 
 * @author gej
 * 
 */
class ControlPanel extends JPanel {
  private static final class RedoAction implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
      PaneManipulator.redoMove();
    }
  }

  private static final class TakeBackButton implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
      PaneManipulator.takeBackMove();
    }
  }

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ControlPanel() {
    // setLayout(new GridLayout(6,1));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    // this.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    CounterLabel remaining = new CounterLabel(Messages.PANES_REMAINING);
    remaining.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    add(remaining);
    PaneManipulator.setRemainingLabel(remaining);

    CounterLabel movesmade = new CounterLabel(Messages.MOVES_MADE);
    movesmade.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    add(movesmade);

    PaneManipulator.setMovesMadeLabel(movesmade);

    this.add(Box.createVerticalStrut(5));

    JPanel buttonPanel = new JPanel();
    // buttonPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    JButton takeback = new JButton("Take Back");
    takeback.addActionListener(new TakeBackButton());
    buttonPanel.add(takeback);

    JButton redo = new JButton("Redo Move");
    redo.addActionListener(new RedoAction());
    buttonPanel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    buttonPanel.add(redo);

    this.add(buttonPanel);
  }
}
