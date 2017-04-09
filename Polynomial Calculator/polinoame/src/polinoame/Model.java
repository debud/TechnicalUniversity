package polinoame;
import java.util.ArrayList;

public class Model {

	ArrayList<Polynomial> poly = new ArrayList<Polynomial>();

	Model() {
		reset();
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		poly.add(p1);
		poly.add(p2);
	}

	public void reset() {
		poly.clear();
	}

	public void setMonomial(int coef, int power, int polynomialNumber) {
		Polynomial p = new Polynomial();
		p = poly.get(polynomialNumber);
		p.addMonomial(coef, power);
		poly.set(polynomialNumber, p);
	}
	
	public Polynomial getAdditionResult(){
		return poly.get(0).addition(poly.get(1));
	}
	
	public Polynomial getSubstractionResult(){
		return poly.get(0).substraction(poly.get(1));
	}
	
	public Polynomial getMultiplicationResult(){
		return poly.get(0).multiply(poly.get(1));
	}
	
	public String getDivisionResult(){
		return poly.get(0).division(poly.get(1));
	}
	
	public String print(int polynomialNumber){
		return(poly.get(polynomialNumber).print());
	}
	
	public String getIntegrationResult(int n){
		return poly.get(n).integrate().print();
	}
	
	public String getDerivationResult(int n){
		return poly.get(n).derivate().print();
	}
	
	public String print(Polynomial p){
		return p.print();
	}
}
