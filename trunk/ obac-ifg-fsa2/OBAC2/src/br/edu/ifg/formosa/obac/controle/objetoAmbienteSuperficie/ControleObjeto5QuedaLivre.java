package br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControleObjeto5QuedaLivre implements ControleObjeto0Generico, Runnable{

	//CONSTANTES
	private final int atrasoMS = 20;//Atraso da thread usado no Sleep (20 milisegundos)
	private final double atrasoSPadrao = 0.04;//Valor do tempo que � incrementado a cada nova posi��o
	private boolean continuar = true;///Vari�vel usada para pausar a simula��o
	private boolean subida = false;
	private boolean primeiraQueda = true;
	private double soma = 0.0;
	private double tempoLocal = 0.0;
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

		cfo.calculaForcaNormal();
		cfs.calculaForcaAtritoQueda();
		cfo.calculaAceleracaoQueda();
		cfs.calculaEscalaVerticalPadrao();
		
		this.primeiraQueda=true;
		
		//Repinta o painel de f�rmulas
		vpf.repaint();
		
		escala = ma.getmEV().getEscalaFimYM()/ 470;
		
		System.out.println("Escala: "+escala);
		System.out.println("Coeficiente de Restitui��o: "+ma.getmO().getCoefRestituicao());
		System.out.println("Fim da escala Pixel: "+ma.getmEV().getEscalaFimYPix());
//		ma.getmO().setPosicaoYMetros(1000);Desnecess�rio isso
//		ma.getmO().setPosicaoInicialYM(1000);E isso tamb�m
		
		//In�cio da thread
		t = new Thread(this);
		t.start();

		System.out.println("Acelera��o: " +ma.getmO().getAceleracao());


		/*objectControl.calculaNormal(); --
		surfaceControl.calculaAtrito(); --
		objectControl.calculaAceleracaoFall(); --
		surfaceControl.calculaEscalaFall(); --
		objectControl.calculaCoefRestituicao(e); achei inutil rsrs*/
	}

	@Override
	public void run() {
		while(true){
			
			if(continuar){
				//Acelera��o>0 = Queda no final (bateu)
				if(ma.getmO().getAceleracao()>0 && bateu()){
					System.out.println("Here");
					ma.getmO().setVelocidadeInicial(ma.getmO().getCoefRestituicao()*ma.getmO().getVelocidade());//V0=V*CoefR.
					ma.getmO().setPosicaoInicialYM(0);
					ma.getmO().setPosicaoYMetros(0);
					acoesInversas();
					
					cfo.calculaNovaPosicaoY(0);//Subida
					ma.getmO().setPosicaoYMetros(ma.getmO().getPosicaoYMetros() * (-1));
					
					if(velocidadeZero())break;
				}
				//Acelera��o<0 = Subida no final (caiu de novo)
				if(ma.getmO().getAceleracao()<0 && velocidadeZero()){
					System.out.println("There");
					ma.getmO().setVelocidadeInicial(0);//V0=0
					ma.getmO().setPosicaoInicialYM(ma.getmO().getPosicaoYMetros());
					acoesInversas();
				}
				
				//Movimenta��o do objeto
				ma.setTempoAtual(ma.getTempoAtual()+atrasoSPadrao);
				ma.getmO().setVelocidade(ma.getmO().getVelocidadeInicial()+ma.getmO().getAceleracao()*ma.getTempoAtual());
					
//				cfo.calculaVelocidadeTorricelli(ma.getmO().getPosicaoYMetros());
				if(!subida){
					cfo.calculaNovaPosicaoY(1000);//Queda
				}else	{
					cfo.calculaNovaPosicaoY(0);//Subida
					ma.getmO().setPosicaoYMetros(ma.getmO().getPosicaoYMetros() * (-1));
				}
				
				//Parada no carregamento para dar o realismo da simula��o
				//Esta ocorre no final para possibilitar a pausa da simula��o
					try {	t.sleep(atrasoMS);	}
					catch (InterruptedException e) {System.err.println("Erro na Thread!");}
			}
				//Repinta o painel para mostar o andamento da simula��o
				ma.getmO().setPosicaoYPx(UtilidadeConvercoesEscala.metroParaPixelV(ma.getmEV(), ma.getmO().getPosicaoYMetros())-30);
				//__> Aqui est� subitraindo 30px pois os mesmos foram retirados da escala
				//____Por hora corrigiu o impacto com o solo, mas a parte da decida continua falha
				//____COm o coeficiente em 1 a simula��o funciona bem, exceto por um glitche no momento da queda
				
				
				
				cOBAC.repinta();
				vpf.repaint();
				//Repinta o painel de f�rmulas
				vpf.repaint();
		}
	}
	
	//M�todos privados
	//---Verifica o momento do impacto do objeto
	private boolean bateu(){
		if(ma.getmO().getPosicaoYPx()>(UtilidadeConvercoesEscala.metroParaPixelV(ma.getmEV(), 0) - 30)){
			System.out.println("Hitted");
			return true;
		}
		return false;
	}
	//---Verifica o ponto m�ximo da subida (ou a aprada total do objeto) 
	private boolean velocidadeZero(){
		if(ma.getmO().getVelocidade()<=0){
			System.out.println("Zero");
			return true;
		}
		return false;
	}
	//---Usado tanto na subida quanto na descida para quando realizam a��es comuns nos momentos
	//-----em que as partes da simula��o v�o se inverter de queda para impacto e vice-versa
	//-----deve ser chamado ap�s  o set da nova velocidade
	private void acoesInversas(){
		System.out.println("Change");
		ma.getmO().setAceleracao(ma.getmO().getAceleracao()*(-1));//A=-A
		ma.getmO().setVelocidade(ma.getmO().getVelocidadeInicial());//V=V0
		ma.setTempoTotal(ma.getTempoTotal()+ma.getTempoAtual());//TempoT+=TempoA
		ma.setTempoAtual(0);//TempoA=0
		subida=!subida;
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

