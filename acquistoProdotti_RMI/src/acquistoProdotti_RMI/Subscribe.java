/**
 * @author Francesca Stefano matr. 353310 
 */
package acquistoProdotti_RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  This code snippet is defining a Java interface named `Subscribe` that extends the `Remote`
 * interface. This interface declares two methods: `subscribe` and `unsubscribe`, both of which take a
 * parameter of type `SellWriter` and throw a `RemoteException`. This interface is likely used in a
 * remote method invocation (RMI) scenario for subscribing and unsubscribing to some service or events.
 * 
 */

public interface Subscribe extends Remote {

	/**
	 * The function "subscribe" takes a SellWriter object as a parameter and may
	 * throw a RemoteException.
	 * 
	 * @param w The parameter "w" is an object of type SellWriter, which is used to
	 *          subscribe to a service
	 *          or functionality.
	 * @throws RemoteException  used to mange the Exception
	 */
	void subscribe(final SellWriter w) throws RemoteException;

	/**
	 * The function `unsubscribe` in Java takes a `SellWriter` parameter and throws
	 * a `RemoteException`.
	 * 
	 * @param w The parameter "w" is of type SellWriter, which is used to specify
	 *          the writer that needs to
	 *          be unsubscribed.
	 * @throws RemoteException used to mange the Exception
	 */
	void unsubscribe(final SellWriter w) throws RemoteException;

}
