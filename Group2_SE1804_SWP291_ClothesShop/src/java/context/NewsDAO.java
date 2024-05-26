/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.News;

/**
 *
 * @author ADMIN
 */
public class NewsDAO extends DBContext{
      public ArrayList<News> pagging(int index){
        ArrayList<News> b = new ArrayList<>();
        try {
            String sql = "select * from `ClothesShop`.`News` order by nid limit 4 offset ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index-1)*4);
            ResultSet rs = stm.executeQuery();     
            while (rs.next()) {                
                b.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(6), rs.getBoolean(5)));
            }
        } catch (Exception e) {
        }
        return b;
    }
    
    public  int count(){
        try {
            String sql = "select count (*) from `ClothesShop`.`News`";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public ArrayList<News> detail(int blog_id){
        ArrayList<News> b = new ArrayList<>();
        try {
            String sql = "select * from `ClothesShop`.`News` where nid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, blog_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
               b.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(6), rs.getBoolean(5)));
            }
        } catch (Exception e) {
        }
        return b;
    }
    
    public ArrayList<News> list(){
        ArrayList<News> b = new ArrayList<>();
        try {
            String sql = "select * from `ClothesShop`.`News`";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                b.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(6), rs.getBoolean(5)));
            }
        } catch (Exception e) {
        }
        return b;
    }
    
   

}
