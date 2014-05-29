package br.edu.ifg.formosa.obac.controle.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;

public class ControlePainelConfiguracaoAtualizacoes {
	
	public ControlePainelConfiguracaoAtualizacoes(final VisaoPainelConfiguracao vpc, final ModeloPainelConfiguracao mpc) {
		//Mudan�a nos r�tulos de dados da propuls�o e nas simula��es dispon�veis
		vpc.getCsPropulsao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(vpc.getCsPropulsao().getSelectedItem().equals(mpc.getCanhao())){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(mpc.getDado1Canhao());
					vpc.getrPropulsaoDado2().setText(mpc.getDado2Canhao());
					//Caixa de sele��o das simula��es
					if(vpc.getCsAmbienteSimulacao().getItemCount()==5)//Teste l�gico para que adicione apenas uma vez
						vpc.getCsAmbienteSimulacao().insertItemAt(mpc.getLancamentoObliquo(), vpc.getCsAmbienteSimulacao().getItemCount());
				}
				else if(vpc.getCsPropulsao().getSelectedItem().equals(mpc.getMola())){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(mpc.getDado1Mola());
					vpc.getrPropulsaoDado2().setText(mpc.getDado2Mola());
					//Caixa de sele��o das simula��es
					if(vpc.getCsAmbienteSimulacao().getItemCount()==6)//Teste l�gico para n�o remover uma linha desnecess�ria
						vpc.getCsAmbienteSimulacao().removeItemAt(vpc.getCsAmbienteSimulacao().getItemCount()-1);//Remove a op��o de lan�amento obl�quo 
				}
				else{
					JOptionPane.showMessageDialog(null,
						"Falha catastr�fica no funcionamento do programa", 
						"Aviso", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		
		//Coeficiente de restitui��o
		vpc.getdObjetoCoeficienteRestituicao().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vpc.getrObjetoAtualCoefRest().setText(""+(vpc.getdObjetoCoeficienteRestituicao().getValue()/100.0));
				//O valor do Deslizante (JSlider) � pego e dividido por 100
				//Em seguida � passado para um r�tulo (JLabel), a fim do usu�rio saber o valor que a simula��o usar�
			}
		});
	}

}
