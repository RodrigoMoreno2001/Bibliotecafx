package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Libro;
import com.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroDAO implements ILibroDAO {
    @Override
    public void guardarLibro(Libro libro) {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.persist(libro);
            transaction.commit();
        }
    }

    @Override
    public void actualizarLibro(Libro libro) {

    }

    @Override
    public void eliminarLibro(String ISBN) {

    }

    @Override
    public Libro obtenerLibroPorISBN(String ISBN) {

        Libro libro=null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            libro = session.createQuery("from Libro where ISBN = :ISBN", Libro.class)
                    .setParameter("id_autor",ISBN)
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public List<Libro> obtenerTodosLibros() {
        return List.of();
    }
}
