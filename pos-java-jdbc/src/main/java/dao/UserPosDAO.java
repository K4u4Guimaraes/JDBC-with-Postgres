package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn_jdbc.SingleConnection;
import model.BeanUserPhone;
import model.Phone;
import model.UserPosJava;

public class UserPosDAO {

	private Connection conn;

	public UserPosDAO() {
		conn = SingleConnection.getConnection();
	}

	public void insertUserDAO(UserPosJava user) {
		try {
			String sql = "Insert into userposjava (nameUser, email) VALUES (?,?)";
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, user.getName());
			insert.setString(2, user.getEmail());
			insert.execute();
			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("erro ao inserir, cheque os dados novamente");
		}
	}

	public List<UserPosJava> lis() throws Exception {
		List<UserPosJava> list = new ArrayList<UserPosJava>();
		String sql = "SELECT * FROM userposjava";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			UserPosJava user = new UserPosJava();
			user.setId(result.getLong("id"));
			user.setName(result.getString("nameUser"));
			user.setEmail(result.getString("email"));

			list.add(user);

		}

		return list;

	}
	
	
	public UserPosJava search(Long id) throws Exception {
		UserPosJava search = new UserPosJava();
		String sql = "SELECT * FROM userposjava WHERE id = "+id;
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			search.setId(result.getLong("id"));
			search.setName(result.getString("nameUser"));
			search.setEmail(result.getString("email"));


		}

		return search;

	}

	public void Update(UserPosJava user) {

		String sql = "UPDATE userposjava set nameUser = ? where id = " + user.getId();
		try {

			PreparedStatement upd = conn.prepareStatement(sql);
			upd.setString(1, user.getName());
			upd.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void Delete(Long id) {
		try {
			String sql = "DELETE FROM userposjava WHERE id = " + id;
			PreparedStatement delete = conn.prepareStatement(sql);
			delete.execute();
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
		public void insertPhone(Phone phone) {
			
			try {
					String sql = "INSERT INTO phoneuser (numberUser,phoneType,userPerson) VALUES (?,?,?)";
					PreparedStatement insertP = conn.prepareStatement(sql);
					insertP.setString(1, phone.getNumber());
					insertP.setString(2, phone.getType());
					insertP.setLong(3, phone.getIdUser());
					insertP.execute();
					conn.commit();
			}catch(Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
		public List<BeanUserPhone> listUserPhone(long idUser){
			List<BeanUserPhone> list = new ArrayList<BeanUserPhone>();
			String sql = "SELECT nameUser, numberUser,email FROM phoneuser AS phone INNER JOIN userposjava AS userp "
					+ " ON phone.userperson = userp.id"
					+ " WHERE userp.id = "+idUser;
			
			try {
				PreparedStatement inner = conn.prepareStatement(sql);
				ResultSet result = inner.executeQuery();
				conn.commit();
				while(result.next()){
					BeanUserPhone beanUser = new BeanUserPhone();
					beanUser.setName(result.getString("nameUser"));
					beanUser.setEmail(result.getString("email"));
					beanUser.setNumber(result.getString("numberUser"));
					
					list.add(beanUser);
					}
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
			return list;
		}
		
		public void deleteUserPhone(long idUser) {
			try {
			
				String sqlPhone = "DELETE FROM phoneuser WHERE userperson = " + idUser;
				String sqlUser = "DELETE FROM userposjava WHERE id = " + idUser;
			
				PreparedStatement deletePhone = conn.prepareStatement(sqlPhone);
				deletePhone.executeUpdate();
				conn.commit();
				
				PreparedStatement deleteUser = conn.prepareStatement(sqlUser);
				deleteUser.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}

}
