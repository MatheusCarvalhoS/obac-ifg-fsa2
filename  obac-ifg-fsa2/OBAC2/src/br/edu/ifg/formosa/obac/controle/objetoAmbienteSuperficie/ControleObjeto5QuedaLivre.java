package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControleObjeto5QuedaLivre implements ControleObjeto0Generico, Runnable{

	//CONSTANTES
	private final int ATRASO_MS = 20;//Atraso da thread usado no Sleep (20 milisegundos)
	private final double ATRASO_PADRAO = 0.04;//Valor do tempo que � incrementado a cada nova posi��o
	private boolean continuar = true;///Vari�vel usada para pausar a simula��o
	private boolean queda = true;
	private double soma = 0.0;//Posi��o atual em pixeis do objeto, se sua nova posi��o for menor
								//ou igual a �ltima posi��o do objeto
	double escala = 0.0;

	private ModeloAmbiente ma;
	private VisaoPainelFormulas vpf;
	private ControleOBAC cOBAC;
	private ControleFormulasObjeto cfo;
	private ControleFormulasSuperficie cfs;
	//VARI�VEL DA THREAD
	private Thread t;
	
	public ControleObjeto5QuedaLivre(ModeloAmbiente ma, VisaoPainelFormulas vpf, ControleOBAC cOBAC, ControleFormulasObjeto cfo,
			ControleFormulasSuperficie cfs) {

		this.ma = ma;
		this.vpf = vpf;
		this.cOBAC = cOBAC;
		this.cfo = cfo;
		this.cfs = cfs;
		
		ma.getmO().setPosicaoYMetros(1000);
		
		cfo.calculaForcaNormal();
		cfs.calculaForcaAtritoQueda();
		cfo.calculaAceleracaoQueda();
		cfs.calculaEscalaVerticalPadrao();

		//Repinta o painel de f�rmulas
		vpf.repaint();
		
		escala = ma.getmEV().getEscalaFimYM()/ 470;
		
		System.out.println("Escala: "+escala);
		System.out.println("Coeficiente de Restitui��o: "+ma.getmO().getCoefRestituicao());
		System.out.println("Fim da escala Pixel: "+ma.getmEV().getEscalaFimYPix());

		//In�cio da thread
		t = new Thread(this);
		t.start(); 
	}

	@Override
	public void run() {

		while(true){
			
			if(continuar){
				
				double velocidadeFinal1;
				//In�cio do momento de colis�o
				if(ma.getmO().getPosicaoYPx() >= 470){//S� entra aqui quando ocorre a colis�o com o solo
					//Inverte o valor da acelera��o
					if(ma.getmO().getAceleracao() < 0){//Adequa a aceleraa��o para a subida
						ma.getmO().setAceleracao(ma.getmO().getAceleracao() *  (-1));
					}
					
					ma.getmO().setPosicaoYPx(470);//Corrige a posi��o em px
					int velocidadeFinal2 = 0;//= velocidade final ^2
					
					if(soma>=0 && !queda){
						System.out.println("SEGUNDA");
						ma.getmO().setVelocidadeInicial(0);
						cfo.calculaVelocidadeTorricelli(soma * escala);
//						velocidadeFinal2 = (int)(0 + 2 * ma.getmO().getAceleracao() * (soma * escala));
					}
					else{
						System.out.println("Primeiro");
						cfo.calculaVelocidadeTorricelli(ma.getmO().getPosicaoYMetros());
//						velocidadeFinal2 = (int)((ma.getmO().getVelocidadeInicial() * ma.getmO().getVelocidadeInicial()) +2 * ma.getmO().getAceleracao() * (ma.getmEV().getEscalaFimYM()));
						//Varia��o de espa�o
					}
					
					soma = 0;
					
					//F�rmula da velocidade p�s colis�o
					velocidadeFinal1 =
							ma.getmO().getVelocidade()*(ma.getmO().getMassa()- 1000000000 *ma.getmO().getCoefRestituicao())/
							(ma.getmO().getMassa() + 1000000000)*(-1);
					
					if(velocidadeFinal1 <=0)
						continuar = false;
					
					ma.getmO().setVelocidadeInicial(velocidadeFinal1);
					ma.getmO().setAceleracao(ma.getmO().getAceleracao() * (-1));
					ma.setTempoAtual(this.ATRASO_PADRAO);
					queda = false;			
					
				}//Fim do momento de colis�o
				
				if(queda){//Inicio da primeira queda livre
					
					cfo.calculaNovaPosicaoY();
					
					ma.getmO().setPosicaoYPx(599 -
							UtilidadeConvercoesEscala.metroParaPixelV(ma.getmEV(), ma.getmO().getPosicaoYMetros()));
					
				}//Fim da primeira QL
				else{//Movimento de subida e descida depois da primeira queda
					cfo.calculaNovaPosicaoY();
					
                	//novaPosic�o � igual ao solo - posicaoAtualYPixel
                	double novaPosicao = 470 - (ma.getmO().getPosicaoYMetros() / escala);
                	//- (environment.getObjeto().getPosicaoAtualY()/environment.getSurface().getEscala())
	                if(novaPosicao <= (ma.getmO().getPosicaoYPx())){
	                	System.out.println("PY: "+ma.getmO().getPosicaoYMetros());
	                	soma = (ma.getmO().getPosicaoYMetros() / escala);
	                }
	                ma.getmO().setPosicaoYPx((int)novaPosicao);
//	                ma.getmO().setPosicaoYPx(UtilidadeConvercoesEscala.metroParaPixelV(ma.getmEV(), ma.getmO().getPosicaoYMetros()));
	                
				}//Fim do movimento de subida e descida depois da primeira queda
				
				//Repinta o painel para mostar o andamento da simula��o
				cOBAC.repinta();
				//Repinta o painel de f�rmulas
				vpf.repaint();
				
				//Atualiza o tempo
				ma.setTempoAtual(ma.getTempoAtual()+ATRASO_PADRAO);
			}
			//Parada no carregamento para dar o realismo da simula��o
			//Esta ocorre no final para possibilitar a pausa da simula��o
				try {	t.sleep(ATRASO_MS);	}
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
	public void parar() {pausar();	t.interrupt(); 	t.stop();}

}

