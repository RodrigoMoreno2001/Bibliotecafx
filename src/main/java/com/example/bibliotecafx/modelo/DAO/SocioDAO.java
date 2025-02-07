package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Socio;
import com.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SocioDAO implements ISocioDAO {


    @Override
    public void guardarSocio(Socio socio) {
        Transaction transaction=null;

        try(Session session= HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.persist(socio);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void modificarSocio(Socio socio) {
        Transaction transaction=null;

        try(Session session= HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.merge(socio);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void eliminarSocio(Socio socio) {

        Transaction transaction=null;

        try(Session session= HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.remove(socio);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<Socio> buscarSociosPorNombre(String nombre) {
        List<Socio> socios = new ArrayList<>();

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            socios = session.createQuery("from Socio where nombre = :nombre",Socio.class)
                    .setParameter("nombre",nombre)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return socios;
    }

    @Override
    public List<Socio> buscarSociosPorTelefono(String telefono) {
        List<Socio> socios = new ArrayList<>();

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            socios = session.createQuery("from Socio where telefono = :telefono",Socio.class)
                    .setParameter("telefono",telefono)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return socios;
    }

    @Override
    public List<Socio> listarSocios() {
        List<Socio> socios = new ArrayList<>();

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            socios = session.createQuery("from Socio",Socio.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return socios;
    }
}
