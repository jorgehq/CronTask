package Tests.Domain.MedioContacto;

import Domain.Colaborador.MedioDeContacto.*;
import org.junit.jupiter.api.Test;

public class TestAPImail {
/*
       @Test
    public void testEnviarMensaje(){
           Mediodecontacto m=new Mediodecontacto("jor2018qw@gmail.com",InstantMessageApp.MAIL);
        m.enviarMensaje("Mensaje de prueba");
    }
    @Test
    public void testWhatsaap(){
        Mediodecontacto m=new Mediodecontacto("1154892103",InstantMessageApp.WHATSAPP);
        m.enviarMensaje("Mensaje de prueba");
    }
    @Test
    public void testTelegram(){
        Mediodecontacto m=new Mediodecontacto("1154892103",InstantMessageApp.TELEGRAM);
        m.enviarMensaje("Mensaje de prueba");
    }
/*
    @Test
    public void testNotificarInteresados(){
        List<MedioDeContacto> medios=new ArrayList<>();
        medios.add(new EmailAdapter(new Email("Pon tu mail aqui")));
        Colaborador colaborador1 = new PersonaHumana("Gold", "D", LocalDate.now(), null, medios, null, 123456789);
        Heladera heladera1 = new Heladera("Heladera1", 100, "40", "40", null, LocalDate.now(),null,null,null,null,null);
        heladera1.suscribirse(colaborador1);
        heladera1.notificarInteresados(new NotificacionFaltanViandas(10,heladera1));
    }
    @Test
    public void verificarSugerenciaAceptada(){
        Colaborador colaborador1 = new PersonaHumana("Gold", "D", LocalDate.now(), null, new ArrayList<>(), null, 123456789);
        Colaborador colaborador2 = new PersonaHumana("Jonh", "Wick", LocalDate.now(), null, new ArrayList<>(), null, 987654321);
        Heladera heladera1 = new Heladera("Heladera1", 100, "40", "40", null, LocalDate.now(),null,
            null,null,null,new RepoTecnicos());
        Heladera heladera2 = new Heladera("Heladera2", 100, "40", "40", null, LocalDate.now(),null,
            null,null,null,new RepoTecnicos());
    heladera1.suscribirse(colaborador1);
    heladera1.suscribirse(colaborador2);
    heladera1.agregarVianda(new Vianda(null,null,null,null,null,null,null));
    heladera1.agregarVianda(new Vianda(null,null,null,null,null,null,null));

        heladera1.notificarInteresados(new NotificacionIncidente(heladera1,new SugerenciaAlAzar()));
        System.out.println(heladera1.getViandasEnHeladera().size()+" Tamanio");
        System.out.println(heladera2.getViandasEnHeladera().size()+" Tamanio");
    colaborador1.aceptarSugerenciaNotificacion(0,1);
    colaborador2.aceptarSugerenciaNotificacion(0,1);
    }
*/
}
