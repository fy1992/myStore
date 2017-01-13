package cn.dahe.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import cn.dahe.dto.Pager;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import cn.dahe.dao.IBaseDao;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	private Class<?> clz;

	public Class<?> getClz() {
		if(clz == null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	@Override
	public void add(T t) {
		this.getSession().save(t);
	}

	@Override
	public Integer save(T t) {
		return (Integer) this.getSession().save(t);
	}

	@Override
	public void update(T t) {
		this.getSession().update(t);
	}
	@Override
	public T load(int id) {
		return (T) this.getSession().load(getClz(), id);
	}

	@Override
	public T get(int id){
		return (T) this.getSession().get(getClz(), id);
	}

	@Override
	public void delete(int id) {
		T t = this.load(id);
		this.getSession().delete(t);
	}

    @Override
    public T findByName(String name, int storeId) {
        String hql = "from "+ getClz().getName() +" where name = ? and storeId = ?";
        return (T)queryByHql(hql, new Object[]{name, storeId});
    }

    public int delete(String hql, List<Object> list){
		Query query = setParameterQuery(hql, list);
		return query.executeUpdate();
	}

	private Query setParameterQuery(String hql,Object[] objs){
		Query q = this.getSession().createQuery(hql);
		if(objs!=null){
			for(int i=0; i<objs.length; i++){
				q.setParameter(i, objs[i]);
			}
		}
		return q;
	}

	private Query setParameterQuery(String hql, List<Object> list){
		Query q = this.getSession().createQuery(hql);
		if(null != list){
			int len = list.size();
			if(len > 0){
				for(int i=0;i<len;i++){
					q.setParameter(i, list.get(i));
				}
			}
		}
		return q;
	}

	public List<T> list(String hql,Object[] objs){
		Query query = null;
		query = this.getSession().createQuery(hql);
		if(objs!=null){
			query = setParameterQuery(hql, objs);
		}
		return query.list();
	}

	public List<T> list(String hql,Object obj){
		return this.list(hql, new Object[]{obj});
	}

	public List<T> list(String hql){
		return this.list(hql, null);
	}

	public Object queryByHql(String hql,Object[] objs){
		Query q = setParameterQuery(hql, objs);
		return q.uniqueResult();
	}

	public Object queryByHql(String hql,Object obj){
		return  this.queryByHql(hql, new Object[]{obj});
	}

	public Object queryByHql(String hql){
		return this.queryByHql(hql, null);
	}

	private String getCountHql(String hql){
		String s = hql.substring(0,hql.indexOf("from"));
		if(s==null||s.trim().equals("")){
			hql="select count(*) "+hql;
		}else{
			hql=hql.replace(s, "select count(*) ");
		}
		hql = hql.replace("fetch", "");
		return hql;
	}

	public List find(String hql, List<Object> list){
		Query q = setParameterQuery(hql, list);
		return q.list();
	}

	/**
	 * 分页查询（多个参数）
	 * */
	public Pager<T> find(String hql, List<Object> list, int start, int pageSize){
		String cHql = getCountHql(hql);
		Query cq = setParameterQuery(cHql ,list);
		Query q = setParameterQuery(hql, list);
		q.setFirstResult(start).setMaxResults(pageSize);
		List<T> datas = q.list();
		Pager<T> Pager = new Pager<T>();
		Long total = (Long)cq.uniqueResult();
		Pager.setStart(start);
		Pager.setAaData(datas);
		Pager.setiTotalDisplayRecords(total);
		Pager.setiTotalRecords(total);
		return Pager;
	}

	public Pager<T> findWithOutPage(String hql, List<Object> list){
		Pager<T> Pager = new Pager<T>();
		String cHql = getCountHql(hql);
		Query cq = setParameterQuery(cHql ,list);
		Query q = setParameterQuery(hql, list);
		List<T> datas = q.list();
		Long total = (Long)cq.uniqueResult();
		Pager.setAaData(datas);
		Pager.setiTotalDisplayRecords(total);
		Pager.setiTotalRecords(total);
		return Pager;
	}

	private void setParam(Query query,Object[] objs){
		if(objs!=null&&objs.length>0){
			int index = 0;
			for(Object o:objs){
				query.setParameter(index++, o);
			}
		}
	}

	private void setParam(Query query, List<Object> list){
		if(list != null && list.size()>0){
			for(int i = 0, len = list.size(); i < len; i++){
				query.setParameter(i, list.get(i));
			}
		}
	}

	public <N extends Object>List<N> listBySql(String sql,Object[] objs,Class<?> clz,boolean hasEntity){
		SQLQuery sq = this.getSession().createSQLQuery(sql);
		setParam(sq, objs);
		if(hasEntity){
			sq.addEntity(clz);
		}else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		return sq.list();
	}

	public <N extends Object>List<N> listBySqlAndList(String sql, List<Object> list, Class<?> clz, boolean hasEntity){
		SQLQuery sq = this.getSession().createSQLQuery(sql);
		setParam(sq, list);
		if(hasEntity){
			sq.addEntity(clz);
		}else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		return sq.list();
	}

	public <N extends Object>List<N> listBySql(String sql,Object obj,Class<?> clz,boolean hasEntity){
		return this.listBySql(sql, new Object[]{obj}, clz, hasEntity);
	}

	public <N extends Object>List<N> listBySql(String sql,Class<?> clz,boolean hasEntity){
		return this.listBySql(sql, null, clz, hasEntity);
	}

	public void updateByHql(String hql,Object ...args){
		Query query = this.getSession().createQuery(hql);
		setParam(query, args);
		query.executeUpdate();
	}

}
