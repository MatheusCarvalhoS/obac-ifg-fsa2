package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.simulacao.ControleSimulacao;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloEscala;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControleObjeto4PlanoPrecipicio implements ControleObjeto0Generico, Runnable{	
	//Constantes
	private final int atrasoMS = 20;//Atraso da thread usado no Sleep (20 milisegundos)
	private final double atrasoSPadrao = 0.04;//Valor do tempo que � incrementado a cada nova posi��o
	
	//Vari�veis
	private boolean iniciouPrecipicio = false;
	private boolean continuar = true;///Vari�vel usada para pausar a simula��o
	private double posInicialXQueda = 0;
	private double tempoLocal = 0;
	private double incrementoQueda = 0;
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
			ControleFormulasObjeto cfo, ControleFormulasSuperficie cfs
			)
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

		ControleSimulacao.mudaMarcadores(ma.getmEH(), 2000);
		ma.getmO().setPosicaoYMetros(ma.getmEV().getMarcadoresEscala()[ModeloEscala.qtdMarcadores]);

		//In�cio da thread
		t = new Thread(this);
		t.start();
	}

	//Rodar
	private boolean torriceli = false;
	@Override
	public void run() {

		//La�o de repeti��o para a executar a movimenta��o do objeto
		while (true) {
			if (continuar) {
				if (!cfo.paradaPlanoPrecipicio()) {
					//Movimento na parte do plano
					if(ma.getmO().getPosicaoXPx()<=329){
						if(torriceli)
						//Atualiza a velocidade
						cfo.calculaVelocidadeTorricelli();
						//Calcula nova posi��o em METROS
						cfo.calculaNovaPosicao();
						//Converte a posi��o em METROS para PIXEL para poder movimentar o objeto
						ma.getmO().setPosicaoXPx(UtilidadeConvercoesEscala.metroParaPixelH(ma.getmEH(), ma.getmO().getPosicaoXMetros()));
						//Atualiza o tempo
						ma.setTempoAtual(ma.getTempoAtual()+atrasoSPadrao);
						torriceli = true;
					}
					// Movimento na parte do precip�cio
					else{
						if(!iniciouPrecipicio){
							ma.getmO().setPosicaoXPx(331);
							ma.getmO().setPosicaoYMetros(0);
							ma.getmO().setVelocidadeY(0);
							ma.getmO().setVelocidadeInicial(ma.getmO().getVelocidade());
							ma.setTempoTotal(ma.getTempoAtual());
							ma.setTempoAtual(0);
							
							double metro = UtilidadeConvercoesEscala.pixelParaMetroH(ma.getmEH(), 202);
							tempoLocal = metro / ma.getmO().getVelocidadeInicial();
									
							iniciouPrecipicio = true;
						}						
						
						ma.getmO().setPosicaoXMetros(ma.getmO().getVelocidadeInicial() * tempoLocal);
						
						ma.getmO().setVelocidadeY(ma.getGravSelecionada() * ma.getTempoAtual());
						ma.getmO().setPosicaoYMetros(ma.getmO().getVelocidadeY() * ma.getTempoAtual() * 0.5);
						
						
						ma.getmO().setPosicaoXPx(UtilidadeConvercoesEscala.metroParaPixelH(ma.getmEH(), ma.getmO().getPosicaoXMetros()));
						ma.getmO().setPosicaoYPx(UtilidadeConvercoesEscala.metroParaPixelV(ma.getmEV(), ma.getmO().getPosicaoYMetros()));
						
						cOBAC.repinta();
						
						//Atualiza o tempo
						ma.setTempoAtual(ma.getTempoAtual() + atrasoSPadrao);
						tempoLocal += atrasoSPadrao;
						System.out.println("Tempo: " + tempoLocal);
					}
					//Comandos abaixo ficam fora das condi��es pois pertencem a ambas
					//Repinta o painel para mostar o andamento da simula��o
					cOBAC.repinta();
					//Repinta o painel de f�rmulas
					vpf.repaint();
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

