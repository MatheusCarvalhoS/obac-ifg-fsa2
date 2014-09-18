package br.edu.ifg.formosa.obac.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;

public class VisaoPropulsao extends JPanel {
	//Constantes
	//--Long
	private static final long serialVersionUID = 1L;
	
	//Vari�veis
	//--Modelos das propuls�es
	private ModeloAmbiente mA = null;
	//--Imagem exibida
	private ImageIcon imagemPropulsao = null;
	//--VisaoPainelConfiguracao
	
	//--Partes Deslisante
	private JPanel pCompressor = null;
	private JSlider deslizanteMola = null;
	private JLabel rotuloCompressao = null;
	
	//Metodos
	//--Construtor
	public VisaoPropulsao(ModeloAmbiente mA, VisaoPainelConfiguracao vPC) {
		super(null);
		
		this.setSize(750, 600);
		this.setOpaque(true);
		
		this.mA = mA;
		
		//Deslizante da mola
			//Painel
			pCompressor = new JPanel(null);
			pCompressor.setBounds(30, 565, 690, 30);
			pCompressor.setBackground(Color.white);
			pCompressor.setBorder(new LineBorder(Color.black));
			pCompressor.setVisible(true);
			pCompressor.setOpaque(true);
			this.add(pCompressor);
			//Rotulo
			rotuloCompressao = new JLabel("100", JLabel.RIGHT);
			rotuloCompressao.setSize(100, 30);
			rotuloCompressao.setFont(new Font(null, 0, 30));
			rotuloCompressao.setForeground(Color.BLACK);
			pCompressor.add(rotuloCompressao);
			//Deslizante
			deslizanteMola = new JSlider(JSlider.HORIZONTAL, 30, 100, 100);
			deslizanteMola.setBounds(100, 1, 589, 28);
			pCompressor.add(deslizanteMola);
			this.repaint();
		//Fim Deslizante da mola
		
		imagemPropulsao = mA.getmP().getImagemPropulsao();
	}
	
	//--Paint
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;		

		g2d.translate(700, 520);
		g2d.rotate(Math.toRadians(mA.anguloInclinacaoGraus));
		g2d.translate(-700, -520);
		
		g2d.drawImage(imagemPropulsao.getImage(), mA.getmP().getPosXProp(), mA.getmP().getPosYProp(), this);
	}

	//Get e Set
	public ImageIcon getImagemPropulsao(){return imagemPropulsao;}
	public void setImagemPropulsao(ImageIcon imagemPropulsao){this.imagemPropulsao = imagemPropulsao;}

	public JPanel getpCompressor() {return pCompressor;}
	public JSlider getDeslizanteMola() {return deslizanteMola;}
	public JLabel getRotuloCompressao() {return rotuloCompressao;}
	
}