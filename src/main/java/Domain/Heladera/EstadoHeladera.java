package Domain.Heladera;

import Domain.Heladera.Sensores.AdapterSensorPeso;
import Domain.Heladera.Sensores.AdapterSensorTemperatura;
import Domain.Incidentes.Alerta;
import Domain.Incidentes.TipoAlerta;
import Domain.Repositorios.RepoTecnicos;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
@Entity
public class EstadoHeladera {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Embedded
  AdapterSensorPeso sensorPeso;
  @Embedded
  AdapterSensorTemperatura sensorTemperatura;
  @Column
  BigDecimal temperaturaMinima;
  @Column
  BigDecimal temperaturaMaxima;
  @Column
  Boolean heladeraAveriada = false;
  @Enumerated(EnumType.STRING)
  NivelDeLlenado nivelDeLlenado;
  @OneToOne(mappedBy = "estadoHeladera")
  Heladera heladera;
  @Column
  LocalDateTime ultimaLectura;
  @Column
  int cantidadLecturasTempInferioresConsecutivas = 0;

  public EstadoHeladera(){}
  public EstadoHeladera(BigDecimal temperaturaMaxima, BigDecimal temperaturaMinima, Heladera heladera) {
    this.temperaturaMaxima = temperaturaMaxima;
    this.temperaturaMinima = temperaturaMinima;
    this.sensorTemperatura = new AdapterSensorTemperatura();
    this.sensorPeso = new AdapterSensorPeso();
    this.nivelDeLlenado = NivelDeLlenado.BAJO;
    this.heladera = heladera;
  }

  // SETTERS
  public void setHeladeraAveriada(Boolean heladeraAveriada) {
    this.heladeraAveriada = heladeraAveriada;
  }

  // GETTERS
  public Boolean getHeladeraAveriada() {
    return heladeraAveriada;
  }

  public void generarAlerta(TipoAlerta tipoAlerta) {
    // TODO: Hay q persistir la alerta
    Alerta alerta = new Alerta(LocalDateTime.now(),this.heladera, tipoAlerta);
    this.marcarComoInactiva();
  }
  public void updateLecturaSensorTemperatura(BigDecimal temperaturaNueva) {
    this.ultimaLectura = LocalDateTime.now();
    if (temperaturaNueva.compareTo(this.temperaturaMinima) < 0 ) {
      this.cantidadLecturasTempInferioresConsecutivas++;
    }
    if (this.cantidadLecturasTempInferioresConsecutivas == 3) {
      this.generarAlerta(TipoAlerta.TEMPERATURA);
    }
  }

  public void actualizarNivelDeLlenado(int capacidadDeViandas) {
    try {
      if (this.esMenorOIgualSegunPorcentaje(capacidadDeViandas, 0.3)) {
        this.nivelDeLlenado = NivelDeLlenado.BAJO;
      } else if (this.esMenorOIgualSegunPorcentaje(capacidadDeViandas, 0.7)) {
        this.nivelDeLlenado = NivelDeLlenado.MEDIO;
      } else {
        this.nivelDeLlenado = NivelDeLlenado.ALTO;
      }
    }catch (Exception e){
      // REVISAR SI HAY Q GENERAR UNA ALERTA
      this.generarAlerta(TipoAlerta.FALLA_DE_CONEXION);
    }
  }

  private boolean esMenorOIgualSegunPorcentaje(int capacidadDeViandas, double porcentaje) {
    BigDecimal capacidadTotal = new BigDecimal(capacidadDeViandas * 400);
    BigDecimal pesoHeladera = this.sensorPeso.obtenerPesoGr(this);
    return  pesoHeladera.divide(capacidadTotal).compareTo( new BigDecimal (porcentaje) ) <= 0;
  }

  public NivelDeLlenado getNivelDeLlenado() {
    return this.nivelDeLlenado;
  }

  public void consultarUltimaLectura(LocalDateTime now) {
    Duration duracion = Duration.between(this.ultimaLectura, now);

    if (Math.abs(duracion.toMinutes()) > 15) {
      this.generarAlerta(TipoAlerta.FALLA_DE_CONEXION);
    }
  }

  public void marcarComoInactiva() {
    this.setHeladeraAveriada(true);
    //RepoTecnicos.getInstance().llamarTecnico(heladera);
  }

  public void iniciarSensores(){
    sensorPeso.iniciarSensor();
    sensorTemperatura.iniciarSensor();
  }

  public AdapterSensorPeso getSensorPeso() {
    return sensorPeso;
  }

  public AdapterSensorTemperatura getSensorTemperatura() {
    return sensorTemperatura;
  }
}

