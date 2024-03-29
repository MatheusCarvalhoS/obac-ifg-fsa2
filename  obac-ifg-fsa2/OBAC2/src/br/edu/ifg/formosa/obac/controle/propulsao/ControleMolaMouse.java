package br.edu.ifg.formosa.obac.controle.propulsao;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleInicioSimulacoes;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPropulsao;

public class ControleMolaMouse {
	
	//Refer�ncias usadas durante o uso da mola
	private ControleOBAC cOBAC;
	private final VisaoPropulsao vp;
	private final VisaoPainelConfiguracao vpc;
	private final ModeloAmbiente ma;
	private ControleInicioSimulacoes cIS = null;
	
//	//Booleana de controle - S� ativa o retorno da mola se ela foi arrastada na �rea permitida
	private boolean restricaoHorizontal = false;
	private boolean restricaoVetical = false;
	//Taxa de ajuste - Ajusta o objeto no eixo Y
	private int ajusteV = 0;
	
	//Mouse Listener
	private ControleMolaListeners cML = null;
	//-----Rea��o da mola/objeto quando o objeto � solto
	private final MouseListener mListener = new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent arg0) {
			super.mouseReleased(arg0);
			
			//Retorna a mola e o objeto para a posi��o inicial antes de d� in�cio a simula��o
			//-----� necess�rio repintar a mola(do tamanho total at� o incial) e mover o objeto de acordo com a expan��o da mola
			//-----Tamb�m � preciso que confirmar que a soltura da mola ocorreu com a mola comprimida 
			if(restricaoHorizontal && restricaoVetical &&
			   ma.getmP().getModeloMola().getTamanhoMolaAtualPix()<ma.getmP().getModeloMola().getTamanhoMolaTotalPix())
			{	
				//� iniciada a simula��o
				new ControleMolaSoltura(vp, ma, cOBAC, /*ControleMolaMouse.this,*/ cIS);
			}
		}
	};
	
	
	
	//Mouse Motion Listener
	//-----Realiza o puxar e arrastar do objeto, que implica na compress�o da mola 
	private final MouseMotionListener mMotionListener = new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) {
			//Corre��o no eixo Y
//			corrigeAreaPegavel();
			reposicionaObjeto(e.getX(), e.getY());
		}
	};
	
	//M�todo usado para ralizar os ajustes nas vari�veis que referen-se a �rea peg�vel da mola
	public void corrigeAreaPegavel(){
		if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==0
			||vpc.getCsAmbienteSimulacao().getSelectedIndex()==3){//Plano ou P&P 
			ajusteV = 0;
		}
		else if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==1){//Subida
			ajusteV = -25;
		}
		else if(vpc.getCsAmbienteSimulacao().getSelectedIndex()==2){//Descida
			ajusteV = 20;
		}
	}
	
//	//M�todo utilizado para reposicionar o objeto e comprimir a mola
//	//Tamb�m indica quando ocorre a soltura do objeto, dando inicio a simula�ao 
	@SuppressWarnings("static-access")
	private void reposicionaObjeto(int x, int y){
		//Booleanas de controle - Verifica se a posi��o do mouse est� em uma �rea v�lida
			//Confirma se a posi��o em que o mouse est� � permitida no eixo x(30px do epa�o vazio e mais 20px para a mola n�o desaparecer)
			restricaoHorizontal =(
				(x>=(ma.getmP().getPosXProp()+ma.getmP().getModeloMola().getTamanhoMolaMinimoPix())
				&& x<=(ma.getmP().getPosXProp()+ma.getmP().getModeloMola().getTamanhoMolaTotalPix())));
			//Confirma se a posi��o do mouse no eixo Y est� correta
			restricaoVetical=(y>=(ma.getmP().getPosYProp())
					  && y<=(ma.getmP().getPosYProp()+ma.getmO().alturaLargura));
			  
		if(restricaoHorizontal && restricaoVetical){
			//Move o objeto
			ma.getmO().setPosicaoXPx(x);
			//Passa o Tamanho da mola de pixel para metros
			ma.getmP().getModeloMola().setTamanhoMolaAtualPix(x-ma.getmP().getPosXM());
			ma.getmP().getModeloMola().setTamanhoMolaAtualM((UtilidadeConvercoesEscala.convertePixelMetro(ma.getmP().getModeloMola().getTamanhoMolaTotalM(), ma.getmP().getModeloMola().getTamanhoMolaAtualPix(), ma.getmP().getModeloMola().getTamanhoMolaTotalPix())));
			//Altera o tamanho da imagem para ajust�la a compress�o
			vp.setImagemPropulsao(new ImageIcon(ma.getmP().getImagemPropulsao().getImage().getScaledInstance(ma.getmP().getModeloMola().getTamanhoMolaAtualPix(), 30, Image.SCALE_DEFAULT)));
			//Repinta o Painel de Repintar
			cOBAC.repinta();
			
			System.out.println("Velocidade: " +(ma.getmP().getModeloMola().getkAtual()+Math.pow(ma.getmP().getModeloMola().getX(), 2))/ma.getmO().getMassa());
		}
		else{
			//-----� preciso que confirmar que a soltura da mola ocorreu com a mola comprimida
			if (ma.getmP().getModeloMola().getTamanhoMolaAtualPix()<ma.getmP().getModeloMola().getTamanhoMolaTotalPix())
			{
				//A simula��o tamb�m ocorre quando o usu�rio sai da �rea delimitada para a intera��o com a mola 
				new ControleMolaSoltura(vp, ma, cOBAC, /*ControleMolaMouse.this,*/ cIS);
			}
		}
	}
	
	//Construtor
		public ControleMolaMouse(ControleOBAC cOBAC, VisaoPropulsao vp,
								VisaoPainelConfiguracao vpc, ModeloAmbiente ma,
								ControleInicioSimulacoes cIS)
		{
			this.cOBAC = cOBAC;
			this.vp = vp;
			this.vpc = vpc;
			this.ma = ma;
			this.cIS = cIS;
		}

		
	//"Getter" e "Setter" das a��es do mouse
		//Adi��o dos mouse*Listeners
		public void ativaMolaMouse(){
			cML = new ControleMolaListeners(vp, ma, cOBAC, this, cIS);
			vp.addMouseListener(cML);
			vp.addMouseMotionListener(cML);
			//vp.addMouseMotionListener(mMotionListener);
//			vp.addMouseListener(mListener);
		}
		//Remo��o dos mouse*Listeners
		public void desativaMolaMouse(){ 
			cML.trocaValorBooleans(false);
			vp.removeMouseListener(cML);
			vp.removeMouseMotionListener(cML);
			cML = null;
			vp.removeMouseMotionListener(mMotionListener);
			vp.removeMouseListener(mListener);
			//E cancelamento da �rea legal da mola (Para previnir falhas)
//			restricaoHorizontal = false;
//			restricaoVetical = false;
		}
	//Get do ajuste feito para comprimir a mola
		public int getAjusteVertica(){return ajusteV;}
}
