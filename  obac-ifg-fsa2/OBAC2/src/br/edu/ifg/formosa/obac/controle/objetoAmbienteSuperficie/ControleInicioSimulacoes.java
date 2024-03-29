package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelFormulas;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControleInicioSimulacoes {
	
	//Refer�ncias necess�rias
	//-----Modelos
	private ModeloAmbiente mA = null;
	//-----Vis�es
	private VisaoPainelConfiguracao vPC = null;
	private VisaoPainelFormulas vPF = null;
	//-----Controles
	private ControleOBAC cOBAC = null;
	private ControleFormulasObjeto cfo = null;
	private ControleFormulasSuperficie cfs = null;
	private ControlePainelFormulas cpf = null;
	private ControleObjeto0Generico cObjeto = null;
	
	public ControleInicioSimulacoes(ModeloAmbiente mA, VisaoPainelConfiguracao vPC,
					VisaoPainelFormulas vPF,
					ControleOBAC cOBAC, ControleFormulasObjeto cfo,
					ControleFormulasSuperficie cfs, ControlePainelFormulas cpf)
	{
		//Passagem das refer�ncias
		this.mA = mA;
		this.vPC = vPC;
		this.vPF = vPF;
		this.cOBAC = cOBAC;
		this.cfo = cfo;
		this.cfs = cfs;
		this.cpf = cpf;
		
	}
	
	
	public void iniciarSimulacao(){
		//Mudan�a no painel de f�rmulas
		cpf.alteraTipoPainel();
		//Bot�o Nova Simula��o vis�vel
		vPC.getBaNovaSimulacao().setVisible(true);
		cOBAC.getPainelAbas().setSelectedIndex(1);
		
		//Plano
		if(vPC.getCsAmbienteSimulacao().getSelectedIndex()==0){cObjeto=new ControleObjeto1Plano(mA, vPF, cOBAC, cfo, cfs);}
		//Subida
		else if(vPC.getCsAmbienteSimulacao().getSelectedIndex()==1){cObjeto=new ControleObjeto2Subida(mA, vPF, cOBAC, cfo, cfs);}
		//Descida
		else if(vPC.getCsAmbienteSimulacao().getSelectedIndex()==2){cObjeto=new ControleObjeto3Descida(mA, vPF, cOBAC, cfo, cfs);}
		//Plano e Precip�cio
		else if(vPC.getCsAmbienteSimulacao().getSelectedIndex()==3){cObjeto=new ControleObjeto4PlanoPrecipicio(mA, vPF, cOBAC, cfo, cfs);}
		//Lan�amento Obl�quo
 		else if(vPC.getCsAmbienteSimulacao().getSelectedIndex()==4){cObjeto=new ControleObjeto6LancamentoObliquo(mA, vPF, cOBAC, cfo, cfs);}
		//Queda Livre
		else if(vPC.getCsAmbienteSimulacao().getSelectedIndex()==5){cObjeto=new ControleObjeto5QuedaLivre(mA, vPF, cOBAC, cfo, cfs);}
	}
	
	public ControleObjeto0Generico getCObjeto(){return cObjeto;}
}
