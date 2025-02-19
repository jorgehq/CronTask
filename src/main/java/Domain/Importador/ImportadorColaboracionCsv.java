package Domain.Importador;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.FormaColaboracion;
import Domain.Colaborador.MedioDeContacto.InstantMessageApp;
import Domain.Colaborador.MedioDeContacto.Mediodecontacto;
import Domain.Colaborador.PersonaHumana;
import Domain.Colaborador.TipoDeColaboracion.DistribuirVianda;
import Domain.Colaborador.TipoDeColaboracion.DonarDinero;
import Domain.Colaborador.TipoDeColaboracion.DonarVianda;
import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Colaborador.Transferencia.FrecuenciaDeDonacion;
import Domain.Converters.FormaColaboracionConverter;
import Domain.Heladera.Vianda;
import Domain.Repositorios.RepoColaboraciones;
import Domain.Repositorios.RepoColaboradores;
import Domain.Converters.TipoDocumentoConverter;
import Domain.Repositorios.RepoUsuario;
import Domain.Repositorios.RepoViandas;
import Domain.Tarjeta.TarjetaColaborador;
import Domain.Ubicacion.Direccion;
import Domain.Ubicacion.Localidad;
import Domain.Ubicacion.Provincia;
import Domain.Usuarios.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ImportadorColaboracionCsv {

  private List<TipoDeColaboracion> colaboraciones = new ArrayList<>();
  private TipoDocumentoConverter tipoDocumentoConverter = new TipoDocumentoConverter();
  private FormaColaboracionConverter formaColaboracionConverter = new FormaColaboracionConverter();

  public void importar(InputStream inputStream) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String linea="DNI;12345678;Juan;Pérez;edmundo@example.com;25/11/2024;DINERO;2000;25/11/1998;BUENOS_AIRES;LA_MATANZA;Abasto;1234;1234";


        String[] datoslinea = linea.split(";");
        String tipoDoc = datoslinea[0].trim();
        String documento = datoslinea[1].trim();
        String nombre = datoslinea[2].trim();
        String apellido = datoslinea[3].trim();
        String mail = datoslinea[4].trim();
        String fechaDeColaboracion = datoslinea[5].trim();
        String formaDeColaboracion = datoslinea[6].trim();
        String cantidad = datoslinea[7].trim();
        String fechaNacimiento = datoslinea[8].trim();
        String provincia = datoslinea[9].trim();
        String localidad = datoslinea[10].trim();
        String domicilio = datoslinea[11].trim();


        FormaColaboracion formaColaboracion = formaColaboracionConverter.stringToFormaColaboracion(formaDeColaboracion);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaColaboracion = LocalDate.parse(fechaDeColaboracion, formatter);
        LocalDate nacimiento=LocalDate.parse(fechaNacimiento, formatter);
        int cantidadI = Integer.parseInt(cantidad);



        if (RepoUsuario.getInstance().buscarPorNombre(mail).size()==0) {
          Usuario u = new Usuario(nombre, apellido, mail, "1234");

            List<Mediodecontacto> medios = new ArrayList<>();
            medios.add(new Mediodecontacto(mail, InstantMessageApp.MAIL));

            Colaborador c = new PersonaHumana(nombre, apellido, nacimiento, new Direccion(Provincia.valueOf(provincia), Localidad.valueOf(localidad), domicilio)
                    , medios, TipoDocumento.valueOf(tipoDoc), Long.parseLong(documento));

            Random random = new Random();
            int numeroAleatorio = random.nextInt(1000000) + 1;
            TarjetaColaborador t = new TarjetaColaborador(String.valueOf(numeroAleatorio), LocalDate.now());

              t.setColaborador(c);
              c.setTarjeta(t);

              RepoColaboradores.getInstance().guardar(c);

              u.asignarColaborador(c);


              RepoUsuario.getInstance().guardar(u);

          }
        Usuario usuario = RepoUsuario.getInstance().buscarPorNombre(mail).get(0);
        List<TipoDeColaboracion> colaboracion = fabricaColaboraciones(fechaColaboracion, formaColaboracion, cantidadI, usuario.getAsignado());

        for (TipoDeColaboracion t : colaboracion) {
          RepoColaboraciones.getInstance().guardar(t);
          usuario.getAsignado().getColaboraciones().add(t);
          RepoColaboradores.getInstance().merge(usuario.getAsignado());
        }



      } catch(IOException e){
        throw new RuntimeException("Error al leer el archivo CSV: " + e.getMessage(), e);
      }

  }

  public List<TipoDeColaboracion> fabricaColaboraciones(LocalDate fechaColaboracion, FormaColaboracion formaColaboracion, int cantidadI,Colaborador co){
    List<TipoDeColaboracion> lista=new ArrayList<>();
    switch(formaColaboracion){
      case DINERO:
        lista.add(new DonarDinero(cantidadI,fechaColaboracion, FrecuenciaDeDonacion.UNICO));

        break;
      case DONACION_VIANDAS:
        for(int i=0;i<cantidadI;i++){
          Vianda v=new Vianda(fechaColaboracion.plusYears(1), fechaColaboracion, 1000, 4, true,co, null);
          lista.add(new DonarVianda(fechaColaboracion,
                  v));
        }

        break;
      case REDISTRIBUCION_VIANDAS:
        lista.add(new DistribuirVianda(null,null,cantidadI,"",fechaColaboracion));
        break;

      default:
        break;
    }
    return lista;
  }

  public List<TipoDeColaboracion> getColaboraciones() {
    return colaboraciones;
  }
}