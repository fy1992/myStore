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

	List<T> list(String hql, Object[] obj);

	List<T> list(String hql);

	List<T> list(String hql, List<Object> list);

	long getCount(String sql);

	Pager<T> findWithOutPage(String hql, List<Object> list);

	int delete(String hql, List<Object> list);

	int delete(String hql, Object[] obj);

	int delete(String hql, Object obj);

	<N extends Object>List<N> listBySqlAndList(String sql, List<Object> list, Class<?> clz, boolean hasEntity);

	<N extends Object>List<N> listBySql(String sql, Object obj, Class<?> clz,boolean hasEntity);

	<N extends Object>List<N> listBySql(String sql, Class<?> clz, boolean hasEntity);
}
