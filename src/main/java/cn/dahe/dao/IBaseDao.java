package cn.dahe.dao;

public interface IBaseDao<T> {

	void add(T t);

	int addAndGetId4Integer(T t);

	Integer save(T t);

	void update(T t);

	T load(int id);

	void delete(int id);

	T get(int id);
}
