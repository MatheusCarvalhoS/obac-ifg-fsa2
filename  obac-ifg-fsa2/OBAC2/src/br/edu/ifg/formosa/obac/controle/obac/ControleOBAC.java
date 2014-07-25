package br.edu.ifg.formosa.obac.controle.obac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import br.edu.ifg.formosa.obac.controle.escala.ControleEscala;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleAmbiente;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoAtualizacoes;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoEntradaDeDados;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoExecucao;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelInformacao;
import br.edu.ifg.formosa.obac.controle.propulsao.ControleMolaMouse;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloEscala;
import br.edu.ifg.formosa.obac.modelo.ModeloMola;
import br.edu.ifg.formosa.obac.modelo.ModeloObjeto;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.modelo.ModeloSuperficie;
import br.edu.ifg.formosa.obac.principal.OBAC;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;
import br.edu.ifg.formosa.obac.visao.VisaoPainelInformacao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelSimulacao;

public class ControleOBAC {
	
	//Paineis desta Classe
		//Painel Principal
		private JPanel painelPrincipal = null;
		//Painel de Abas
		private JTabbedPane painelAbas = null;
		//Painel para o pSimula��o e o pInforma��o para tentar evitar o problema da sobreposi��o que ococrre durante o uso dos m�todos repaint de ambos os paineis
		private JPanel painelDeRepintar = null;
	
	//Modelos
		//Modelo Painel de Configura��o - Cont�m os arrays de string utilizados e as strings que s�o modificadas durante o c�digo
		private ModeloPainelConfiguracao mpc = null;
		//Modelo da Escala Prim�ria
		private ModeloEscala mEPri = null;
		//Modelo da Escala Secund�ria
		private ModeloEscala mESec = null;
		///Modelo do Objeto
		private ModeloObjeto mO = null;
		//Modelo da Superf�cie
		private ModeloSuperficie mS = null;
		//Modelo do Ambiente
		private ModeloAmbiente mA = null;
		//Modelo Da propuls�o por mola
		private ModeloMola mM = null;
		
	//Vis�o
		//Painel de Configura��o
		private VisaoPainelConfiguracao vpc = null;
		//Painel de Informa��es
		private VisaoPainelInformacao vpi = null;
		//Painel de F�rmulas
		private VisaoPainelFormulas vpf = null;
		//Painel de Simula��o
		private VisaoPainelSimulacao vPS = null;
		
	//Controles
		//Controles do Painel de Configura��o
		private ControlePainelConfiguracaoEntradaDeDados cpced = null;
		private ControlePainelConfiguracaoAtualizacoes cpca = null;
		//Controle Painel de Informa��o
		private ControlePainelInformacao cpi = null;
		//Controle da Mola
		private ControleMolaMouse cmm = null;
	
	public ControleOBAC(OBAC obac) {
		
		//Painel Principal
		painelPrincipal = new JPanel(null);
			painelPrincipal.setBackground(Color.black);
			painelPrincipal.setSize(1000, 600);
		obac.add(painelPrincipal);
		
		//Paienl de Abas
		painelAbas = new JTabbedPane();
			painelAbas.setBounds(0, 0, 250, 600);
			painelAbas.setFont(new Font("Arial", Font.BOLD, 15));
		painelPrincipal.add(painelAbas);
		
		//Painel de Repintar
		painelDeRepintar = new JPanel(null);
		painelDeRepintar.setBackground(Color.blue);
		painelDeRepintar.setBounds(251, 0, 750, 600);
		painelPrincipal.add(painelDeRepintar);
		
		
		//Painel de Informa��es
			vpi = new VisaoPainelInformacao();
			painelDeRepintar.add(vpi);
		//Controle do painel de informa��es
			cpi = new ControlePainelInformacao(vpi);
			
		//Modelos das Simula��es
			//Modelo da Escala Prim�ria
			mEPri = new ModeloEscala();
			//Modelo da Escala Secund�ria
			mESec = new ModeloEscala();
			///Modelo do Objeto
			mO = new ModeloObjeto(cpi);
			//Modelo da Superf�cie
			mS = new ModeloSuperficie(cpi);
			//Modelo da Propuls�o por Mola
			mM = new ModeloMola(mO, cpi);
			//Modelo Escala
			mA = new ModeloAmbiente(cpi, mEPri, mESec, mO, mS, mM);
			
		//Painel de Configura��o
			//Modelo Painel de Configura��o
			mpc = new ModeloPainelConfiguracao();
			//Vis�o Painel de Configura��o
			vpc = new VisaoPainelConfiguracao(mpc);
			painelAbas.add(vpc, "Configura��o");
			
		//Painel de F�rmulas
			vpf = new VisaoPainelFormulas();
			painelAbas.add(vpf, "F�rmulas");
			
		//Painel de Simula��o
			vPS = new VisaoPainelSimulacao(mA, vpc);
			painelDeRepintar.add(vPS);
			
		//Controles - Escala/Ambiente
			new ControleEscala(vpi, vPS, vPS.getVisaoEscalaPri(), mA, vpc, mpc);
			new ControleAmbiente(mA, vpc, mpc, this, vPS.getVisaoSuperficie(), vPS);
			
		//Controle da propuls�o por mola
			cmm = new ControleMolaMouse(this, vPS.getVisaoPropulsao(), vPS.getVisaoObjeto(), mA);
			
		//Controles do Painel de Configura��o
			cpca = new ControlePainelConfiguracaoAtualizacoes(vpc, mpc, vpf, vpi, vPS.getVisaoPropulsao(),mM, cmm);
			cpced = new ControlePainelConfiguracaoEntradaDeDados(vpc);
			new ControlePainelConfiguracaoExecucao(vpc, mpc, cpca, cpced);
			
		vpc.getCsPropulsao().setSelectedIndex(1);
			
		//Repintar Applet
			obac.repaint();
	}
	
	//Metodo para repintar o painel de informa��es e o de simula��o
	public void repinta() {
//		this.vpi.repaint();
//		this.vPS.repaint();
		this.painelPrincipal.repaint();
	}
}
