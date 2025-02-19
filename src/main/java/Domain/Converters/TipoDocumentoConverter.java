package Domain.Converters;

import Domain.Colaborador.TipoDocumento.TipoDocumento;

public class TipoDocumentoConverter {

  public TipoDocumento stringToTipoDocumento(String tipoDocumento) {
    switch (tipoDocumento) {
      case "LC":
        return TipoDocumento.LC;
      case "LE":
        return TipoDocumento.LE;
    }
    return TipoDocumento.DNI;
  }
}
