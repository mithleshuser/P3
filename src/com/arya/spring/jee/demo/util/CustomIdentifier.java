package com.arya.spring.jee.demo.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdentifier implements IdentifierGenerator {
	private String defaultPrefix = "RVK";
	private int defaultNumber = 1;

	public CustomIdentifier() {
		System.out.println("printing ]t from identifier");
	}
	@Override
	public Serializable generate(SessionImplementor session, Object arg1) throws HibernateException {
		System.out.println("this is generate");
		String rvk_id = "";
		String digits = "";
		String mid = "";
		PreparedStatement pst = null;
		ResultSet rs = null;

		Connection con = session.connection();
		try {
			pst = con.prepareStatement("SELECT rvk_id FROM regdto order by rvk_id desc LIMIT 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rs != null && rs.next()) {
				System.out.println("rs != null && rs.next() If Block ");
				rvk_id = rs.getString("rvk_id");
				System.out.println(rvk_id);

				String prefix = rvk_id.substring(0, 3);
				String str1[] = rvk_id.split(prefix);

				String s = str1[1];
				mid = s.substring(0, 4);
				String str2[] = s.split(mid);

				DateFormat dateFormat = new SimpleDateFormat("yyyy");
				Calendar cal = Calendar.getInstance();
				mid = dateFormat.format(cal.getTime());
				prefix = prefix.concat(mid);

				digits = String.format("%05d", Integer.parseInt(str2[1]) + 1);

				rvk_id = prefix.concat(digits);
				System.out.println("If pat rvk_id :" + rvk_id);

			} else {
				System.out.println("else part");
				DateFormat dateFormat = new SimpleDateFormat("yyyy");
				Calendar cal = Calendar.getInstance();
				mid = dateFormat.format(cal.getTime());
				defaultPrefix = defaultPrefix.concat(mid);

				digits = String.format("%05d", defaultNumber);
				rvk_id = defaultPrefix.concat(digits);
				System.out.println(rvk_id + "\t employeed id");
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("**************return rvk id**************  " +rvk_id);
		return rvk_id;
	}
}
