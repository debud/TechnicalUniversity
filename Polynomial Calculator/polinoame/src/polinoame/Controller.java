package polinoame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model model;
	private View view;

	Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		view.addMonomialListener(new MonomialListener());
		view.addAdditionListener(new AdditionListener());
		view.addSubstractionListener(new SubstractionListener());
		view.addMultiplicationListener(new MultiplicationListener());
		view.addDivisionListener(new DivisionListener());
		view.addDerivationListener(new DerivationListener());
		view.addIntegrationListener(new IntegrationListener());
	}

	class MonomialListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int coef;
			int power;
			coef = view.getCoef();
			power =  view.getPower();
			if(view.inputValidation){
			model.setMonomial(coef, power, view.getPolynomialNumb());
			if (view.getPolynomialNumb() == 0)
				view.setOutput1(model.print(view.getPolynomialNumb()));
			if (view.getPolynomialNumb() == 1)
				view.setOutput2(model.print(view.getPolynomialNumb()));
			}
		}
	}

	class AdditionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setOutputResult(model.print(model.getAdditionResult()));
			System.out.println(model.print(model.getAdditionResult()));
		}

	}
	
	class SubstractionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setOutputResult(model.print(model.getSubstractionResult()));
		}

	}
	
	class MultiplicationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setOutputResult(model.print(model.getMultiplicationResult()));
		}

	}
	
	class DivisionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setOutputResult(model.getDivisionResult());
		}

	}
	
	class IntegrationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setOutputResult(model.getIntegrationResult(view.getPolynomialNumb()));
		}

	}
	
	class DerivationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setOutputResult(model.getDerivationResult(view.getPolynomialNumb()));
		}

	}
}
