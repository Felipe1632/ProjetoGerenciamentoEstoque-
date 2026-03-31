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
import model.ProdutoBean;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    public void cadastrarProduto (ProdutoBean produtos) {

    String sql = "INSERT INTO produtos (nome, preco, estoque) VALUES (?, ?, ?)";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
        conn = Conexao.conectar();
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
    //ADICIONAR
    public void adicionar(ProdutoBean produtos) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(
                    "INSERT INTO produtos (nome, preco, estoque) values (?, ?, ?)"
            );
            stmt.setString(1, produtos.getNome());
            stmt.setDouble(2, produtos.getPreco());
            stmt.setInt(3, produtos.getEstoque());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // METODO PARA LER
    public List<ProdutoBean> ler () {
            List<ProdutoBean> lerProdutos = new ArrayList();
            // read - select
            try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
           
            stmt = conn.prepareStatement("SELECT * FROM produtos");
           
            rs = stmt.executeQuery();
            while(rs.next()) {
                ProdutoBean produto = new ProdutoBean();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setEstoque(rs.getInt("estoque"));
               
                lerProdutos.add(produto);
            }
           
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return lerProdutos;
    }
    //METODO UPDATE
    public void update(ProdutoBean produtos) {
        try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement(
                "UPDATE produtos set nome = ?, preco = ?, estoque = ?  WHERE id = ?");
        stmt.setString(1, produtos.getNome());
        stmt.setDouble(2, produtos.getPreco());
        stmt.setInt(3, produtos.getEstoque());
        stmt.setInt(4, produtos.getId());
        stmt.executeUpdate();
        
    } catch(SQLException e) {
        e.printStackTrace();
    }
  }
    
    //METODO DELETE
    public void excluir(int id) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement(
                "DELETE FROM produtos WHERE id = ?" );
            stmt.setString(1, String.valueOf(id));
            
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

