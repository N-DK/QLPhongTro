package dao;

import java.util.ArrayList;
import java.util.List;
import static connectDatabase.Main.connect;

public class AbstractDAO<T> {
	protected String sqlFind;

	public List<T> findAll() {
		connect();
		List<T> resutls = new ArrayList<T>();
		return resutls;
	}

}
