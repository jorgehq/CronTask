
package Domain.Colaborador.MedioDeContacto;


import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import javax.mail.internet.MimeMessage;

public class Email {
  private String usuario;
  private String clave;

  Properties prop = new Properties();

  public Email() {
    this.usuario = "cuentatest912@gmail.com ";
    this.clave = "bwsv vzbt vsbs yrin";
  }

  public void propiedades() {

    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");


  }

  public Session configSession() {
    return Session.getInstance(prop,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
              }
            });
  }

  public void enviarMensaje(  String email ,String cuerpo) {
    Session session = configSession();
    propiedades();

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(usuario));
      message.setSubject("Notificacion de colaboracion en Heladera");
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
      message.setText(cuerpo);
      Transport.send(message);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}

