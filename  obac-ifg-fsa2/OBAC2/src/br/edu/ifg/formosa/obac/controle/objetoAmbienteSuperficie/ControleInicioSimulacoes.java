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
	private VisaoPainelConfiguracao vpc = null;
	private VisaoPainelFormulas vPF = null;
	//-----Controles
	private ControleOBAC cOBAC = null;
	private ControleFormulasObjeto cfo = null;
	private ControleFormulasSuperficie cfs = null;
	private ControlePainelFormulas cpf = null;
	
	public ControleInicioSimulacoes(ModeloAmbiente mA, VisaoPainelConfiguracao vpc,
					VisaoPainelFormulas vPF,
					ControleOBAC cOBAC, ControleFormulasObjeto cfo,
					ControleFormulasSuperficie cfs, ControlePainelFormulas cpf)
	{
		//Passagem das refer�ncias
		this.mA = mA;
		this.vpc = vpc;
		this.vPF = vPF;
		this.cOBAC = cOBAC;
		this.cfo = cfo;
		this.cfs = cfs;
		this.cpf = cpf;
		
	}
	
	
	public void iniciarSimulacao(){
		//Mudan�a no painel de f�rmulas
//		cpf.alteraTipoPainel();
		
		//Plano
		if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==0){
			System.out.println("Passou");
			new ControleObjeto1Plano(mA, vPF, cOBAC, cfo, cfs);
		}
		//Subida
//		else if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==1){new ControleObjeto2Subida();}
		//Descida
//		else if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==2){new ControleObjeto3Descida();}
		//Queda Livre
//		else if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==3){new ControleObjeto4PlanoPrecipicio();}
		//Plano e Precip�cio
//		else if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==4){new ControleObjeto5QuedaLivre();}
		//Lan�amento Obl�quo
//		else if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==5){new ControleObjeto6LancamentoObliquo();}
	}
	
}
