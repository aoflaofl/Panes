package com.spamalot.panes;

import com.spamalot.panes.exception.IllegalMoveException;

/**
 * Calculates and makes the changes for a move.
 */
class Move {

  private final Pane jumper;
  private final Pane middle;
  private final Pane target;
  private final ColorTrio before;
  private final ColorTrio after;

  /**
   * Class constructor.
   * 
   * @param jumperPane
   *          The pane that is doing the jumping.
   * @param middlePane
   *          The pane that is being jumped
   * @param targetPane
   *          The landing pane.
   * @throws IllegalMoveException
   *           If the move is not legal.
   */
  Move(final Pane jumperPane, final Pane middlePane, final Pane targetPane) throws IllegalMoveException {

    /*
     * Record the Panes involved in making this move.
     */
    jumper = jumperPane;
    middle = middlePane;
    target = targetPane;

    /*
     * Capture the color of the Panes before the move is made, in case the move
     * is taken back.
     */
    before = new ColorTrio(jumperPane, middlePane, targetPane);

    /*
     * Compute what the panes would look like if the move was made.
     */

    after = before.constructAfter();

    /*
     * and then update the board to reflect it.
     */
    /* TODO: Should this be here? */
    applyAfter();
  }

  /*
   * public void makeMove() throws IllegalMoveException { if (before.isLegal())
   * { after = before.constructAfter(); applyAfter(); } else { throw new
   * IllegalMoveException(Messages.MOVE_NOT_LEGAL); } }
   */

  final int diff() {
    return before.countPanes() - after.countPanes();
  }

  /**
   * Apply the Move to the board. This overwrites the panes that are visible on
   * the board.
   * 
   * @see Pane#apply()
   */
  private void applyBefore() {
    jumper.setColor(before.getJumperColor()).apply();
    middle.setColor(before.getMiddleColor()).apply();
    target.setColor(before.getTargetColor()).apply();
  }

  final void takeBack() {
    applyBefore();
  }

  private void applyAfter() {
    jumper.setColor(after.getJumperColor()).apply();
    middle.setColor(after.getMiddleColor()).apply();
    target.setColor(after.getTargetColor()).apply();
  }

  final void redo() {
    applyAfter();
  }

  @Override
  public final String toString() {
    return jumper.toString() + ' ' + middle + ' ' + target;
  }
}
