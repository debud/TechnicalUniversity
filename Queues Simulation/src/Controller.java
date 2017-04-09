import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends Thread {
	View view;
	Simulation simulation;
	public static boolean simulationAvailable = false;

	Controller(View view) {
		this.view = view;
		view.addSimulationListener(new SimulationListener());
	}

	class SimulationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			do {
				if (view.getMaxArrivial() <= view.getMinArrivial())
					view.putError("Invalid Arrivial Interval");
				else if (view.getMaxService() <= view.getMinService())
					view.putError("Invalid Service Interval");
				else if (view.getLeftEndpointOfInterval() >= view.getRightEndpointOfInterval())
					view.putError("Invalid Interval Endpoints");
				else
					simulation = new Simulation(view.getNoCashiers(), view.getSimulationTime(), view.getMaxArrivial(),
							view.getMinArrivial(), view.getMaxService(), view.getMinService(),
							view.getLeftEndpointOfInterval(), view.getRightEndpointOfInterval());
			} while (view.inputValidation == false);
			simulationAvailable = true;
		}
	}

	public void run() {
		int index;
		simulation.start();

		while (!simulation.simulationEnded) {
			view.setOutputEvents(simulation.getTrackOfEvents());
			for (index = 0; index < view.getNoCashiers(); index++) {
				view.setQueueOutput(index, simulation.getQueueContent(index));
			}
			view.setAvgData(simulation.getAverages());
		}
		simulation.interrupt();
	}
}
