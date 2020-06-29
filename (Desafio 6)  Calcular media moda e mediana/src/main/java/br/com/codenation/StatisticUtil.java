package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		if(elements.length == 0) throw new ArithmeticException("A lista não pode ser vazia");

		int soma = 0;
		for(int i = 0; i < elements.length; i++){
			soma += elements[i];

		}
		int media = soma / elements.length;

		return media;
	}

	public static int mode(int[] elements) {
		if(elements.length == 0) throw new ArithmeticException("A lista não pode ser vazia");

		int moda = 0;
		int cont = 0;
		for(int i = 0; i < elements.length; i++){
			if(countElementsInArray(elements, elements[i]) > cont){
				moda = elements[i];
				cont = countElementsInArray(elements, elements[i]);
			}
		}
		return moda;
	}

	public static int countElementsInArray(int[] elements, int value){
		int cont = 0;
		for(int i = 0; i < elements.length; i++){
			if(value == elements[i]){
				cont++;
			}
		}
		return cont;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int mediana;
		if(elements.length % 2 != 0){
			int posicao = (elements.length - 1) / 2;
			mediana = elements[posicao];

		} else{
			int posicao1 = elements.length / 2;
			int posicao2 = posicao1 - 1;
			mediana = average(new int[]{elements[posicao1], elements[posicao2]});
		}
		return mediana;
	}
}