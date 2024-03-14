/**
 * @author Francesca Stefano matr. 353310
 * This Java class represents a client that subscribes to a server for purchasing products through a
 * remote method invocation (RMI) system.
 */
package acquistoProdotti_RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
 


/**
 * The `clientCallbacks` class in Java simulates a client making multiple purchase attempts by
 * subscribing to a service, sending offers, and checking the results.
 * The class clientCallbacks has three constant variables: MAX, MIN, and
 * NPROD. MAX and MIN are used to generate a random offer price,
 * and NPROD is the total number of products the client attempts to
 *  purchase.
 *  
 */
public class clientCallbacks {
	private static final int MAX = 200;
	private static final int MIN = 10;
	private static final int NPROD = 10;

	/**
	 * The main function simulates a client making multiple purchase attempts by
	 * subscribing to a service,
	 * sending offers, and checking the results.
	 * 
	 * @param args Java program that simulates a client making
	 *  multiple purchase attempts. The main method takes in an array of
	 *  String arguments as input (args)
	 *  The main method starts by initializing the number of successful
	 *  purchases (num_purchase) to 0.
	 *  It then creates a Random object for generating random offer
	 *  prices and a Registry object to interact with the RMI registry.
	 *  
	 *  @throws Exception
	 * 
	 */

	public static void main(final String[] args) throws Exception {

		int num_purchase = 0;
		Random random = new Random();
		Registry registry = LocateRegistry.getRegistry();
		SellWriter w = new SellWriterImpl();
		Subscribe service = (Subscribe) registry.lookup("subscribe");
		service.subscribe(w); 

		/**
		 * This part of the code is a loop that simulates a client making multiple
		 * purchase attempts. Here's a
		 * breakdown of what it does:
		 */
		while (num_purchase < NPROD) {

			int t = random.nextInt(MAX - MIN) + MIN;

			int value = w.getSell();

			System.out.println("Server price :  " + value);

			w.putOffer(t);
			if (w.getResult() == 1) {

				num_purchase += 1;
				System.out.println("The offer is ok, and the number of product is :  " + num_purchase);

			} else {
				System.out.println("The offer is  NOT  ok ");

			}

			Thread.sleep(4000);

		}

		service.unsubscribe(w);
		System.out.println("Disconnection client  ");
		System.exit(0);

	}

}
