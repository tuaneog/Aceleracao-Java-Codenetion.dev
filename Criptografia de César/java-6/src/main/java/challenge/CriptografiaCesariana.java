package challenge;

import java.util.Scanner;

public class CriptografiaCesariana implements Criptografia {

    final int NUMEROS_CASAS = 3;
    final int A = 97;
    final int Z = 122;
    final int ALFABETO = 26;

    @Override
    public String criptografar(String texto) {

        validarTexto(texto);
        texto = texto.toLowerCase();

        String resultadoFinal = "";

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) <= Z && texto.charAt(i) >= A) {
                resultadoFinal += (char) (((texto.charAt(i) - A + NUMEROS_CASAS) % ALFABETO) + A);
            } else {
                resultadoFinal += texto.charAt(i);
            }
        }
        return resultadoFinal;
    }

    @Override
    public String descriptografar(String texto) {

        validarTexto(texto);
        texto = texto.toLowerCase();

        String resultadoFinal = "";

        for(int i = 0; i < texto.length(); i++){
            if(texto.charAt(i) <= Z && texto.charAt(i) >= A){
                resultadoFinal += (char) (((texto.charAt(i) - A + (ALFABETO - NUMEROS_CASAS)) % ALFABETO) + A);
            } else {
                resultadoFinal += texto.charAt(i);
            }
        }
        return resultadoFinal;
   }
   public void validarTexto(String texto){
       if (texto == null) throw new NullPointerException();
       else if (texto.trim().equals("")) throw new IllegalArgumentException();

   }

}
