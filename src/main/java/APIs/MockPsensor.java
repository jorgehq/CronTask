package APIs;

import APIs.WSensor.Reading;
import APIs.WSensor.WSensor;

public class MockPsensor implements WSensor {
  @Override
  public Reading getWeight(String serialNumber) {
    return new Reading(10,"KG");
  }
}
