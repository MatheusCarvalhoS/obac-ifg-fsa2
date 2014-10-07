package br.edu.ifg.formosa.obac.controle.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleInicioSimulacoes;
import br.edu.ifg.formosa.obac.controle.propulsao.ControleMolaMouse;
import br.edu.ifg.formosa.obac.controle.simulacao.ControleSimulacao;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.utilidades.UtilidadeConvercoesEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelSimulacao;

public class ControlePainelConfiguracaoExecucao {
	
	private ModeloAmbiente mA = null;
	private VisaoPainelConfiguracao vPC = null;
	private VisaoPainelSimulacao vPS = null;
	private ControleInicioSimulacoes cIS = null;
	
	public ControlePainelConfiguracaoExecucao(
			final ModeloAmbiente mA, final VisaoPainelSimulacao vPS,
			final VisaoPainelConfiguracao vPC, final ModeloPainelConfiguracao mpc,
			final ControlePainelConfiguracaoAtualizacoes cpca,
			final ControlePainelConfiguracaoEntradaDeDados cpced,
			final ControleInicioSimulacoes cIS, final ControleMolaMouse cMM,
			final ControleOBAC cOBAC)
	{
		this.mA = mA;
		this.vPC = vPC;
		this.vPS = vPS;
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
						else{
							mA.getmEV().setEscalaFimYM(Integer.parseInt(vPC.getCtPropulsaoDado1().getText()));
							ControleSimulacao.mudaMarcadores(mA.getmEV(), (int)mA.getmEV().getEscalaFimYM());
							
						}
					//Retira os listeners case seja LO
						if (vPC.getCsAmbienteSimulacao().getSelectedIndex() == 4) {
							vPS.getVisaoPropulsao().removeMouseListener(cOBAC.getcCM().getcCML());
							vPS.getVisaoPropulsao().removeMouseMotionListener(cOBAC.getcCM().getcCML());						
						}
					//_________________________________________________
					//Troca do r�tulo do painel
					vPC.getBaIniciaPausar().setText(mpc.getBotaoPausar());
				}
				
				//Pausa simula��o
				else if(vPC.getBaIniciaPausar().getText().equals(mpc.getBotaoPausar())){
					cIS.getCObjeto().pausar();
					//Troca do r�tulo do painel
					vPC.getBaIniciaPausar().setText(mpc.getBotaoContinuar());
				}
				else if(vPC.getBaIniciaPausar().getText().equals(mpc.getBotaoContinuar())){
					cIS.getCObjeto().continuar();
					//Troca do r�tulo do painel
					vPC.getBaIniciaPausar().setText(mpc.getBotaoPausar());
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
				//Para a simula��o
				cIS.getCObjeto().parar();
				//Reserta os valores do PConfig
				vPC.getCtPropulsaoDado2().setText("");
				vPC.getCtObjetoMassa().setText("");
				//____________________________________________________
				//Troca o r�tulo do bot�o Para garantir que seja Iniciar Simula��o
					vPC.getBaIniciaPausar().setText(mpc.getBotaoIniciar());
				//____________________________________________________
				//Reativar componentes do painel de Configura��o
					cpca.desativaComponentes(true);
				//Deixa o slider invisivel
					vPS.getVisaoAuxiliar().getpCompressor().setVisible(false);
					vPS.getVisaoAuxiliar().getpCompressor().setEnabled(true);
				//Ajustes de acordo co a simula��o
					if(vPC.getCsPropulsao().getSelectedIndex()==0
					   && vPC.getCsAmbienteSimulacao().getSelectedIndex()!=4){
						vPC.getCtPropulsaoDado1().setEnabled(false);
						vPC.getCtPropulsaoDado1().setText("0");
					}
					else if(vPC.getCsPropulsao().getSelectedIndex()==2){
						vPC.getCtPropulsaoDado2().setEnabled(false);
						vPC.getCtPropulsaoDado2().setText("0");
					}
					else{
						vPC.getCtPropulsaoDado1().setEnabled(true);
						vPC.getCtPropulsaoDado1().setText("");
					}
				//Reposiciona o objeto
					mA.getmO().setPosicaoXPx(130);
					if (vPC.getCsAmbienteSimulacao().getSelectedIndex() == 4)
						mA.getmO().setPosicaoYPx(UtilidadeConvercoesEscala.metroParaPixelV(mA.getmEV(), 0));
					
				//Readiciona o listener do canhao case seja LO
					if (vPC.getCsAmbienteSimulacao().getSelectedIndex() == 4) {
						vPS.getVisaoPropulsao().addMouseListener(cOBAC.getcCM().getcCML());
						vPS.getVisaoPropulsao().addMouseMotionListener(cOBAC.getcCM().getcCML());
					} else {
						vPS.getVisaoPropulsao().removeMouseListener(cOBAC.getcCM().getcCML());
						vPS.getVisaoPropulsao().removeMouseMotionListener(cOBAC.getcCM().getcCML());						
					}
				//Repinta
					cOBAC.repinta();
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
		mA.getmP().getModeloMola().setTamanhoMolaTotalM(Double.parseDouble(vPC.getCtPropulsaoDado1().getText().replaceAll(",", ".")));
		//Constante el�stica
		mA.getmP().getModeloMola().setkAtual(
				Double.parseDouble(vPC.getCtPropulsaoDado2().getText().replaceAll(",", "."))/100);
		//Listener da mola
		vPS.getVisaoAuxiliar().getpCompressor().setVisible(true);
		vPS.getVisaoAuxiliar().getpCompressor().setEnabled(false);
//		cMM.ativaMolaMouse();
	}
	
}
