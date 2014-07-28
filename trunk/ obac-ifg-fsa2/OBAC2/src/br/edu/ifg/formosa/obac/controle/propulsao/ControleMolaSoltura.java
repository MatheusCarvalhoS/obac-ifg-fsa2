package br.edu.ifg.formosa.obac.controle.propulsao;

import java.awt.Image;

import javax.swing.ImageIcon;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.visao.VisaoObjeto;
import br.edu.ifg.formosa.obac.visao.VisaoPropulsao;

public class ControleMolaSoltura implements Runnable{
	
	private VisaoObjeto vo = null;
	private VisaoPropulsao vp = null;
	private ModeloAmbiente ma = null;
	private ControleOBAC cOBAC = null;
	private Thread t = null;
	
	public ControleMolaSoltura(VisaoObjeto vo, VisaoPropulsao vp, ModeloAmbiente ma, ControleOBAC cOBAC, ControleMolaMouse cmm) {
		this.vo = vo;
		this.vp = vp;
		this.ma = ma;
		this.cOBAC = cOBAC;
		
		//Remo��o dos mouse*listeners
//		cmm.desativaMolaMouse();
		
		t = new Thread(this);
		t.start();
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void run() {
		//� calculada a taxa de deforma��o e a velocidade de lan�amento do objeto
		ma.getmM().calculaX();
//		ma.getmP().velocidadeLancamento();
		//###Retornar com a velocidade assim que o controle principal da simula��o estiver funcionando
		
		//Vari�veis usadas no retorno da mola
			double cont = 0.5;
			int atraso = 20;
		//La�o de repeti��o que traz a mola para a posi��o inicial
		for (double i = ma.getmM().getTamanhoMolaAtualPix();
			i < ma.getmM().getTamanhoMolaTotalPix();
			i+=cont)
		{
			//Altera o tamanho da imagem para ajust�la a compress�o
			vp.setImagemPropulsao(new ImageIcon(ma.getmM().getImagemMola().getImage().getScaledInstance((int)i, 30, Image.SCALE_DEFAULT)));
			//Corrige o valor na vari�vel do tamanho da mola localizado no modelo
			ma.getmM().setTamanhoMolaAtualPix((int)i);
			//Move o objeto (valor de i+posi��o inicial do objeto+ 1 pixel para o objeto ficar na frente da mola)
			ma.getmO().setPosicaoXPx((int)i+ma.getmM().getPosX()+1);
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
		if(ma.getmM().getTamanhoMolaAtualPix()<ma.getmM().getTamanhoMolaTotalPix()){
			//Retorna a mola para a posi��o original (primeiro no modelo depois na imagem)
			ma.getmM().setTamanhoMolaAtualPix(ma.getmM().getTamanhoMolaTotalPix());
			vp.setImagemPropulsao(ma.getmM().getImagemMola());
			//Posiciona o Objeto na posi��o original
			ma.getmO().setPosicaoXPx(130);
			//Repaint
			cOBAC.repinta();
		}
			
		//Para esta Thrad
		t.stop();
	}
	
}
