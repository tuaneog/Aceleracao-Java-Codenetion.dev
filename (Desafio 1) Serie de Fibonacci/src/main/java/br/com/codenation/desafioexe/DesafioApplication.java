package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> fibonacciNumbers = new ArrayList<>();

		int numeroAtual = 1;
		int numeroAnterior = 0;

        fibonacciNumbers.add(numeroAnterior);
        fibonacciNumbers.add(numeroAtual);

        while(numeroAtual < 350) {

            numeroAtual = numeroAnterior + numeroAtual;
            numeroAnterior = numeroAtual - numeroAnterior;
            fibonacciNumbers.add(numeroAtual);
        }
        return fibonacciNumbers;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}
}