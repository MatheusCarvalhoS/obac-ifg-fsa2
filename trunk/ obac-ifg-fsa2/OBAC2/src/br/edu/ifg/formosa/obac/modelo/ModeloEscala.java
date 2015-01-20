package br.edu.ifg.formosa.obac.modelo;

public class ModeloEscala {
	
	//Constantes
	//--Inteiro
	public final int tamanhoPrecipicioPix = 330;//Este valor � usado porque assim � considerado que 50% + 1pix do tamanho do objeto estar� por cima do plano, de forma que o equil�brio dele seja mantido(Tamnho_do_plano(330)-Tamho_do_objeto(30)-50%+1_do_Objeto_Pix(16))
	public final int fimAmbienteYPix = 600;
	public final static int qtdMarcadores = 4; //Numero de marcadores existentes nas escalas
	
	//Variaveis
	//--Inteiro
	private int escalaInicioX = 130; //Inicio da escala X (px)
	private int escalaInicioY = 520; //Inicio da escala Y (px)
	
	private int escalaFimXPix = 700; //Fim da escala X (px)
	private int escalaFimYPix = 520; //Fim da escala Y (px)
	
	private long escalaFimXM = 0; //Fim da escala X (m)
	private long escalaFimYM = 0; //Fim da escala Y (m)
	
	private int espacamentoMarcadores = 0; //Espacamento entre os marcadores (px)
	
	private int comprimentoEscalaPX = escalaFimXPix - escalaInicioX;
	private int comprimentoEscalaPY = escalaFimYPix - escalaInicioY;
	
	public static final double marcadorInicial = 0.0; //� Constante, mas deixei aqui para um melhor entendimento
	private double[] marcadoresEscala = new double[qtdMarcadores + 1]; //0-Menor / 4-Maior
	/* Explica��o do 'qtdMarcadores + 1'
	 * -O qtdMarcadores indica os marcadores desenhados 'dentro' da escala, 
	 * n�o conta os das extremidades, por isso o '+1' devido ao inicial ser uma constante
	 */
	
	//Metodos
	//--Getters
	public int getEscalaInicioX() {return escalaInicioX;}
	public int getEscalaInicioY() {return escalaInicioY;}
	public int getEscalaFimXPix() {return escalaFimXPix;}
	public int getEscalaFimYPix() {return escalaFimYPix;}	
	public int getEspacamentoMarcadores() {return espacamentoMarcadores;}
	public int getComprimentoEscalaPX() {return comprimentoEscalaPX;}
	public int getComprimentoEscalaPY() {return comprimentoEscalaPY;}
	public long getEscalaFimXM() {return escalaFimXM;}
	public long getEscalaFimYM() {return escalaFimYM;}	
	public double[] getMarcadoresEscala() {return marcadoresEscala;}
	
	//--Setters
	public void setEscalaInicioX(int escalaInicioX) {this.escalaInicioX = escalaInicioX;}
	public void setEscalaInicioY(int escalaInicioY) {this.escalaInicioY = escalaInicioY;}
	public void setEscalaFimXPix(int escalaFimXPix) {this.escalaFimXPix = escalaFimXPix;}
	public void setEscalaFimYPix(int escalaFimYPix) {this.escalaFimYPix = escalaFimYPix;}	
	public void setEspacamentoMarcadores(int espacamentoMarcadores) {this.espacamentoMarcadores = espacamentoMarcadores;}
	public void setEscalaFimXM(long escalaFimXM) {this.escalaFimXM = escalaFimXM;}
	public void setEscalaFimYM(long escalaFimYM) {this.escalaFimYM = escalaFimYM;}
}