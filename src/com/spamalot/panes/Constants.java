package com.spamalot.panes;

/**
 * Constants file for Panes.
 */
public final class Constants {
  /**
   * Debug mode or not.
   */
  static final boolean DEBUG = true;

  /**
   * Width of the frame.
   */
  static final int FRAME_WIDTH = 900;
  /**
   * Height of the frame.
   */
  static final int FRAME_HEIGHT = 350;
  /**
   * X Size of a pane in pixels.
   */
  static final int PANE_X_SIZE = 40;
  /**
   * Y Size of a pane in pixels.
   */
  static final int PANE_Y_SIZE = 40;
  /**
   * The space between panes on the board.
   */
  static final int SPACE_BETWEEN_PANES = 3;
  /**
   * The size of the border in pixels around the Panes Grid.
   */
  static final int BORDER_SIZE_AROUND_PANES_GRID = 5;
  /**
   * The size of the move hint circle.
   */
  static final int MOVE_HINT_CIRCLE_SIZE = 15;
  /**
   * How many times to go through the scramble process.
   */
  static final int SCRAMBLE_PASSES = 100;
  /**
   * The number of panes wide this puzzle is. Width * Height has to be evenly
   * divisible by 6.
   */
  static final int PUZZLE_WIDTH = 12;
  /**
   * The number of panes high the puzzle is.
   */
  static final int PUZZLE_HEIGHT = 6;
  /**
   * Number of panes at start of game. Remember that composite panes count as
   * two panes, which is where the 1.5 comes in.
   */
  static final int START_NUMBER_OF_PANES = (int) ((PUZZLE_WIDTH * PUZZLE_HEIGHT) * 1.5);
  /**
   * The number of rows in the Log Area.
   */
  public static final int ROWS_IN_LOG_AREA = 8;
  /**
   * The number of columns in the Log Area.
   */
  public static final int COLUMNS_IN_LOG_AREA = 8;

  /**
   * The number of colors being used.
   */
  static final int NUMBER_OF_COLORS = 6;

  /**
   * Do not allow instantiation.
   */
  private Constants() {
  }
}
