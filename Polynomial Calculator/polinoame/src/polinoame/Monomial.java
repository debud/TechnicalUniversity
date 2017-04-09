package polinoame;

public class Monomial implements Comparable{
	private double coef;
	private int power;

	Monomial(double coef, int power) {
		this.coef = coef;
		this.power = power;
	}

	/**
	 * get-eri pentru coeficientul si puterea monomului
	 * */
	double getCoef() {
		return coef;
	}

	int getPower() {
		return power;
	}

	/**
	 * set-eri pentru coeficientul si puterea monomului
	 * */
	void setCoef(double coef) {
		this.coef = coef;
	}

	void setPower(int power) {
		this.power = power;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * suprascrierea metodei toString()
	 * se afiseaza valorile numerice fara zero-uri nesemnificative dupa virgula
	 * se specifica semnul coeficientului
	 */
	public String toString() {
		String outputCoef = new String("");
		String outputPower = new String("");
		String signSpecifier = new String("");
		String precisionSpecifier = new String("");
		
		  if(coef == (long) coef)
		        outputCoef = String.format("%+d", (long)coef);
		    else{
		    	signSpecifier = String.format("%+.2f", coef);
		    	outputCoef = String.format("%s", signSpecifier);
		    }
		  if(power == (long) power)
		        outputPower = String.format("%d", (long)power);
		    else{
		    	precisionSpecifier = String.format("%+d", power);
		    	outputPower = String.format("%s", precisionSpecifier);
		    }
			return (outputCoef + "*X^" + outputPower);
	}

	@Override
	public int compareTo(Object arg0) {
		return (int)(((Monomial) arg0).getPower() - power);
		// TODO Auto-generated method stub
	}
}
