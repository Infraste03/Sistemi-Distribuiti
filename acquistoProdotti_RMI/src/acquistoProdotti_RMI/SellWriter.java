/**
 * @author Francesca Stefano matr. 353310
 * This Java code snippet is defining an interface named `SellWriter` that extends the `Remote`
 * interface. This interface is intended for use in remote method invocation (RMI) scenarios. It
 * 
  * declares several methods that can be invoked remotely: `putSell`, `getSell`, `putOffer`, `getOffer`,
 */
package acquistoProdotti_RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

 
/**
 * 
 * This Java code snippet defines an interface named `SellWriter` that extends
 *  the `Remote` interface, indicating that it is intended for use in remote method invocation (RMI)
 * scenarios.
 *
 */

public interface SellWriter extends Remote {
	/**
	 * The function `putSell` in Java is used to place a sell order with a specified
	 * quantity.
	 * 
	 * @param s The parameter "s" in the method "putSell" is of type integer and
	 *          represents the quantity
	 *          of items to be sold.
	 *@throws RemoteException
	 */
	void putSell(final int s) throws RemoteException;

	/**
	 * The function `getSell()` in Java returns an integer value representing the
	 * sell price and may throw
	 * a RemoteException.
	 * 
	 * @return An integer value representing the sell value is being returned.
	 * @throws RemoteException
	 */
	int getSell() throws RemoteException;

	/**
	 * The function `putOffer` in Java is used to submit an offer with a specified
	 * integer value and may
	 * throw a `RemoteException`.
	 * 
	 * @param o The parameter "o" in the method "putOffer" is an integer value
	 *          representing an offer.
	 * @throws RemoteException
	 */
	void putOffer(final int o) throws RemoteException;

	/**
	 * The function `getOffer()` in Java returns an integer value and may throw a
	 * `RemoteException`.
	 * 
	 * @return An integer value representing an offer is being returned.
	 * @throws RemoteException
	 */
	int getOffer() throws RemoteException;

	/**
	 * The function `putResult` in Java is used to store a final integer result and
	 * may throw a
	 * RemoteException.
	 * 
	 * @param r The parameter "r" is an integer value that represents the result to
	 *          be stored or processed
	 *          in the method "putResult".
	 * @throws RemoteException
	 */
	void putResult(final int r) throws RemoteException;

	/**
	 * The function `getResult` in Java returns an integer value and may throw a
	 * `RemoteException`.
	 * 
	 * @return An integer value is being returned.
	 * @throws RemoteException
	 */
	int getResult() throws RemoteException;

}
