/**
 * @author Francesca Stefano matr. 353310
 * The SubscribeImpl class extends UnicastRemoteObject and contains a Set of SellWriter objects.
 */
package acquistoProdotti_RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

 

/**
 * The `SubscribeImpl` class in Java implements the `Subscribe` interface and
 * manages a set of
 * `SellWriter` objects for subscribing and unsubscribing.
 */
public class SubscribeImpl extends UnicastRemoteObject implements Subscribe {
	private static final long serialVersionUID = 1L;
	/** list of writers */
	private Set<SellWriter> writers;

	/**
	 * This code snippet is defining a constructor for the `SubscribeImpl` class in
	 * Java. The constructor
	 * takes a `Set` of `SellWriter` objects as a parameter and assigns it to the
	 * `writers` field of the
	 * class.
	 * @throws RemoteException  The constructor is also throwing a `RemoteException`.
	 * @param sw used to initialise the writers 
	 */
	public SubscribeImpl(final Set<SellWriter> sw) throws RemoteException {
		this.writers = sw;
	}

	/**
	 * The `subscribe` function adds a `SellWriter` object to a list of writers.
	 * 
	 * @param w The parameter "w" in the method "subscribe" is of type SellWriter.
	 * @throws RemoteException
	 */
	public void subscribe(final SellWriter w) throws RemoteException {
		this.writers.add(w);
	}

	/**
	 * The `unsubscribe` function removes a `SellWriter` object from a list of
	 * writers.
	 * 
	 * @param w The parameter `w` in the `unsubscribe` method is of type
	 *          `SellWriter`.
	 * @throws RemoteException
	 */
	public void unsubscribe(final SellWriter w) throws RemoteException {
		this.writers.remove(w);
	}

}
