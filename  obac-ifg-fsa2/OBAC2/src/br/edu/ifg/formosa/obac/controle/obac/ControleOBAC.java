package br.edu.ifg.formosa.obac.controle.obac;

import java.awt.Color;

import javax.swing.JPanel;

import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoEntradaDeDados;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoAtualizacoes;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoExecucao;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.principal.OBAC;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelInformacao;

public class ControleOBAC {
	
	//Painel Principal
		private JPanel painelPrincipal = null;
	//Modelo Painel de Configura��o - Cont�m os arrays de string utilizados e as strings que s�o modificadas durante o c�digo
		private ModeloPainelConfiguracao mpc = null;
	//Painel de Configura��o
		private VisaoPainelConfiguracao vpc = null;
	//Painel de Informa��es
		private VisaoPainelInformacao vpi = null;
		
	//Painel da Superf�cie
	//Painel do Objeto
	//Painel da Escala
	//Painel de Simula��o
	
	public ControleOBAC(OBAC obac) {
		
		//Painel Principal
		painelPrincipal = new JPanel(null);
			painelPrincipal.setBackground(Color.BLUE);
			painelPrincipal.setSize(1000, 600);
		obac.add(painelPrincipal);
		
		//Modelo Painel de Configura��o
		mpc = new ModeloPainelConfiguracao();
		
		//Painel de Configura��o
		vpc = new VisaoPainelConfiguracao(mpc);
		painelPrincipal.add(vpc);
		
		//Controles do Painel de Configura��o
		new ControlePainelConfiguracaoAtualizacoes(vpc, mpc);
		new ControlePainelConfiguracaoEntradaDeDados(vpc);
		new ControlePainelConfiguracaoExecucao(vpc, mpc);
		
		
		//Painel de Informa��es
		vpi = new VisaoPainelInformacao();
		painelPrincipal.add(vpi);
		
		//Repintar Applet
		obac.repaint();
	}
	
}
