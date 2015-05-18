package br.edu.ifg.formosa.obac.controle.obac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleAmbiente;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleFormulasObjeto;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleFormulasSuperficie;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleInicioSimulacoes;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleSuperficie;
import br.edu.ifg.formosa.obac.controle.obstaculo.ControleObstaculoMouse;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoAtualizacoes;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoEntradaDeDados;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelConfiguracaoExecucao;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelFormulas;
import br.edu.ifg.formosa.obac.controle.paineis.ControlePainelInformacao;
import br.edu.ifg.formosa.obac.controle.propulsao.ControleCanhaoMouse;
import br.edu.ifg.formosa.obac.controle.propulsao.ControleMolaMouse;
import br.edu.ifg.formosa.obac.controle.propulsao.ControlePropulsao;
import br.edu.ifg.formosa.obac.controle.propulsao.MolaDeslizanteListener;
import br.edu.ifg.formosa.obac.controle.simulacao.ControleSimulacao;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.principal.OBAC;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;
import br.edu.ifg.formosa.obac.visao.VisaoPainelInformacao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelSimulacao;

public class ControleOBAC {
	//
	//Paineis desta Classe
		//Painel Principal
		private JPanel painelPrincipal = null;
		//Painel de Abas
		private JTabbedPane painelAbas = null;
		//Painel para o pSimula��o e o pInforma��o para tentar evitar o problema da sobreposi��o que ococrre durante o uso dos m�todos repaint de ambos os paineis
		private JPanel painelDeRepintar = null;
	
	//Modelos
		//Modelo Painel de Configura��o - Cont�m os arrays de string utilizados e as strings que s�o modificadas durante o c�digo
		private ModeloPainelConfiguracao mPC = null;
		//Modelo do Ambiente
		private ModeloAmbiente mA = null;
		
	//Vis�o
		//Painel de Configura��o
		private VisaoPainelConfiguracao vPC = null;
		//Painel de Informa��es
		private VisaoPainelInformacao vPI = null;
		//Painel de F�rmulas
		private VisaoPainelFormulas vpf = null;
		//Painel de Simula��o
		private VisaoPainelSimulacao vPS = null;
		
	//Controles
		//Controles do Painel de F�rmulas
		private ControlePainelFormulas cpf = null;
		//Controle F�rmulas Objeto
		private ControleFormulasObjeto cFO = null;
		//Controle F�rmulas Superf�cie
		private ControleFormulasSuperficie cFS = null;
		//Controle Inicio Simula��es
		private ControleInicioSimulacoes cIS = null;
		//Controle das Superf�cies
		private ControleSuperficie cS = null;
		//Controles do Painel de Configura��o
		private ControlePainelConfiguracaoEntradaDeDados cPCED = null;
		private ControlePainelConfiguracaoAtualizacoes cPCA = null;
		//ControleInicioSimulacoes Painel de Informa��o
		private ControlePainelInformacao cpi = null;
		//ControleInicioSimulacoes da Mola
		private ControleMolaMouse cMM = null;
		//ControleListenersCanhao
		private ControleCanhaoMouse cCM = null;
		//ControleObst�culoMouse
		private ControleObstaculoMouse cOM = null;
		//ControlePropuls�o
		private ControlePropulsao cP = null;
	
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
			vPI = new VisaoPainelInformacao();
			painelDeRepintar.add(vPI);
		//ControleInicioSimulacoes do painel de informa��es
			cpi = new ControlePainelInformacao(vPI);
		//Painel de Configura��o
			//Modelo Painel de Configura��o
			mPC = new ModeloPainelConfiguracao();
			//Vis�o Painel de Configura��o
			vPC = new VisaoPainelConfiguracao(mPC);
			painelAbas.add(vPC, "Configura��o");
			
		//Painel de F�rmulas
			//Vis�o Painel de F�rmulas
			vpf = new VisaoPainelFormulas();
			painelAbas.add(vpf, "F�rmulas");
			//Controle painel de f�rmulas
			cpf = new ControlePainelFormulas(vpf, vPC);
			
		//Modelos das Simula��es
			//Modelo Escala
			mA = new ModeloAmbiente(cpi, vpf, cpf);
			
		//Painel de Simula��o
			vPS = new VisaoPainelSimulacao(mA, vPC);
			painelDeRepintar.add(vPS);
			
		//Controles de f�rmulas
			cFO = new ControleFormulasObjeto(mA, vpf, cpf);
			cFS = new ControleFormulasSuperficie(mA, vpf, cpf);//Adicionar o vpf nesta classe para atualizar o pFormulas
			//Instanciar o Controle de in�cio das simula��es
		//Controle do in�cio das simula��es
			cIS = new ControleInicioSimulacoes(mA, vPC, vpf, this, cFO, cFS, cpf);
		
		//Controle das Superf�cies
			cS = new ControleSuperficie(mA.getmS(), vPC, mPC, this);
		
		//Controle da propuls�o por mola
			cMM = new ControleMolaMouse(this, vPS.getVisaoPropulsao(), vPC, mA, cIS);
			
		//Controle Listeners Canhao
			cCM = new ControleCanhaoMouse(mA, vPS, vPC, this);
		
		//ControleObstaculoMouse
			cOM = new ControleObstaculoMouse(vPS.getVisaoObstaculo(), mA.getmObs(), this); 
			
		//Controles do Painel de Configura��o
			cPCA = new ControlePainelConfiguracaoAtualizacoes(mA, vPC, mPC, vpf, vPI, vPS,mA.getmP(), cOM, this);
			cPCED = new ControlePainelConfiguracaoEntradaDeDados(vPC);
			new ControlePainelConfiguracaoExecucao(mA, vPS, vPC, mPC, cPCA, cPCED, cIS, cMM, this);
		
		//Controles/Listeners da mola
		MolaDeslizanteListener mDL = new MolaDeslizanteListener(vPC, vPS, mA, this, cIS);
			vPS.getVisaoAuxiliar().getDeslizanteMola().addChangeListener(mDL);
			vPS.getVisaoAuxiliar().getDeslizanteMola().addMouseListener(mDL);
			
		//Controles - Escala/Ambiente
			new ControleSimulacao(vPI, vPS, mA, vPC, mPC, this);
			new ControleAmbiente(mA, vPC, mPC, this, vPS, cPCA);
			
		//Controle Propuls�o
			cP = new ControlePropulsao(vPI, vPS, mA, vPC, mPC, this);
			
		//Repintar Applet
			obac.repaint();
	}
	
	//Metodo para repintar o painel de informa��es e o de simula��o
	public void repinta() {
		this.painelPrincipal.repaint();
	}
	
	//--Getters
	public ControleCanhaoMouse getcCM() {return cCM;}
	public JTabbedPane getPainelAbas(){return painelAbas;}
}
