package br.edu.ifg.formosa.obac.visao;

import javax.swing.JPanel;

import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;

public class VisaoPainelSimulacao extends JPanel {
	//Constantes
	//--Long
	private static final long serialVersionUID = 1L;
	
	//Variaveis
	//--Superficie
	private VisaoSuperficie vS = null;
	//--Objeto
	private VisaoObjeto vO = null;
	//--Propulsao
	private VisaoPropulsao vP = null;
	//--Obst�culo
	private VisaoObstaculo vObs = null;
	//--Escala
	private VisaoEscala vEH = null; //Visao Escala Horizontal - Utilizada em: Plano, Subida, Descida, P&P e Proj�til
	private VisaoEscala vEV = null; //Visao Escala Vertical - Utilizada em: Queda e Proj�til
	
	//Metodos
	//--Construtor
	public VisaoPainelSimulacao(ModeloAmbiente mA, VisaoPainelConfiguracao vPC) {
		super(null);
		
		this.setSize(750, 600);
		
		vS = new VisaoSuperficie(vPC, mA);
		vO = new VisaoObjeto(mA);
		vP = new VisaoPropulsao(mA);
		vObs = new VisaoObstaculo(mA);
		vEH = new VisaoEscala(mA.getmEH(), mA.getmO(), mA.getmP());
		vEV = new VisaoEscala(mA.getmEV(), mA.getmO(), mA.getmP());
		
		this.add(vObs);
		this.add(vEH);
		this.add(vEV);
		this.add(vP);
		this.add(vO);
		this.add(vS);
		
		vEV.setVisible(false); //A Escala Vertical s� � utilizada na Queda e no Proj�til
	}
	
	//--Getters
	public VisaoObjeto getVisaoObjeto(){return vO;}
	public VisaoPropulsao getVisaoPropulsao(){return vP;}
	public VisaoObstaculo getVisaoObstaculo() {return vObs;}
	public VisaoEscala getVisaoEscalaH() {return vEH;}
	public VisaoEscala getVisaoEscalaV() {return vEV;}
	public VisaoSuperficie getVisaoSuperficie() {return vS;}
}