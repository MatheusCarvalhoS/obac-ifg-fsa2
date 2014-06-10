package br.edu.ifg.formosa.obac.controle.obac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import br.edu.ifg.formosa.obac.controle.escala.ControleEscala;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoAtualizacoes;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoEntradaDeDados;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoExecucao;
import br.edu.ifg.formosa.obac.modelo.ModeloEscala;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.principal.OBAC;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;
import br.edu.ifg.formosa.obac.visao.VisaoPainelInformacao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelSimulacao;

public class ControleOBAC {
	
	//Painel Principal
		private JPanel painelPrincipal = null;
	//Painel de Abas
		private JTabbedPane painelAbas = null;
	//Modelo Painel de Configura��o - Cont�m os arrays de string utilizados e as strings que s�o modificadas durante o c�digo
		private ModeloPainelConfiguracao mpc = null;
	//Modelo da Escala
		private ModeloEscala mE = null;
	//Painel de Configura��o
		private VisaoPainelConfiguracao vpc = null;
	//Painel de Informa��es
		private VisaoPainelInformacao vpi = null;
	//Painel de F�rmulas
		private VisaoPainelFormulas vpf = null;
	//Painel de Simula��o
		private VisaoPainelSimulacao vPS = null;
	
	public ControleOBAC(OBAC obac) {
		
		//Painel Principal
		painelPrincipal = new JPanel(null);
			painelPrincipal.setBackground(Color.BLACK);
			painelPrincipal.setSize(1000, 600);
		obac.add(painelPrincipal);
		
		//Paienl de Abas
		painelAbas = new JTabbedPane();
			painelAbas.setBounds(0, 0, 250, 600);
			painelAbas.setFont(new Font("Arial", Font.BOLD, 15));
		painelPrincipal.add(painelAbas);
		
		//Modelo Painel de Configura��o
		mpc = new ModeloPainelConfiguracao();
		
		//Modelo Escala
		mE = new ModeloEscala();
		
		//Painel de Configura��o
		vpc = new VisaoPainelConfiguracao(mpc);
		painelAbas.add(vpc);
		painelAbas.setTitleAt(0, "Configura��o");
		
		//Painel de F�rmulas
		vpf = new VisaoPainelFormulas();
		
		painelAbas.add(vpf);
		painelAbas.setTitleAt(1, "F�rmulas");
		
		//Controles do Painel de Configura��o
		new ControlePainelConfiguracaoAtualizacoes(vpc, mpc, vpf);
		new ControlePainelConfiguracaoEntradaDeDados(vpc);
		new ControlePainelConfiguracaoExecucao(vpc, mpc);		
		
		//Painel de Informa��es
		vpi = new VisaoPainelInformacao();
		painelPrincipal.add(vpi);
		
		//Painel de Simula��o
		vPS = new VisaoPainelSimulacao(mE);
		painelPrincipal.add(vPS);
		
		//Controle do Painel de Escalas
		new ControleEscala(vPS.getVisaoEscala(), mE, vpc);
		
		//Repintar Applet
		obac.repaint();
	}
	
}
