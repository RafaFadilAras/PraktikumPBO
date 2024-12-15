/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author M S I
 */
public class Pegawai {
    
    private int idpegawai;
    private String nama;
    private String alamat;
    private String notelp;
    private String jabatan;
    private String username;
    private String password;

    // Constructor
    public Pegawai() {
    }

     public Pegawai(String nama, String alamat, String noTelp, String jabatan, String username, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.notelp = notelp;
        this.jabatan = jabatan;
        this.username = username;
        this.password = password;
    }

    // Getter and Setter
   public int getidpegawai() {
        return idpegawai;
    }

    public void setidpegawai(int idpegawai) {
        this.idpegawai = idpegawai;
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getalamat() {
        return alamat;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }

    public String getnotelp() {
        return notelp;
    }

    public void setnotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getjabatan() {
        return jabatan;
    }

    public void setjabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    // Method to save or update a Pegawai
    public Pegawai getById(int id){
        Pegawai peg = new Pegawai();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai " 
                + "WHERE idpegawai = '" + id + "'");

        try {
            while(rs.next()){
                peg = new Pegawai();
                peg.setidpegawai(rs.getInt("idpegawai"));
                peg.setnama(rs.getString("nama"));
                peg.setalamat(rs.getString("alamat"));
                peg.setnotelp(rs.getString("notelp"));
                peg.setjabatan(rs.getString("jabatan"));
                peg.setusername(rs.getString("username"));
                peg.setpassword(rs.getString("password"));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       return peg;
    }
    
    public ArrayList<Pegawai> getAll(){
        ArrayList<Pegawai> ListPegawai = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai");
        
        try {
            while(rs.next()){
                Pegawai peg = new Pegawai();
                peg.setidpegawai(rs.getInt("idpegawai"));
                peg.setnama(rs.getString("nama"));
                peg.setalamat(rs.getString("alamat"));
                peg.setnotelp(rs.getString("notelp"));
                peg.setjabatan(rs.getString("jabatan"));
                peg.setusername(rs.getString("username"));
                peg.setpassword(rs.getString("password"));
                ListPegawai.add(peg);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return ListPegawai;
    }
    
    public ArrayList<Pegawai> search(String keyword){
         ArrayList<Pegawai> ListPegawai = new ArrayList<>();

          String sql = "SELECT * FROM pegawai WHERE "
            + "nama LIKE '%" + keyword + "%' "
            + "OR nama LIKE '%" + keyword + "%' "
            + "OR alamat LIKE '%" + keyword + "%' "
            + "OR notelp LIKE '%" + keyword + "%' "
            + "OR jabatan LIKE '%" + keyword + "%' "
            + "OR username LIKE '%" + keyword + "%' "
            + "OR password LIKE '%" + keyword + "%'";
            
         ResultSet rs = DBHelper.selectQuery(sql);
         
          try {
            while(rs.next()){
                Pegawai peg = new Pegawai();
                peg.setidpegawai(rs.getInt("idpegawai"));
                peg.setnama(rs.getString("nama"));
                peg.setalamat(rs.getString("alamat"));
                peg.setnotelp(rs.getString("notelp"));
                peg.setjabatan(rs.getString("jabatan"));
                peg.setusername(rs.getString("username"));
                peg.setpassword(rs.getString("password"));
                
                ListPegawai.add(peg);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return ListPegawai;
    }
    
   public void save() {
        if (getById(idpegawai).getidpegawai()== 0) {
            String SQL = "INSERT INTO pegawai (nama, alamat, notelp, jabatan, username, password) VALUES ("
            + "'" + this.nama + "', "
            + "'" + this.alamat + "', "
            + "'" + this.notelp + "', "
            + "'" + this.jabatan + "', "
            + "'" + this.username + "', "
            + "'" + this.password + "'"
            + ")";

            this.idpegawai = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE pegawai SET "
            + "nama = '" + this.nama + "', "
            + "alamat = '" + this.alamat + "', "
            + "notelp = '" + this.notelp + "', "
            + "jabatan = '" + this.jabatan + "', "
            + "username = '" + this.username + "', "
            + "password = '" + this.password + "' "
            + "WHERE idpegawai = '" + this.idpegawai + "'";

            DBHelper.executeQuery(SQL); 
        }
    }
    
    public void delete(){
        String SQL = "DELETE FROM pegawai WHERE idpegawai = '" + this.idpegawai + "'";
        DBHelper.executeQuery(SQL);
    }
    
    public boolean isUsernameExists(String username) {
        boolean result = false;
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai WHERE username = '" + username + "'");
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}