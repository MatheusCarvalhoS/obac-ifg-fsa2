package br.edu.ifg.formosa.obac.modelo;


public class ModeloPainelConfiguracao {
	
	//Array de String usado nos tipos de propuls�o 
	private final String [] propulsoes = {"Canh�o", "Mola"};
	
	//Array de String usado nos tipos de simula��o
	private final String [] simulacoesPadrao = {"Plano", "Subida", "Decida", "Plano e Precip�cio", "Queda Livre"};
	//String que completa o array acima quando a propuls�o canh�o � selecionada, sendo tamb� utilizada em ControlePainelConfiguracaoAtualizacoes
	private final String lancamentoObliquo = "Lan�amento Obl�quo";
	
	//Array de String usado nos tipos de atrito
	private final String [] atritos = {"Asfalto", "Alum�nio", "Madeira"};
	
	//Array de String usado nos tipos de gravidade
	private final String [] gravidade = {"Terra", "Lua", "Marte"};
	
	//Strings utilizadas no bot�o bIniciarPausa em VisaoPainelConfiguracao e em ControlePainelConfiguracaoExecucao
	private String botaoIniciar = "Iniciar Simula��o";
	private String botaoPausar = "Pausar Simula��o";
	
	//Strings utilizadas em uma codi��o de ControlePainelConfiguracaoAtualizacoes
	private final String canhao = "Canh�o";
	private final String mola = "Mola";
	
	//Strings usadas em r�tulos que s�o alterados no painel de informa��es
	private final String dado1Canhao = "�ngulo (�)"; 
	private final String dado2Canhao = "Energia (J)";
	private final String dado1Mola = "Tamaho da Mola(m)";
	private final String dado2Mola = "Constante El�stica (N/m)";
	
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
	public String getBotaoIniciar() {
		return botaoIniciar;
	}
	public void setBotaoIniciar(String botaoIniciar) {
		this.botaoIniciar = botaoIniciar;
	}
	public String getBotaoPausar() {
		return botaoPausar;
	}
	public void setBotaoPausar(String botaoPausar) {
		this.botaoPausar = botaoPausar;
	}
	
}
