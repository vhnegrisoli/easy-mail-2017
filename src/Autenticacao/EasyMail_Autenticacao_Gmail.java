package Autenticacao;


import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lord Negrisoli
 */
public class EasyMail_Autenticacao_Gmail {

    private String usuario;
    private String senha;
    private Session sessao;
    
    public EasyMail_Autenticacao_Gmail(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Session estaAutenticado() {
        Properties propriedades = new Properties();
        propriedades.put("mail.smtp.host", "smtp.gmail.com");
        propriedades.put("mail.smtp.socketFactory.port", "465");
        propriedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propriedades.put("mail.smtp.auth", "true");
        propriedades.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(propriedades,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        session.setDebug(true);

        String resultado = "";

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse("teste@teste.com");

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("");//Assunto
            message.setText("");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            resultado = "Email enviado com sucesso!";
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválido.\nPor favor, tente novamente. ");
            throw new RuntimeException(e);

        }
        return session;
    }

}
