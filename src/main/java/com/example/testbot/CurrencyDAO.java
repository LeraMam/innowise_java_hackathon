package com.example.testbot;

import com.example.testbot.CurrencyState;
import com.example.testbot.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CurrencyDAO {
    public void save(CurrencyState currencyState) {
        Session session = HibernateConnect.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(currencyState);
        tx1.commit();
        session.close();
    }
}
