package pl.pk99.gwiazdy.data;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.pk99.gwiazdy.model.Gwiazda;
import pl.pk99.gwiazdy.model.Gwiazdozbior;

import java.util.List;

//Klasa bezpośrednio zarządza bazą danych (umożliwia zapisywanie, usuwanie oraz wczytywanie gwiazd z bazy)
/*
    Schemat bazy:
    Tabela GWIAZDY w relacji 1 do 1 z tabelą DEKLINACJE
    Tabela GWIAZDY w relacji 1 do 1 z tabelą REKTASCENSJE
    Tabela GWIAZDY w relacji * do 1 z tabelą GWIAZDOZBIORY
 */

public abstract class GwiazdyDatabaseManager {

    public static void uaktualnijWBazie(Gwiazda gwiazda) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(gwiazda);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void zapiszDoBazy(Gwiazda gwiazda) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(gwiazda);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void usunZBazy(Gwiazda gwiazda) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(gwiazda.getGwiazdozbior());
            gwiazda.setGwiazdozbior(null);
            session.delete(gwiazda);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public static List<Gwiazda> wczytajGwiazdyZBazy() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Gwiazda> gwiazdy = session.createQuery("from Gwiazda", Gwiazda.class).list();
            transaction.commit();
            return gwiazdy;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public static List<Gwiazdozbior> wczytajGwiazdozbioryZBazy() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Gwiazdozbior> gwiazdozbiory = session.createQuery("from Gwiazdozbior", Gwiazdozbior.class).list();
            transaction.commit();
            return gwiazdozbiory;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
