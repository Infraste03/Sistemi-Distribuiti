/**
 * @author Francesca Stefano matr 353310
 * 
 * The `Client` class in Java establishes a connection to a message broker, sends pricing information
 * to a server, receives responses, and handles product purchases based on server responses.
 */

package acquistoProdotti_JMS;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.QueueSession;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import jakarta.jms.TopicSession;

import java.util.Random;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * The `Client` class in Java establishes a connection to a message broker,
 * sends messages with pricing
 * information to a server, receives responses, and handles the purchase of
 * products based on server
 * responses.
 */

public class Client implements MessageListener {
  private static final String BROKER_URL_PUB = "tcp://localhost:61616";
  private static final String QUEUE_NAME = "server";
  private static final String QUEUE_NAME1 = "server1";
  private static int MAX = 200;
  private static int MIN = 10;
  private static final String TOPIC_NAME = "topic";
  /** Object for the connection of ActiveMQConnection */
  private ActiveMQConnection connection = null;
  /** variable with the number of purchase */
  private int prodottiClient = 0;
  private static final int PRODOTTIMAX = 10;
  /** destination for the response of the server */
  private Destination tempDestEsito;
  /** Object Random to generate the price */
  Random randomClient = new Random();
  /** Random ID of the Client */
  private int clientId = randomClient.nextInt(1000);
  /** consumer message response */
  MessageConsumer responseConsumer = null;
  /** Object for the session of connection */
  private QueueSession session = null;

  /**
   * The `connect` method establishes a connection to a message broker, creates a
   * session and queue for
   * message communication, sends a message indicating client connection, and sets
   * up a listener for
   * response messages.
   */
  public void connect() {
    try {
      ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(Client.BROKER_URL_PUB);
      connection = (ActiveMQConnection) cf.createConnection();
      connection.start();

      session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
      Queue queue = session.createQueue(QUEUE_NAME); // All clients send messages on the same queue

      MessageProducer producer = session.createProducer(queue);
      TextMessage message = session.createTextMessage();
      message.setText("Client connected");
      message.setIntProperty("ClientId", this.clientId);
      producer.send(message);

      tempDestEsito = session.createTemporaryQueue();
      responseConsumer = session.createConsumer(tempDestEsito);
      responseConsumer.setMessageListener(this);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * The `send()` method in Java creates a message with a random price, sets
   * properties, and sends it to
   * a server queue.
   */

  public void send() throws JMSException {

    try {

      session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

      Destination serverQueue = session.createQueue(QUEUE_NAME1 + "_" + this.clientId);
      MessageProducer producer = session.createProducer(serverQueue);

      TextMessage request = session.createTextMessage();

      Random r = new Random();
      int result = r.nextInt(MAX - MIN) + MIN;

      request.setIntProperty("Prezzo", result);

      request.setJMSReplyTo(tempDestEsito);

      request.setJMSCorrelationID("" + this.clientId);
      System.out.println("Send offer to server");

      producer.send(request);

    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  /**
   * The `receive` function sets up message consumers for both a topic and a queue
   * using ActiveMQ in
   * Java.
   */

  public void receive() {
    try {
      ActiveMQConnectionFactory cfTopic = new ActiveMQConnectionFactory(Client.BROKER_URL_PUB);
      ActiveMQConnection connectionTopic = (ActiveMQConnection) cfTopic.createConnection();
      connectionTopic.start();

      TopicSession sessionTopic = connectionTopic.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
      Topic topic = sessionTopic.createTopic(TOPIC_NAME);
      MessageConsumer consumerTopic = sessionTopic.createConsumer(topic);
      consumerTopic.setMessageListener(this);

      MessageConsumer consumerQueue = session.createConsumer(tempDestEsito);
      consumerQueue.setMessageListener(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** {@inheritDoc} **/
  /**
   * The `onMessage` function processes incoming messages, performs actions based
   * on message content, and
   * terminates the client when a certain condition is met.
   * 
   * @param m The code snippet you provided is a method that processes incoming
   *          JMS messages. The
   *          parameter `m` represents the incoming message received by the
   *          method. The method checks if the
   *          message is an instance of `TextMessage`, then performs different
   *          actions based on the content of the
   *          message.
   */
  public void onMessage(final Message m) {

    if (m instanceof TextMessage) {
      try {
        String text = ((TextMessage) m).getText();
        if ("OK".equals(((TextMessage) m).getText())) {
          System.out.println("Response from server : OK");
          prodottiClient += 1;
          System.out.println("Prodotti acquistati: " + prodottiClient);
        } else if ("NOT OK".equals(((TextMessage) m).getText())) {
          System.out.println("Response from server : NOT OK");
        } else if (text.startsWith("The price is")) {
          System.out.println("Product Price by SERVER: " + ((TextMessage) m).getText());
          this.send();
        }

        if (prodottiClient == PRODOTTIMAX) {
          System.out.println("#######TERMINATED CLIENT#######");
          session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

          Destination serverQueue = session.createQueue(QUEUE_NAME1 + "_" + this.clientId);
          MessageProducer producer = session.createProducer(serverQueue);

          TextMessage request = session.createTextMessage();

          request.setIntProperty("Fine", this.clientId);

          producer.send(request);

          Thread.sleep(200);
          connection.close();
          System.exit(0);
        }
      } catch (JMSException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * The main function creates a Client object, connects to a server, and receives
   * data.
   * 
   * @param args The `args` parameter in the `main` method is an array of strings
   *             that allows you to pass
   *             command-line arguments to your Java program when it is executed.
   *             These arguments can be accessed
   *             within the `main` method and used to customize the behavior of
   *             the program.
   */

  public static void main(final String[] args) {

    Client c = new Client();
    c.connect();
    c.receive();

  }
}