package Domain.Heladera.Sensores;

import APIs.TSensor.Action;
import Domain.Heladera.EstadoHeladera;
import java.math.BigDecimal;

public class AccionInformarHeladera implements Action {
  EstadoHeladera estadoHeladera;

  public AccionInformarHeladera(EstadoHeladera estadoHeladera) {
    this.estadoHeladera = estadoHeladera;
  }

  @Override
  public void executeForTemperature(double temperaturaNueva) {
    this.estadoHeladera.updateLecturaSensorTemperatura(new BigDecimal( temperaturaNueva) );

  }
}
