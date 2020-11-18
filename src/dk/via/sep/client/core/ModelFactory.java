package dk.via.sep.client.core;

import dk.via.sep.client.model.ModelInterface;
import dk.via.sep.client.model.ModelManager;

public class ModelFactory {

  private final ClientFactory cf;
  private ModelInterface modelInterface;

  public ModelFactory(ClientFactory cf) {
    this.cf = cf;
  }

  public ModelInterface getModelManager() {
    if (modelInterface == null)
      modelInterface = new ModelManager(cf.getClientNetwork());
    return modelInterface;
  }
}
