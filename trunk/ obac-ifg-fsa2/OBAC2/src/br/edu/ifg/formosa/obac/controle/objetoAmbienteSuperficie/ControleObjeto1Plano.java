package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControleObjeto1Plano implements ControleObjeto0Generico, Runnable{	
	//Constantes
		private final int atrasoMS = 20;//Atraso da thread usado no Sleep (20 milisegundos)
		private final double atrasoSPadrao = 0.04;//Valor do tempo que � incrementado a cada nova posi��o
	//Vari�veis
		private boolean continuar = true;///Vari�vel usada para pausar a simula��o
		private Thread t = null;//Thread
	//Vari�veis do OBAC
		//-----Modelos
		private ModeloAmbiente ma = null;
		//-----Vis�es
		private VisaoPainelFormulas vpf = null;
		//-----Controles
		private ControleOBAC cOBAC = null;
		private ControleFormulasObjeto cfo = null;
	
	
	//Construtor______________________________
	public ControleObjeto1Plano(ModeloAmbiente ma,VisaoPainelFormulas vpf, 
								ControleOBAC cOBAC,
								ControleFormulasObjeto cfo, ControleFormulasSuperficie cfs)
	{
		//Passagem das refetrencias
			this.ma = ma;
			this.cOBAC = cOBAC;
			this.vpf = vpf;
			this.cfo = cfo;
		//C�clculos referentes a esta simula��o
			cfo.calculaForcaNormal();//For�a normal
			cfs.calculaForcaAtritoPadrao();//For�a de Atrito
			cfo.calculaAceleracaoPlano();//Acelera��o
			cfo.calculaPosFinalPadrao();//Posi��o final em Metros
			cfs.calculaEscalaPadrao();//Escala
			ma.getmO().setPosFinalXPix(130+UtilidadeConvercoesEscala.converteMetroEmPixelX(ma.getmEH().getComprimentoEscalaPX(), ma.getmO().getPosFinalXMetros(), ma.getmEH().getEscalaFimXM()));//Ponto fina em Pixel
			cfo.calculaTempo();//Tempo total de Simula��o em segundos
			ma.setTempoAtual(0);//Seta o tempo inicial na vari�vel
			//Repinta o painel de f�rmulas
			vpf.repaint();
		
		//In�cio da thread
		t = new Thread(this);
		t.start();
	}
	
	//Rodar
	@Override
	public void run() {
		
		//La�o de repeti��o para a executar a movimenta��o do objeto
		while (true) {
			if (continuar==true) {
				if (cfo.paradaPlano()==false) {
					
					//Calcula nova posi��o em METROS
					cfo.calculaNovaPosicao();
					//Converte a posi��o em METROS para PIXEL para poder movimentar o objeto
					ma.getmO().setPosicaoXPx(130 +UtilidadeConvercoesEscala.converteMetroEmPixelX(ma.getmEH().getComprimentoEscalaPX(),ma.getmO().getPosicaoXMetros(),ma.getmEH().getEscalaFimXM()));					
					//Repinta o painel para mostar o andamento da simula��o
					cOBAC.repinta();
					//Repinta o painel de f�rmulas
					vpf.repaint();
					//Atualiza o tempo
					ma.setTempoAtual(ma.getTempoAtual()+atrasoSPadrao);
					
					cfo.calculaVelocidadeTorricelli(ma.getmO().getPosicaoXMetros());
				}
				else {parar();}
			}
			//Parada no carregamento para dar o realismo da simula��o
			//Esta ocorre no final para possibilitar a pausa da simula��o
				try {	t.sleep(atrasoMS);	}
				catch (InterruptedException e) {System.err.println("Erro na Thread!");}
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
