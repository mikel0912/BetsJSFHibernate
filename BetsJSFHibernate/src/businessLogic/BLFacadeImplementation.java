package businessLogic;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dataAccess.DataAccessInterface;
import domain.Event;
import domain.Question;
import domain.Registered;
import domain.Sport;
import domain.User;
import exceptions.ErabiltzaileaAlreadyExist;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation  implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
	}

	public BLFacadeImplementation(DataAccessInterface da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");

		da.emptyDatabase();
		da.open();
		da.initializeDB();
		da.close();

		dbManager=da;		
	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		dbManager.open();
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date)  {
		dbManager.open();
		List<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		dbManager.open();
		List<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}


	public void close() {
		//DataAccess dB4oManager=new DataAccess(false);

		//dB4oManager.close();
		dbManager.close();


	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeBD(){
		dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
	
	public Registered erregistratu(String username, String password, Integer bankAccount) {
		dbManager.open();
		Registered r=null;
		try {
			r= dbManager.storeRegistered(username, password, bankAccount);
		}catch(ErabiltzaileaAlreadyExist e){
			System.out.println(e.getMessage());
		}
		dbManager.close();
		return r;
	}
	
	public User login(String username, String password) {
		dbManager.open();
		User u = null;
		if(dbManager.isLogin(username, password)) {
			dbManager.open();
			u= dbManager.getUser(username);
		}
		dbManager.close();
		return u;
	}
	
	public boolean gertaerakSortu(String description,Date eventDate, Sport sport) throws EventFinished{
    	if(new Date().compareTo(eventDate)>0)
			throw new EventFinished("Gertaera honen data dagoeneko pasa da");
    	dbManager.open();
    	boolean b = dbManager.gertaerakSortu(description, eventDate, sport);
    	dbManager.close();
    	return b;
    }
	
	public List<Sport> getSports(){
		dbManager.open();
		List<Sport> s = dbManager.getSports();
		dbManager.close();
		return s;
	}
	
	
	public List<Event> getEventsSport(Sport sp){
		dbManager.open();
		List<Event> ev = dbManager.getEventsSport(sp);
		dbManager.close();
		return ev;
	}
	
	public boolean gertaeraEzabatu(Event ev) {
		dbManager.open();
		Boolean ema = dbManager.gertaeraEzabatu(ev);
		dbManager.close();
		return ema;
	}
	
	public List<Question> getQuestionDate(Date date){
		dbManager.open();
		List<Question> ema = dbManager.getQuestionDate(date);
		dbManager.close();
		return ema;
	}

}

