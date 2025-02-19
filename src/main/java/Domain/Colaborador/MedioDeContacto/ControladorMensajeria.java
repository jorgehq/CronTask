package Domain.Colaborador.MedioDeContacto;

public class ControladorMensajeria implements InstantMessageSender{
    @Override
    public void sendMessage(InstantMessageApp provider, String contacto, String message) {
        switch (provider){
            case MAIL -> new Email().enviarMensaje(contacto,message);
            case WHATSAPP ->  new WhatsApp().enviarMensaje(contacto,message);
            case TELEGRAM -> new Telegram().enviarMensaje(contacto,message);
        }
    }
}
