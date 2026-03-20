/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    public void cadastrarProduto (ProdutoBean produtos) {

    String sql = "INSERT INTO produto (nome, preco, estoque) VALUES (?, ?, ?)";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
        conn = Conexao.getConexao();
        pstm = conn.prepareStatement(sql);

        pstm.setString(1, produtos.getNome());
        pstm.setDouble(2, produtos.getPreco());
        pstm.setInt(3, produtos.getEstoque());

        pstm.execute();

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
    } finally {
        try {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  

public void cadastrar(ProdutoBean produto) {

    String sql = "INSERT INTO produto (nome, preco, estoque) VALUES (?, ?, ?)";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
        conn = Conexao.getConexao();
        pstm = conn.prepareStatement(sql);

        pstm.setString(1, produto.getNome());
        pstm.setDouble(2, produto.getPreco());
        pstm.setInt(3, produto.getEstoque());

        pstm.execute();

        System.out.println("Produto inserido com sucesso!");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




    public List<ProdutoBean> listar(){
        List<ProdutoBean> produto = new ArrayList();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement(
                    "SELECT * FROM produto");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProdutoBean produtos = new ProdutoBean(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("estoque"));
                
                produto.add(produtos);

            }    rs.close(); stmt.close(); conn.close();
        } catch (SQLException e) { e.printStackTrace();}
        return produto;
  } 
}
