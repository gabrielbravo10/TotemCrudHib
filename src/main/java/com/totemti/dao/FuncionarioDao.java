package com.totemti.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.totemti.model.Funcionario;
import com.totemti.util.HibernateUtil;

public class FuncionarioDao implements IFuncionarioDao {

	@Override
	public boolean saveFuncionario(Funcionario funcionario) {
		Transaction transaction = null;
		boolean flag = false;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start the transaction
			transaction = session.beginTransaction();

			// save employee object
			session.persist(funcionario);

			// commit the transaction
			transaction.commit();
			
			flag = true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> getFuncionarios() {
		List<Funcionario> funcionarios = null;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			funcionarios = session.createQuery("from Funcionario").list();

			transaction.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return funcionarios;
	}

	@Override
	public Funcionario getFuncionarioById(int id) {
		Funcionario funcionario = null;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			funcionario = session.get(Funcionario.class, id);

			transaction.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return funcionario;
	}

	@Override
	public boolean updateFuncionario(Funcionario funcionario) {
		boolean flag = false;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start the transaction
			transaction = session.beginTransaction();

			// update employee object
			session.saveOrUpdate(funcionario);

			// commit the transaction
			transaction.commit();

			flag = true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return flag;
	}

	@Override
	public boolean deleteFuncionario(int id) {
		boolean flag = false;
		Transaction transaction = null;
		Funcionario funcionario = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start the transaction
			transaction = session.beginTransaction();

			// delete employee object
			funcionario = session.get(Funcionario.class, id);
			session.delete(funcionario);

			// commit the transaction
			transaction.commit();
			flag = true;

			flag = true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return flag;
	
	}

}
