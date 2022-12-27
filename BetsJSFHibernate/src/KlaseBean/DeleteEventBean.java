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
import domain.Sport;
import exceptions.EventFinished;
import facade.bean.FacadeBean;

public class DeleteEventBean {
	private Date data;
	private static BLFacade facadeBL;
	private Event gertaera;
	private static List<Event> gertaerak = new ArrayList<Event>();
	private Question galdera;
	private static List<Question> galderak = new ArrayList<Question>();
	private Sport kirola;
	private static List<Sport> kirolak = new ArrayList<Sport>();

	public DeleteEventBean() {
		facadeBL =FacadeBean.getBusinessLogic();
		kirolak = facadeBL.getSports();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Question getGaldera() {
		return galdera;
	}

	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}

	public List<Question> getGalderak() {
		return galderak;
	}

	public void setGalderak(List<Question> galderak) {
		this.galderak = galderak;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+event.getObject()));
	}

	public static BLFacade getFacadeBL() {
		return facadeBL;
	}

	public static void setFacadeBL(BLFacade facadeBL) {
		DeleteEventBean.facadeBL = facadeBL;
	}

	public List<Event> getGertaerak() {
		return gertaerak;
	}

	public void setGertaerak(List<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}

	public Event getGertaera() {
		return gertaera;
	}

	public void setGertaera(Event gertaera) {
		this.gertaera = gertaera;
	}

	public void onDateSelect2(SelectEvent event) {
		gertaerak =facadeBL.getEvents(data);
		galderak=null;
		galdera=null;
		gertaera=null;
	}

	public void onEventSelect(SelectEvent event) {
		galderak =gertaera.getQuestions();
	}

	public Sport getKirola() {
		return kirola;
	}

	public void setKirola(Sport kirola) {
		this.kirola = kirola;
	}

	public List<Sport> getKirolak() {
		return kirolak;
	}

	public void setKirolak(List<Sport> kirolak) {
		this.kirolak = kirolak;
	}

	public void onSportSelect(SelectEvent event) {
		System.out.println(kirola);
		System.out.println(kirola.getEvents());
		//gertaerak=kirola.getEvents();
		gertaerak = facadeBL.getEventsSport(kirola);
	}

	public void onButtonSelect() {
		if(kirola!=null) {
			if(gertaera!=null) {
				facadeBL.gertaeraEzabatu(gertaera);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Gertaera ondo ezabatu da"));
				gertaerak=facadeBL.getEventsSport(kirola);
				gertaera=null;
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ez duzu gertaerarik hautatu"));
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ez duzu kirolik hautatu"));
		} 
	}


}
