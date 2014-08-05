package br.edu.ifg.formosa.obac.controle.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.obac.controle.objetoAmbienteSuperficie.ControleInicioSimulacoes;
import br.edu.ifg.formosa.obac.controle.propulsao.ControleMolaMouse;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;

public class ControlePainelConfiguracaoExecucao {

	public ControlePainelConfiguracaoExecucao(
			final ModeloAmbiente mA,
			final VisaoPainelConfiguracao vpc, final ModeloPainelConfiguracao mpc,
			final ControlePainelConfiguracaoAtualizacoes cpca,
			final ControlePainelConfiguracaoEntradaDeDados cpced,
			final ControleInicioSimulacoes cIS, final ControleMolaMouse cMM)
	{
		
		//Bot�o Iniciar/Pausar
		vpc.getBaIniciaPausar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Inicia simula��o
				if (vpc.getBaIniciaPausar().getText().equals(mpc.getBotaoIniciar())==true
					&& cpced.verificaCampos()==true) {
					cpca.desativaComponentes(false);//Desativar componentes
					if (vpc.getCsPropulsao().getSelectedIndex()==0//Propuls�o por canh�o
						||vpc.getCsAmbienteSimulacao().getSelectedIndex()==4)//Simula��o da queda Livre
					 {
						cIS.iniciarSimulacao();
					}
					else {
						//Tamanho da Mola
						mA.getmP().getModeloMola().setTamanhoMolaTotalM(Double.parseDouble(vpc.getCtPropulsaoDado1().getText()));
						//Constante el�stica
						mA.getmP().getModeloMola().setkAtual(Double.parseDouble(vpc.getCtPropulsaoDado2().getText().replaceAll(",", ".")));
						//Massa
						mA.getmO().setMassa(Double.parseDouble(vpc.getCtObjetoMassa().getText().replaceAll(",", ".")));
						//Listener da mola
						cMM.ativaMolaMouse();
					}
				}
				//Pausa simula��o
				else if(vpc.getBaIniciaPausar().getText().equals(mpc.getBotaoPausar())){}
				//Se vier para c� a simula��o deu errado por uma falha no c�digo
				else{}
			}
		});
		
		/*Bot�o nova simula��o - Para simula��o retornando-a para seu estado original,
		 *tornando poss�vel a realiza��o de uma nova simula��o*/ 
		vpc.getBaNovaSimulacao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cpca.desativaComponentes(true);//Ativar componentes
			}
		});
	}
	
}
