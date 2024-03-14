/**
 * @author Francesca Stefano matr. 353310
 * The class SellWriterImpl extends UnicastRemoteObject and implements SellWriter, storing
 * server-generated price, client offer, and result.
 */
package acquistoProdotti_RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


 /**
 * The `SellWriterImpl` class in Java implements the `SellWriter`
 *  interface and provides methods to set
 *  and retrieve prices, offers, and results for selling items.
 */

public class SellWriterImpl extends UnicastRemoteObject implements SellWriter {
	private static final long serialVersionUID = 1L;
	/** server_price is used to storage the server price */
	private int server_price = 0; 
	/** client_offer is used to storage the offer of the client  */
	private int client_offer = 0;
	/** result is used for the confirm  */
	private int result = 0;

	/**The `public SellWriterImpl() throws RemoteException { }` is a constructor for
	* the `SellWriterImpl`
	* class in Java.
	* @throws RemoteException
	* */
	public SellWriterImpl() throws RemoteException {
	}

	/**
	 * The function `putSell` sets the `server_price` variable to the value passed
	 * as a parameter.
	 * 
	 * @param s The parameter `s` in the `putSell` method represents the price at
	 *          which the item is being
	 *          sold.
	 */
	public void putSell(final int s) throws RemoteException {
		this.server_price = s;
	}

	/**
	 * This Java function returns the value of the "server_price" variable and may
	 * throw a
	 * RemoteException.
	 * 
	 * @return The method `getSell()` is returning the value of the `server_price`
	 *         variable.
	 */
	public int getSell() throws RemoteException {
		return this.server_price;
	}

	/**
	 * The `putOffer` function in Java sets the client's offer value.
	 * 
	 * @param o The parameter "o" in the method "putOffer" represents an integer
	 *          value that is being
	 *          passed to the method.
	 */
	public void putOffer(final int o) throws RemoteException {
		this.client_offer = o;
	}

	/**
	 * This Java function returns the client's offer and may throw a
	 * RemoteException.
	 * 
	 * @return The method `getOffer()` is returning the value of the `client_offer`
	 *         variable.
	 */
	public int getOffer() throws RemoteException {
		return this.client_offer;
	}

	/**
	 * This Java function sets the result value to the provided integer.
	 * 
	 * @param r The parameter "r" is an integer value that represents the result to
	 *          be stored in the
	 *          "result" variable of the class.
	 */
	public void putResult(final int r) throws RemoteException {
		this.result = r;
	}

	/**
	 * This Java function named `getResult` returns the value of the `result`
	 * variable and may throw a
	 * `RemoteException`.
	 * 
	 * @return The `result` variable is being returned.
	 */
	public int getResult() throws RemoteException {
		return this.result;
	}

}
