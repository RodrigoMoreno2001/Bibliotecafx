package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Libro;
import com.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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
        Transaction transaction=null;

        try(Session session= HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.merge(libro);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void eliminarLibro(Libro libro) {

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

        List<Libro> libros = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            libros = session.createQuery("from Libro", Libro.class)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public Libro obtenerLibrosPorTitulo(String aBuscar) {
        return null;
    }

    @Override
    public Libro obtenerLibrosPorAutor(String aBuscar) {
        return null;
    }
}
