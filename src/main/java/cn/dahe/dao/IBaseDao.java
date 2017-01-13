package cn.dahe.dao;

public interface IBaseDao<T> {

	void add(T t);

	Integer save(T t);

	void update(T t);

	T load(int id);

	void delete(int id);

	T get(int id);

	/**
	 * 根据名称和所属店面id判断是否已存在
	 * @param name
	 * @param storeId
	 * @return
	 */
	T findByName(String name, int storeId);
}
