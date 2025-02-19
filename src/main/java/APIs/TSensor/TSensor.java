package APIs.TSensor;

public interface TSensor {
  void connect(String serialNumber);
  void onTemperatureChange(Action action);
}
