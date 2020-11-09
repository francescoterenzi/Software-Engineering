/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import database.DatabaseManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.regex.Matcher;
import javax.jms.MessageListener;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.regex.Pattern;



/**
 * Class to listen for messages coming from the broker
 * @author Giulio Serra 1904089
*/
public class FlightListner extends Observable implements MessageListener{
    
    private TopicConnection topicConnection; // topic based on the connection
    private TopicSession topicSession = null; // session 
    private Destination destination = null;
    private MessageProducer producer = null;
    
     private final static Pattern statusPattern = Pattern.compile(".* : (.*)");
    
    public FlightListner(){
        
        Context jndiContext = null;
        ConnectionFactory topicConnectionFactory = null;

        String destinationName = "dynamicTopics/Flights"; // destination name
        
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
                    = topicSession.createSubscriber((Topic) destination);
            

            topicSubscriber.setMessageListener(this);
        } catch (JMSException err) {
            err.printStackTrace();
        } catch (NamingException err) {
            err.printStackTrace();
        }
    }
    
    /**
     * Stop the listner
     * @author Giulio Serra 1904089
     */
    public void stop() {
        try {
            topicConnection.stop();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

    /**
     * Start the listner
     * @author Giulio Serra 1904089
     */
    public void start() {
        try {
            topicConnection.start();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

    /**
     * Event handler to react on messages arrival
     * @author Giulio Serra 1904089
     */
    public void onMessage(Message mex) {
        try {
         
           String flight = mex.getStringProperty("flight");
           String text = ((TextMessage)mex).getText();
          

            Matcher matcher = statusPattern.matcher(text);
            if (matcher.find()) {
                String status = matcher.group(1);
                
                System.out.println("Arrived flight: " + flight + " has landed: " + status);
                DatabaseManager.addFlight(flight, status);
                
            }
           
     
           
           /*System.out.println("Database content:");
           DatabaseManager.printContent();*/
         

        } catch (JMSException err) {
            err.printStackTrace();
        }
        
        
    }
} 