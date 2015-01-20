package br.edu.ifg.formosa.obac.controle.propulsao;

import java.awt.Image;

import javax.swing.ImageIcon;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleInicioSimulacoes;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPropulsao;

public class ControleMolaSolturaDeslizante implements Runnable{

	private VisaoPropulsao vp = null;
	private ModeloAmbiente ma = null;
	private ControleOBAC cOBAC = null;
//	private ControleMolaMouse cMM = null;
	private ControleInicioSimulacoes cIS =null;
	private Thread t = null;
	
	public ControleMolaSolturaDeslizante(VisaoPropulsao vp,ModeloAmbiente ma,
										 ControleOBAC cOBAC, ControleInicioSimulacoes cIS, VisaoPainelConfiguracao vPC)
	{
		this.vp = vp;
		this.ma = ma;
		this.cOBAC = cOBAC;
		this.cIS = cIS;
		
		//� calculada a velocidade de lan�amento do objeto
		ma.getmP().getModeloMola().calculaVelocidadeLancamento();
		
		//Torna vis�vel o bot�o de nova simula��o
		vPC.getBaNovaSimulacao().setVisible(true);
		t = new Thread(this);
		t.start();
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void run() {
		//Vari�veis usadas no retorno da mola
			double cont = 0.5;
			int atraso = 20;
		//La�o de repeti��o que traz a mola para a posi��o inicial
		for (double i = ma.getmP().getModeloMola().getTamanhoMolaAtualPix();
			i < ma.getmP().getModeloMola().getTamanhoMolaTotalPix();
			i+=cont)
		{
			//Altera o tamanho da imagem para ajust�la a compress�o
			vp.setImagemPropulsao(new ImageIcon(ma.getmP().getImagemPropulsao().getImage().getScaledInstance((int)i, 30, Image.SCALE_DEFAULT)));
			//Corrige o valor na vari�vel do tamanho da mola localizado no modelo
			ma.getmP().getModeloMola().setTamanhoMolaAtualPix((int)i);
			//Move o objeto (valor de i+posi��o inicial do objeto+ 1 pixel para o objeto ficar na frente da mola)
			ma.getmO().setPosicaoXPx((int)i+ma.getmP().getPosXM()+1);
			//Repaint
			cOBAC.repinta();
			//Sleep - pausas no carregamento
			try {t.sleep(atraso);}
			catch (InterruptedException e) {e.printStackTrace();}
			//Incremento do segundo contador para d� a impress�o de acelera��o da mola
			cont+=0.5;
		}
		
		//Corre��o dos valores do tamnho da mola e da posi��o do objeto para evitar o desconforto visual
		//Ocorre apenas quando a imagem n�o est� no tamanho correto
		if(ma.getmP().getModeloMola().getTamanhoMolaAtualPix()<ma.getmP().getModeloMola().getTamanhoMolaTotalPix()){
			//Retorna a mola para a posi��o original (primeiro no modelo depois na imagem)
			ma.getmP().getModeloMola().setTamanhoMolaAtualPix(ma.getmP().getModeloMola().getTamanhoMolaTotalPix());
			vp.setImagemPropulsao(ma.getmP().getImagemPropulsao());
			//Posiciona o Objeto na posi��o original
			ma.getmO().setPosicaoXPx(130);
			//Repaint
			cOBAC.repinta();
		}
		if(ma.getmO().getPosFinalXPix()!=130){
			ma.getmO().setPosicaoXPx(130);
		}
		//Inicia a movimenta��o do objeto
		cIS.iniciarSimulacao();
		//Para esta Thrad
		t.interrupt();
		t.stop();
	}
	
}
