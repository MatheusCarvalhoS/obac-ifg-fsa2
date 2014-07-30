package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelFormulas;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;
import br.edu.ifg.formosa.obac.visao.VisaoPainelSimulacao;

public class ControleObjeto1Plano implements ControleObjeto0Generico, Runnable{
	
	//Constantes
	private final int atrasoMS = 20;//Atraso da thread usado no Sleep (20 milisegundos)
	private final double atrasoSPadrao = 0.4;//Valor do tempo que � incrementado a cada nova posi��o
	//Vari�veis
	private double atrasoS = 0.4;//Valor do tempo que � usado nos c�lculos
	private boolean continuar = true;///Vari�vel usada para pausar a simula��o
	//Vari�veis do OBAC
	//-----Modelos
	private ModeloAmbiente ma = null;
	//-----Vis�es
	private VisaoPainelSimulacao vps = null;
	private VisaoPainelFormulas vpf = null;
	//-----Controles
	private ControlePainelFormulas cpf = null;
	private ControleFormulasObjeto cfo = null;
	private ControleFormulasSuperficie cfs = null;
	
	
	//Controle______________________________
	public ControleObjeto1Plano(ModeloAmbiente ma, VisaoPainelSimulacao vps,
								VisaoPainelFormulas vpf, ControlePainelFormulas cpf,
								ControleFormulasObjeto cfo, ControleFormulasSuperficie cfs) {
		
	}
	
	@Override
	public void run() {}
	@Override
	public void continuar() {}
	@Override
	public void pausar() {}
	@Override
	public void parar() {}
	
}
