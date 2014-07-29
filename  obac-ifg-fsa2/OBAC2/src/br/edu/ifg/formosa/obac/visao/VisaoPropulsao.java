package br.edu.ifg.formosa.obac.visao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloMola;

public class VisaoPropulsao extends JPanel {
	//Constantes
	//--Long
	private static final long serialVersionUID = 1L;
	
	//Vari�veis
	//--Modelos das propuls�es
	private ModeloAmbiente mA = null;
	//--Imagem exibida
	private ImageIcon imagemPropulsao = null;
	
	//Metodos
	//--Construtor
	public VisaoPropulsao(ModeloAmbiente mA) {
		super(null);
		
		this.setSize(750, 600);
		this.setOpaque(true);
		
		this.mA = mA;
		imagemPropulsao = mA.getmM().getImagemMola();
	}
	
	//--Paint
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;		

		g2d.translate(mA.getmM().getPosX(), mA.getmM().getPosY());
		g2d.rotate(Math.toRadians(mA.anguloInclinacaoGraus));
		g2d.translate(-mA.getmM().getPosX(), -mA.getmM().getPosY());
		
		g2d.drawImage(imagemPropulsao.getImage(), mA.getmM().getPosX(), mA.getmM().getPosY(), this);
		
		g2d.setColor(Color.red);
		g2d.drawRect(mA.getmM().getPosX(),
					mA.getmM().getPosY(),
					100,
					mA.getmO().alturaLargura);
	}

	//Get e Set
	public ImageIcon getImagemPropulsao(){return imagemPropulsao;}
	public void setImagemPropulsao(ImageIcon imagemPropulsao){this.imagemPropulsao = imagemPropulsao;}
}