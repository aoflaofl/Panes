package com.spamalot.panes;

import com.spamalot.panes.exception.IllegalMoveException;
import com.spamalot.panes.gui.CounterLabel;
import com.spamalot.panes.gui.LogArea;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This is a Mediator class for the Panes game.
 */
public final class PaneManipulator {

  private static PaneGrid board;
  private static CounterLabel remainingLabel;
  private static CounterLabel movesmadeLabel;
  private static LogArea textarea;
  private static Pane lifted;
  private static Deque<Move> moves = new ArrayDeque<>();
  private static Deque<Move> redo = new ArrayDeque<>();

  private static boolean moveHint = true;

  private static long randomSeed = -1;

  private PaneManipulator() { // Do not instantiate
  }

  static void init() {
    newGame();
  }

  public static void setRemainingLabel(final CounterLabel remaining) {
    remainingLabel = remaining;
  }

  public static void setMovesMadeLabel(final CounterLabel movesmade) {
    movesmadeLabel = movesmade;
  }

  public static void setPaneGrid(final PaneGrid b) {
    board = b;
  }

  static void setLogArea(final LogArea a) {
    textarea = a;
  }

  private static void log(final String message) {
    if (Constants.DEBUG) {
      textarea.write(message);
    }
  }

  static void clickPane(final Pane p) {
    if (lifted == null) {
      lift(p);
    } else {
      drop(p);
    }
  }

  private static void lift(final Pane p) {
    log("Attempting to lift " + p);
    if (p.isEmpty()) {
      log(" Can't lift that\n");
    } else {
      log(" Lifting\n");
      lifted = p;
      if (moveHint) {
        lifted.setMoveHints(true);
      }
    }
  }

  private static void drop(final Pane target) {
    log("Attempting to drop on " + target);
    try {
      if (lifted.canJumpTo(target)) {
        log(" Dropping\n");
        if (moveHint) {
          lifted.setMoveHints(false);
        }
        Move move = board.makeMove(lifted, target);
        log(move.toString());
        pushMove(move);
        movesmadeLabel.inc();
        remainingLabel.setCounter(board.getPaneCount());
        lifted = null;
      } else {
        if (moveHint) {
          lifted.setMoveHints(false);
        }
        throw new IllegalMoveException(Messages.MOVE_NOT_LEGAL);
      }
    } catch (IllegalMoveException i) {
      log(" Not a legal move\n");
      lift(target);
      // log(i.getMessage());
    }
  }

  public static void takeBackMove() {
    log("Take Back\n");

    if (moves.isEmpty()) {
      log(Messages.NO_MOVES_TO_TAKE_BACK);
      return;
    }

    Move move = moves.pop();
    board.takeBackMove(move);
    redo.push(move);
    remainingLabel.setCounter(board.getPaneCount());
    movesmadeLabel.dec();
  }

  public static void redoMove() {
    log("Redo\n");

    if (redo.isEmpty()) {
      log(Messages.NO_MOVES_TO_REDO);
      return;
    }

    Move move = redo.pop();
    board.redoMove(move);
    moves.push(move);
    remainingLabel.setCounter(board.getPaneCount());
    movesmadeLabel.inc();
  }

  private static void pushMove(final Move move) {
    moves.push(move);
    redo.clear();
  }

  public static void newGame() {
    board.resetBoard();
    board.scrambleBoard(randomSeed);
    remainingLabel.setCounter(board.getPaneCount());
    movesmadeLabel.setCounter(0);
    redo.clear();
    moves.clear();
  }

  public static void exit() {
    log("Exiting");
    System.exit(0);
  }
}
