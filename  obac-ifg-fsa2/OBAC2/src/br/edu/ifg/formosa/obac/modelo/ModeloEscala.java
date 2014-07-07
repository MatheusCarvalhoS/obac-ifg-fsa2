package br.edu.ifg.formosa.obac.modelo;

public class ModeloEscala {
	
	//Variaveis
	//--Inteiro
	private int escalaInicioX = 100; //Inicio da escala X (px)
	private int escalaInicioY = 564; //Inicio da escala Y (px)
	
	private int escalaFimXPix = 700; //Fim da escala X (px)
	private int escalaFimYPix = 564; //Fim da escala Y (px)
	
	private long escalaFimXM = 700; //Fim da escala X (m)
	private long escalaFimYM = 564; //Fim da escala Y (m)
	
	private int espacamentoMarcadores = 0; //Espacamento entre os marcadores (px)	
	private int qtdMarcadores = 5; //Numero de marcadores existentes na escala
	
	private int escalaSelecionada = 0;/*Utilizada para desenhar a imagem de fundo
									   *0-plano/1-subida/2-descida/3-prec./4-queda/5-projetil
	                                   */
	
	//--Double
	private double angulo = 0; //Angulo para rotacionar a escala; -- Ta repetida em modelo superf�cie
	
	//Metodos
	//--Getters
	public int getEscalaInicioX() {return escalaInicioX;}
	public int getEscalaInicioY() {return escalaInicioY;}
	public int getEscalaFimXPix() {return escalaFimXPix;}
	public int getEscalaFimYPix() {return escalaFimYPix;}	
	public long getEscalaFimXM() {return escalaFimXM;}
	public long getEscalaFimYM() {return escalaFimYM;}	
	public int getEspacamentoMarcadores() {return espacamentoMarcadores;}
	public int getQtdMarcadores() {return qtdMarcadores;}
	public int getEscalaSelecionada() {return escalaSelecionada;}
	public double getAngulo() {return angulo;}
	
	//--Setters
	public void setEscalaInicioX(int escalaInicioX) {this.escalaInicioX = escalaInicioX;}
	public void setEscalaInicioY(int escalaInicioY) {this.escalaInicioY = escalaInicioY;}
	public void setEscalaFimXPix(int escalaFimXPix) {this.escalaFimXPix = escalaFimXPix;}
	public void setEscalaFimYPix(int escalaFimYPix) {this.escalaFimYPix = escalaFimYPix;}	
	public void setEscalaFimXM(long escalaFimXM) {this.escalaFimXM = escalaFimXM;}
	public void setEscalaFimYM(long escalaFimYM) {this.escalaFimYM = escalaFimYM;}	
	public void setEspacamentoMarcadores(int espacamentoMarcadores) {this.espacamentoMarcadores = espacamentoMarcadores;}
	public void setQtdMarcadores(int qtdMarcadores) {this.qtdMarcadores = qtdMarcadores;}
	public void setEscalaSelecionada(int escalaSelecionada) {this.escalaSelecionada = escalaSelecionada;}
	public void setAngulo(double angulo) {this.angulo = angulo;}
}