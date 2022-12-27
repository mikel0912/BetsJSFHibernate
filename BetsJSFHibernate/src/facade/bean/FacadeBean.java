package facade.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.HibernateDataAccessDAO;

public class FacadeBean {
	private static FacadeBean singleton = new FacadeBean( );
	private static BLFacade facadeInterface;
	private FacadeBean(){
		try { facadeInterface=new BLFacadeImplementation(new HibernateDataAccessDAO()); }
		catch (Exception e) {
			System.out.println(e);
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: "+e.getMessage());
		}}
	public static BLFacade getBusinessLogic( ) {
		return facadeInterface;
	}
}
