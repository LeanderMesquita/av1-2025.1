package bean;

import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.ArrayList;
import entity.Jogada;
import dao.JogadaDAO;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean
public class JogadaBean {
	private Jogada jogada = new Jogada();
	private List<Jogada> jogadas = new ArrayList<Jogada>();
	
	public JogadaBean() {
		jogadas = JogadaDAO.listAll();
	}
	
	public String salvar() {
		
		try {
			definirResultado(jogada);
			JogadaDAO.save(jogada);
			jogada = new Jogada();
			jogadas = JogadaDAO.listAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogada cadastrada com sucesso."));
			return null;
			
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar."));
			return null;
		}
		
		
		
	}
	
	public String atualizar() {
		
		try {
			JogadaDAO.update(jogada);
			jogadas = JogadaDAO.listAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogada atualizada com sucesso."));
			return null;
			
		} catch(Exception e) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar."));
			return null;
		}
		
		
		
	}
	public String deletar(Jogada jogada) {
		
		try {
			
			JogadaDAO.delete(jogada);
			jogadas = JogadaDAO.listAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogada deletada com sucesso."));
			return null;
			
		} catch(Exception e) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar."));
			return null;
		}
		
		
		
	}
	
	public void prepararAtualizacao(Jogada jogada) {
		this.jogada = jogada;
	}
	
	public void definirResultado(Jogada jogada) {
		
		if(jogada.getJogada1().equals(jogada.getJogada2())){
			jogada.setResultado("Empate");
			
		} else if (
			jogada.getJogada1().equals("Pedra") && jogada.getJogada2().equals("Tesoura") ||
			jogada.getJogada1().equals("Papel") && jogada.getJogada2().equals("Pedra") ||
			jogada.getJogada1().equals("Tesoura") && jogada.getJogada2().equals("Papel"))
		{
			jogada.setResultado(jogada.getJogador1()+" venceu!");
		} else {
			jogada.setResultado(jogada.getJogador2()+" venceu!");
		}
		
		
	}

	public Jogada getJogada() {
		return jogada;
	}

	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}

	public List<Jogada> getJogadas() {
		return jogadas;
	}

	public void setJogadas(List<Jogada> jogadas) {
		this.jogadas = jogadas;
	}
	
	
	
}
