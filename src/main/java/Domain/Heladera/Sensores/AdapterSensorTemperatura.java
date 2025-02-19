package Domain.Heladera.Sensores;

import APIs.MockPsensor;
import APIs.MockTsensor;
import APIs.TSensor.Action;
import APIs.TSensor.TSensor;
import Domain.Heladera.EstadoHeladera;
import Domain.Heladera.Heladera;
import jakarta.persistence.*;

import java.util.concurrent.ThreadLocalRandom;

@Embeddable
public class AdapterSensorTemperatura {
@Transient
  TSensor sensorTemperaturaAPI;
@Column
private String numeroSerie;


  public AdapterSensorTemperatura(TSensor sensorTemperaturaAPI, String numeroSerie) {

    this.numeroSerie = numeroSerie;
  }

  public AdapterSensorTemperatura() {
     this.numeroSerie = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
  }

  public void conectar() {
    this.sensorTemperaturaAPI.connect(this.numeroSerie);
  }

  public void cargarAccionInformarHeladera(EstadoHeladera estadoHeladera) {
    this.sensorTemperaturaAPI.onTemperatureChange(new AccionInformarHeladera(estadoHeladera) );
  }
  public void iniciarSensor(){

    sensorTemperaturaAPI=new MockTsensor();
  }

  public String getNumeroSerieTemperatura() {
    return numeroSerie;
  }
}
