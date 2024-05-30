/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.util.ArrayList;
import model.Brand;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author chien
 */
public class BrandDAO extends DBContext{
    
    public ArrayList<Brand> getAllCate() {
        ArrayList<Brand> listCate = new ArrayList<>();
        String sql = "select * from brand";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listCate.add(new Brand(rs.getInt("bid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCate;
    }

    public ArrayList<Brand> sortCateByName() {
        ArrayList<Brand> listCate = new ArrayList<>();
        String sql = "select * from brand order by name desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listCate.add(new Brand(rs.getInt("bid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCate;
    }

    public void addBrand(String name) {
        String sql = "insert into brand values (?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void deleteCate(int bid) {
        String sql = "delete from product\n"
                + "where bid=?\n"
                + "\n"
                + "delete from brand\n"
                + "where bid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, bid);
            stm.setInt(2, bid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCate(Brand c) {
        String sql = "Update brand set name = ? where bid =? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getName());
            stm.setInt(2, c.getBid());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Brand getCateById(int bid) {
        String sql = "select * from brand where bid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bid);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Brand(bid, rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
