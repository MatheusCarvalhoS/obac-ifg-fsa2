package br.edu.ifg.formosa.obac.controle.propulsao;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleInicioSimulacoes;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPropulsao;

public class ControleMolaListeners implements MouseListener, MouseMotionListener{

	private VisaoPropulsao vP = null;
	private ModeloAmbiente mA = null;
	private ControleOBAC cOBAC = null;
	private ControleMolaMouse cMM = null;
	private ControleInicioSimulacoes cIS = null;
	//Booleana de controle - S� ativa o retorno da mola se ela foi arrastada na �rea permitida
	private boolean restricaoHorizontal = false;
	private boolean restricaoVetical = false;
	
	public ControleMolaListeners(VisaoPropulsao vP, ModeloAmbiente mA, ControleOBAC cOBAC,
								ControleMolaMouse cMM, ControleInicioSimulacoes cIS)
	{
		this.vP = vP;
		this.mA = mA;
		this.cOBAC = cOBAC;
		this.cMM = cMM;
		this.cIS = cIS;
	}
	
	@Override//--Realiza o puxar e arrastar do objeto, que implica na compress�o da mola
	public void mouseDragged(MouseEvent e) {
		reposicionaObjeto(e.getX(), e.getY());
	}

	@Override//--Rea��o da mola/objeto quando o objeto � solto
	public void mouseReleased(MouseEvent e) {
		//Retorna a mola e o objeto para a posi��o inicial antes de d� in�cio a simula��o
		//-----� necess�rio repintar a mola(do tamanho total at� o incial) e mover o objeto de acordo com a expan��o da mola
		//-----Tamb�m � preciso que confirmar que a soltura da mola ocorreu com a mola comprimida 
		if(restricaoHorizontal && restricaoVetical &&
		   mA.getmP().getModeloMola().getTamanhoMolaAtualPix()<mA.getmP().getModeloMola().getTamanhoMolaTotalPix())
		{	
			//� iniciada a simula��o
			new ControleMolaSoltura(vP, mA, cOBAC, cMM, cIS);
		}
		
	}
	
//M�todo utilizado para reposicionar o objeto e comprimir a mola
//Tamb�m indica quando ocorre a soltura do objeto, dando inicio a simula�ao 
	@SuppressWarnings("static-access")
	private void reposicionaObjeto(int x, int y){
		//Booleanas de controle - Verifica se a posi��o do mouse est� em uma �rea v�lida
			//Confirma se a posi��o em que o mouse est� � permitida no eixo x(30px do epa�o vazio e mais 20px para a mola n�o desaparecer)
			restricaoHorizontal =(
				(x>=(mA.getmP().getPosXProp()+mA.getmP().getModeloMola().getTamanhoMolaMinimoPix())
				&& x<=(mA.getmP().getPosXProp()+mA.getmP().getModeloMola().getTamanhoMolaTotalPix())));
			//Confirma se a posi��o do mouse no eixo Y est� correta
			restricaoVetical=(y>=(mA.getmP().getPosYProp())
					  && y<=(mA.getmP().getPosYProp()+mA.getmO().alturaLargura));
			  
		if(restricaoHorizontal && restricaoVetical){
			//Move o objeto
			mA.getmO().setPosicaoXPx(x);
			//Passa o Tamanho da mola de pixel para metros
			mA.getmP().getModeloMola().setTamanhoMolaAtualPix(x-mA.getmP().getPosXM());
			mA.getmP().getModeloMola().setTamanhoMolaAtualM((UtilidadeConvercoesEscala.convertePixelMetro(mA.getmP().getModeloMola().getTamanhoMolaTotalM(), mA.getmP().getModeloMola().getTamanhoMolaAtualPix(), mA.getmP().getModeloMola().getTamanhoMolaTotalPix())));
			//Altera o tamanho da imagem para ajust�la a compress�o
			vP.setImagemPropulsao(new ImageIcon(mA.getmP().getImagemPropulsao().getImage().getScaledInstance(mA.getmP().getModeloMola().getTamanhoMolaAtualPix(), 30, Image.SCALE_DEFAULT)));
			//Repinta o Painel de Repintar
			cOBAC.repinta();
			
			System.out.println("Velocidade: " +(mA.getmP().getModeloMola().getkAtual()+Math.pow(mA.getmP().getModeloMola().getX(), 2))/mA.getmO().getMassa());
		}
		else{
			//-----� preciso que confirmar que a soltura da mola ocorreu com a mola comprimida
			if (mA.getmP().getModeloMola().getTamanhoMolaAtualPix()<mA.getmP().getModeloMola().getTamanhoMolaTotalPix())
			{
				//A simula��o tamb�m ocorre quando o usu�rio sai da �rea delimitada para a intera��o com a mola 
				new ControleMolaSoltura(vP, mA, cOBAC, cMM, cIS);
			}
		}
	}
	
//Trocar valores dos booleans
	public void trocaValorBooleans(boolean valorNovo){
		restricaoHorizontal = valorNovo;
		restricaoVetical = valorNovo;
	}

//M�todos dos listeners que n�o ser�o usados
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	
}
