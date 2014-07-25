package br.edu.ifg.formosa.obac.controle.propulsao;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoObjeto;
import br.edu.ifg.formosa.obac.visao.VisaoPropulsao;

public class ControleMolaMouse {
	
	//Refer�ncias usadas durante o uso da mola
	private ControleOBAC cOBAC;
	private final VisaoPropulsao vp;
	private final VisaoObjeto vo;
	private final ModeloAmbiente ma;
	
	//Booleana de controle - S� ativa o retorno da mola se ela foi arrastada na �rea permitida
	private boolean areaDaMola = false;
	
	//Mouse Listener
	//-----Rea��o da mola/objeto quando o objeto � solto
	private final MouseListener mListener =new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent arg0) {
			super.mouseReleased(arg0);
			
			//Retorna a mola e o objeto para a posi��o inicial antes de d� in�cio a simula��o
			//-----� necess�rio repintar a mola(do tamanho total at� o incial) e mover o objeto de acordo com a expan��o da mola
			//-----Tamb�m � preciso que confirmar que a soltura da mola ocorreu com a mola comprimida 
			if(areaDaMola && ma.getmM().getTamanhoMolaAtualPix()<ma.getmM().getTamanhoMolaTotalPix()){
				new ControleMolaSoltura(vo, vp, ma, cOBAC, ControleMolaMouse.this);
			}
						
			//� iniciada a simula��o
		}
	};
		
	//Mouse Motion Listener
	//-----Realiza o puxar e arrastar do objeto, que implica na compress�o da mola 
	private final MouseMotionListener mMotionListener = new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) {
			reposicionaObjeto(e.getX(), e.getY());
		}
	};
	
	//M�todo utilizado para reposicionar o objeto e comprimir a mola
	//Tamb�m indica quando ocorre a soltura do objeto, dando inicio a simula�ao 
	private void reposicionaObjeto(int x, int y){
		//Booleana de controle - Verifica se a posi��o do mouse est� em uma �rea v�lida
		areaDaMola =
			((x>=55 && x<=130)//Confirma se a posi��o em que o mouse est� � permitida no eixo x(30px do epa�o vazio e mais 20px para a mola n�o desaparecer)
			 &&(y>=470 && y<=500));//Confirma se a posi��o do mouse no eixo Y est� correta
			  
		if(areaDaMola){
			//Move o objeto
			ma.getmO().setPosicaoXPx(x);
			//Passa o Tamanho da mola de pixel para metros
			ma.getmM().setTamanhoMolaAtualPix(x-30);
			ma.getmM().setTamanhoMolaTotalM((UtilidadeConvercoesEscala.convertePixelMetro(ma.getmM().getTamanhoMolaTotalM(), ma.getmM().getTamanhoMolaAtualPix(), ma.getmM().getTamanhoMolaTotalPix())));
			//Altera o tamanho da imagem para ajust�la a compress�o
			vp.setImagemPropulsao(new ImageIcon(ma.getmM().getImagemMola().getImage().getScaledInstance(ma.getmM().getTamanhoMolaAtualPix(), 30, Image.SCALE_DEFAULT)));
			
			//Repinta o Painel de Repintar
			cOBAC.repinta();
		}
		else{
			//-----� preciso que confirmar que a soltura da mola ocorreu com a mola comprimida
			if (ma.getmM().getTamanhoMolaAtualPix()<ma.getmM().getTamanhoMolaTotalPix()) {
				//A simula��o tamb�m ocorre quando o usu�rio sai da �rea delimitada para a intera��o com a mola 
				new ControleMolaSoltura(vo, vp, ma, cOBAC, this);
			}
		}
	}
	
	//Construtor
		public ControleMolaMouse(ControleOBAC cOBAC, VisaoPropulsao vp, VisaoObjeto vo,
								ModeloAmbiente ma)
		{
			this.cOBAC = cOBAC;
			this.vp = vp;
			this.vo = vo;
			this.ma = ma;
		}

		
	//"Getter" e "Setter" das a��es do mouse
		//Adi��o dos mouse*Listeners
		public void ativaMolaMouse(){
			vp.addMouseMotionListener(mMotionListener);
			vp.addMouseListener(mListener);
		}
		//Remo��o dos mouse*Listeners
		public void desativaMolaMouse(){
			vp.removeMouseMotionListener(mMotionListener);
			vp.removeMouseListener(mListener);
			areaDaMola = false;//E cancelamento da �rea legal da mola (Para previnir falhas)
		}
}
