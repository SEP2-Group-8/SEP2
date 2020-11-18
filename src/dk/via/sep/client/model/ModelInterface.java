package dk.via.sep.client.model;

import dk.via.sep.shared.utils.Subject;

public interface ModelInterface extends Subject {
  void login(String value, String value1);
  void register(String un, String pw, String e_mail);
}
