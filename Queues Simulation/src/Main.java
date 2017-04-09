import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

//import Controller.SimulationListener;

public class Main {
	private static final View view = new View();
	public static void main(String[] args) throws InterruptedException{
		view.setVisible(true);
		Controller controller = new Controller(view);
		while(controller.simulationAvailable == false){
			  Thread.sleep(4000);}
		if(controller.simulationAvailable){
			System.out.println("da");
		controller.start();
		}
}
}

