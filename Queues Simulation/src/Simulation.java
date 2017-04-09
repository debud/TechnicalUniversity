import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Simulation extends Thread {

	Cashier[] cashier;
	/* input data */
	private int noCashiers; /* number of */
	private int simulationTime;
	private int maxArrivialTime;
	private int minArrivialTime;
	private int maxServiceTime;
	private int minServiceTime;
	/* output data */
	private int avgWaitingTime;
	private int avgServiceTime;
	private int avgEmptyTime;
	private int peakHour;
	private int intervalAvgWaitingTime;
	private int intervalAvgServiceTime;
	private int intervalAvgEmptyTime;
	public Cashier[] thread;
	private Random random = new Random();
	private static int currentTime = 0;
	private static String trackOfEvents = new String("");
	public boolean simulationEnded = false;
	private int maximumSizeSum = -1;
	private int totalQSize = 0;
	public static void appendCurrentEvent(String currentEvent) {
		trackOfEvents += currentEvent;
	}

	String getTrackOfEvents() {
		return trackOfEvents;
	}


	public void setAvgWaitingTime() {
		int sum = 0;
		for (int iterator = 0; iterator < noCashiers; iterator++) {
			sum += thread[iterator].getWaitingQueueTime();
		}
		if (totalQSize != 0)
		avgWaitingTime = sum / totalQSize;
	}

	public void setAvgEmptyTime() {
		int sum = 0;
		for (int iterator = 0; iterator < noCashiers; iterator++) {
			sum += thread[iterator].getEmptyQueueTime();
		}
		avgEmptyTime = sum / noCashiers;
	}

	public void setAvgServiceTime() {
		int sum = 0;
		for (int iterator = 0; iterator < noCashiers; iterator++) {
			sum += thread[iterator].getServiceQueueTime();
		}
		if (totalQSize != 0)
		avgServiceTime = sum / totalQSize;
	}

	public void setIntervalAvgWaitingTime() {
		int sum = 0;
		for (int iterator = 0; iterator < noCashiers; iterator++) {
			sum += thread[iterator].getIntervalWaitingQueueTime();
		}
		if (totalQSize != 0)
		intervalAvgWaitingTime = sum / totalQSize;
	}

	public void setIntervalAvgEmptyTime() {
		int sum = 0;
		for (int iterator = 0; iterator < noCashiers; iterator++) {
			sum += thread[iterator].getIntervalEmptyQueueTime();
		}
		intervalAvgEmptyTime = sum / noCashiers;
	}

	public void setIntervalAvgServiceTime() {
		int sum = 0;
		for (int iterator = 0; iterator < noCashiers; iterator++) {
			sum += thread[iterator].getIntervalServiceQueueTime();
		}
		if (totalQSize != 0)
		intervalAvgServiceTime = sum / totalQSize;
	}

	public String getAverages() {
		return ("\n Average Waiting Time = " + avgWaitingTime + "\n Average Service Time = " + avgServiceTime
				+ "\n Average Empty Time = " + avgEmptyTime + "\n Peak Hour = " + peakHour 
				+ "\n Specified Interval Averages : " + "\n Average Waiting Time = " + intervalAvgWaitingTime
				+ "\n Average Service Time = " + intervalAvgServiceTime + "\n Average Empty Time = "
				+ intervalAvgEmptyTime);
	}

	public Simulation() {

	}

	public Simulation(int noCashiers, int simulationTime, int maxArrivialTime, int minArrivialTime, int maxServiceTime,
			int minServiceTime, int leftEndpoint, int rightEndpoint) {
		this.noCashiers = noCashiers;
		this.simulationTime = simulationTime;
		this.maxArrivialTime = maxArrivialTime;
		this.minArrivialTime = minArrivialTime;
		this.maxServiceTime = maxServiceTime;
		this.minServiceTime = minServiceTime;
		thread = new Cashier[noCashiers];
		for (int iterator = 0; iterator < noCashiers; iterator++) {
			thread[iterator] = new Cashier(leftEndpoint, rightEndpoint);
		}
	}

	public static int getCurrentTime() {
		return currentTime;
	}

	int getShortestQueue() {

		int minimumSize = 2147483647;
		/*
		 * the minimum queue size of a thread chasier
		 */
		int minimumQindex = 0;
		/*
		 * the index of the thread chasier with the minimum queue size
		 */	
		int iterator = 0;
		int sizeSum = 0;
		/*
		 * actualizez si ora de varf, data de ora curenta pentru coada de
		 * lungime maxima
		 */

		for (iterator = 0; iterator < noCashiers; iterator++) {
			if (minimumSize > thread[iterator].queue.size()) {
				minimumSize = thread[iterator].queue.size();
				minimumQindex = iterator;
			}
			sizeSum += thread[iterator].queue.size();
		}
		if (maximumSizeSum < sizeSum) {
			maximumSizeSum = sizeSum;
			peakHour = getCurrentTime();
		}
		totalQSize = sizeSum;
		return minimumQindex;

	}

	public String getQueueContent(int index) {
		try {
			return thread[index].printQueue();
		} catch (Exception e) {
			;
		}
		return "";
	}

	public void run() {
		int randomServiceTime;
		int randomArrivialTime;
		int iterator;
		int id = 0;
		
		for (iterator = 0; iterator < noCashiers; iterator++) {
			thread[iterator].setName("Cashier" + (iterator + 1));
		}
		for (iterator = 0; iterator < noCashiers; iterator++)
			thread[iterator].start();
		while (currentTime < simulationTime) {
			setAvgWaitingTime();
			setAvgEmptyTime();
			setAvgServiceTime();
			setIntervalAvgWaitingTime();
			setIntervalAvgEmptyTime();
			setIntervalAvgServiceTime();
			randomServiceTime = random.nextInt(maxServiceTime - minServiceTime) + minServiceTime;
			randomArrivialTime = random.nextInt(maxArrivialTime - minArrivialTime)
					+ minArrivialTime; /* timpul de sosire dintre clienti */
			Client c = new Client(++id, randomServiceTime, currentTime);
			Cashier best = thread[getShortestQueue()];

			best.addClient(c);
			try {
				Thread.sleep(1000 * randomArrivialTime);
			} catch (InterruptedException e) {
			}
			currentTime++;
		}
		boolean ok = true;
		while (currentTime++ <= simulationTime && ok) {
			ok = false;
			for (iterator = 0; iterator < noCashiers && !ok; iterator++)
				if (thread[iterator].queue.size() > 0)
					ok = true;
		}
		if (currentTime >= simulationTime) {
			for (iterator = 0; iterator < noCashiers; iterator++) {
				thread[iterator].interrupt();

			}
		}
		simulationEnded = true;
		this.interrupt();
	}
}
