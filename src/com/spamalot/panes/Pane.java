package com.spamalot.panes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

class Pane extends JPanel implements MouseListener {

  private static final long serialVersionUID = 1L;
  private PaneColor color;
  private boolean mark = false;

  private ArrayList<Pane> targets = new ArrayList<>();
  private HashMap<Pane, Pane> middles = new HashMap<>();

  Pane(final PaneColor clr) {
    this.color = clr;

    this.setPreferredSize(new Dimension(Constants.PANE_X_SIZE, Constants.PANE_Y_SIZE));

    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.RED, Color.YELLOW));

    addMouseListener(this);
  }

  final Pane setColor(final PaneColor clr) {
    this.color = clr;
    return this;
  }

  public final PaneColor getColor() {
    return color;
  }

  @Override
  public final String toString() {
    return color.toString();
  }

  @Override
  public void mouseClicked(final MouseEvent e) { // Event not used.
  }

  @Override
  public void mousePressed(final MouseEvent e) { // Event not used.
  }

  @Override
  public final void mouseReleased(final MouseEvent e) {
    PaneManipulator.clickPane(this);
  }

  @Override
  public void mouseEntered(final MouseEvent e) { // Event not used.
  }

  @Override
  public void mouseExited(final MouseEvent e) { // Event not used.
  }

  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);
    g.setColor(color.value());
    g.fillRect(0, 0, Constants.PANE_X_SIZE, Constants.PANE_Y_SIZE);
    if (mark) {
      g.setColor(Color.WHITE);
      g.fillOval((Constants.PANE_X_SIZE - Constants.MOVE_HINT_CIRCLE_SIZE) / 2,
          (Constants.PANE_Y_SIZE - Constants.MOVE_HINT_CIRCLE_SIZE) / 2, Constants.MOVE_HINT_CIRCLE_SIZE,
          Constants.MOVE_HINT_CIRCLE_SIZE);
    }
  }

  /**
   * Swap one Pane's color for another. This method should only be used when
   * generating a new board. To save time, no Panes will actually be drawn with
   * this method. Therefore, after all the swaps are done, all Panes should have
   * their apply method called.
   * 
   * @param p The pane to swap with.
   * @see PaneGrid#scrambleBoard()
   * @see #apply()
   */

  final void swapWith(Pane p) {
    PaneColor tmp = p.getColor();
    p.setColor(color);
    setColor(tmp);
  }

  /**
   * Tells a pane to place its color on the pane grid.
   */
  final void apply() {
    setColor(color);
    repaint();
  }

  public final boolean isEmpty() {
    return this.color == PaneColor.EMPTY;
  }

  final void register(final int x, final int y, final PaneGrid b) {
    int targetX;
    int targetY;
    for (int i = -2; i <= 2; i += 2) {
      for (int j = -2; j <= 2; j += 2) {
        if (i == 0 && j == 0) {
          continue;
        }

        targetX = x + i;
        targetY = y + j;

        Pane target = b.getPane(targetX, targetY);

        /*
         * target will only be null if it's not on the board if the registering is done
         * after the PaneGrid has been filled with Panes.
         */
        if (target != null) {
          targets.add(target);

          int middleX = (x + targetX) / 2;
          int middleY = (y + targetY) / 2;

          Pane middle = b.getPane(middleX, middleY);
          middles.put(target, middle);
        }
      }
    }
  }

  /**
   * Checks if this Pane can jump to a target Pane.
   * 
   * @param target The Pane being jumped to
   * @return true if this Pane can jump to target Pane.
   */
  final boolean canJumpTo(final Pane target) {
    Pane middle = middles.get(target);
    if (middle == null) {
      return false;
    }

    return this.color.canJump(middle.getColor()) && this.color.canLand(target.getColor());
  }

  final Pane getMiddle(final Pane target) {
    return middles.get(target);

  }

  private final void setHintMarker(final boolean turnOn) {
    mark = turnOn;
  }

  public final void setMoveHints(final boolean turnOn) {
    for (Pane targetPane : targets) {
      if (this.canJumpTo(targetPane)) {
        targetPane.setHintMarker(turnOn);
        targetPane.apply();
      }
    }
  }
}
