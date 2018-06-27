/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import negocio.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import negocio.GerenciadorDAO;
import negocio.Leilao;

/**
 *
 * @author Júlio
 */
public class GerenciadorDAOJavaDb implements GerenciadorDAO{
    private static GerenciadorDAOJavaDb ref;
    
    public static GerenciadorDAOJavaDb getInstance() throws GerenciadorDAOException {
        if (ref == null)
            ref = new GerenciadorDAOJavaDb();
        return ref;
    }
    
    private GerenciadorDAOJavaDb() throws GerenciadorDAOException {
        try {
             Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new GerenciadorDAOException("JdbcOdbDriver not found!!");
        }
        // Cria o banco de dados vazio
        // Retirar do comentário se necessário
        /*
        try {
            createDB();
        } catch (Exception ex) {
            System.out.println("Problemas para criar o banco: "+ex.getMessage());
            System.exit(0);
        }
        // Cria o banco de dados vazio
        // Retirar do comentário se necessário
        /*
        try {
            createDB();
        } catch (Exception ex) {
            System.out.println("Problemas para criar o banco: "+ex.getMessage());
            System.exit(0);
        }
        */
    }
    
    private void deleteDB() throws GerenciadorDAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement sta = con.createStatement();
            String sql = "DROP TABLE Pessoas";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new GerenciadorDAOException(ex.getMessage());
        }
    }
    
    private static void createDB() throws GerenciadorDAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement sta = con.createStatement();
            String query1 = "CREATE TABLE Pessoas ("
                    + "CPFCNPJ CHAR(11) NOT NULL CONSTRAINT PESSOAS_PK PRIMARY KEY,"
                    + "NOME VARCHAR(100) NOT NULL,"
                    + "EMAIL CHAR(50) NOT NULL"
                    + ")";
            
            String query2 = "CREATE TABLE Leiloes ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "NOME VARCHAR(100) NOT NULL,"
                    + "CATEGORIA VARCHAR(20) NOT NULL,"
                    + "DONO CHAR(11) NOT NULL,"
                    + "LANCEMIN DECIMAL(12,2) NOT NULL,"
                    + "STATUS VARCHAR(10) NOT NULL,"
                    + "CONSTRAINT LEILOES_FK FOREIGN KEY (DONO) REFERENCES Pessoas (CPFCNPJ)"
                    + ")";
                             
            String query3 = "CREATE TABLE Lances ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "LEILAO INTEGER NOT NULL,"
                    + "VALOR DECIMAL(12,2) NOT NULL,"
                    + "USUARIO VARCHAR(500) NOT NULL,"
                    + "CONSTRAINT LANCES_FK FOREIGN KEY (LEILAO) REFERENCES Leiloes (ID)"
                    + ")";
            
            String query4 = "CREATE TABLE Bens ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "LEILAO INTEGER NOT NULL,"
                    + "NOME VARCHAR(100) NOT NULL,"
                    + "DESCB VARCHAR(200) NOT NULL,"
                    + "DESCD VARCHAR(500) NOT NULL,"
                    + "CONSTRAINT BENS_FK FOREIGN KEY (LEILAO) REFERENCES Leiloes (ID)"
                    + ")";

            sta.executeUpdate(query1);
            sta.executeUpdate(query2);
            sta.executeUpdate(query3);
            sta.executeUpdate(query4);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new GerenciadorDAOException(ex.getMessage());
        }
    }
    
    private boolean adicionarPessoaTeste() throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO PESSOAS (CPFCNPJ, NOME, EMAIL) VALUES (?,?,?)" //                             1        2         3            4          5             6
                    );
            stmt.setString(1, "12212212288");
            stmt.setString(2, "Manolo Manolense");
            stmt.setString(3, "manolo_lol@hotmail.com");

            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao adicionar.", ex);
        }
    }
    
    private static Connection getConnection() throws SQLException {
        //derbyDB sera o nome do diretorio criado localmente
        return DriverManager.getConnection("jdbc:derby:derbyDB");
    }
    
    @Override
    public boolean adicionar(Pessoa p) throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO PESSOAS (CPFCNPJ, NOME, EMAIL) VALUES (?,?,?)" //                             1        2         3            4          5             6
                    );
            stmt.setString(1, p.getDocumento());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getEmail());

            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public Pessoa getPessoaPorNome(String n) throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM PESSOAS WHERE NOME=?"
                    );
            stmt.setString(1, n);
            ResultSet resultado = stmt.executeQuery();
            Pessoa p = null;
            if(resultado.next()) {
                String nome = resultado.getString("NOME");
                String email = resultado.getString("EMAIL");
                String documento = resultado.getString("CPFCNPJ");
                p = new Pessoa(nome, email, documento);
            }
            return p;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Pessoa> getTodos() throws GerenciadorDAOException {
        //deleteDB();
        //createDB();
        //adicionarPessoaTeste();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM PESSOAS ORDER BY NOME");
            List<Pessoa> lista = new ArrayList<>();
            while(resultado.next()) {
                String nome = resultado.getString("NOME");
                String email = resultado.getString("EMAIL");
                String documento = resultado.getString("CPFCNPJ");
                Pessoa p = new Pessoa(nome, email, documento);
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }    
    }
    
    @Override
    public Pessoa getPessoaPorDocumento(String n) throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM PESSOAS WHERE CPFCNPJ=?"
                    );
            stmt.setString(1, n);
            ResultSet resultado = stmt.executeQuery();
            Pessoa p = null;
            if(resultado.next()) {
                String nome = resultado.getString("NOME");
                String email = resultado.getString("EMAIL");
                String documento = resultado.getString("CPFCNPJ");
                p = new Pessoa(nome, email, documento);
            }
            return p;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }
    
    public List<Leilao> getTodosLeiloes(){
        
    }
}
