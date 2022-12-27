package KlaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Sport;
import exceptions.EventFinished;
import facade.bean.FacadeBean;

public class CreateEventBean {
	private Date data;
	private String description;
	private static BLFacade facadeBL;
	private Sport sport;
	private static List<Sport> sports = new ArrayList<Sport>();	
	
	public CreateEventBean() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static BLFacade getFacadeBL() {
		return facadeBL;
	}

	public static void setFacadeBL(BLFacade facadeBL) {
		CreateEventBean.facadeBL = facadeBL;
	}
	
	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public List<Sport> getSports() {
		this.lortuSports();
		return sports;
	}

	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}
	
	public void lortuSports() {
		sports=facadeBL.getSports();
	}

	public void onButtonSelect() {
		try {
			if(sport!=null) {
				if(data!=null) {
					if(description!="") {
						facadeBL.gertaerakSortu(description, data, sport);
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Gertaera ondo sortu da"));
					}else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Ez duzu deskripziorik idatzi"));
					}
				}else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Ez duzu datarik hautatu"));
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ez duzu kirolik hautatu"));
			}
		} catch (EventFinished e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROR: Data dagoeneko pasa da"));
		} 
	}
}

