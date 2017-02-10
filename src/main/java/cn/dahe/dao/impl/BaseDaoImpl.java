package cn.dahe.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import cn.dahe.dto.Pager;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import cn.dahe.dao.IBaseDao;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
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
	public boolean add(T t) {
		boolean b = false;
		try {
			Serializable io = this.getHibernateTemplate().save(t);
			b = io != null;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public int addAndGetId4Integer(T t) {
		int id = 0;
		try{
			id = (int) this.getHibernateTemplate().save(t);
		}catch (Exception e){
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean update(T t) {
		boolean b = false;
		try {
			this.getHibernateTemplate().update(t);
			b = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(int id) {
		return (T) this.getHibernateTemplate().load(getClz(), id);
	}

	@Override
	public T get(int id){
		return (T) this.getHibernateTemplate().get(getClz(), id);
	}

	@Override
	public boolean delete(int id) {
		boolean b = false;
		try {
			T t = this.load(id);
			this.getHibernateTemplate().delete(t);
			b = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public int delete(String hql, List<Object> list) {
		Query query = setParameterQuery(hql, list);
		return query.executeUpdate();
	}

	@Override
	public long getCount(String sql) {
		Query q = super.currentSession().createQuery(sql);
		return (long) q.uniqueResult();
	}

	/**
	 * 通过hql语句获取一个实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql, Object[] obj) {
		List<T> list = null;
		try {
			list = (List<T>) this.getHibernateTemplate().find(hql, obj);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 通过hql语句获取一个实体
	 */
	@Override
	public T get(String hql) {
		return get(hql, null);
	}

	/**
	 * 传入hql查询语句和object数组类型的参数，返回查询list集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String hql, Object[] obj) {
		List<T> list = null;
		try {
			list = (List<T>) this.getHibernateTemplate().find(hql, obj);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过hql语句查询List集合
	 */
	@Override
	public List<T> getList(String hql) {
		return getList(hql, null);
	}

	public List<T> list(String hql, Object[] objs){
		Query query = super.currentSession().createQuery(hql);
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
		Pager<T> Pager = new Pager<>();
		Long total = (Long)cq.uniqueResult();
		Pager.setStart(start);
		Pager.setAaData(datas);
		Pager.setiTotalDisplayRecords(total);
		Pager.setiTotalRecords(total);
		return Pager;
	}

    @Override
	public Pager<T> findWithOutPage(String hql, List<Object> list){
		Pager<T> Pager = new Pager<>();
		String cHql = getCountHql(hql);
		Query cq = setParameterQuery(cHql, list);
		Query q = setParameterQuery(hql, list);
		List<T> datas = q.list();
		Long total = (Long)cq.uniqueResult();
		Pager.setAaData(datas);
		Pager.setiTotalDisplayRecords(total);
		Pager.setiTotalRecords(total);
		return Pager;
	}

	public <N extends Object>List<N> listBySql(String sql,Object[] objs,Class<?> clz,boolean hasEntity){
		SQLQuery sq = super.currentSession().createSQLQuery(sql);
		setParam(sq, objs);
		if(hasEntity){
			sq.addEntity(clz);
		}else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		return sq.list();
	}

	@Override
	public <N extends Object>List<N> listBySqlAndList(String sql, List<Object> list, Class<?> clz, boolean hasEntity){
		SQLQuery sq = super.currentSession().createSQLQuery(sql);
		setParam(sq, list);
		if(hasEntity){
			sq.addEntity(clz);
		}else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		return sq.list();
	}

    @Override
	public <N extends Object>List<N> listBySql(String sql,Object obj,Class<?> clz,boolean hasEntity){
		return this.listBySql(sql, new Object[]{obj}, clz, hasEntity);
	}

    @Override
	public <N extends Object>List<N> listBySql(String sql,Class<?> clz,boolean hasEntity){
		return this.listBySql(sql, null, clz, hasEntity);
	}


	private Query setParameterQuery(String hql,Object[] objs){
		Query q = super.currentSession().createQuery(hql);
		if(objs != null){
			for(int i = 0; i < objs.length; i++){
				q.setParameter(i, objs[i]);
			}
		}
		return q;
	}

	private Query setParameterQuery(String hql, List<Object> list) {
		Query q = super.currentSession().createQuery(hql);
		if (null != list) {
			int len = list.size();
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					q.setParameter(i, list.get(i));
				}
			}
		}
		return q;
	}

	private void setParam(Query query,Object[] objs){
		if(objs != null && objs.length > 0){
			int index = 0;
			for(Object o : objs){
				query.setParameter(index++, o);
			}
		}
	}

	private void setParam(Query query, List<Object> list){
		if(list != null && list.size() > 0){
			for(int i = 0, len = list.size(); i < len; i++){
				query.setParameter(i, list.get(i));
			}
		}
	}

	private String getCountHql(String hql){
		String s = hql.substring(0, hql.indexOf("from"));
		if(StringUtils.isBlank(s)){
			hql = "select count(*) " + hql;
		}else{
			hql = hql.replace(s, "select count(*) ");
		}
		hql = hql.replace("fetch", "");
		return hql;
	}
}
