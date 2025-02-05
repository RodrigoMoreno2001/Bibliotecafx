package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutorDAO implements IAutorDAO {
    @Override
    public void guardarAutor(Autor autor) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.persist(autor);
            transaction.commit();
        }
    }

    @Override
    public void actualizarAutor(Autor autor) {

    }

    @Override
    public void eliminarAutor(Long idAutor) {

    }

    @Override
    public Autor obtenerAutorPorId(Long idAutor) {
        Autor autor=null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            autor = session.createQuery("from Autor where idAutor = :idAutor", Autor.class)
                    .setParameter("idAutor",idAutor)
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return autor;
    }

    @Override
    public List<Autor> obtenerTodosAutores() {
        return List.of();
    }
}
