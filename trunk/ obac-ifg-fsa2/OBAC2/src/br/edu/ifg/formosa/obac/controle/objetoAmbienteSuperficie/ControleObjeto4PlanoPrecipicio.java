package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloEscala;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControleObjeto4PlanoPrecipicio implements ControleObjeto0Generico, Runnable{	
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
	public ControleObjeto4PlanoPrecipicio(ModeloAmbiente ma,VisaoPainelFormulas vpf, 
								ControleOBAC cOBAC,
								ControleFormulasObjeto cfo, ControleFormulasSuperficie cfs)
	{
		//Passagem das refetrencias
			this.ma = ma;
			this.cOBAC = cOBAC;
			this.vpf = vpf;
			this.cfo = cfo;
			
		//C�clculos referentes a parte plana desta simula��o
			cfo.calculaForcaNormal();//For�a normal
			cfs.calculaForcaAtritoPadrao();//For�a de Atrito
			cfo.calculaAceleracaoPlano();//Acelera��o
			cfo.calculaPosFinalPadrao();//Posi��o final em Metros
			cfs.calculaEscalaPadrao();//Escala
			cfs.calculaEscalaVerticalPadrao();//Escala Vertical - 1000m
			ma.getmO().setPosFinalXPix(130+UtilidadeConvercoesEscala.converteMetroEmPixelX(ma.getmEH().getComprimentoEscalaPX(), ma.getmO().getPosFinalXMetros(), ma.getmEH().getEscalaFimXM()));//Ponto final em Pixel
			cfo.calculaTempo();//Tempo total de Simula��o em segundos
			ma.setTempoAtual(0);//Seta o tempo inicial na vari�vel
			//Repinta o painel de f�rmulas
			vpf.repaint();
		
			ma.getmO().setPosicaoYMetros(ma.getmEV().getMarcadoresEscala()[ModeloEscala.qtdMarcadores]);
			
		//In�cio da thread
		t = new Thread(this);
		t.start();
	}
	
	//Rodar
	@Override
	public void run() {
		
		//La�o de repeti��o para a executar a movimenta��o do objeto
		while (true) {
			if (continuar) {
				if (cfo.paradaPlanoPrecipicio()==false) {
					//Movimento na parte do plano
					if(ma.getmO().getPosicaoXPx()<=329){
						//Calcula nova posi��o em METROS
						cfo.calculaNovaPosicao();
						
						/*System.out.println("\nX em Metros: " + ma.getmO().getPosicaoXMetros());
						System.out.println("Y em Metros: " + ma.getmO().getPosicaoYMetros());
						System.out.println("X em pixels: " + ma.getmO().getPosicaoXPx());
						System.out.println("---X em pixels Final: " + ma.getmO().getPosFinalXPix());
						System.out.println("Y em pixels: " + ma.getmO().getPosicaoYPx());*/
						//Converte a posi��o em METROS para PIXEL para poder movimentar o objeto
						ma.getmO().setPosicaoXPx(UtilidadeConvercoesEscala.metroParaPixelH(ma.getmEH(), ma.getmO().getPosicaoXMetros()));
						//Atualiza o tempo
						ma.setTempoAtual(ma.getTempoAtual()+atrasoSPadrao);
					}
					// Movimento na parte do precip�cio
					else{
						ma.getmP().getmC().novoX();
						ma.getmO().setPosicaoYMetros(ma.getmO().getPosicaoYMetros() - 
													 ma.getmP().getmC().novoYPEP());
						
						/*System.out.println("\nX em Metros: " + ma.getmO().getPosicaoXMetros());
						System.out.println("Y em Metros: " + ma.getmO().getPosicaoYMetros());
						System.out.println("X em pixels: " + ma.getmO().getPosicaoXPx());
						System.out.println("Y em pixels: " + ma.getmO().getPosicaoYPx());*/
						
						//Atualizar pixels  
						ma.getmO().setPosicaoXPx(
								UtilidadeConvercoesEscala.metroParaPixelH(ma.getmEH(), ma.getmO().getPosicaoXMetros()));
						
						ma.getmO().setPosicaoYPx(
								UtilidadeConvercoesEscala.metroParaPixelV(ma.getmEV(), ma.getmO().getPosicaoYMetros()) + 30);
						
						try {	
							t.sleep(atrasoMS);	
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						cOBAC.repinta();
						
						//Atualiza o tempo
						ma.setTempoAtual(ma.getTempoAtual() + atrasoSPadrao);						
					}
					//Comandos abaixo ficam fora das condi��es pois pertencem a ambas
						//Repinta o painel para mostar o andamento da simula��o
						cOBAC.repinta();
						//Repinta o painel de f�rmulas
						vpf.repaint();
					//Atualiza a velocidade
						cfo.calculaVelocidadeTorricelli();
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

