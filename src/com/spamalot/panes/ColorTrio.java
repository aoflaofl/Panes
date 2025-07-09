package com.spamalot.panes;

import com.spamalot.panes.exception.IllegalAdditionException;
import com.spamalot.panes.exception.IllegalMoveException;
import com.spamalot.panes.exception.IllegalSubtractionException;

/**
 * Manage a color trio.
 * 
 * @author gej
 * 
 */
class ColorTrio {

  private final PaneColor jumperColor;
  private final PaneColor middleColor;
  private final PaneColor targetColor;

  private ColorTrio(final PaneColor jumper, final PaneColor jumpee, final PaneColor target) {
    jumperColor = jumper;
    middleColor = jumpee;
    targetColor = target;
  }

  ColorTrio(final Pane jumper, final Pane jumpee, final Pane target) {
    jumperColor = jumper.getColor();
    middleColor = jumpee.getColor();
    targetColor = target.getColor();
  }

  /**
   * @return the jumperColor
   */
  public final PaneColor getJumperColor() {
    return jumperColor;
  }

  /**
   * @return the middleColor
   */
  public final PaneColor getMiddleColor() {
    return middleColor;
  }

  /**
   * @return the targetColor
   */
  public final PaneColor getTargetColor() {
    return targetColor;
  }

  /**
   * Count the number of Panes in the three colors of this ColorTrio. Composite
   * panes count as two.
   * 
   * @return the number of panes in this ColorTrio.
   */
  final int countPanes() {
    return jumperColor.count() + middleColor.count() + targetColor.count();
  }

  /**
   * Check if this trio is a legal move.
   * 
   * @return true if this trio represents a legal move.
   * @deprecated Don't know why.
   */
  /*
   * public final boolean isLegal() { return PaneColor.isLegal(jumperColor,
   * middleColor, targetColor); }
   */

  /**
   * Return a ColorTrio with the colors that would result from jumping the
   * jumper over the jumpee to land on the target.
   * 
   * @return the ColorTrio with the move made.
   * @throws IllegalMoveException
   *           when there is an illegal move
   */
  final ColorTrio constructAfter() throws IllegalMoveException {
    ColorTrio result = null;
    try {
      result = new ColorTrio(PaneColor.EMPTY, middleColor.minus(jumperColor), targetColor.plus(jumperColor));
    } catch (IllegalSubtractionException e) {
      throw new IllegalMoveException();
    } catch (IllegalAdditionException e) {
      throw new IllegalMoveException();
    }
    return result;
  }
}
