package dados;

import java.util.List;
import negocio.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import negocio.Bem;
import negocio.GerenciadorDAO;
import negocio.Lance;
import negocio.Leilao;

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
                    + "USUARIO CHAR(11) NOT NULL,"
                    + "CONSTRAINT LANCES_LEILAO_FK FOREIGN KEY (LEILAO) REFERENCES Leiloes (ID)"
                    + "CONSTRAINT LANCES_PESSOAS_FK FOREIGN KEY (USUARIO) REFERENCES Pessoas (CPFCNPJ)"
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
                    "INSERT INTO PESSOAS (CPFCNPJ, NOME, EMAIL) VALUES (?,?,?)" 
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
    
    private boolean adicionarLeilaoTeste() throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Leiloes (NOME, CATEGORIA, DONO, LANCEMIN, STATUS) VALUES (?,?,?,?,?)" 
                    );
            stmt.setString(1, "Leilão de Bicicletas");
            stmt.setString(2, "Esportivo");
            stmt.setString(3, "12212212288");
            stmt.setString(4, "1000");
            stmt.setString(5, "criado");

            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao adicionar.", ex);
        }
    }
    
    private boolean adicionarBemTeste() throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Bens (LEILAO, NOME, DESCB, DESCD) VALUES (?,?,?,?)" 
                    );
            stmt.setString(1, "1");
            stmt.setString(2, "calor cross");
            stmt.setString(3, "Croizinha cromada");
            stmt.setString(4, "Duas rodas, dois pedais, muito estilosa");

            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao adicionar.", ex);
        }
    }
    
    private boolean removerBemTeste() throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "DELETE FROM Leiloes WHERE ID = (?)" 
                    
                    );
            stmt.setString(1, "2");


            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao adicionar.", ex);
        }
    }
    
    private boolean adicionarLanceTeste() throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Lances (LEILAO, VALOR, USUARIO) VALUES (?,?,?)" 
                    );
            stmt.setString(1, "1");
            stmt.setString(2, "1200");
            stmt.setString(3, "12212212288");

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
                    "INSERT INTO PESSOAS (CPFCNPJ, NOME, EMAIL) VALUES (?,?,?)" 
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
        //adicionarLeilaoTeste();
        //adicionarBemTeste();
        //adicionarLanceTeste();
        //deleteDB();
        //createDB();
        //adicionarPessoaTeste();
        //removerBemTeste();
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
    
    private ArrayList<Leilao> getLeiloes() throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();

        ArrayList<Leilao> lista = new ArrayList<>();

        ResultSet resultadoLeilao = stmt.executeQuery("SELECT * FROM LEILOES");
        while (resultadoLeilao.next()) {
            String leilaoID = resultadoLeilao.getString("ID");
            String nome = resultadoLeilao.getString("NOME");
            String categoria = resultadoLeilao.getString("CATEGORIA");
            String dono = resultadoLeilao.getString("DONO");
            Double lanceMin = Double.parseDouble(resultadoLeilao.getString("LANCEMIN"));
            String status = resultadoLeilao.getString("STATUS");

            Leilao l = new Leilao(leilaoID, nome, categoria, dono, lanceMin, status);
            lista.add(l);
        }
        return lista;
    }
    
    private ArrayList<Bem> getBens(String leilaoID) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Bem> lote = new ArrayList<>();
        ResultSet resultadoBem = stmt.executeQuery("SELECT * FROM BENS WHERE LEILAO = " + leilaoID);
        while (resultadoBem.next()) {
            String bemId = resultadoBem.getString("ID");
            String bemNome = resultadoBem.getString("NOME");
            String bemDescriBreve = resultadoBem.getString("DESCB");
            String bemDescriDetalh = resultadoBem.getString("DESCD");

            Bem b = new Bem(bemId, bemNome, bemDescriBreve, bemDescriDetalh);
            lote.add(b);
        }
        return lote;
    }
    
    private ArrayList<Lance> getLances(String leilaoID) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Lance> lances = new ArrayList<>();
        ResultSet resultadoLance = stmt.executeQuery("SELECT * FROM LANCES WHERE LEILAO = " + leilaoID);
        while(resultadoLance.next()) {
            String lanceId = resultadoLance.getString("ID");
            Double lanceValor = Double.parseDouble(resultadoLance.getString("VALOR"));
            String lanceUsuario = resultadoLance.getString("USUARIO");

            Lance lance = new Lance(lanceId, lanceValor, lanceUsuario);
            lances.add(lance);
        }
        return lances;
    }

    @Override
    public List<Leilao> getTodosLeiloes() throws GerenciadorDAOException {
        try {
            List<Leilao> listaLeilao = getLeiloes();
            List<Bem> listaBens;
            for (Leilao l : listaLeilao) {
                l.setLote(getBens(l.getId()));
                l.setLances(getLances(l.getId()));
            }

            return listaLeilao;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<String> getTodosLeiloesTitulo() throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();

            ArrayList<String> lista = new ArrayList<>();

            ResultSet resultadoLeilao = stmt.executeQuery("SELECT * FROM LEILOES");
            while (resultadoLeilao.next()) {
                String nome = resultadoLeilao.getString("NOME");
                lista.add(nome);
            }
            return lista;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public String getLeilaoCategoria(String leilao) throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM LEILOES WHERE NOME = ?"
                    );
            stmt.setString(1, leilao);
            ResultSet resultado = stmt.executeQuery();
            String categoria = "";
            if(resultado.next()) {
                categoria = resultado.getString("CATEGORIA");
            }
            return categoria;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public String getLeilaoDono(String leilao) throws GerenciadorDAOException {
                try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM LEILOES WHERE NOME = ?"
                    );
            stmt.setString(1, leilao);
            ResultSet resultado = stmt.executeQuery();
            String dono = "";
            if(resultado.next()) {
                dono = resultado.getString("DONO");
            }
            return dono;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public String getLeilaoLanceMin(String leilao) throws GerenciadorDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM LEILOES WHERE NOME = ?"
                    );
            stmt.setString(1, leilao);
            ResultSet resultado = stmt.executeQuery();
            String lanceMin = "";
            if(resultado.next()) {
                lanceMin = resultado.getString("LANCEMIN");
            }
            return lanceMin;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public String getLeilaoStatus(String leilao) throws GerenciadorDAOException {
                try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM LEILOES WHERE NOME = ?"
                    );
            stmt.setString(1, leilao);
            ResultSet resultado = stmt.executeQuery();
            String status = "";
            if(resultado.next()) {
                status = resultado.getString("STATUS");
            }
            return status;
        } catch (SQLException ex) {
            throw new GerenciadorDAOException("Falha ao buscar.", ex);
        }
    }
}
