package br.edu.ifg.formosa.obac.controle.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.edu.ifg.formosa.obac.modelo.ModeloPainelFormulas;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;

public class ControlePainelConfiguracaoAtualizacoes {
	
	//Constantes
	private final VisaoPainelConfiguracao vpc;
	private final ModeloPainelConfiguracao mpc;
	private final VisaoPainelFormulas vpf;
	
	//Construtor
	public ControlePainelConfiguracaoAtualizacoes(final VisaoPainelConfiguracao vpc, final ModeloPainelConfiguracao mpc, final VisaoPainelFormulas vpf) {
		this.vpc = vpc;
		this.mpc = mpc;
		this.vpf = vpf;
		
		acaoPropulsoes();
		acaoCoefDeRestituicao();
		
	}
	
	//Altera��es realizadas de acordo com a propuls�o selecionada
	private void acaoPropulsoes(){
		vpc.getCsPropulsao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Propuls�o pelo canh�o
				if(vpc.getCsPropulsao().getSelectedItem().equals(mpc.getCanhao())){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(mpc.getDado1Canhao());
					vpc.getrPropulsaoDado2().setText(mpc.getDado2Canhao());
					//Caixa de sele��o das simula��es
					if(vpc.getCsAmbienteSimulacao().getItemCount()==5)//Teste l�gico para que adicione apenas uma vez
						vpc.getCsAmbienteSimulacao().insertItemAt(mpc.getLancamentoObliquo(), vpc.getCsAmbienteSimulacao().getItemCount());
					//Painel de F�rmulas
					vpf.getAtVInicial().setText(ModeloPainelFormulas.propCanhao);
				}
				//Propuls�o pela mola
				else if(vpc.getCsPropulsao().getSelectedItem().equals(mpc.getMola())){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(mpc.getDado1Mola());
					vpc.getrPropulsaoDado2().setText(mpc.getDado2Mola());
					//Caixa de sele��o das simula��es
					if(vpc.getCsAmbienteSimulacao().getItemCount()==6)//Teste l�gico para n�o remover uma linha desnecess�ria
						vpc.getCsAmbienteSimulacao().removeItemAt(vpc.getCsAmbienteSimulacao().getItemCount()-1);//Remove a op��o de lan�amento obl�quo
					//Painel de F�rmulas
					vpf.getAtVInicial().setText(ModeloPainelFormulas.propMola);
				} 
				else{
					JOptionPane.showMessageDialog(null,
						"Falha catastr�fica no funcionamento do programa", 
						"Aviso", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
	}
	
	//Atualiza��o do r�tulo da barra do Coeficiente de restitui��o
	private void acaoCoefDeRestituicao(){
		vpc.getdObjetoCoeficienteRestituicao().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vpc.getrObjetoAtualCoefRest().setText(""+(vpc.getdObjetoCoeficienteRestituicao().getValue()/100.0));
				//O valor do Deslizante (JSlider) � pego e dividido por 100
				//Em seguida � passado para um r�tulo (JLabel), a fim do usu�rio saber o valor que a simula��o usar�
			}
		});
	}

	//M�todo usado na execu��o para que a intera��o com os componentes seja removida
	public void desativaComponentes(boolean ativado){
		vpc.getCsPropulsao().setEnabled(ativado);
		vpc.getCtPropulsaoDado1().setEnabled(ativado);
		vpc.getCtPropulsaoDado2().setEnabled(ativado);
		vpc.getCsAmbienteSimulacao().setEnabled(ativado);
		vpc.getCsAmbienteAtrito().setEnabled(ativado);
		vpc.getCsAmbienteGravidade().setEnabled(ativado);
		vpc.getCtObjetoMassa().setEnabled(ativado);
		vpc.getdObjetoCoeficienteRestituicao().setEnabled(ativado);
		vpc.getBoColisaoSim().setEnabled(ativado);
		vpc.getBoColisaoNao().setEnabled(ativado);
		vpc.getBaNovaSimulacao().setVisible(!ativado);
	}
}
