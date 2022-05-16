/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reclamation;
//import util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;
import util.session;


/**
 *
 * @author Houcem
 */
public class ReclamationService {
	
	Connection cnx = MyDB.getInstance().getConnection();

	public void ajouter(Reclamation t) {
		try {
                
            String requete = "INSERT INTO reclamation (user_id,objet,desc_r,status) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, session.getSession().getId()+"");
            pst.setString(2, t.getObjet());
            pst.setString(3, t.getDesc());
            pst.setString(4,t.getStatus());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
	}


	public void supprimer(Reclamation t) {
		  try {
            String requete = "DELETE FROM reclamation WHERE idr=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	}


	public void modifier(Reclamation t) {
		   try {
            String requete = "UPDATE reclamation SET objet=?, desc_r=?, status=? WHERE idr=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getId());
            pst.setString(1, t.getObjet());
            pst.setString(2, t.getDesc());
            pst.setString(3, t.getStatus());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	}

	public List<Reclamation> afficher() {
		 ObservableList <Reclamation> ListProject =FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reclamation WHERE user_id = "+session.getSession().getId();
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ListProject.add(new Reclamation(rs.getInt("idr"),rs.getString("objet"),rs.getString("desc_r"),rs.getString("status")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return ListProject;
	}
        
	public List<Reclamation> search(String x,String y) {
		 ObservableList <Reclamation> ListProject = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM Reclamation inner join user on user.id = Reclamation.user_id where ( user.username like ? or email like ? or role like ? ) and user_id = "+session.getSession().getId();
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+x+"%");
			pst.setString(2, "%"+x+"%");
			pst.setString(3, "%"+x+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ListProject.add(new Reclamation(rs.getInt("id"),rs.getString("objet"),rs.getString("desc_r"),rs.getString("status")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return ListProject;
	}
	
}
