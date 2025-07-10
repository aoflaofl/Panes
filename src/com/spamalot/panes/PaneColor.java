package com.spamalot.panes;

import com.spamalot.panes.exception.IllegalAdditionException;
import com.spamalot.panes.exception.IllegalSubtractionException;
import java.awt.Color;

/**
 * Handles all the color needs for a game of Panes. Since we are dealing with
 * the primary colors Red, Blue and Yellow and the composites made from them, we
 * just use three bits (boolean variables) to represent the colors.
 * 
 * @author gej
 * 
 */
final class PaneColor implements java.io.Serializable {
  private static final PaneColor RED = new PaneColor(true, false, false);
  private static final PaneColor BLUE = new PaneColor(false, true, false);
  private static final PaneColor YELLOW = new PaneColor(false, false, true);
  private static final PaneColor PURPLE = new PaneColor(true, true, false);
  private static final PaneColor ORANGE = new PaneColor(true, false, true);
  private static final PaneColor GREEN = new PaneColor(false, true, true);
  static final PaneColor EMPTY = new PaneColor(false, false, false);

  /**
   * An array holding all the colors for easy iteration.
   */
  static final PaneColor[] PALLET = { PaneColor.RED, PaneColor.BLUE, PaneColor.YELLOW, PaneColor.PURPLE,
      PaneColor.GREEN, PaneColor.ORANGE, };

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final PaneColor WHITE = null;

  private boolean redC;
  private boolean blueC;
  private boolean yellowC;

  private Color value;

  private PaneColor(final boolean redBit, final boolean blueBit, final boolean yellowBit) {
    // TODO: Check for all bits being on.
    redC = redBit;
    blueC = blueBit;
    yellowC = yellowBit;
    value = realcolor();
  }

  // TODO: Implement a Builder inner class

  /**
   * Return the PaneColor that matches the colors passed in.
   * 
   * @param redBit    True if the color has red in it.
   * @param blueBit   True if the color has blue in it.
   * @param yellowBit True if the color has Yellow in it.
   * @return a PaneColor object matching the arguments passed in.
   */
  private static PaneColor instance(final boolean redBit, final boolean blueBit, final boolean yellowBit) {
    // Handle all possible color combinations using a more direct approach
    if (redBit && blueBit && yellowBit) {
      return PaneColor.WHITE;
    } else if (redBit && blueBit) {
      return PaneColor.PURPLE;
    } else if (redBit && yellowBit) {
      return PaneColor.ORANGE;
    } else if (blueBit && yellowBit) {
      return PaneColor.GREEN;
    } else if (redBit) {
      return PaneColor.RED;
    } else if (blueBit) {
      return PaneColor.BLUE;
    } else if (yellowBit) {
      return PaneColor.YELLOW;
    } else {
      return PaneColor.EMPTY;
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[ ");
    if (redC) {
      s.append("Red ");
    }
    if (blueC) {
      s.append("Blue ");
    }
    if (yellowC) {
      s.append("Yellow ");
    }
    s.append(']');
    return s.toString();
  }

  Color value() {
    return value;
  }

  private Color realcolor() {
    if (redC) {
      if (blueC) {
        return Color.magenta;
      } else if (yellowC) {
        return Color.orange;
      } else {
        return Color.red;
      }
    } else if (blueC) {
      if (yellowC) {
        return Color.green;
      }
      return Color.blue;
    } else if (yellowC) {
      return Color.yellow;
    } else {
      return Color.gray;
    }
  }

  /**
   * @return true if this color is a composite color (purple, orange, green).
   */
  private boolean isComposite() {
    return this == PaneColor.GREEN || this == PaneColor.ORANGE || this == PaneColor.PURPLE;
  }

  private boolean isPrimary() {
    return !isComposite() && this != PaneColor.EMPTY;
  }

  PaneColor minus(final PaneColor jumpColor) throws IllegalSubtractionException {
    PaneColor retVal = null;

    if ((this.isPrimary() && jumpColor.isPrimary()) || this == jumpColor) {
      retVal = PaneColor.EMPTY;

    } else if (jumpColor.isPrimary()) {
      boolean redBit = this.redC;
      boolean blueBit = this.blueC;
      boolean yellowBit = this.yellowC;
      if (jumpColor.redC) {
        redBit = false;
      }
      if (jumpColor.blueC) {
        blueBit = false;
      }
      if (jumpColor.yellowC) {
        yellowBit = false;
      }
      retVal = PaneColor.instance(redBit, blueBit, yellowBit);
    } else {
      throw new IllegalSubtractionException();
    }
    return retVal;
  }

  PaneColor plus(final PaneColor c) throws IllegalAdditionException {
    if (this == PaneColor.EMPTY || this == c) {
      return c;
    } else if (this.isPrimary() && c.isPrimary()) {
      boolean redBit = this.redC;
      boolean blueBit = this.blueC;
      boolean yellowBit = this.yellowC;

      if (c.redC) {
        redBit = true;
      }
      if (c.blueC) {
        blueBit = true;
      }
      if (c.yellowC) {
        yellowBit = true;
      }
      return PaneColor.instance(redBit, blueBit, yellowBit);
    } else {
      throw new IllegalAdditionException();
    }
  }

  private boolean has(final PaneColor c) {
    return ((redC && c.redC) || (blueC && c.blueC) || (yellowC && c.yellowC));
  }

  int count() {
    if (isComposite()) {
      return 2;
    }
    if (this == PaneColor.EMPTY) {
      return 0;
    }
    return 1;
  }

  boolean canJump(final PaneColor p) {
    if (p == PaneColor.EMPTY) {
      return false;
    }
    if (this == p) {
      return true;
    }
    if (this.isComposite()) {
      return false;
    }
    if (!p.isComposite()) {
      return true;
    }
    return (p.has(this));
  }

  boolean canLand(final PaneColor p) {
    if (this == p) {
      return true;
    }
    if (p == PaneColor.EMPTY) {
      return true;
    }
    return (!this.isComposite() && !p.isComposite());
  }
}
