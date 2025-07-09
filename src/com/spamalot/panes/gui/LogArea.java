package com.spamalot.panes.gui;

import java.awt.TextArea;

import com.spamalot.panes.Constants;

/**
 * The logging area that is displayed when debugging.
 * 
 * @author gej
 * 
 */
public class LogArea extends TextArea {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private boolean on = true;

  public LogArea() {
    super(Constants.ROWS_IN_LOG_AREA, Constants.COLUMNS_IN_LOG_AREA);
    this.setEditable(false);
  }

  public final void write(final String text) {
    if (on) {
      this.append(text);
    }
  }

  // TODO Remove unused code found by UCDetector
  // public final void turnOn() {
  // on = true;
  // }

  // TODO Remove unused code found by UCDetector
  // public final void turnOff() {
  // on = false;
  // }
}
