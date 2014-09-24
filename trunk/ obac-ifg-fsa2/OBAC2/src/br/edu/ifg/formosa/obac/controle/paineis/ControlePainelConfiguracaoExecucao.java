package br.edu.ifg.formosa.obac.controle.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleInicioSimulacoes;
import br.edu.ifg.formosa.obac.controle.propulsao.ControleMolaMouse;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelSimulacao;

public class ControlePainelConfiguracaoExecucao {
	
	private ModeloAmbiente mA = null;
	private VisaoPainelConfiguracao vPC = null;
	private ControleInicioSimulacoes cIS = null;
	
	public ControlePainelConfiguracaoExecucao(
			final ModeloAmbiente mA,
			final VisaoPainelConfiguracao vPC, final ModeloPainelConfiguracao mpc,
			final ControlePainelConfiguracaoAtualizacoes cpca,
			final ControlePainelConfiguracaoEntradaDeDados cpced,
			final ControleInicioSimulacoes cIS, final ControleMolaMouse cMM)
	{
		this.mA = mA;
		this.vPC = vPC;
		this.cIS = cIS;
		
		//Bot�o Iniciar/Pausar
		vPC.getBaIniciaPausar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Inicia simula��o
				if (vPC.getBaIniciaPausar().getText().equals(mpc.getBotaoIniciar())==true
					&& cpced.verificaCampos()==true) {
					//Desativar componentes do Painel de Cofigura��o
						cpca.desativaComponentes(false);
					//____________________________________________________
					//Passagem do valor da massa para o ModeloObjeto
						mA.getmO().setMassa(Double.parseDouble(vPC.getCtObjetoMassa().getText().replaceAll(",", ".")));
					//____________________________________________________
					//In�cio dos testes l�gicos para executar
					//--Propuls�o por canh�o
						if (vPC.getCsPropulsao().getSelectedIndex()==0){exeCanhao();}
					//--Execu��o da mola
						else if (vPC.getCsPropulsao().getSelectedIndex()==1){exeMola();}
					//--Execu��o Queda Livre
						else{cIS.iniciarSimulacao();}
					//____________________________________________________
					//Troca do r�tulo do painel
					vPC.getBaIniciaPausar().setText(mpc.getBotaoPausar());
				}
				
				//Pausa simula��o
				else if(vPC.getBaIniciaPausar().getText().equals(mpc.getBotaoPausar())){
					
					vPC.getBaIniciaPausar().setText(mpc.getBotaoIniciar());
				}
				
				//Se vier para c� a simula��o deu errado por uma falha no c�digo
				else{System.err.println("!!!Erro!!!");}
			}
		});
		
		/*Bot�o nova simula��o - Para simula��o retornando-a para seu estado original,
		 *tornando poss�vel a realiza��o de uma nova simula��o*/ 
		vPC.getBaNovaSimulacao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Troca o r�tulo do bot�o Para garantir que seja Iniciar Simula��o
					vPC.getBaIniciaPausar().setText(mpc.getBotaoIniciar());
				//____________________________________________________
				//Reativar componentes do painel de Configura��o
					cpca.desativaComponentes(true);
			}
		});
	}
	
	private void exeCanhao(){
		//�ngulo
		mA.getmP().setAnguloRotacaoGraus(
				Double.parseDouble(vPC.getCtPropulsaoDado1().getText().replace(",", ".")));
		//Energia
		mA.getmP().getmC().setEnergia(
				Double.parseDouble(vPC.getCtPropulsaoDado2().getText().replace(",", ".")));
		
		mA.getmP().getmC().calculaVelocidade(); //Velocidade do canh�o
		
		cIS.iniciarSimulacao();
	}
	
	private void exeMola(){
		//Tamanho da Mola
		mA.getmP().getModeloMola().setTamanhoMolaTotalM(Double.parseDouble(vPC.getCtPropulsaoDado1().getText()));
		//Constante el�stica
		mA.getmP().getModeloMola().setkAtual(
				Double.parseDouble(vPC.getCtPropulsaoDado2().getText().replaceAll(",", "."))/100);
		//Listener da mola
//		cMM.ativaMolaMouse();
	}
	
}
