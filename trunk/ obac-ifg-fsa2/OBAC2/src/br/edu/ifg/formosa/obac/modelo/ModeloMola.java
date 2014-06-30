package br.edu.ifg.formosa.obac.modelo;

public class ModeloMola {
	
	//Medidas da MOLA
	private final double tamanhoMolaIniPix = 100;//tamanho inicial em pixels 
	private double tamanhoMolaFinPix;//tamanho final em pixels = Pos inicial do objeto
	private double tamanhoIniMolaM;//tamanho inicial em metros
	private double tamanhoMolaFinM;//tamanho final em metros
	
	//Taxa de deforma��o da mola
	private double x;
	
	//Constante el�stica(k)
	private double kAtual;//Atual
	
	//Modelo do Objeto
	private ModeloObjeto mo = null;//OBS Matheus: A posi��o original dos objetos no eixo x n�o altera na subida, descida e P&P. Ela gira sozinha com a rota��o do painel, sem altera��es no valor xPix dela.
								   //Por isso eu pe�o que considere a op��o de haver apenas uma vari�vel e uma constante para a posi��o em pixel do objeto
								  //Tbm aproveito a deixa para pedir uma forma de vc alocar as vari�veis que s�o citadas em controlePainelInforma��o 
	
	//Construtor
	public ModeloMola(ModeloObjeto mo) {this.mo = mo;}
	
	//C�lculo de avelocidade - V0 = (K+x^2/m)
	public double velocidadeLancamento(){return ((kAtual+Math.pow(x, 2))/mo.getMassa());}
	
	//Calculo de X
//	public calculaX(/*Recebe a posi��o atual do objeto*/){
		//SUBTRAI O TAMNHO DA MULA PELO TAMNHO FINAL DA MOLA (POSI��O INICIAL DO OBJETO NO EIXO X OU Y NO CASO DA QUEDA LIVRE)
//		(tamanhoIniMolaM-tamanhoIniMolaM)
		//O TAMANHO DE X � DADO EM METROS, MAS AKI AINDA EST� EM PIXEL, LOGO, TEM QUE EXISTIR UM M�TODO EST�TICO PARA ISSO SER FEITO
//		return X;
//	}
	
//Getters
	public double getTamanhoMolaFinPix() {return tamanhoMolaFinPix;}
	public double getTamanhoMolaFinM() {return tamanhoMolaFinM;}
	public double getX() {return x;}
	public double getkAtual() {return kAtual;}
	public double getTamanhoMolaIniPix() {return tamanhoMolaIniPix;}
//Setters
	public void setTamanhoMolaFinPix(double tamanhoMolaFinPix) {this.tamanhoMolaFinPix = tamanhoMolaFinPix;}
	public void setTamanhoIniMolaM(double tamanhoIniMolaM) {this.tamanhoIniMolaM = tamanhoIniMolaM;}
	public void setTamanhoMolaFinM(double tamanhoMolaFinM) {this.tamanhoMolaFinM = tamanhoMolaFinM;}
//	public void setX(double x) {this.x = x;} - Pode n�o ser vi�vel a sua aplica��o, j� que o calculo dele � feito anesta classe
	public void setkAtual(double kAtual) {this.kAtual = kAtual;}

}
