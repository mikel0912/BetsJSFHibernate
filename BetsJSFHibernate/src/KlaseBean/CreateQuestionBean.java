package KlaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import facade.bean.FacadeBean;

public class CreateQuestionBean {
	private Date data;
	private String question;
	private Float minBet;
	private static BLFacade facadeBL;
	private Event gertaera;
	private static List<Event> gertaerak = new ArrayList<Event>();	
	
	public CreateQuestionBean() {
		facadeBL =FacadeBean.getBusinessLogic();
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+event.getObject()));
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Float getMinBet() {
		return minBet;
	}

	public void setMinBet(Float minBet) {
		this.minBet = minBet;
	}

	public static BLFacade getFacadeBL() {
		return facadeBL;
	}

	public static void setFacadeBL(BLFacade facadeBL) {
		CreateQuestionBean.facadeBL = facadeBL;
	}

	public Event getGertaera() {
		return gertaera;
	}

	public void setGertaera(Event gertaera) {
		this.gertaera = gertaera;
	}

	public List<Event> getGertaerak() {
		return gertaerak;
	}

	public void setGertaerak(List<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}

	public void onDateSelect2(SelectEvent event) {
		gertaerak =facadeBL.getEvents(data);
	}
	
	public void onButtonSelect() {
		try {
			if(gertaera!=null) {
				if(question!="") {
					if(minBet!=null) {
						facadeBL.createQuestion(gertaera, question, minBet);
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Galdera ondo sortu da"));
					}else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Min bet null da"));
					}
				}else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Galdera deskripzioa hutsa da"));
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ez duzu gertaerarik hautatu"));
			}
		} catch (EventFinished e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROR: Gertaera dagoeneko bukatu da"));
		} catch (QuestionAlreadyExist e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROR: Gertaera horretan galdera berdina sortuta dago"));
		}
	}
}
