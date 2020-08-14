package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object objeto) {
        return somarCamposComAnotacao(objeto, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object objeto) {
        return somarCamposComAnotacao(objeto, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object objeto) {
        BigDecimal soma = somar(objeto);
        BigDecimal subtracao = subtrair(objeto);
        return  soma.subtract(subtracao);
    }

    public BigDecimal somarCamposComAnotacao(Object objeto, Class tipoAnotacao){
        Field[] todosCampos = objeto.getClass().getDeclaredFields();
        BigDecimal soma = BigDecimal.ZERO;

        for (Field campo: todosCampos){
            if (campo.getDeclaredAnnotations().length > 0 && campo.isAnnotationPresent(tipoAnotacao)){
                if (campo.getType() != BigDecimal.class){
                    return BigDecimal.ZERO;
                }
                campo.setAccessible(true);
                try {
                    soma = soma.add((BigDecimal) campo.get(objeto));
                }
                catch(Exception e){

                }
            }
        }
        return soma;
    }
}
