package dataAccess;

//hello
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Admin;
import domain.Event;
import domain.Question;
import domain.Registered;
import domain.Sport;
import domain.User;
import eredua.HibernateUtil;
import exceptions.ErabiltzaileaAlreadyExist;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class HibernateDataAccessDAO implements DataAccessInterface {
	private Session session;


	public HibernateDataAccessDAO()  {	
		open();
	}


	@Override
	public void open() {
		System.out.println("Opening DataBase");
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@Override
	public void initializeDB() {
		session.beginTransaction();
		try {
			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}
			
			Sport sp1 = new Sport("Futbola");
			Sport sp2 = new Sport("Saskibaloia");
			Sport sp3 = new Sport("Tennis");
			
			Event ev1 = sp1.addEvent("Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = sp1.addEvent("Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = sp1.addEvent("Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = sp1.addEvent("Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = sp1.addEvent("Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = sp1.addEvent("Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = sp1.addEvent("Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = sp1.addEvent("Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = sp1.addEvent("Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = sp1.addEvent("Betis-Real Madrid", UtilDate.newDate(year, month, 17));
			
			Event ev11 = sp1.addEvent("Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = sp1.addEvent("Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = sp1.addEvent("Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = sp1.addEvent("Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = sp1.addEvent("Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = sp1.addEvent("Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));
			
			Event ev17 = sp1.addEvent("Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = sp1.addEvent("Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = sp1.addEvent("Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = sp1.addEvent("Betis-Real Madrid", UtilDate.newDate(year, month, 28));

			/*Event ev1 = new Event("Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event("Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event("Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event("Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event("Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event("Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 28));*/

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}
			/*
			 session.persist(q1); 
			 session.persist(q2); 
			 session.persist(q3);
			 session.persist(q4); 
			 session.persist(q5); 
			 session.persist(q6);
			 */
			/*ev1.setSport(sp1);
			sp1.addEvent(ev1);
			ev2.setSport(sp1);
			sp1.addEvent(ev2);
			ev3.setSport(sp1);
			sp1.addEvent(ev3);
			ev4.setSport(sp1);
			sp1.addEvent(ev4);
			ev5.setSport(sp1);
			sp1.addEvent(ev5);
			ev6.setSport(sp1);
			sp1.addEvent(ev6);
			ev7.setSport(sp1);
			sp1.addEvent(ev7);
			ev8.setSport(sp1);
			sp1.addEvent(ev8);
			ev9.setSport(sp1);
			sp1.addEvent(ev9);
			ev10.setSport(sp1);
			sp1.addEvent(ev10);
			ev11.setSport(sp1);
			sp1.addEvent(ev11);
			ev12.setSport(sp1);
			sp1.addEvent(ev12);
			ev13.setSport(sp1);
			sp1.addEvent(ev13);
			ev14.setSport(sp1);
			sp1.addEvent(ev14);
			ev15.setSport(sp1);
			sp1.addEvent(ev15);
			ev16.setSport(sp1);
			sp1.addEvent(ev16);
			ev17.setSport(sp1);
			sp1.addEvent(ev17);
			ev18.setSport(sp1);
			sp1.addEvent(ev18);
			ev19.setSport(sp1);
			sp1.addEvent(ev19);
			ev20.setSport(sp1);
			sp1.addEvent(ev20);*/

			session.persist(sp1);
			session.persist(sp2);
			session.persist(sp3);
			
			/*session.persist(ev1);
			session.persist(ev2);
			session.persist(ev3);
			session.persist(ev4);
			session.persist(ev5);
			session.persist(ev6);
			session.persist(ev7);
			session.persist(ev8);
			session.persist(ev9);
			session.persist(ev10);
			session.persist(ev11);
			session.persist(ev12);
			session.persist(ev13);
			session.persist(ev14);
			session.persist(ev15);
			session.persist(ev16);
			session.persist(ev17);
			session.persist(ev18);
			session.persist(ev19);
			session.persist(ev20);*/

			User a = new Admin("admin", "123", 1234);
			session.persist(a);

			session.getTransaction().commit();
			System.out.println("DB initialized");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		session.beginTransaction();
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		System.out.println(session+" "+event);


		Event ev = (Event) session.get(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) {
			session.getTransaction().rollback();
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		}
		Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		session.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		session.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		session.beginTransaction();
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=:date");
		query.setParameter("date", date);
		List<Event> events = query.list();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		session.getTransaction().commit();
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		session.beginTransaction();
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :hasData and :bukData");   
		query.setParameter("hasData", firstDayMonthDate);
		query.setParameter("bukData", lastDayMonthDate);
		List<Date> dates = query.list();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		session.getTransaction().commit();
		return res;
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		session.beginTransaction();
		Event ev = (Event) session.get(Event.class, event.getEventNumber());
		session.getTransaction().commit();
		return ev.DoesQuestionExists(question);

	}

	@Override
	public void close(){
		//db.close();
		//System.out.println("DataBase closed");
		System.out.println("Closing DataBase");
		HibernateUtil.getSessionFactory().close();
		session = null;
	}



	@Override
	public void emptyDatabase() {

		/*File f=new File(c.getDbFilename());
		f.delete();
		File f2=new File(c.getDbFilename()+"$");
		f2.delete();*/

	}

	@Override
	public User getUser(String username) {
		session.beginTransaction();
		User  r = null;
		try {
			r = (User) session.get(User.class, username);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			session.getTransaction().rollback();
			return null;
		}
		return r;
	}

	@Override
	public Registered storeRegistered(String username, String password, Integer bankAccount) throws ErabiltzaileaAlreadyExist {
		Registered r =(Registered) this.getUser(username);
		this.open();
		if(r!=null) {
			throw new ErabiltzaileaAlreadyExist("Dagoeneko existitzen da erabiltzaile bat izen horrekin. Izena: " + username);
		}
		session.beginTransaction();
		Registered ad = new Registered(username, password, bankAccount);
		session.persist(ad);
		session.getTransaction().commit();
		return ad;
	}

	@Override
	public boolean isLogin(String username, String password) {
		User u = this.getUser(username);
		if(u!=null) {
			if(u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean gertaerakSortu(String description,Date eventDate, Sport sport) throws EventFinished{
		boolean b = true;
		session.beginTransaction();
		try {
			Sport spo =(Sport) session.get(Sport.class, sport.getSportNumber());
			System.out.println(spo);
			if(spo!=null) {
				Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate= :eventDate");   
				query.setParameter("eventDate", eventDate);
				List<Event> events = query.list();
				for(Event ev: events) {
					if(ev.getDescription().equals(description)) {
						b = false;
					}
				}
				if(b) {
					//Event e = new Event(description, eventDate);
					//e.setSport(spo);
					Event e = spo.addEvent(description, eventDate);
					session.persist(e);
				}
			}else {
				b= false;
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			session.getTransaction().rollback();
			return false;
		}
		return b;
	}

	public List<Sport> getSports() {
		session.beginTransaction();
		System.out.println(">> DataAccess: getSports");
		Vector<Sport> res = new Vector<Sport>();
		Query query = session.createQuery("SELECT sp FROM Sport sp");
		List<Sport> sports = query.list();
		for (Sport sp : sports) {
			System.out.println(sp.toString());
			res.add(sp);
		}
		session.getTransaction().commit();
		return res;
	}
	
	public List<Event> getEventsSport(Sport sp){
		session.beginTransaction();
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.sport=:sport");
		query.setParameter("sport", sp);
		List<Event> events = query.list();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		session.getTransaction().commit();
		return res;
	}
	
	public boolean gertaeraEzabatu(Event ev) {
		session.beginTransaction();
		System.out.println("Removing event");
		try {
			session.delete(ev);
			session.getTransaction().commit();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
	public List<Question> getQuestionDate(Date date){
		session.beginTransaction();
		System.out.println(">> DataAccess: getQuestionDate");
		Vector<Question> res = new Vector<Question>();
		Query query = session.createQuery("SELECT q FROM Question q WHERE q.event.eventDate=:date");
		query.setParameter("date", date);
		List<Question> questions = query.list();
		for (Question q : questions) {
			System.out.println(q.toString());
			res.add(q);
		}
		session.getTransaction().commit();
		return res;
		
	}

}

