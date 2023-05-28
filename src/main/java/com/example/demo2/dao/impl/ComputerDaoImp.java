package com.example.demo2.dao.impl;


import com.example.demo2.dao.ComputerDao;
import com.example.demo2.entities.Computer;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerDaoImp implements ComputerDao {

    private Connection conn = DB.getConnection();

    @Override
    public void insert(Computer computer) {

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO Computer (marque,name,prix,generation,typePros,ssd,anneFabri) " +
                    "VALUES (?,?,?,?,?,?,?)");

            ps.setString(1, computer.getMarque());
            ps.setString(2, computer.getName());

            ps.setInt(3, computer.getPrix());

            ps.setString(4, String.valueOf(computer.getGeneration()));

            ps.setString(5, String.valueOf(computer.getTypeProcesor()));

            ps.setBoolean(6, computer.isSsd());

            ps.setDate(7, (Date) computer.getAnneFabri());


            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);

                    computer.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            System.out.println(computer);
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void update(Computer computer) {
        PreparedStatement ps=null ;
        try {
            ps = conn.prepareStatement("Update Computer set marque=? ,name=? ,prix=? ,generation=?" +
                    " ,typePros=? ,ssd=? ,anneFabri=? where id=?");

            ps.setString(1, computer.getMarque());
            ps.setString(2, computer.getName());

            ps.setInt(3, computer.getPrix());

            ps.setString(4, String.valueOf(computer.getGeneration()));

            ps.setString(5, String.valueOf(computer.getTypeProcesor()));

            ps.setBoolean(6, computer.isSsd());

            ps.setDate(7, (Date) computer.getAnneFabri());

            ps.setInt(8,computer.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }   finally {
        DB.closeStatement(ps);
    }
}




    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps=null;
       try {
           ps = conn.prepareStatement("DELETE FROM COMPUTER WHERE id =?");
           ps.setInt(1,id);

           ps.executeUpdate();
       }
       catch (Exception e){
           e.printStackTrace();

       }finally {
           DB.closeStatement(ps);
       }

    }

    @Override
    public Computer findById(Integer id) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        Computer computer=new Computer();

        try {
        ps= conn.prepareStatement("Select marque,name,prix,generation,typePros,ssd,anneFabri from Computer where id=? ");
        ps.setInt(1,id);
        rs=ps.executeQuery();
        computer.setMarque(rs.getString(1));
        computer.setName(rs.getString(2));
        computer.setPrix(rs.getInt(3));
        computer.setGeneration(Computer.generations.valueOf( rs.getString(4)));
        computer.setTypeProcesor(Computer.typePros.valueOf(rs.getString(5)));
        computer.setSsd(rs.getBoolean(6));
        computer.setAnneFabri(rs.getDate(7));

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return computer;
    }

    @Override
    public List<Computer> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps=conn.prepareStatement("Select * from Computer");
            rs=ps.executeQuery();
            List<Computer> computerList = new ArrayList<>();
            while(rs.next()!=false){
                Computer computer=new Computer();
                computer.setMarque(rs.getString("marque"));
                /*System.out.println(rs.getString("marque"));

                System.out.println(rs.getString("name"));

                System.out.println(rs.getInt("prix"));

                System.out.println(Computer.generations.valueOf( rs.getString("generation")));
                System.out.println(Computer.typePros.valueOf(rs.getString("typePros")));
                System.out.println(rs.getBoolean("ssd"));
                System.out.println(rs.getDate("anneFabri"));*/
                computer.setId(rs.getInt("id"));
                computer.setName(rs.getString("name"));
                computer.setPrix(rs.getInt("prix"));
                computer.setGeneration(Computer.generations.valueOf( rs.getString("generation")));
                computer.setTypeProcesor(Computer.typePros.valueOf(rs.getString("typePros")));
                computer.setSsd(rs.getBoolean("ssd"));
                computer.setAnneFabri(rs.getDate("anneFabri"));
                computerList.add(computer);


            }
            return computerList;


        }catch (Exception e){e.printStackTrace();}finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }


        return null;
    }
}