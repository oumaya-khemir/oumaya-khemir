package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
    User findById(long id_AGENT);
	@Query(value = "SELECT u.`user_id` FROM `user` u join user_roles r on u.`user_id`=r.user_id WHERE r.role_id=1 ", nativeQuery = true)
	public List<String> findClient_pt_100(int nbpoint);



	/* liste des users */
	@Query(value = "SELECT user_id FROM user_roles WHERE role_id=1", nativeQuery = true)
	public List<Long> ListeUsers();
	
	@Modifying
	@Query(value = "UPDATE `user` SET `etat`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ConfirmerLiv(String etat1, long id);
	
	@Query(value = "SELECT `user_id` FROM `user` WHERE `etat`=?1 and `disponible`=?2 and `lieux_travail`=?3 ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public Long findparhasard(String etat,String dispo,String lieuxTravail);	
	
	/* Change disponibilité livreur */
	@Modifying
	@Query(value = "UPDATE `user` SET `disponible`=?1 WHERE `user_id`=?2", nativeQuery = true)
	public void ChangeDispo(String Dispo, long id);

	
	@Query(value = "SELECT * FROM `user` WHERE `username` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonChoix(String cle);
	@Query(value = "SELECT * FROM `user` WHERE `email` LIKE ?1%", nativeQuery = true)
	public List<User> getUserSelonEmail(String cle);
	
	@Query(value = "SELECT * FROM `user`  ORDER BY `signup_day` DESC LIMIT 8", nativeQuery = true)
	public List<User> getNewUsers();
	
	@Query(value = "SELECT COUNT(*) FROM user ", nativeQuery = true)
	public int NombreUsers();
	
	


	
	@Query(value = "SELECT SUM(salaire_brut) FROM `salaire`", nativeQuery = true)
	public float TotalSalaries();

	@Query(value = "SELECT count(*) from user where point_fidelite <100 ",nativeQuery = true)
	public int nombreUsersbyPointfideletInf100();
    //SELECT * FROM `user`  ORDER BY `signup_day` DESC LIMIT 3
	@Query(value = " SELECT count(*) from user where point_fidelite BETWEEN 100 and 300  ",nativeQuery = true)
	public int nombreUsersbyPointfideletbetwen100et300();
	@Query(value = " SELECT count(*)from user where point_fidelite >=300",nativeQuery=true)
	public int nombreUsersbyPointfideletSup300();
	@Query(value = "SELECT Avg(`point_fidelite`) FROM user",nativeQuery=true)
	public float moyenneNpointFidelet();

}
