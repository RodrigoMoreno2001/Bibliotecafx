package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AutorDAO implements IAutorDAO {
    @Override
    public void guardarAutor(Autor autor) {
        Transaction transaction=null;

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.persist(autor);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void actualizarAutor(Autor autor) {
        Transaction transaction=null;

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.merge(autor);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void eliminarAutor(Autor autor) {
        Transaction transaction=null;

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.remove(autor);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Autor> obtenerAutorPorNombre(String nombre) {
        List<Autor> autor=new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            autor = session.createQuery("from Autor where nombre LIKE :nombre", Autor.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return autor;
    }

    @Override
    public List<Autor> obtenerTodosAutores() {

        List<Autor> autores = new ArrayList<>();

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            autores = session.createQuery("from Autor",Autor.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }
}
