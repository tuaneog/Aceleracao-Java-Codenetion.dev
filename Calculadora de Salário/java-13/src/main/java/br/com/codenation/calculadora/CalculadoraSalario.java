package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		if(salarioBase < 1039){
			return (long) 0.0;

		} else {
			double salarioLiquido = salarioBase - calcularInss(salarioBase);
			salarioLiquido -= calcularIrrf(salarioLiquido);

			return Math.round(salarioLiquido);
		}

	}

	private double calcularInss(double salarioBase) {

		double descontoInss = 0;

		if(salarioBase <= 1500){
			descontoInss = salarioBase * 0.08;

		} else if(salarioBase <= 4000){
			descontoInss = salarioBase * 0.09;

		} else {
			descontoInss = salarioBase * 0.11;

		}
		return descontoInss;
	}
	private double calcularIrrf (double salarioBrutoInss) {

		double descontoIrrf = 0;

		if(salarioBrutoInss > 3000 && salarioBrutoInss <= 6000){
			descontoIrrf = salarioBrutoInss * 0.075;

		} else if(salarioBrutoInss > 6000) {
			descontoIrrf = salarioBrutoInss * 0.15;

		}
		return descontoIrrf;
	}

}