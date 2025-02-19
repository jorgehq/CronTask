package Domain.Heladera.Sensores;

import APIs.MockPsensor;
import APIs.WSensor.Reading;
import APIs.WSensor.WSensor;
import Domain.Exception.ErrorConeccionSensorDePeso;
import Domain.Heladera.EstadoHeladera;
import Domain.Incidentes.TipoAlerta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Embeddable
public class AdapterSensorPeso {

  @Transient
  WSensor sensorPesoAPI;
@Column
  String numeroDeSerie;


  public AdapterSensorPeso() {
    this.numeroDeSerie = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
  }

  public void iniciarSensor(){
    sensorPesoAPI=new MockPsensor();
  }
  public BigDecimal obtenerPesoGr(EstadoHeladera estadoHeladera) {

    Reading reading = null;
    try {
      reading = this.sensorPesoAPI.getWeight(this.numeroDeSerie);
    }catch(Exception e) {
      estadoHeladera.generarAlerta(TipoAlerta.FALLA_DE_CONEXION);
      throw new ErrorConeccionSensorDePeso("error");
    }

    if (Objects.equals(reading.unit, "KG")){
      return new BigDecimal( reading.value * 1000 );
    }

    return BigDecimal.valueOf(this.toKG(reading.value) * 1000 );
  }

  private double toKG(double reading) {
    return reading / 2.205;
  }

  public String getNumeroDeSerie() {
    return numeroDeSerie;
  }

  public String numeroDeSeriePeso() {
    return numeroDeSerie;
  }
}
