package br.edu.ifg.formosa.obac.modelo;

public class ModeloPainelConfiguracao {
	private final String [] propulsoes = {"Canh�o", "Mola"};
	
	private final String canhao = "Canh�o";
	private final String mola = "Mola";
	private final String dado1Canhao = "�ngulo (�)"; 
	private final String dado2Canhao = "Energia (J)";
	private final String dado1Mola = "Tamaho da Mola(m)";
	private final String dado2Mola = "Constante El�stica (N/m)";
	
	private final String [] simulacoesPadrao = {"Plano", "Subida", "Decida", "Plano e Precip�cio", "Queda Livre"};
	private final String lancamentoObliquo = "Lan�amento Obl�quo";
	

	private final String [] atritos = {"Asfalto", "Alum�nio", "Madeira"};
	private final String [] gravidade = {"Terra", "Lua", "Marte"};
	
//Getters
	public String getCanhao() {
		return canhao;
	}
	public String getMola() {
		return mola;
	}
	public String getDado1Canhao() {
		return dado1Canhao;
	}
	public String getDado2Canhao() {
		return dado2Canhao;
	}
	public String getDado1Mola() {
		return dado1Mola;
	}
	public String getDado2Mola() {
		return dado2Mola;
	}
	public String[] getSimulacoesPadrao() {
		return simulacoesPadrao;
	}
	public String getLancamentoObliquo() {
		return lancamentoObliquo;
	}
	public String[] getAtritos() {
		return atritos;
	}
	public String[] getGravidade() {
		return gravidade;
	}
	public String[] getPropulsoes() {
		return propulsoes;
	}
	
}
