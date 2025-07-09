package com.spamalot.panes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

import com.spamalot.panes.exception.IllegalMoveException;

/**
 * The grid the panes will be displayed in.
 * 
 * @author gej
 * 
 */
public class PaneGrid extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Pane[][] panes = new Pane[Constants.PUZZLE_WIDTH][Constants.PUZZLE_HEIGHT];
  private int panesRemaining = Constants.START_NUMBER_OF_PANES;

  private long gameNumber;

  public PaneGrid() {
    GridLayout layout = new GridLayout(Constants.PUZZLE_HEIGHT, Constants.PUZZLE_WIDTH, Constants.SPACE_BETWEEN_PANES, Constants.SPACE_BETWEEN_PANES);
    setLayout(layout);
    setBackground(Color.black);
    this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, Constants.BORDER_SIZE_AROUND_PANES_GRID));
    // this.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    initPanes();

    Dimension d = this.getPreferredSize();
    this.setMaximumSize(d);
    this.setMinimumSize(d);
  }

  // Fill in the board with Panes.
  private void initPanes() {
    int position = 0;
    for (int i = 0; i < Constants.PUZZLE_HEIGHT; i++) {
      for (int j = 0; j < Constants.PUZZLE_WIDTH; j++) {
        panes[j][i] = new Pane(PaneColor.EMPTY);
        this.add(panes[j][i], position++);
      }
    }

    for (int i = 0; i < Constants.PUZZLE_HEIGHT; i++) {
      for (int j = 0; j < Constants.PUZZLE_WIDTH; j++) {
        panes[j][i].register(j, i, this);
      }
    }
  }

  final void resetBoard() {
    int palletCur = 0;
    for (int i = 0; i < Constants.PUZZLE_HEIGHT; i++) {
      for (int j = 0; j < Constants.PUZZLE_WIDTH; j++) {
        panes[j][i].setColor(PaneColor.PALLET[palletCur]);
        palletCur = (palletCur + 1) % Constants.NUMBER_OF_COLORS;
      }
    }
    this.panesRemaining = Constants.START_NUMBER_OF_PANES;
  }

  /**
   * Scramble the panes. TODO: Add options to scramble with more or less
   * difficulty. Putting composites in corner squares and edges would make it
   * more difficult, etc.
   * 
   * @param seed
   *          A random seed number, so games could be replicated.
   */
  final void scrambleBoard(final long seed) {
    final Random rg = new Random();

    if (seed >= 0) {
      gameNumber = seed;
    } else {
      gameNumber = rg.nextLong();
    }
    rg.setSeed(gameNumber);

    for (int t = 0; t < Constants.SCRAMBLE_PASSES; t++) {
      for (int x1 = 0; x1 < Constants.PUZZLE_WIDTH; x1++) {
        for (int y1 = 0; y1 < Constants.PUZZLE_HEIGHT; y1++) {
          int x2 = rg.nextInt(Constants.PUZZLE_WIDTH);
          int y2 = rg.nextInt(Constants.PUZZLE_HEIGHT);
          panes[x1][y1].swapWith(panes[x2][y2]);
        }
      }
    }
    applyAll();
  }

  private void applyAll() {
    for (int i = 0; i < Constants.PUZZLE_HEIGHT; i++) {
      for (int j = 0; j < Constants.PUZZLE_WIDTH; j++) {
        panes[j][i].apply();
      }
    }
  }

  /**
   * Makes the move defined by lifted and target. Jumps the lifted Pane over the
   * Pane in the middle to the Target Pane.
   * 
   * @param lifted
   *          The pane that was lifted
   * @param target
   *          The lifted Pane's landing pane.
   * @return a Move object that can be used to take back a move or redo a move.
   * @throws IllegalMoveException
   *           thrown if the arguments do not represent a legal move.
   */
  final Move makeMove(final Pane lifted, final Pane target) throws IllegalMoveException {
    // Put the three panes into a Move and make the
    // move. This also checks legality.
    Pane middle = lifted.getMiddle(target);
    Move before = new Move(lifted, middle, target);
    panesRemaining = panesRemaining - before.diff();
    return before;
  }

  final void takeBackMove(final Move move) {
    move.takeBack();
    panesRemaining += move.diff();
  }

  final void redoMove(final Move move) {
    move.redo();
    panesRemaining -= move.diff();
  }

  final Pane getPane(final int x, final int y) {
    if ((x < 0) || (x >= Constants.PUZZLE_WIDTH) || (y < 0) || (y >= Constants.PUZZLE_HEIGHT)) {
      return null;
    }
    return panes[x][y];
  }

  final int getPaneCount() {
    return panesRemaining;
  }
}
