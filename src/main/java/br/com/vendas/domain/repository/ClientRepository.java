package br.com.vendas.domain.repository;

import br.com.vendas.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByName(String name);

    @Query("select c from Client c left join fetch c.orderList where c.id = :id ")
    Client findClientFetchOrdering( @Param("id") Integer id );

    //Se quiser usar consulta customizada
    @Query(value = "select * from client c where c.name like '%:name%' ", nativeQuery = true)
    List<Client> encontrarPorNome(@Param("name") String name);

    //Não precisa colocar a query, somente se for customizada
    //Toda ação de alteração em banco de dados precisa colocar a anotation @Modifying
//    @Query("delete from Client c where c.name =:name ")
//    @Modifying
//    void deleteByName(String name);

//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public Client save(Client client){
//        entityManager.persist(client);
//        return client;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Client> getAll(){
//        return entityManager.createQuery("from Client", Client.class).getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Client> getByName(String name){
//        String jpql = "select c from Client c where c.name like :name";
//        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
//        query.setParameter("name", "%"+name+"%");
//        return query.getResultList();
//    }
//
//    @Transactional
//    public Client update(Client client){
//        entityManager.merge(client);
//        return client;
//    }
//
//    @Transactional
//    public void delete(Client client){
//        if (!entityManager.contains(client)){
//            client = entityManager.merge(client);
//        }
//        entityManager.remove(client);
//    }
//
//    @Transactional
//    public void delete(Integer id){
//        Client client = entityManager.find(Client.class, id);
//        delete(client);
//    }
}
