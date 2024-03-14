/**
 * @author Francesca Stefano matr. 353310
 * This Java class represents a server that manages client subscriptions and handles purchase offers
 * for products.
 */

package acquistoProdotti_RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

 
/**
 * The `serverCallbacks` class in Java implements a server that handles client
 * subscriptions and
 * manages purchase offers.
 */
public class serverCallbacks {
	private static final int PORT = 1099;
	private static final int MAX = 200;
	private static final int MIN = 10;
	private static final int NUMCLIENT = 3;// NUMBER OF CLIENTS

/**
 * This Java function creates a server that handles client subscriptions and
 * sells based on pricing
 * comparisons.
 * 
 * @param args The `args` parameter in the `main` method is an array of strings
 *             that represent the
 *             command-line arguments passed to the Java program when it is
 *             executed. These arguments can be used
 *             to provide input or configuration parameters to the program.
 * @throws Exception to manage the Exception
 */
	public static void main(final String[] args) throws Exception {
		Random random = new Random();
		Registry registry = LocateRegistry.createRegistry(PORT);
		Set<SellWriter> writers = new CopyOnWriteArraySet<>();
		Subscribe service = new SubscribeImpl(writers);
		registry.rebind("subscribe", service);
		System.out.println("Server on " + PORT);
		boolean started = false;

		// This part of the code represents the main logic of the server program.
		while (true) {
			System.out.println("Num client: " + writers.size());

			if (writers.size() >= NUMCLIENT || started) {

				started = true;
				int t = random.nextInt(MAX - MIN) + MIN;

				try {
					if (writers.size() == 0) {
						started = false;
						System.out.println("Logout server! ");
						System.exit(0);
					}

					for (SellWriter w : writers) {

						w.putSell(t);
						if (w.getOffer() >= w.getSell()) {
							System.out.println("The price of the server is : " + w.getSell() +
									"the offer of the client is : " + w.getOffer()
									+ " so, the purchase is confirmed! ");
							w.putResult(1);
						} else {
							System.out.println("The price of the server is : " + w.getSell() +
									"the offer of the client is : " + w.getOffer()
									+ " so, the purchase is NOT confirmed! ");
							w.putResult(-1);
						}
					}

					Thread.sleep(4000);
				}

				catch (Exception e) {
					continue;
				}

			}

		}

	}

}
