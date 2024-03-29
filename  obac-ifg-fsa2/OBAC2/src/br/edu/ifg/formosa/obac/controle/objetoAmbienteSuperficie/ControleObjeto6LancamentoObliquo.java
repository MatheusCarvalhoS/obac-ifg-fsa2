package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.simulacao.ControleSimulacao;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeArredondamento;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControleObjeto6LancamentoObliquo implements ControleObjeto0Generico, Runnable{
	
	public ControleObjeto6LancamentoObliquo() {}
	
	//Constantes
	//--Int
	private final int atrasoMS = 20; //Atraso da thread usado no Sleep (20 milisegundos)
	//--Double
	private final double atrasoSPadrao = 0.04;//Valor do tempo que � incrementado a cada nova posi��o
	//Vari�veis
	//--Boolean
	private boolean continuar = true; //Vari�vel usada para pausar a simula��o
	//--Threads
	private Thread t = null; //Thread
	//Vari�veis do OBAC
	//-----Modelos
	private ModeloAmbiente ma = null;
	//-----Vis�es
	private VisaoPainelFormulas vpf = null;
	//-----Controles
	private ControleOBAC cOBAC = null;
	private ControleFormulasObjeto cfo = null;
	
		//Controle______________________________
		public ControleObjeto6LancamentoObliquo(ModeloAmbiente ma,VisaoPainelFormulas vpf, ControleOBAC cOBAC,
									ControleFormulasObjeto cfo, ControleFormulasSuperficie cfs)
		{
			//Passagem das refetrencias
				this.ma = ma;
				this.cOBAC = cOBAC;
				this.vpf = vpf;
				this.cfo = cfo;
			
			//C�clculos referentes a esta simula��o
			ma.getmP().getmC().tempoTotal();
			ma.getmP().getmC().alcanceMaximo();
			ma.getmO().setPosFinalXMetros(ma.getmP().getmC().getAlcanceMaximo());
			ma.getmP().getmC().alturaMaxima();
			cfs.calculaEscalaLO();
			ma.setTempoAtual(0);
			cOBAC.repinta();
			
			//Repinta o painel de f�rmulas
			vpf.repaint();
			
			//In�cio da thread
			t = new Thread(this);
			t.start();
		}
		
		//Rodar
		@Override
		public void run() 
		{
			//La�o de repeti��o para a executar a movimenta��o do objeto
			while (true) {
				if (continuar) {
					if (cfo.paradaLO()) {
						ma.getmP().getmC().novoX();
						ma.getmP().getmC().novoY();
						
						//Atualizar pixels  
						ma.getmO().setPosicaoXPx(
								UtilidadeConvercoesEscala.metroParaPixelH(ma.getmEH(), ma.getmO().getPosicaoXMetros()) - 18);
						
						ma.getmO().setPosicaoYPx(
								UtilidadeConvercoesEscala.metroParaPixelV(ma.getmEV(), ma.getmO().getPosicaoYMetros()) - 30);
												
						cOBAC.repinta();
						
						//Atualiza o tempo
						ma.setTempoAtual(ma.getTempoAtual() + atrasoSPadrao);
						
					} else {
						parar();
					}
				}
				//Parada no carregamento para dar o realismo da simula��o
				//Esta ocorre no final para possibilitar a pausa da simula��o
					try {	t.sleep(atrasoMS);	}
					catch (InterruptedException e) {System.err.println("Erro na Thread!");}
					
					cOBAC.repinta();
			}			
		}
		
		//Continuar
		@Override
		public void continuar() {continuar=true;}
		
		//Pausar
		@Override
		public void pausar() {continuar=false;}
		
		//Parar
		@Override
		@SuppressWarnings("deprecation")
		public void parar() {cOBAC.repinta(); pausar();	t.interrupt(); 	t.stop();}
}
