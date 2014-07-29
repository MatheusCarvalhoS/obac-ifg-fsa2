package br.edu.ifg.formosa.obac.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import br.edu.ifg.formosa.obac.modelo.ModeloPainelFormulas;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeCores;

public class VisaoPainelFormulas extends JScrollPane{
	
	//Serial
	private static final long serialVersionUID = 1L;
	
	//Paineis interno
		//Simula��es normais - Sem colis�o
		private JPanel pFundo = null;
		private JPanel pVInicial = null;
		private JPanel pFNormal = null;
		private JPanel pAtrito = null;
		private JPanel pAceleracao = null;
		private JPanel pPosFinal = null;
		private JPanel pTempo = null;
		private JPanel pNovaPos = null;
		//Simula��es normais - Com a colis�o
		private JPanel pColisao = null;
		private JPanel pVelocidadePosColisao = null;
		private JPanel pNovaPosColisao = null;
		//Espec�ficas dos lan�amento obliquo
		private JPanel pMovimentoHorizontal = null;
		private JPanel pMovimentoVertical = null;
		private JPanel pAlcanceTotalHorizontal = null;
		private JPanel pAlcanceTotalVertical = null;
	
	//R�tulos
		//Simula��es normais - Sem colis�o
		private JLabel rVInicial = null;
		private JLabel rFNormal = null;
		private JLabel rAtrito = null;
		private JLabel rAceleracao = null;
		private JLabel rPosFinal = null;
		private JLabel rTempo = null;
		private JLabel rNovaPos = null;
		//Simula��es normais - Com a colis�o
		private JLabel rColisao = null;
		private JLabel rVelocidadePosColisao = null;
		private JLabel rNovaPosColisao = null;
		//Espec�ficas dos lan�amento obliquo
		private JLabel rMovimentoHorizontal = null;
		private JLabel rMovimentoVertical = null;
		private JLabel rAlcanceTotalHorizontal = null;
		private JLabel rAlcanceTotalVertical = null;
	
	//Paineis de rolagem
		//Simula��es normais - Sem colis�o
		private JScrollPane prVInicial = null;
		private JScrollPane prFNormal = null;
		private JScrollPane prAtrito = null;
		private JScrollPane prAceleracao = null;
		private JScrollPane prPosFinal = null;
		private JScrollPane prTempo = null;
		private JScrollPane prNovaPos = null;
		//Simula��es normais - Com a colis�o
		private JScrollPane prColisao = null;
		private JScrollPane prVelocidadePosColisao = null;
		private JScrollPane prNovaPosColisao = null;
		//Espec�ficas dos lan�amento obliquo
		private JScrollPane prMovimentoHorizontal = null;
		private JScrollPane prMovimentoVertical = null;
		private JScrollPane prAlcanceTotalHorizontal = null;
		private JScrollPane prAlcanceTotalVertical = null;
		
	//�reas de Texto
		//Simula��es normais - Sem colis�o
		private JTextArea atVInicial = null;
		private JTextArea atFNormal = null;
		private JTextArea atAtrito = null;
		private JTextArea atAceleracao = null;
		private JTextArea atPosFinal = null;
		private JTextArea atTempo = null;
		private JTextArea atNovaPos = null;
		//Simula��es normais - Com a colis�o
		private JTextArea atColisao = null;
		private JTextArea atVelocidadePosColisao = null;
		private JTextArea atNovaPosColisao = null;
		//Espec�ficas dos lan�amento obliquo
		private JTextArea atMovimentoHorizontal = null;
		private JTextArea atMovimentoVertical = null;
		private JTextArea atAlcanceTotalHorizontal = null;
		private JTextArea atAlcanceTotalVertical = null;
	
	
	//Fontes
	private Font fonteTitulos  = null;
	private Font fonteFormulas = null;
	
	//Layout
	private BoxLayout layout = null;
	
	public VisaoPainelFormulas() {
		this.setSize(250, 800);
		
		//Painel
		pFundo = new JPanel();
		this.setViewportView(pFundo);
		
		//Layout do painel de Fundo
		layout = new BoxLayout(pFundo, BoxLayout.Y_AXIS);
		pFundo.setLayout(layout);
		
		//Fontes
		fonteTitulos = new Font("Arial", Font.BOLD, 15);
		fonteFormulas = new Font("Arial", Font.BOLD, 13);
		
		//Velocidade Inicial
		rVInicial = new JLabel("Velocidade Inicial");//R�tulo
		atVInicial = new JTextArea(ModeloPainelFormulas.propCanhao);//�rea de Texto
		addComponentes(pVInicial, rVInicial, prVInicial, atVInicial, UtilidadeCores.azulNaval, UtilidadeCores.amareloClaro, 5, true);
		
		//For�a Normal
		rFNormal = new JLabel("For�a Normal");
		atFNormal = new JTextArea(ModeloPainelFormulas.forcaNormal);
		addComponentes(pFNormal, rFNormal, prFNormal, atFNormal, UtilidadeCores.azulIndigo, UtilidadeCores.amareloKhaki, 3, true);
		
		//Atrito
		rAtrito = new JLabel("Atrito");
		atAtrito = new JTextArea(ModeloPainelFormulas.atrito);
		addComponentes(pAtrito, rAtrito, prAtrito, atAtrito, UtilidadeCores.azulEscuro, UtilidadeCores.amareloLimao, 3, true);
		
		//Acelera��o
		rAceleracao = new JLabel("Acelera��o");
		atAceleracao = new JTextArea(ModeloPainelFormulas.aceleracaoDescida);
		addComponentes(pAceleracao, rAceleracao, prAceleracao, atAceleracao, UtilidadeCores.azul, UtilidadeCores.amarelo, 7, true);
		
		//Posi��o final
		rPosFinal = new JLabel("Posi��o Final");
		atPosFinal = new JTextArea(ModeloPainelFormulas.posicaoFinalPadrao);
		addComponentes(pPosFinal, rPosFinal, prPosFinal, atPosFinal, UtilidadeCores.azulCobalto, UtilidadeCores.amareloAcafrao, 6, true);
		
		//Tempo
		rTempo = new JLabel("Tempo");
		atTempo = new JTextArea(ModeloPainelFormulas.tempo +"\n" +ModeloPainelFormulas.tempoTotal);
		addComponentes(pTempo, rTempo, prTempo, atTempo, UtilidadeCores.azulMetalico, UtilidadeCores.amareloOuro, 6, true);
		
		//Nova Posi��o
		rNovaPos = new JLabel("Nova Posi��o");
		rNovaPos.setToolTipText("Equa��o hor�ria das abscissas");
		atNovaPos = new JTextArea(ModeloPainelFormulas.equaHorariaAbscissa);
		addComponentes(pNovaPos, rNovaPos, prNovaPos, atNovaPos, UtilidadeCores.azulMedio, UtilidadeCores.amareloQueimado, 6, true);
		
		//Colis�o
			//Colis�o
			rColisao = new JLabel("Colis�o");
			atColisao = new JTextArea(ModeloPainelFormulas.colisao);
			addComponentes(pColisao, rColisao, prColisao, atColisao, UtilidadeCores.azulNeon, UtilidadeCores.laranja, 6, false);
			
			//Velocidade p�s Colos�o - Criar os componentes e refazer o esquema de cores
			rVelocidadePosColisao = new JLabel("Velocidade Ap�s Colis�o");
			rVelocidadePosColisao.setToolTipText("Equa��o de Torricceli");
			atVelocidadePosColisao = new JTextArea(ModeloPainelFormulas.equaTorricceli);
			addComponentes(pVelocidadePosColisao, rVelocidadePosColisao, prVelocidadePosColisao, atVelocidadePosColisao, UtilidadeCores.azulCeu, UtilidadeCores.laranjaAvermelhado, 6, false);
			
			//Nova Posi��o Ap�s Colis�o
			rNovaPosColisao = new JLabel("Nova Posi��o Ap�s Colis�o");
			rNovaPosColisao.setToolTipText("Equa��o hor�ria das abscissas");
			atNovaPosColisao = new JTextArea(ModeloPainelFormulas.equaHorariaAbscissa);
			addComponentes(pNovaPosColisao, rNovaPosColisao, prNovaPosColisao, atNovaPosColisao, UtilidadeCores.azulClaro, UtilidadeCores.vermelho, 6, false);
		
		
		//Lan�amento Obl�quo
			//Movimento Horizontal
			rMovimentoHorizontal = new JLabel("Movimento Horizontal");
			atMovimentoHorizontal = new JTextArea(ModeloPainelFormulas.movimentoHorizontal);
			addComponentes(pMovimentoHorizontal, rMovimentoHorizontal, prMovimentoHorizontal, atMovimentoHorizontal, UtilidadeCores.azulEscuro, UtilidadeCores.amarelo, 5, false);
			
			//Movimento Vertical
			rMovimentoVertical = new JLabel("Movimento Vertical");
			atMovimentoVertical = new JTextArea(ModeloPainelFormulas.movimentoVertical);
			addComponentes(pMovimentoVertical, rMovimentoVertical, prMovimentoVertical, atMovimentoVertical, UtilidadeCores.azul, UtilidadeCores.amareloAcafrao, 5, false);
			
			//Alcance Total Horizontal
			rAlcanceTotalHorizontal = new JLabel("Alcance Total Horizontal");
			atAlcanceTotalHorizontal = new JTextArea(ModeloPainelFormulas.alcanceHorizontal);
			addComponentes(pAlcanceTotalHorizontal, rAlcanceTotalHorizontal, prAlcanceTotalHorizontal, atAlcanceTotalHorizontal, UtilidadeCores.azulCobalto, UtilidadeCores.amareloQueimado, 5, false);
			
			//Altura Total Vertical
			rAlcanceTotalVertical = new JLabel("Altura Total Vertical");
			atAlcanceTotalVertical = new JTextArea(ModeloPainelFormulas.alturaVertical);
			addComponentes(pAlcanceTotalVertical, rAlcanceTotalVertical, prAlcanceTotalVertical, atAlcanceTotalVertical, UtilidadeCores.azulMetalico, UtilidadeCores.amareloOuro, 5, false);
		
	}
	
	//Este m�todo Recebe os tr�s componentes que se referem a uma f�rmula
	//Passa suas posi��es, tamanhos e outras configura��es
	private void addComponentes(JPanel p, JLabel r, JScrollPane pr, JTextArea at, Color corFundo, Color corFonte, int numLinhasAT, boolean painelVisivel){
		//Painel - Cont�m todos os componentes a seguir e � adicionado no painel de fundo
		p = new JPanel(new BorderLayout());
			p.setBackground(corFundo);//Muda a cor de fundo
			p.setBorder(new LineBorder(Color.BLACK, 1));//Define a borda dos paineis para que seja vista a delimita��o que existe entre cada painel
			p.setVisible(painelVisivel);//Utilizado para que os paineis que n�o pertencem a imula��o principal n�o apare�am, caso eles sejeam nexes�rio fica a cargo do controle decidir quais seram vistao. Isso � feito assim que a simula��o � iniciada. 
		pFundo.add(p);
		
		//R�tulo - Indica a f�rmula pressa, � adicionado no painel de sua f�rmula
		r.setFont(fonteTitulos);//Define a fonte utilizada nos nomes das f�rmulas
		r.setForeground(corFonte);//Muda a cor da fonte
		p.add(r, BorderLayout.NORTH);
		
		//�rea de Texto - �rea onde as f�rmulas s�o exibidas, � adicionado no painel de rolagen da sua f�rmula
		at.setFont(fonteFormulas);
			at.setBackground(corFundo);//Muda a cor de fundo
			at.setDisabledTextColor(corFonte);//Muda a cor da fonte
			at.setEnabled(false);//Impossibilita a edi��o do texto pelo usu�rio
		
		//Painel de Rolagem - Recebe a �rea de texto e e� adicionado no painel da f�rmula
		pr = new JScrollPane(at);
			pr.setBorder(null);//Retira a borda do painel de rolagem da f�rmula 
			pr.setPreferredSize(new Dimension(220, (numLinhasAT*15)));//Define as propor��es m�ximas do Painel de Rolagem considerando o n�mero de linhas nescess�rias para ele (15 pix por linha)
			pr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);//Retira a rolagem vertical do painel
		p.add(pr, BorderLayout.CENTER);
	}
	
//Getters das Areas de Texto___________________________________________________
	//Estas tem o texto modificado durante a execu��o
	public JTextArea getAtVInicial() {return atVInicial;}
	public JTextArea getAtFNormal() {return atFNormal;}
	public JTextArea getAtAtrito() {return atAtrito;}
	public JTextArea getAtAceleracao() {return atAceleracao;}
	public JTextArea getAtPosFinal() {return atPosFinal;}
	public JTextArea getAtTempo() {return atTempo;}
	public JTextArea getAtNovaPos() {return atNovaPos;}
	public JTextArea getAtColisao() {return atColisao;}
	public JTextArea getAtNovaPosColisao() {return atNovaPosColisao;}
	public JTextArea getAtMovimentoHorizontal() {return atMovimentoHorizontal;}
	public JTextArea getAtMovimentoVertical() {return atMovimentoVertical;}
	public JTextArea getAtAlcanceTotalHorizontal() {return atAlcanceTotalHorizontal;}
	public JTextArea getAtAlcanceTotalVertical() {return atAlcanceTotalVertical;}

//Setter dos Paineis de Rolagem_______________________________________________
	//dependendo da simula��o estes paineis podem ou n�o aparecer
	public void setPVInicial(boolean visivel) {pVInicial.setVisible(visivel);}
	public void setPFNormal(boolean visivel) {pFNormal.setVisible(visivel);}
	public void setPAtrito(boolean visivel) {pAtrito.setVisible(visivel);}
	public void setPAceleracao(boolean visivel) {pAceleracao.setVisible(visivel);}
	public void setPPosFinal(boolean visivel) {pPosFinal.setVisible(visivel);}
	public void setPTempo(boolean visivel) {pTempo.setVisible(visivel);}
	public void setPNovaPos(boolean visivel) {pNovaPos.setVisible(visivel);}
	public void setPColisao(boolean visivel) {pColisao.setVisible(visivel);}
	public void setPNovaPosColisao(boolean visivel) {pNovaPosColisao.setVisible(visivel);}
	public void setpMovimentoHorizontal(boolean visivel) {pMovimentoHorizontal.setVisible(visivel);}
	public void setpMovimentoVertical(boolean visivel) {pMovimentoVertical.setVisible(visivel);}
	public void setpAlcanceTotalHorizontal(boolean visivel) {pAlcanceTotalHorizontal.setVisible(visivel);}
	public void setpAlcanceTotalVertical(boolean visivel) {pAlcanceTotalVertical.setVisible(visivel);}
	
//Modificador do tempo para as simul��es
	public void modificaPTempo(boolean lancamentoObliq){
		if(lancamentoObliq){
			atTempo.setBackground(UtilidadeCores.azulIndigo);//�rea de texto
			atTempo.setForeground(UtilidadeCores.amareloKhaki);
			rTempo.setBackground(UtilidadeCores.azulIndigo);//r�tulo
			rTempo.setForeground(UtilidadeCores.amareloKhaki);
			pTempo.setBackground(UtilidadeCores.azulIndigo);//painel
		}
		else{
			atTempo.setBackground(UtilidadeCores.azulMetalico);//�rea de texto
			atTempo.setForeground(UtilidadeCores.amareloOuro);
			rTempo.setBackground(UtilidadeCores.azulMetalico);//r�tulo
			rTempo.setForeground(UtilidadeCores.amareloOuro);
			pTempo.setBackground(UtilidadeCores.azulMetalico);//painel}
		}
	}
	
}