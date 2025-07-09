package com.spamalot.panes.exception;

/**
 * Thrown when a move is not correct.
 * 
 * @author gej
 * 
 */
public class IllegalMoveException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -5820689415169616099L;

  public IllegalMoveException() {
    super();
  }

  public IllegalMoveException(final String s) {
    super(s);
  }
}
