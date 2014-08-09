package br.edu.ifg.formosa.obac.modelo;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelFormulas;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelInformacao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ModeloPropulsao {

	//Vari�veis
	//--URL
	private URL urlProp = this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/obac/imagens/propulsaoObstaculo/");
	//--Imagem
	private ImageIcon imagemPropulsao = null;
	//--Int
	private int translaX = 30; //Vari�veis utilizadas para rotacionar o canh�o
	private int translaY = 470;
	//Posi��o da propuls�o - pixels
	private int posX = 30;
	private int posY = 470;
	
	//--Modelos
	private ModeloMola mM = null;
	private ModeloCanhao mC = null;
	
	//M�todos
	//--Construtor
	public ModeloPropulsao(ModeloAmbiente ma, ControlePainelInformacao cpi,
						   ControlePainelFormulas cpf, VisaoPainelFormulas vpf)
	{
		mM = new ModeloMola(ma, cpi, cpf, vpf);
		mC = new ModeloCanhao(ma, cpi, cpf, vpf);
		trocaImagemProp(true);
	}
	//--Troca de Imagens
	public void trocaImagemProp(boolean canhao){
		if (canhao) {//Imagem do canh�o
			posY=460;
			imagemPropulsao = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/obac/imagens/propulsaoObstaculo/canhao.png"));
			imagemPropulsao = new ImageIcon(imagemPropulsao.getImage().getScaledInstance(100, 40, Image.SCALE_DEFAULT));
		}else{//Imagem da mola
			posY=470;
			imagemPropulsao = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/obac/imagens/propulsaoObstaculo/mola100px.png"));
		}
	}
	
	//--Getters
	public ModeloMola getModeloMola(){return mM;}
	public ModeloCanhao getmC() {return mC;}
	public ImageIcon getImagemPropulsao(){return imagemPropulsao;}
	public int getTranslaX() {return translaX;}
	public int getTranslaY() {return translaY;}
	public int getPosX() {return posX;}
	public int getPosY() {return posY;}
	//--Seters
	public void setPosX(int posX) {this.posX = posX;}
	public void setPosY(int posY) {this.posY = posY;}
}