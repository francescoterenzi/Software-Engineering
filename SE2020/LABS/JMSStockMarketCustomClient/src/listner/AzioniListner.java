/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listner;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Quotazione;

/**
 *
 * @author studente
 */
public class AzioniListner extends Observable implements MessageListener {

    private TopicConnection topicConnection; // topic based on the connection
    private TopicSession topicSession = null; // session 
    private Destination destination = null;
    private MessageProducer producer = null;

    public AzioniListner() {
        
        Context jndiContext = null;
        ConnectionFactory topicConnectionFactory = null;

        String destinationName = "dynamicTopics/Quotazioni"; // destination name
        
       
        try {

            Properties props = new Properties();

            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616"); // URL OF THE PUBLISHER
            jndiContext = new InitialContext(props);

            topicConnectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Destination) jndiContext.lookup(destinationName);
            topicConnection = (TopicConnection) topicConnectionFactory.createConnection();
            
            
            topicSession = (TopicSession) topicConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
          
     
            TopicSubscriber topicSubscriber
                    = topicSession.createSubscriber((Topic) destination,"Nome = 'Barilla'",true); // filtering
            
            /*TopicSubscriber topicSubscriber
                    = topicSession.createSubscriber((Topic) destination); // no filtering*/
            

            topicSubscriber.setMessageListener(this);
        } catch (JMSException err) {
            err.printStackTrace();
        } catch (NamingException err) {
            err.printStackTrace();
        }
    }

    /**
     * Chiude la ricezione dei messaggi sulla topic quotazioni
     */
    public void stop() {
        try {
            topicConnection.stop();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

    /**
     * Apre la ricezione dei messaggi sulla topic quotazioni
     */
    public void start() {
        try {
            topicConnection.start();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

    public void onMessage(Message mex) {
        try {
            String nome = mex.getStringProperty("Nome");
            float valore = mex.getFloatProperty("Valore");

            Quotazione quotazione = new Quotazione(nome, valore);
            System.out.println("Nuova quoatazione in arrivo: " + quotazione.toString());

        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

}
