package Domain.Colaborador.MedioDeContacto;

public interface InstantMessageSender {
    void sendMessage(
            InstantMessageApp provider,
            String contacto,
            String message);
}
