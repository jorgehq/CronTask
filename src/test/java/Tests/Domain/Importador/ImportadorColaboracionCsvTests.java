package Tests.Domain.Importador;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.PersonaHumana;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Importador.ImportadorColaboracionCsv;
import Domain.Repositorios.RepoColaboraciones;
import Domain.Repositorios.RepoColaboradores;
import Domain.Usuarios.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImportadorColaboracionCsvTests {
/*
  Colaborador pablin = new PersonaHumana("pablin","moroso", null,null,null, TipoDocumento.DNI,1107201100);
  Colaborador colo = new PersonaHumana("colo","dalmasso",null,
      null,null,TipoDocumento.DNI,1234567890);
  Usuario usuarioPablin = new Usuario("pablin","moroso","riberCarp","EnzoGod10!");
  String path = "src/test/java/Tests/Domain/Importador/colaboradoresCsv.csv";
  ImportadorColaboracionCsv importador;

  @BeforeEach
  void inicializar(){
    RepoColaboradores.getInstance().resetInstance();
    RepoColaboraciones.getInstance().resetInstance();
    importador = new ImportadorColaboracionCsv();
    RepoColaboradores.getInstance().addColaborador(pablin);
    RepoColaboradores.getInstance().addColaborador(colo);
    pablin.addUsuario(usuarioPablin);
  }


  @Test
  void seCargan2ColaboracionesAlRepo() {
    importador.importar(path);
    Assertions.assertTrue(RepoColaboraciones.getInstance().getColaboraciones().size() == 2);
  }

  @Test
  void pablinTieneUsuarioPorLoQNoSeLeCreaUno(){
    importador.importar(path);
    Assertions.assertTrue(usuarioPablin.getNombreUsuario().equals("riberCarp"));
  }

  @Test
  void coloNoTieneUsuarioPorLoQSeLeCreaUno() {
    importador.importar(path);
    Assertions.assertTrue(colo.getNombreUsuario().equals("ginger"));
  }

 */
}
