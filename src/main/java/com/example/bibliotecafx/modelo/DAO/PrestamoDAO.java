package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Prestamo;
import com.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO implements IPrestamoDAO {
    @Override
    public void guardarPrestamo(Prestamo prestamo) {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){

            Transaction transaction = session.beginTransaction();
            session.persist(prestamo);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){

            Transaction transaction = session.beginTransaction();
            session.remove(prestamo);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> obtenerPrestamos(String nombre) {
        List<Prestamo> prestamos=new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            prestamos = session.createQuery("from Prestamo", Prestamo.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public List<Prestamo> obtenerPrestamosActuales() {
        return List.of();
    }
}
