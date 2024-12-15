/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author M S I
 */
public class Peminjaman {

    private int idpeminjaman;
    private Buku buku = new Buku();
    private Anggota anggota = new Anggota();
    private Pegawai pegawai = new Pegawai();
    private LocalDate tanggalpinjam;
    private LocalDate tanggalkembali;

    public Peminjaman(Buku buku, Anggota anggota, Pegawai pegawai, LocalDate tanggalpinjam, LocalDate tanggalkembali) {
        this.buku = buku;
        this.anggota = anggota;
        this.pegawai = pegawai;
        this.tanggalpinjam = tanggalpinjam;
        this.tanggalkembali = tanggalkembali;
    }

    public Peminjaman() {
    }

    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public void setTanggalpinjam(LocalDate tanggalpinjam) {
        this.tanggalpinjam = tanggalpinjam;
    }

    public void setTanggalkembali(LocalDate tanggalkembali) {
        this.tanggalkembali = tanggalkembali;
    }

    public int getIdpeminjaman() {
        return idpeminjaman;
    }

    public Buku getBuku() {
        return buku;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public LocalDate getTanggalpinjam() {
        return tanggalpinjam;
    }

    public LocalDate getTanggalkembali() {
        return tanggalkembali;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public Peminjaman getById(int id) {
        Peminjaman peminjaman = new Peminjaman();
        ResultSet rs = DBHelper.selectQuery(
                "SELECT "
                + "p.idpeminjaman AS idpeminjaman, "
                + "p.tanggalpinjam AS tanggalpinjam, "
                + "p.tanggalkembali AS tanggalkembali, "
                + "b.idbuku AS idbuku, "
                + "b.judul AS judul, "
                + "a.idanggota AS idanggota, "
                + "a.nama AS nama, " 
                + "g.idpegawai AS idpegawai, " 
                + "g.nama AS namapeg " 
                + "FROM peminjaman p "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
                + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN pegawai g ON p.idpegawai = g.idpegawai "
                + "WHERE p.idpeminjaman = '" + id + "'"); 

        try {
            while (rs.next()) {
                peminjaman = new Peminjaman();
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getPegawai().setidpegawai(rs.getInt("idpegawai"));
                peminjaman.getPegawai().setnama(rs.getString("namapeg"));
                peminjaman.setTanggalpinjam(rs.getDate("tanggalpinjam").toLocalDate());
                peminjaman.setTanggalkembali(rs.getDate("tanggalkembali").toLocalDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return peminjaman;
    }

    public ArrayList<Peminjaman> getAll() {
    ArrayList<Peminjaman> ListPeminjaman = new ArrayList<>();

    ResultSet rs = DBHelper.selectQuery(
        "SELECT "
        + "p.idpeminjaman AS idpeminjaman, "
        + "p.tanggalpinjam AS tanggalpinjam, "
        + "p.tanggalkembali AS tanggalkembali, "
        + "b.idbuku AS idbuku, "
        + "b.judul AS judul, "
        + "a.idanggota AS idanggota, "
        + "a.nama AS nama, "
        + "g.idpegawai AS idpegawai, "
        + "g.nama AS namapeg "
        + "FROM peminjaman p "
        + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
        + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
        + "LEFT JOIN pegawai g ON p.idpegawai = g.idpegawai");

    try {
        while (rs.next()) {
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
            peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
            peminjaman.getAnggota().setNama(rs.getString("nama"));
            peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
            peminjaman.getBuku().setJudul(rs.getString("judul"));
            peminjaman.getPegawai().setidpegawai(rs.getInt("idpegawai"));
            peminjaman.getPegawai().setnama(rs.getString("namapeg"));

            Date tanggalPinjamDate = rs.getDate("tanggalpinjam");
            if (tanggalPinjamDate != null) {
                peminjaman.setTanggalpinjam(tanggalPinjamDate.toLocalDate());
            }

            Date tanggalKembaliDate = rs.getDate("tanggalkembali");
            if (tanggalKembaliDate != null) {
                peminjaman.setTanggalkembali(tanggalKembaliDate.toLocalDate());
            }

            ListPeminjaman.add(peminjaman);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return ListPeminjaman;
}

    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList<>();

        ResultSet rs = DBHelper.selectQuery(
                "SELECT "
                + "p.idpeminjaman AS idpeminjaman, "
                + "p.tanggalpinjam AS tanggalpinjam, "
                + "p.tanggalkembali AS tanggalkembali, "
                + "b.idbuku AS idbuku, "
                + "b.judul AS judul, "
                + "a.idanggota AS idanggota, "
                + "a.nama AS nama "
                + "g.idpegawai AS idpegawai, "
                + "g.nama AS namapeg "
                + "FROM peminjaman p "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
                + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN pegawai g ON p.idpegawai = g.idpegawai "
                + "WHERE p.tanggalpinjam LIKE '%" + keyword + "%' "
                + "OR p.tanggalkembali LIKE '%" + keyword + "%' "
                + "OR b.judul LIKE '%" + keyword + "%' "
                + "OR a.nama LIKE '%" + keyword + "%' "
                + "OR g.nama LIKE ;%" + keyword + "%' ");

        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getPegawai().setidpegawai(rs.getInt("idpegawai"));
                peminjaman.getPegawai().setnama(rs.getString("namapeg"));
                peminjaman.setTanggalpinjam(rs.getDate("tanggalpinjam").toLocalDate());
                peminjaman.setTanggalkembali(rs.getDate("tanggalkembali").toLocalDate());

                ListPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListPeminjaman;
    }

    public void save() {

        if (getById(idpeminjaman).getIdpeminjaman() == 0) {
            String SQL = "INSERT INTO peminjaman (idanggota, idbuku, idpegawai, tanggalPinjam, tanggalKembali) VALUES("
                    + this.getPegawai().getidpegawai() + ", "
                    + this.getAnggota().getIdanggota() + ", "
                    + this.getBuku().getIdbuku() + ", "
                    + "'" + this.tanggalpinjam + "', "
                    + "'" + this.tanggalkembali + "'"
                    + ")";
            this.idpeminjaman = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE peminjaman SET "
                    + "idpegawai = " + this.getPegawai().getidpegawai() + ", "
                    + "idanggota = " + this.getAnggota().getIdanggota() + ", "
                    + "idbuku = " + this.getBuku().getIdbuku() + ", "
                    + "tanggalPinjam = '" + this.tanggalpinjam + "', "
                    + "tanggalKembali = '" + this.tanggalkembali + "' "
                    + "WHERE idpeminjaman = '" + this.idpeminjaman + "'";
            DBHelper.executeQuery(SQL);
        }

    }
    
    public void update() {
        String SQL = "UPDATE peminjaman SET "
                + "tanggalkembali = " + (this.tanggalkembali != null ? "'" + this.tanggalkembali + "'" : "NULL") + " "
                + "WHERE idpeminjaman = '" + this.idpeminjaman + "';";

        try {
            DBHelper.executeQuery(SQL);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    

    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman= '" + this.idpeminjaman + "';";
        DBHelper.executeQuery(SQL);
    }

}