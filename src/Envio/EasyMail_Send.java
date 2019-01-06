package Envio;


/**
 *
 * @author Lord Negrisoli
 */
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EasyMail_Send {

    private String email;
    private String destinatario;
    private String assunto;
    private String mensagem;
    private Session sessao;

    public EasyMail_Send(String email, String destinatario, String assunto, String mensagem, Session sessao) {
        this.email = email;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.sessao = sessao;
    }

    public String send() {

        String resultado = "Falha ao enviar";
        try {

            Message message = new MimeMessage(sessao);
            message.setFrom(new InternetAddress(email)); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(destinatario);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);//Assunto
            message.setText(mensagem);
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            resultado = "Email enviado com sucesso!";
            return resultado;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
