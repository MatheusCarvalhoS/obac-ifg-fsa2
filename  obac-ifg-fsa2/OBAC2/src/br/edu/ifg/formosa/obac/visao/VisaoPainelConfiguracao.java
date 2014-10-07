package br.edu.ifg.formosa.obac.visao;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeMascaraNumerica;

public class VisaoPainelConfiguracao extends JPanel{

	private static final long serialVersionUID = 1L;
	
	//Paineis
		private JPanel pPropulsao = null;
		private JPanel pAmbiente = null;
		private JPanel pObjeto = null;
		private JPanel pObjetoDeslizante = null;
		private JPanel pObstaculo = null;
	//R�tulos
		//T�tulos dos paineis
		private JLabel rTituloPropulsao = null;
		private JLabel rTituloAmbiente = null;
		private JLabel rTituloObjeto = null;
		private JLabel rTituloColisao = null;
		//R�tulos do painel de Propuls�o
		private JLabel rPropulsaoDado1 = null;//Pode exibir tr�s textos diferentes em sua configura��o
		private JLabel rPropulsaoDado2 = null;//Pode exibir dois textos distintos
		//R�tulos do painel do Ambiente
		private JLabel rAmbienteDadoSimulacao = null;
		private JLabel rAmbienteDadoAtrito = null;
		private JLabel rAmbienteDadoGravidade = null;
		//R�tulos do painel do Objeto
		private JLabel rObjetoDadoMassa = null;
		private JLabel rObjetoDadoCoeficienteRestituicao = null;
		private JLabel rObjetoAtualCoefRest = null;
		//R�tulos dos painel de Colis�o
		private JLabel rColisaoObstaculo = null;
	//Caixas de Texto
		//Propuls�o
		private JFormattedTextField ctPropulsaoDado1 = null;
		private JFormattedTextField ctPropulsaoDado2 = null;
		//Objeto
		private JFormattedTextField ctObjetoMassa = null;
	//Caixas de Sele��o
		//Propuls�o
		private JComboBox<String> csPropulsao = null;
		//Ambiente
		private JComboBox<String> csAmbienteSimulacao = null;
		private JComboBox<String> csAmbienteAtrito = null;
		private JComboBox<String> csAmbienteGravidade = null;
	//Bot�es de Op��o
		private JRadioButton boColisaoSim = null;
		private JRadioButton boColisaoNao = null;
	//Grupo de Botoes
		private ButtonGroup gbColisaoSN = null;
	//Botoes de A��es
		private JButton baIniciaPausar = null;
		private JButton baNovaSimulacao = null;
	//Deslizante
		private JSlider dObjetoCoeficienteRestituicao = null;
	//Fonte
		private Font fonte = null;
	//Borda
		private LineBorder bordaCT = null;
	//Formata numero digitado - Formata��o para os n�meros nas Caixas de Texto
		private DefaultFormatterFactory fnd = null;
		
	//Modelo painel de configura��o
		private ModeloPainelConfiguracao mpc = null;
	
	//Construtor
	public VisaoPainelConfiguracao(ModeloPainelConfiguracao mpc) {
		super(null);
		this.setSize(250, 600);
		this.setBackground(Color.DARK_GRAY);
		
		//Modelo painel de configura��o
			this.mpc = mpc;
		
		//Fonte da maioria dos textos
			fonte = new Font("Arial", Font.BOLD, 13);
		
		//Borda
		bordaCT = new LineBorder(Color.LIGHT_GRAY, 1);
		
		//Formata N�mero Digitado
		fnd = new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("###0.##")));
		
		//T�tulo do painel principal
			
		//Configura��o dos paineis internos
		configPainelPropulsao();
		configPainelAmbiente();
		configPainelObjeto();
		configPainelColisao();
		
		//Bot�es de a��es
			//Inicia/Pausa
			baIniciaPausar = new JButton(mpc.getBotaoIniciar());
				baIniciaPausar.setFont(fonte);
				baIniciaPausar.setBounds(30, 510, 190, 22);
			this.add(baIniciaPausar);
			//Nova simula��o
			baNovaSimulacao = new JButton("Nova Simula��o");
				baNovaSimulacao.setFont(fonte);
				baNovaSimulacao.setBounds(30, 537, 190, 22);
				baNovaSimulacao.setVisible(false);
			this.add(baNovaSimulacao);
		
		this.repaint();
	}
	
	//Respons�vel pela cria��o do subpainel relacionado as configura��es da PROPULS�O
	private void configPainelPropulsao(){
		//T�tulo
			rTituloPropulsao = new JLabel("Propuls�o");
				configRotulos(rTituloPropulsao, 10, 5, 230, 20);
			this.add(rTituloPropulsao);
		//Painel
			pPropulsao = new JPanel(null);
				pPropulsao.setBackground(Color.GRAY);
				pPropulsao.setBounds(10, 25, 230, 110);
			this.add(pPropulsao);
		//Caixas de sele��o
			csPropulsao = new JComboBox<>(mpc.getPropulsoes());
				csPropulsao.setBounds(10, 5, 210, 20);
				csPropulsao.setFont(fonte);
			pPropulsao.add(csPropulsao);
		//R�tulos
		//Dado 1
			//R�tulo
			rPropulsaoDado1 = new JLabel(mpc.getDado1Canhao());
				configRotulos(rPropulsaoDado1, 10, 25, 210, 20);
			pPropulsao.add(rPropulsaoDado1);
			//Caixas de Texto
			ctPropulsaoDado1 = new JFormattedTextField();
				configCaixasTexto(ctPropulsaoDado1, 10, 45, 210, 20);
				ctPropulsaoDado1.setText("0");
				ctPropulsaoDado1.setEnabled(false);
			pPropulsao.add(ctPropulsaoDado1);
		//Dado 2
			//R�tulo
			rPropulsaoDado2 = new JLabel(mpc.getDado2Canhao());
				configRotulos(rPropulsaoDado2, 10, 65, 210, 20);
			pPropulsao.add(rPropulsaoDado2);
			//Caixas de Texto
			ctPropulsaoDado2 = new JFormattedTextField();
				configCaixasTexto(ctPropulsaoDado2, 10, 85, 210, 20);
			pPropulsao.add(ctPropulsaoDado2);
	}
	
	//Respons�vel pela cria��o do subpainel relacionado as configura��es de AMBIENTE
	private void configPainelAmbiente(){
		//T�tulo
			rTituloAmbiente = new JLabel("Ambiente");
				configRotulos(rTituloAmbiente, 10, 145, 230, 20);
			this.add(rTituloAmbiente);
		//Painel
			pAmbiente = new JPanel(null);
				pAmbiente.setBackground(Color.GRAY);
				pAmbiente.setBounds(10, 165, 230, 125);
			this.add(pAmbiente);
		//Simula��o
			//R�tulo
			rAmbienteDadoSimulacao = new JLabel("Simula��o");
				configRotulos(rAmbienteDadoSimulacao, 10, 0, 210, 20);
			pAmbiente.add(rAmbienteDadoSimulacao);
			//Caixa de Sele��o
			csAmbienteSimulacao = new JComboBox<>(mpc.getSimulacoesPadrao());
				csAmbienteSimulacao.insertItemAt(mpc.getLancamentoObliquo(), csAmbienteSimulacao.getItemCount());
				csAmbienteSimulacao.setBounds(10, 20, 210, 20);
				csAmbienteSimulacao.setFont(fonte);
			pAmbiente.add(csAmbienteSimulacao);
		//Atrito
			//R�tulo
			rAmbienteDadoAtrito = new JLabel("Superf�cie");
				configRotulos(rAmbienteDadoAtrito, 10, 40, 210, 20);
			pAmbiente.add(rAmbienteDadoAtrito);
			//Caixa de Sele��o
			csAmbienteAtrito = new JComboBox<>(mpc.getAtritos());
				csAmbienteAtrito.setBounds(10, 60, 210, 20);
				csAmbienteAtrito.setFont(fonte);
			pAmbiente.add(csAmbienteAtrito);
		//Gravidade
			//R�tulo
			rAmbienteDadoGravidade = new JLabel("Gravidade");
				configRotulos(rAmbienteDadoGravidade, 10, 80, 210, 20);
			pAmbiente.add(rAmbienteDadoGravidade);
			//Caixa de Sele��o
			csAmbienteGravidade = new JComboBox<>(mpc.getGravidade());
				csAmbienteGravidade.setBounds(10, 100, 210, 20);
				csAmbienteGravidade.setFont(fonte);
			pAmbiente.add(csAmbienteGravidade);
	}
	
	//Respons�vel pela cria��o do subpainel relacionado as configura��es do OBJETO
	private void configPainelObjeto(){
		//T�tulo
			rTituloObjeto = new JLabel("Objeto");
				configRotulos(rTituloObjeto, 10, 300, 230, 20);
			this.add(rTituloObjeto);
		//Painel
			pObjeto = new JPanel(null);
				pObjeto.setBackground(Color.GRAY);
				pObjeto.setBounds(10, 320, 230, 100);
			this.add(pObjeto);
		//Massa
			//R�tulo
			rObjetoDadoMassa = new JLabel("Massa (g)");
				configRotulos(rObjetoDadoMassa, 10, 0, 210, 20);
			pObjeto.add(rObjetoDadoMassa);
			//Caixa de texto
			ctObjetoMassa = new JFormattedTextField();
				configCaixasTexto(ctObjetoMassa, 10, 20, 210, 20);
			pObjeto.add(ctObjetoMassa);
		//Coeficiente de Restitui��o
			//R�tulo
			rObjetoDadoCoeficienteRestituicao = new JLabel("Coeficiente de restitui��o");
				configRotulos(rObjetoDadoCoeficienteRestituicao, 10, 40, 210, 20);
			pObjeto.add(rObjetoDadoCoeficienteRestituicao);
			//Painel Interno
			pObjetoDeslizante = new JPanel(null);
				pObjetoDeslizante.setBackground(Color.WHITE);
				pObjetoDeslizante.setBounds(10, 60, 210, 35);
			pObjeto.add(pObjetoDeslizante);
			//Deslizante
			dObjetoCoeficienteRestituicao = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
				dObjetoCoeficienteRestituicao.setBackground(Color.WHITE);
				dObjetoCoeficienteRestituicao.setBounds(0, 0, 210, 20);
				dObjetoCoeficienteRestituicao.setMajorTickSpacing(25);
				dObjetoCoeficienteRestituicao.setEnabled(false);
			pObjetoDeslizante.add(dObjetoCoeficienteRestituicao);
			//Rotulo do deslizante
			rObjetoAtualCoefRest = new JLabel("0.0");
				configRotulos(rObjetoAtualCoefRest, 10, 15, 200, 20);
				rObjetoAtualCoefRest.setForeground(Color.BLACK);
			pObjetoDeslizante.add(rObjetoAtualCoefRest);
	}
	
	//Respons�vel pela cria��o do subpainel relacionado as configura��es da COLIS�O
	private void configPainelColisao(){
		//T�tulo
//			rTituloColisao = new JLabel("Colis�o");
//				configRotulos(rTituloColisao, 10, 430, 230, 20);
//			this.add(rTituloColisao);
		//Painel
//			pObstaculo = new JPanel(null);
//				pObstaculo.setBackground(Color.GRAY);
//				pObstaculo.setBounds(10, 450, 230, 45);
//			this.add(pObstaculo);
		//R�tulo
//			rColisaoObstaculo = new JLabel("Deseja colocar um obstaculo?");
//				configRotulos(rColisaoObstaculo, 10, 0, 210, 20);
//			pObstaculo.add(rColisaoObstaculo);
		//Bot�es de Op��o
			//Sim
			boColisaoSim = new JRadioButton("Sim");
//				boColisaoSim.setFont(fonte);
//				boColisaoSim.setForeground(Color.WHITE);
//				boColisaoSim.setBackground(Color.GRAY);
//				boColisaoSim.setBounds(10, 20, 100, 20);
//			pObstaculo.add(boColisaoSim);
			//N�o
			boColisaoNao = new JRadioButton("N�o");
//				boColisaoNao.setFont(fonte);
//				boColisaoNao.setForeground(Color.WHITE);
//				boColisaoNao.setBackground(Color.GRAY);
				boColisaoNao.setSelected(true);
//				boColisaoNao.setBounds(105, 20, 105, 20);
//			pObstaculo.add(boColisaoNao);
		//Grupo de Bot�es
//			gbColisaoSN = new ButtonGroup();
//				gbColisaoSN.add(boColisaoSim);
//				gbColisaoSN.add(boColisaoNao);
	}
	
	//Utilizada internamente no posicionamento e configura��es adicionais dos R�TULOS
		private void configRotulos(JLabel rotulo, int x0, int y0, int xF, int yF){
			rotulo.setFont(fonte);
			rotulo.setForeground(Color.WHITE);
			rotulo.setBounds(x0, y0, xF, yF);
		}
		
	//Utilizada internamente no posicionamento e configura��es adicionais das CAIXAS DE TEXTO
		private void configCaixasTexto(JFormattedTextField caixaTexto, int x0, int y0, int xF, int yF){
			caixaTexto.setFont(fonte);
			caixaTexto.setBorder(bordaCT);
			caixaTexto.setFormatterFactory(fnd);
			caixaTexto.setDocument(new UtilidadeMascaraNumerica());
			caixaTexto.setBounds(x0, y0, xF, yF);
		}

//Getters______________________________________________________________________
		public JLabel getrPropulsaoDado1() {
			return rPropulsaoDado1;
		}

		public JLabel getrPropulsaoDado2() {
			return rPropulsaoDado2;
		}

		public JFormattedTextField getCtPropulsaoDado1() {
			return ctPropulsaoDado1;
		}

		public JFormattedTextField getCtPropulsaoDado2() {
			return ctPropulsaoDado2;
		}

		public JFormattedTextField getCtObjetoMassa() {
			return ctObjetoMassa;
		}

		public JComboBox<String> getCsPropulsao() {
			return csPropulsao;
		}

		public JComboBox<String> getCsAmbienteSimulacao() {
			return csAmbienteSimulacao;
		}

		public JComboBox<String> getCsAmbienteAtrito() {
			return csAmbienteAtrito;
		}

		public JComboBox<String> getCsAmbienteGravidade() {
			return csAmbienteGravidade;
		}

		public JRadioButton getBoColisaoSim() {
			return boColisaoSim;
		}

		public JRadioButton getBoColisaoNao() {
			return boColisaoNao;
		}

		public ButtonGroup getGbColisaoSN() {
			return gbColisaoSN;
		}

		public JButton getBaIniciaPausar() {
			return baIniciaPausar;
		}

		public JButton getBaNovaSimulacao() {
			return baNovaSimulacao;
		}

		public JSlider getdObjetoCoeficienteRestituicao() {
			return dObjetoCoeficienteRestituicao;
		}

		public JLabel getrObjetoAtualCoefRest() {
			return rObjetoAtualCoefRest;
		}
	
}
