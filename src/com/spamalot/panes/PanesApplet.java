package com.spamalot.panes;

import javax.swing.JApplet;

/**
 * @author gej
 * 
 */
public class PanesApplet extends JApplet {

  /**
   * 
   */
  private static final long serialVersionUID = -5774190312718632599L;

  @Override
  public final void init() {
    com.spamalot.panes.Panes.init();
  }

}
