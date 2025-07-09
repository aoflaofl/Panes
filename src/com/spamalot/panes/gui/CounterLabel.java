package com.spamalot.panes.gui;

import javax.swing.JLabel;

/**
 * A Label that has a counter that can be incremented and decremented. The
 * object takes some text that can be prepended to the counter. Could be used,
 * for example, as score keeping mechanism in a game.
 * 
 * @author Gene Johannsen (initial creation)
 */
public class CounterLabel extends JLabel {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;

  /**
   * A string to prepend to the counter. The value of the counter will be
   * directly appended to this text, so provide a space or other punctuation if
   * required.
   */
  private String text = "";

  /**
   * Holds the value of the counter.
   */
  private int counter = 0;

  /**
   * Constructor. The counter defaults to 0.
   * 
   * @param txt
   *          - The text to be prepended to the counter.
   */
  CounterLabel(final String txt) {
    super();
    this.text = txt;
    super.setText(txt + counter);
    // this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
  }

  // TODO Remove unused code found by UCDetector
  // /**
  // * Constructor.
  // *
  // * @param txt
  // * The text to be prepended to the counter.
  // * @param count
  // * The initial value of the counter.
  // */
  // public CounterLabel(final String txt, final int count) {
  // super();
  // this.text = txt;
  // this.counter = count;
  // super.setText(txt + count);
  // }

  /**
   * Set the text to be prepended to the counter.
   * 
   * @param txt
   *          Text to be prepended to the counter.
   */
  public final void setText(final String txt) {
    this.text = txt;
    super.setText(txt + counter);
  }

  /**
   * Sets the counter to a value.
   * 
   * @param count
   *          Value to set the counter to.
   */
  public final void setCounter(final int count) {
    this.counter = count;
    super.setText(text + this.counter);
  }

  /**
   * Increment the counter by 1.
   * 
   */
  public final void inc() {
    this.counter++;
    super.setText(text + counter);
  }

  // TODO Remove unused code found by UCDetector
  // /**
  // * Increments the counter by a value.
  // *
  // * @param value
  // * Value added to the counter.
  // */
  // public final void inc(final int value) {
  // this.counter += value;
  // super.setText(text + counter);
  // }

  /**
   * Decrement the counter by 1.
   * 
   */
  public final void dec() {
    this.counter--;
    super.setText(text + counter);
  }

  // TODO Remove unused code found by UCDetector
  // /**
  // * Decrement the counter by a value.
  // *
  // * @param value
  // * Value subtracted from the counter.
  // */
  // public final void dec(final int value) {
  // this.counter -= value;
  // super.setText(text + counter);
  // }
}
