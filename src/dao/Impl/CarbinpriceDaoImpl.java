package dao.Impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtils;

import dao.CarbinpriceDao;
import entity.Carbinprice;
import entity.Flights;

public class CarbinpriceDaoImpl implements CarbinpriceDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
	    this.sessionFactory = sessionFactory;
	}
	public void save(Carbinprice carbinprice) {
		// TODO Auto-generated method stub
		HibernateUtils hu = new HibernateUtils();
		Session session = hu.getSession();
		Transaction ts = session.beginTransaction();
		session.save(carbinprice);
		ts.commit();
		hu.closeSession(session);
	}
	public Carbinprice findByflightId(int flightid) {
		// TODO Auto-generated method stub
		HibernateUtils hu = new HibernateUtils();
		Session session = hu.getSession();
		String hql = "from Carbinprice as c where c.flightid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, flightid);
		List<Carbinprice> list = query.list();
		Iterator<Carbinprice> iterator = list.iterator();
		if(iterator.hasNext()){
			Carbinprice  carbinprice= iterator.next();
			hu.closeSession(session);
			return carbinprice;
			}else{
				hu.closeSession(session);
				return null;
			}
	}
}
