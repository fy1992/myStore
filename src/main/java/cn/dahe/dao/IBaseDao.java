package cn.dahe.dao;

import cn.dahe.dto.Pager;

import java.util.List;

public interface IBaseDao<T> {

	boolean add(T t);

	int addAndGetId4Integer(T t);

	boolean update(T t);

	T load(int id);

	boolean delete(int id);

	T get(int id);

	T get(String hql, Object[] obj);

	T get(String hql);

	List<T> getList(String hql, Object[] obj);

	List<T> getList(String hql);

	long getCount(String sql);

	Pager<T> findWithOutPage(String hql, List<Object> list);

	int delete(String hql, List<Object> list);

	<N extends Object>List<N> listBySqlAndList(String sql, List<Object> list, Class<?> clz, boolean hasEntity);

	<N extends Object>List<N> listBySql(String sql, Object obj, Class<?> clz,boolean hasEntity);

	<N extends Object>List<N> listBySql(String sql, Class<?> clz, boolean hasEntity);
}
