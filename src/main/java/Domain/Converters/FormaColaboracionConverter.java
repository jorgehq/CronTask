package Domain.Converters;

import Domain.Colaborador.FormaColaboracion;

public class FormaColaboracionConverter {

  public FormaColaboracion stringToFormaColaboracion(String formaColaboracion) {
    switch (formaColaboracion) {
      case "DINERO":
        return FormaColaboracion.DINERO;
      case "DONACION_VIANDAS":
        return FormaColaboracion.DONACION_VIANDAS;
      case "REDISTRIBUCION_VIANDAS":
        return FormaColaboracion.REDISTRIBUCION_VIANDAS;
    }
    return FormaColaboracion.ENTREGA_TARJETAS;
  }
}
