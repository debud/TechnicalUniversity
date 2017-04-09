
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.lang.Object;

public class Cashier extends Thread {
	BlockingQueue<Client> queue = new LinkedBlockingQueue();
	private int emptyTime = 0;
	private int serviceTime = 0;
	private int waitingTime = 0;
	boolean specifiedInterval = false;
	private int specifiedIntervalLeft = 0; 
	private int specifiedIntervalRight = 0;
	private int intervalEmptyTime = 0;
	private int intervalServiceTime = 0;
	private int intervalWaitingTime = 0;
	private String currentEvent = new String("");
	
	Cashier(int specifiedIntervalLeft, int specifiedIntervalRight){
		specifiedInterval = true;
		this.specifiedIntervalLeft = specifiedIntervalLeft;
		this.specifiedIntervalRight = specifiedIntervalRight;
	}

	void addClient(Client client) {
		queue.add(client);
	}

	/*
	 * geteri pentru timpul mediu de servire, asteptare, coada goala a cozii
	 * prezente in intreaga simulare
	 */
	
	void setCurrentEvent(String value){
		currentEvent = value;
	}
	
	String getCurrentEvent(){
		return currentEvent;
	}
	
	int getEmptyQueueTime() {
		return this.emptyTime;
	}

	int getServiceQueueTime() {
		return this.serviceTime;
	}

	int getWaitingQueueTime() {
		return this.waitingTime;
	}
	
	int getIntervalEmptyQueueTime() {
		return this.intervalEmptyTime;
	}

	int getIntervalServiceQueueTime() {
		return this.intervalServiceTime;
	}

	int getIntervalWaitingQueueTime() {
		return this.intervalWaitingTime;
	}

	/*
	 * geteri pentru timpul mediu de servire, asteptare, coada goala a cozii
	 * prezente in intervalul specificat
	 */
	int getEmptyQueueTimeInterval() {
		return this.intervalEmptyTime;
	}

	int getServiceQueueTimeInterval() {
		return this.intervalServiceTime;
	}

	int getWaitingQueueTimeInterval() {
		return this.intervalWaitingTime;
	}

	/* seter al intervalului */
	void setSpecifiedInterval(int left, int right) {
		specifiedIntervalLeft = left;
		specifiedIntervalRight = right;
		specifiedInterval = true;
	}

	/* verifica  daca o valoare temporala apartine intervalului*/
	boolean checkCurrentTimeInterval(int currentTime) {
		if(specifiedInterval)
			if (currentTime >= specifiedIntervalLeft && currentTime <= specifiedIntervalRight)
				return true;
		return false;
	}
	
	String printQueue()
	{
		String content = new String("");
		for (Iterator<Client> it = queue.iterator(); it.hasNext();) {
			Client c = it.next();
			content += c.toString();
		}
		return (content);
	}

	public void run() {
		while (Thread.interrupted() == false) {
			if (queue.size() > 0) {
				Client client = queue.poll();
				/* client repreznta acum clientul curent servit la casa */
				serviceTime += client.getServiceTime();
				/*
				 * timpul de asteptare este de la sosire la coada pana in
				 * prezent->ajuns la casa
				 */
				waitingTime += Simulation.getCurrentTime() - client.getArrivialTime();
				/*
				 * Daca s-a specificat un interval anume, atunci tinem evidenta
				 * timpului de asteptare si de servire intre limite
				 */
				if (specifiedInterval && checkCurrentTimeInterval(Simulation.getCurrentTime())) {
					/*
					 * daca timpul de sosire la coada al clientului se
					 * incadreaza in interval, atunci timpul de asteptare se
					 * calculeaza in intregime, altfel se ia in considerare doar
					 * timpul de asteptare cuprins intre limita inferioara si
					 * timpul curent
					 */
					if (checkCurrentTimeInterval(client.getArrivialTime()))
						intervalWaitingTime += Simulation.getCurrentTime() - client.getArrivialTime();
					else
						intervalWaitingTime += Simulation.getCurrentTime() - specifiedIntervalLeft;
					/*
					 * daca timpul de servire al clientului se extinde in afara
					 * limitei superioare a intervalului specificat , retinem
					 * cat timp se incadreaza in acest interval
					 */
					if (checkCurrentTimeInterval(Simulation.getCurrentTime() + client.getServiceTime()))
						intervalServiceTime += client.getServiceTime();
					else
						intervalServiceTime += specifiedIntervalRight - Simulation.getCurrentTime();
				}
				Simulation.appendCurrentEvent("\n At " + Thread.currentThread().getName() + " serves Client_" + client.getId() + 
						" for " + client.getServiceTime() + " minutes;  Queue size = "  + queue.size() + " Current Time = " + Simulation.getCurrentTime());
				try {
					Thread.sleep(client.getServiceTime() * 1000);
				} catch (InterruptedException e) {
				}

			} else {
				try {
					emptyTime++;
					if (specifiedInterval && checkCurrentTimeInterval(Simulation.getCurrentTime()))
						intervalEmptyTime++;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

			}
		}
	}

}
