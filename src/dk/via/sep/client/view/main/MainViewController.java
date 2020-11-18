package dk.via.sep.client.view.main;

import dk.via.sep.client.core.ViewHandler;

public class MainViewController {

  private MainViewModel mainViewModel;
  private ViewHandler vh;

  public void init(MainViewModel mainViewModel, ViewHandler vh) {
    this.mainViewModel = mainViewModel;
    this.vh = vh;
  }
}
