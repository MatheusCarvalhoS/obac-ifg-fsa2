package br.edu.ifg.formosa.obac.visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.ifg.formosa.obac.utilidades.UtilidadeRotuloTransparente;

public class VisaoPainelInformacao extends JPanel{
	
	//Serial
	private static final long serialVersionUID = 1L;
	
	//Primeira Linha---------------------------------------
		//Gravidade
		private UtilidadeRotuloTransparente rGravidade;
		private UtilidadeRotuloTransparente rGravidadeValor;
		//Coeficiente de Atrito
		private UtilidadeRotuloTransparente rCoefAtrito;
		private UtilidadeRotuloTransparente rCoefAtritoValor;
		//Acelera��o
		private UtilidadeRotuloTransparente rAceleracao;
		private UtilidadeRotuloTransparente rAceleracaoValor;
		//Posi��o Final no Eixo X
		private UtilidadeRotuloTransparente rPosFinalX;
		private UtilidadeRotuloTransparente rPosFinalXValor;
		//Posi��o Final no Eixo Y
		private UtilidadeRotuloTransparente rPosFinalY;
		private UtilidadeRotuloTransparente rPosFinalYValor;
	//Segunda Linha----------------------------------------
		//For�a Normal
		private UtilidadeRotuloTransparente rForcaNormal;
		private UtilidadeRotuloTransparente rForcaNormalValor;
		//For�a de Atrito
		private UtilidadeRotuloTransparente rForcaAtrito;
		private UtilidadeRotuloTransparente rForcaAtritoValor;
		//Velocidade
		private UtilidadeRotuloTransparente rVelocidade;
		private UtilidadeRotuloTransparente rVelocidadeValor;
		//Posi��o no Eixo X
		private UtilidadeRotuloTransparente rPosAtualEixoX;
		private UtilidadeRotuloTransparente rPosAtualEixoXValor;
		//Posi��o no Eixo Y
		private UtilidadeRotuloTransparente rPosAtualEixoY;
		private UtilidadeRotuloTransparente rPosAtualEixoYValor;
	//Terceira Linha---------------------------------------
		//Tempo
		private UtilidadeRotuloTransparente rTempo;
		private UtilidadeRotuloTransparente rTempoValor;
		//Mola Taxa de Deforma��o
		private UtilidadeRotuloTransparente rX;
		private UtilidadeRotuloTransparente rXValor;
		//Mola Constante El�stica
		private UtilidadeRotuloTransparente rK;
		private UtilidadeRotuloTransparente rKValor;
		//Canh�o Energia
		private UtilidadeRotuloTransparente rJ;
		private UtilidadeRotuloTransparente rJValor;
		//Canh�o �ngulo
		private UtilidadeRotuloTransparente rA;
		private UtilidadeRotuloTransparente rAValor;
		//Velocidade P�s Colis�o
		private UtilidadeRotuloTransparente rVelocidadePosColisao;
		private UtilidadeRotuloTransparente rVelocidadePosColisaoValor;
		
	//Layout
		private GridBagLayout layout = null;
		private GridBagConstraints gbc = null;
	
	public VisaoPainelInformacao() {
		this.setOpaque(false);
		this.setSize(750, 60);
		this.setBackground(Color.DARK_GRAY);
		
		//Layout
		layout = new GridBagLayout();
		this.setLayout(layout);//O painel passa a utilizar o layout autom�tico
		
		//Vari�vel respons�vel pelo posicionamento e aloca��o dos componentes
		gbc = new GridBagConstraints();
			gbc.insets = new Insets(2, 10, 0, 0);
			gbc.weighty = 1;
			this.gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx =10;
			gbc.ipady = 20;
			//Configura gridwidth e gridheight (quadros ocupados nos eixos x e y)
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
		
		//R�tulo
			//Primeira Linha---------------------------------------
				//Gravidade
				rGravidade = new UtilidadeRotuloTransparente("Gravidade:");
					rGravidade.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rGravidade, 1, 0);
				//Coeficiente de Atrito
				rCoefAtrito = new UtilidadeRotuloTransparente("Coef. de Atrito:");
					rCoefAtrito.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rCoefAtrito, 1, 2);
				//Acelerea��o
				rAceleracao = new UtilidadeRotuloTransparente("Acelera��o:");
					rAceleracao.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rAceleracao, 1, 4);
				//Posi��o Final no Eixo X
				rPosFinalX = new UtilidadeRotuloTransparente("Pos. Final:");
					rPosFinalX.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rPosFinalX, 1, 6);
				//Posi��o Final no Eixo X
				rPosFinalY = new UtilidadeRotuloTransparente("Pos. Final:");
					rPosFinalY.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rPosFinalY, 1, 8);
			//Segunda Linha---------------------------------------
				//For�a de Atrito
				rForcaAtrito = new UtilidadeRotuloTransparente("For�a de Atrito:");
					rForcaAtrito.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rForcaAtrito, 2, 0);
				//For�a Normal
				rForcaNormal = new UtilidadeRotuloTransparente("For�a Normal:");
					rForcaNormal.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rForcaNormal, 2, 2);
				//Velocidade
				rVelocidade = new UtilidadeRotuloTransparente("Velocidade:");
					rVelocidade.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rVelocidade, 2, 4);
				//Posi��o no Eixo X
				rPosAtualEixoX = new UtilidadeRotuloTransparente("Pos. Atual Eixo X:");
					rPosAtualEixoX.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rPosAtualEixoX, 2, 6);
				//Posi��o no Eixo Y
				rPosAtualEixoY = new UtilidadeRotuloTransparente("Pos. Atual Eixo Y:");
					rPosAtualEixoY.setHorizontalAlignment(JLabel.RIGHT);
					addComponent(rPosAtualEixoY, 2, 8);
			//Terceira Linha---------------------------------------
				//Tempo
				rTempo = new UtilidadeRotuloTransparente("Tempo:");
					rTempo.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rTempo, 3, 0);
				//Mola Taxa de Deforma��o
				rX = new UtilidadeRotuloTransparente("Taxa de Deforma��o:");
					rX.setHorizontalAlignment(JLabel.RIGHT);
					rX.setVisible(false);
				addComponent(rX, 3, 2);
				//Mola Constante El�stica
				rK = new UtilidadeRotuloTransparente("Const. El�stica:");
					rK.setHorizontalAlignment(JLabel.RIGHT);
					rK.setVisible(false);
				addComponent(rK, 3, 4);
				//Canh�o Energia
				rJ = new UtilidadeRotuloTransparente("Energia:");
					rJ.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rJ, 3, 2);
				//Canh�o �ngulo
				rA = new UtilidadeRotuloTransparente("�ngulo:");
					rA.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rA, 3, 4);
				//Velocidade P�s Colis�o
				rVelocidadePosColisao = new UtilidadeRotuloTransparente("V. P�s Colis�o:");
					rVelocidadePosColisao.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rVelocidadePosColisao, 3, 6);
			
		//Altera��o nos valores de espa�amento dos componentes
			gbc.insets = new Insets(2, 5, 0, 10);
			
		//Valores
			//Primeira Linha---------------------------------------
				//Gravidade	
				rGravidadeValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rGravidadeValor, 1, 1);
				//Coeficiente de Atrito
				rCoefAtritoValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rCoefAtritoValor, 1, 3);
				//Acelerea��o
				rAceleracaoValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rAceleracaoValor, 1, 5);
				//Posi��o Final no eixo X
				rPosFinalXValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rPosFinalXValor, 1, 7);
				//Posi��o Final no eixo Y
				rPosFinalYValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rPosFinalYValor, 1, 9);
			//Primeira Linha---------------------------------------
				//For�a Normal
				rForcaNormalValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rForcaNormalValor, 2, 1);
				//For�a de Atrito
				rForcaAtritoValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rForcaAtritoValor, 2, 3);
				//Velocidade
				rVelocidadeValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rVelocidadeValor, 2, 5);
				//Posi��o no Eixo X
				rPosAtualEixoXValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rPosAtualEixoXValor, 2, 7);
				//Posi��o no Eixo Y
				rPosAtualEixoYValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rPosAtualEixoYValor, 2, 9);
			//Terceira Linha---------------------------------------
				//Tempo
				rTempoValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rTempoValor, 3, 1);
				//Mola Taxa de Deforma��o
				rXValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rXValor, 3, 3);
				//Mola Constante El�stica
				rKValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rKValor, 3, 5);
				//Canh�o Energia
				rJValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rJValor, 3, 3);
				//Canh�o �ngulo
				rAValor = new UtilidadeRotuloTransparente("0.0");
				addComponent(rAValor, 3, 5);
				//Velocidade P�s Colis�o
				rVelocidadePosColisaoValor = new UtilidadeRotuloTransparente("0.0");
					rVelocidadePosColisaoValor.setHorizontalAlignment(JLabel.RIGHT);
				addComponent(rVelocidadePosColisaoValor, 3, 7);
	}
	
	//Posiciona os componentes
	private void addComponent(Component c, int linha, int coluna){
		//Configura gridX e gridY
			gbc.gridx = coluna;
			gbc.gridy = linha;
		//Configura restri��es e adiciona componentes
			this.add(c, gbc);
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        Color ppColor = new Color(0, 0, 0, 115); //r,g,b,alpha
        g.setColor(ppColor);
        g.fillRect(0,0,750,60); //x,y,width,height
    }

	//Getters
	public UtilidadeRotuloTransparente getrPosFinalXValor() {return rPosFinalXValor;}
	public UtilidadeRotuloTransparente getrPosFinalYValor() {return rPosFinalYValor;}
	public UtilidadeRotuloTransparente getrGravidadeValor() {return rGravidadeValor;}
	public UtilidadeRotuloTransparente getrCoefAtritoValor() {return rCoefAtritoValor;}
	public UtilidadeRotuloTransparente getrAceleracaoValor() {return rAceleracaoValor;}
	public UtilidadeRotuloTransparente getrForcaNormalValor() {return rForcaNormalValor;}
	public UtilidadeRotuloTransparente getrForcaAtritoValor() {return rForcaAtritoValor;}
	public UtilidadeRotuloTransparente getrVelocidadeValor() {return rVelocidadeValor;}
	public UtilidadeRotuloTransparente getrPosAtualEixoXValor() {return rPosAtualEixoXValor;}
	public UtilidadeRotuloTransparente getrPosAtualEixoYValor() {return rPosAtualEixoYValor;}
	public UtilidadeRotuloTransparente getrTempoValor() {return rTempoValor;}
	public UtilidadeRotuloTransparente getrXValor() {return rXValor;}
	public UtilidadeRotuloTransparente getrKValor() {return rKValor;}
	public UtilidadeRotuloTransparente getrJValor() {return rJValor;}
	public UtilidadeRotuloTransparente getrAValor() {return rAValor;}
	public UtilidadeRotuloTransparente getrVelocidadePosColisaoColisao(){return rVelocidadePosColisaoValor;}
	
	//Setters especiais
	public void setVisivelMola(){
		//Tona as vari�veis relacionadas a MOLA vis�vel
		rK.setVisible(true);
		rKValor.setVisible(true);
		rX.setVisible(true);
		rXValor.setVisible(true);
		//Tona as vari�veis relacionadas ao CANH�O invis�veis
		rJ.setVisible(false);
		rJValor.setVisible(false);
		rA.setVisible(false);
		rAValor.setVisible(false);
	}
	
	public void setVisivelCanhao(){
		//Tona as vari�veis relacionadas a CANH�O vis�vel
		rJ.setVisible(true);
		rJValor.setVisible(true);
		rA.setVisible(true);
		rAValor.setVisible(true);
		//Tona as vari�veis relacionadas a MOLA invis�veis
		rK.setVisible(false);
		rKValor.setVisible(false);
		rX.setVisible(false);
		rXValor.setVisible(false);
	}
	
	public void setVisivelColisao(boolean colisao){
		rVelocidadePosColisao.setVisible(colisao);
		rVelocidadePosColisaoValor.setVisible(colisao);
	}
}