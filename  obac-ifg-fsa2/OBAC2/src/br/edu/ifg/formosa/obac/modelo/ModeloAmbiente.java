package br.edu.ifg.formosa.obac.modelo;

import java.awt.Color;
import java.net.URL;

import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelInformacao;

public class ModeloAmbiente {	
	//Constantes
	//--Double
	public static final double gravidadeTerra = 9.8;
	public static final double gravidadeLua = 1.6;
	public static final double gravidadeMarte = 3.7;

	//--String
	public static final String modeloURL = "br/edu/ifg/formosa/obac/imagens/";
	
	//Variáveis
	//--Double
	public static double anguloInclinacaoGraus = 0;
	private double gravSelecionada; // Recebe ou a gravidade da Terra, da Lua ou de Marte	
	private double tempoAtual; //OBAC1
	private double tempoTotal; //OBAC1
	
	//--URL
	private URL urlGr = this.getClass().getClassLoader().getResource(modeloURL + "ambiente/" + "terra" + ".png"); //gravidade
	private URL urlA = this.getClass().getClassLoader().getResource(modeloURL + "andaimes/" + "plano" + ".png"); //andaime
	private URL urlGu = this.getClass().getClassLoader().getResource(modeloURL + "andaimes/" + "guindasteF" + ".png"); //guindaste
	
	//--ControleInicioSimulacoes Painel de Configuração - atualizar dados referente ao ambiente
	private static ControlePainelInformacao cpi = null;
	
	//--Objeto, Escala e Superficie
	private ModeloEscala mEH = null;
	private ModeloEscala mEV = null;
	private ModeloObjeto mO = null;
	private ModeloSuperficie mS = null;
	private ModeloPropulsao mP = null;
	private ModeloObstaculo mObs = null;
	
	//--Color
	public static Color cor = Color.black; //Utilizado pra desenhar a escala de maneira que fique bem visivel nas simuações
	
	//Metodos	
	//--Construtor
	public ModeloAmbiente(ControlePainelInformacao cpi, ModeloEscala mEH, ModeloEscala mEV,
						  ModeloObjeto mO, ModeloSuperficie mS, ModeloPropulsao mP, ModeloObstaculo mObs)
	{
		ModeloAmbiente.cpi = cpi;
		this.mEH = mEH;
		this.mEV = mEV;
		this.mO = mO;
		this.mS = mS;
		this.mP = mP;
		this.mObs = mObs;
	}
	
	//--Getters
	public double getGravSelecionada() {return gravSelecionada;}
	public double getTempoAtual() {return tempoAtual;}
	public double getTempoTotal() {return tempoTotal;}
	public ModeloEscala getmEH() {return mEH;}
	public ModeloEscala getmEV() {return mEV;}
	public ModeloObjeto getmO() {return mO;}
	public ModeloSuperficie getmS() {return mS;}
	public ModeloPropulsao getmP(){return mP;}
	public ModeloObstaculo getmObs(){return mObs;}
	public URL getUrlGr() {return urlGr;}
	public URL getUrlA() {return urlA;}
	public URL getUrlGu() {return urlGu;}
	
	//--Setters
	public void setUrlGr(String urlGr) {this.urlGr = this.getClass().getClassLoader().getResource(modeloURL + "ambiente/" + urlGr + ".png");}
	public void setUrlA(String urlA) {this.urlA = this.getClass().getClassLoader().getResource(modeloURL + "andaimes/" + urlA + ".png");}
	public void setUrlGu(String urlGu) {this.urlGu = this.getClass().getClassLoader().getResource(modeloURL + "andaimes/" + urlGu + ".png");}
	public void setTempoAtual(double tempoAtual) {this.tempoAtual = tempoAtual;}
	
	//--Setters relacionados ao Painel de Informação
	public void setGravSelecionada(double gravSelecionada) {
		this.gravSelecionada = gravSelecionada;
		cpi.mudaValorGravidade(this.gravSelecionada); //Altera a gravidade no painel de informações
	}
	public void setTempoTotal(double tempoTotal) {
		this.tempoTotal = tempoTotal;
		cpi.mudaValorTempo(this.tempoTotal); //Altualiza o valor do tempo no painel de informações
	}
}