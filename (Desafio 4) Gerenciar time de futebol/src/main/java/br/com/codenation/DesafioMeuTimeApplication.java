package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<Time> timesLista = new ArrayList<>();
	List<Jogador> jogadoresLista = new ArrayList<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao,
							String corUniformePrincipal, String corUniformeSecundario) {

		if(buscarTime(id) != null) {
			throw new IdentificadorUtilizadoException("Time já cadastrado no sistema");

		}
		timesLista.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento,
							   Integer nivelHabilidade, BigDecimal salario) {

		if(buscarTime(idTime) == null) {
			throw new TimeNaoEncontradoException("Id do time não foi encontrado");

		} else if (buscarJogador(id) != null){
			throw new IdentificadorUtilizadoException("Jogador já está cadastrado no sistema");

		}
		Jogador novoJogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		jogadoresLista.add(novoJogador);
		buscarTime(idTime).jogadoresDoTime.add(novoJogador);
	}

	public void definirCapitao(Long idJogador) {
		
		Jogador jogador = buscarJogador(idJogador);
		if(jogador != null){
			Long idTime = jogador.getIdTime();
			buscarTime(idTime).setIdCapitao(idJogador);

		} else {
			throw new JogadorNaoEncontradoException("Jogador não encontrado no sistema");
		}
	}

	public Time buscarTime(Long idTime){
		for(int i = 0; i < timesLista.size(); i++){
			if(timesLista.get(i).getId().equals(idTime)){
				return  timesLista.get(i);
			}
		}
		return null;
	}

	public Jogador buscarJogador(Long idJogador){
		for(int i = 0; i < jogadoresLista.size(); i++){
			if(jogadoresLista.get(i).getId().equals(idJogador)){
				return  jogadoresLista.get(i);
			}
		}
		return null;
	}

	public Long buscarCapitaoDoTime(Long idTime) {

		if(timesLista.contains(buscarTime(idTime)) == false){
			throw new TimeNaoEncontradoException("Time não encontrado no sistema");
		}
		Long idCapitao = buscarTime(idTime).getIdCapitao();
		if(idCapitao == -1L){
			throw new CapitaoNaoInformadoException("O time informado não possui capitão");
		}
		return idCapitao;
	}

	public String buscarNomeJogador(Long idJogador) {
		for(int i = 0; i < jogadoresLista.size(); i++){
			if(jogadoresLista.get(i).getId().equals(idJogador)){
				return jogadoresLista.get(i).getNome();

			}
		}
		throw new JogadorNaoEncontradoException("Jogador não encontrado no sistema");
	}

	public String buscarNomeTime(Long idTime) {
		for(int i = 0; i < timesLista.size(); i++){
			if(timesLista.get(i).getId().equals(idTime)){
				return timesLista.get(i).getNome();
			}
		}
		throw new TimeNaoEncontradoException("Time não encontrado no sistema");
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {

		if(buscarTime(idTime) == null){
			throw new TimeNaoEncontradoException("Time não encontrado no sistema");

		}
		List<Jogador> jogadoresDoTime = buscarTime(idTime).jogadoresDoTime;
		List<Long> listaDeIds = new ArrayList<>();
		for (Jogador j:jogadoresDoTime) {
			listaDeIds.add(j.getId());

		}
		Collections.sort(listaDeIds);
		return listaDeIds;
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {

		if(buscarTime(idTime) == null) {
			throw new TimeNaoEncontradoException("Time não encontrado no sistema");
		}
		Time time = buscarTime(idTime);

		int maiorHabilidade = -1;
		Long idJogadorMaiorHabilidade = - 1L;

		for(int i = 0; i < time.jogadoresDoTime.size(); i++){
			if(time.jogadoresDoTime.get(i).getNivelHabilidade() > maiorHabilidade){
				maiorHabilidade = time.jogadoresDoTime.get(i).getNivelHabilidade();
				idJogadorMaiorHabilidade = time.jogadoresDoTime.get(i).getId();
			}
		}
		return idJogadorMaiorHabilidade;
	}

	public Long buscarJogadorMaisVelho(Long idTime) {

		if(buscarTime(idTime) == null) {
			throw new TimeNaoEncontradoException("Time não encontrado no sistema");

		}
		Time time = buscarTime(idTime);
		int maiorIdade = -1;
		Long idJogadorMaisVelho = -1L;

		for(int i = 0; i < time.jogadoresDoTime.size(); i++){
			if(time.jogadoresDoTime.get(i).getIdade() > maiorIdade){
				maiorIdade = time.jogadoresDoTime.get(i).getIdade();
				idJogadorMaisVelho = time.jogadoresDoTime.get(i).getId();

			} else if(time.jogadoresDoTime.get(i).getIdade() == maiorIdade) {
				if(idJogadorMaisVelho > time.jogadoresDoTime.get(i).getId()){
					idJogadorMaisVelho = time.jogadoresDoTime.get(i).getId();
				}
			}
		}
		return idJogadorMaisVelho;
	}

	public List<Long> buscarTimes() {
		List<Long> listaDeIds = new ArrayList<>();
		for (Time t:timesLista) {
			listaDeIds.add(t.getId());

		}
		if(timesLista == null){
			return null;
		}
		Collections.sort(listaDeIds);
		return listaDeIds;

	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		
		if(buscarTime(idTime) == null) {
			throw new TimeNaoEncontradoException("Time não encontrado no sistema");
		
		}
		Time time = buscarTime(idTime);

		BigDecimal maiorSalario = BigDecimal.ZERO;
		Long idJogadorMaiorSalario = -1L;
		
		for(int i = 0; i < time.jogadoresDoTime.size(); i++){
			if(time.jogadoresDoTime.get(i).getSalario().compareTo(maiorSalario) >= 0){
				maiorSalario = time.jogadoresDoTime.get(i).getSalario();
				idJogadorMaiorSalario = time.jogadoresDoTime.get(i).getId();

			} else if(time.jogadoresDoTime.get(i).getSalario().equals(maiorSalario)){
				if(idJogadorMaiorSalario > time.jogadoresDoTime.get(i).getId()){
					idJogadorMaiorSalario = time.jogadoresDoTime.get(i).getId();
				}
			}
		}
		return idJogadorMaiorSalario;
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		for(int i = 0; i < jogadoresLista.size(); i++){
			if(jogadoresLista.get(i).getId().equals(idJogador)){
				return jogadoresLista.get(i).getSalario();
			}
		}
		throw new JogadorNaoEncontradoException("Jogador não encontrado no sistema");
	}

	public List<Long> buscarTopJogadores(Integer top) {

		Collections.sort(jogadoresLista, new JogadorComparator());

		List<Long> idTopJogadores = new ArrayList<>();
		for(int i = 0; i < top && i < jogadoresLista.size(); i++) {
			idTopJogadores.add(jogadoresLista.get(i).getId());
		}
		return idTopJogadores;
	}

}
