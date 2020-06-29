package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    final int PONTOSCARTEIRA = 20;
    final int VAGAS = 10;
    final int IDADE = 55;



    List<Carro> listaDeCarros = new ArrayList<>();

    public void estacionar(Carro carro) {
        if(carro.getMotorista().getPontos() >= PONTOSCARTEIRA){
            throw new EstacionamentoException ("Não foi possui estacionar, pois o motorista tem mais de 20 pontos na carteira");

        }
        if(listaDeCarros.size() < VAGAS){
            listaDeCarros.add(carro);

        } else {
            int indiceDaTroca = -1;
            for(int i = 0; i < listaDeCarros.size(); i++){
                if(listaDeCarros.get(i).getMotorista().getIdade() < IDADE){
                    indiceDaTroca = i;
                    break;
                }
            }
            if(indiceDaTroca != -1){
                listaDeCarros.remove(indiceDaTroca);
                listaDeCarros.add(carro);

            }else {
                throw new EstacionamentoException ("Não foi possível estacionar, pois todos os motoristas possuem mais de 55 anos");
            }
        }
      
    }

    public int carrosEstacionados() {
        return listaDeCarros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return listaDeCarros.contains(carro);
    }
}
