package com.example.bibliotecafx.modelo.DAO;

import com.example.bibliotecafx.modelo.entities.Autor;
import com.example.bibliotecafx.modelo.entities.Prestamo;
import com.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
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
    public void actualizarPrestamo(Prestamo prestamo) {
        Transaction transaction=null;

        try(Session session= HibernateUtil.getSessionFactory().openSession()){

            transaction= session.beginTransaction();

            session.merge(prestamo);

            transaction.commit();

        }catch (Exception e){

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Prestamo> obtenerPrestamos() {
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
        LocalDate fechaHoy=LocalDate.now();
        List<Prestamo> prestamos=new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            prestamos = session.createQuery("from Prestamo where fechaDevolucion is null or fechaDevolucion > :fechaHoy ", Prestamo.class)
                    .setParameter("fechaHoy",fechaHoy)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prestamos;

    }
}
