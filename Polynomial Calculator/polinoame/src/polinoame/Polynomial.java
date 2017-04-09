package polinoame;

import java.util.ArrayList;

import java.util.Iterator;

public class Polynomial {

	ArrayList<Monomial> monomialList = new ArrayList<Monomial>();

	public Polynomial() {
	}

	/**
	 * @param coef
	 *            reprezinta coeficientul monomului
	 * @param power
	 *            reprezinta puterea monomului Metoda adauga monomul la
	 *            polinomul curent.Trateaza cazul in care exista deja in lista
	 *            de monoame a polinomului un termen cu aceeasi putere, prin
	 *            adunarea coeficientilor corespunzatori Daca suma
	 *            coeficientilor este nula, se elimina monomul din lista
	 */

	public void addMonomial(double coef, int power) {
		boolean added = false;
		if (coef == 0)
			return;
		for (Iterator<Monomial> it = monomialList.iterator(); it.hasNext();) {
			if (added) {
				break;
			} else {

				Monomial m = it.next();
				if (m.getPower() == power) {
					if (coef + m.getCoef() == 0) {
						monomialList.remove(monomialList.indexOf(m));

					} else {
						m.setCoef(coef + m.getCoef());
					}
					added = true;
				}
			}
		}

		if (added == false) {
			Monomial newMonomial = new Monomial(coef, power);
			monomialList.add(newMonomial);
		}
	}

	/**
	 * @param term
	 *            este polinomul termen inclus in operatie
	 * @param sign
	 *            semnul care desemneaza operatia de adunare/scadere
	 */
	public void setResult(Polynomial term, int sign) {
		for (Monomial each : term.monomialList) {
			this.addMonomial((sign * each.getCoef()), each.getPower());
		}
	}

	/**
	 * metoda addition returneaza o valoare de tip Polinom Rezultatul returnat
	 * retine suma dintre polinomul p trimis ca parametru si polinomul care
	 * apeleaza metoda respectiva
	 **/
	public Polynomial addition(Polynomial p) {
		Polynomial result = new Polynomial();

		result.setResult(this, 1);
		result.setResult(p, 1);
		return ((Polynomial) result);
	}

	/**
	 * metoda substraction returneaza o valoare de tip Polinom. Rezultatul
	 * returnat retine diferenta dintre polinomul care apeleaza metoda
	 * respectiva si polinomul p trimis ca parametru
	 **/
	public Polynomial substraction(Polynomial p) {
		Polynomial result = new Polynomial();

		result.setResult(this, 1);
		result.setResult(p, -1);
		return ((Polynomial) result);
	}

	/**
	 * metoda multiply returneaza o valoare de tip Polinom. Rezultatul returnat
	 * retine produsul dintre polinomul care apeleaza metoda respectiva si
	 * polinomul p trimis ca parametru cele 2 foreach-uri parcurg lista de
	 * monoame a celor 2 polinoame individual, cu scopul de a efectua operatiile
	 * matematice de inmultire a fiecarui monom din primul polinom cu fiecare
	 * monom din cel de-al doilea monom
	 **/
	public Polynomial multiply(Polynomial p) {
		Polynomial result = new Polynomial();
		for (Monomial factor1 : monomialList) {
			for (Monomial factor2 : p.monomialList) {
				result.addMonomial(factor1.getCoef() * factor2.getCoef(), factor1.getPower() + factor2.getPower());
			}
		}
		return ((Polynomial) result);
	}

	public Polynomial derivate() {
		Polynomial result = new Polynomial();
		for (Monomial each : monomialList) {
			if (each.getPower() > 0)
				result.addMonomial(each.getCoef() * each.getPower(), each.getPower() - 1);
		}
		return ((Polynomial) result);
	}

	public Polynomial integrate() {
		Polynomial result = new Polynomial();
		for (Monomial each : monomialList) {
			result.addMonomial(each.getCoef() / (each.getPower() + 1), each.getPower() + 1);
		}
		return ((Polynomial) result);
	}

	double getPolyCoef(int index) {
		return (monomialList.get(index).getCoef());
	}

	int getPolyPower(int index) {
		return (monomialList.get(index).getPower());
	}

	/**
	 * 
	 * Operatiile asupra polinoamelor se realizeaza in multimea numerelor
	 * intregi, asadar daca catul impartirii coeficientilor gradului cel mai
	 * mare al deimpartitului si impartitorului este egal cu un numar subunitar,
	 * echivalent cu 0 ca numar intreg, atunci se negljeaza rezultatul la
	 * impartire, insa se considera ca termenul cu gradul cel mai mare al
	 * impartitorului se reduce confoorm algoritmului de impartire a 2 polinoame
	 */
	public String division(Polynomial divisor) throws ArithmeticException {
		Polynomial result = new Polynomial();
		Polynomial divident = new Polynomial();
		Polynomial substractor = new Polynomial();

		if (divisor.getPolyCoef(0) == 0) {
			throw new ArithmeticException("Division By Zero Exception !");
		}
		divident = this;
		while (divident.monomialList.size() > 0) {

			if (divident.getPolyPower(0) >= divisor.getPolyPower(0)) {
				result.monomialList.sort(null);
				result.addMonomial(divident.getPolyCoef(0) / divisor.getPolyCoef(0),
						divident.getPolyPower(0) - divisor.getPolyPower(0));
				int lastIndex = result.monomialList.size() - 1;
				Polynomial lastMonomialResult = new Polynomial();
				lastMonomialResult.addMonomial(result.getPolyCoef(lastIndex), result.getPolyPower(lastIndex));
				substractor = divisor.multiply(lastMonomialResult);
				divident = divident.substraction(substractor);
			//	if (divident.monomialList.size() > 0)
			//		if (divident.getPolyCoef(0)
			//				/ divisor.getPolyCoef(0) != (long) (divident.getPolyCoef(0) / divisor.getPolyCoef(0))) {
				//		divident.monomialList.remove(0);
				//	}
			} else
				break;
		}
		return (divisionPrint(result, divident));
	}

	/**
	 * se afiseaza polinomul curent sub forma matematica, metoda toString()
	 * fiind suprascrisa corespunzator in clasa Monomial
	 */
	public String print() {
		String output = "";

		monomialList.sort(null);
		if (monomialList.isEmpty())
			output = "0";
		else {
			for (Monomial each : monomialList) {
				output = output + each.toString();
			}
		}
		return output;
	}

	public String divisionPrint(Polynomial result, Polynomial remainder) {
		return ("catul = " + result.print() + " \n restul = " + remainder.print());
	}
}
