package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.entity.Pet;
import com.util.HibernateSessionFactory;

public class PetDao {
	public List<Pet> qeuryPets(Pet p){
		List<Pet> petList = null;
		
		Session session = HibernateSessionFactory.getSession();
		
		Criteria query = session.createCriteria(Pet.class);
		
		// 如果主键不为空，则按主键查询，否则进行组合查询
		if (p.getId() != null){
			query.add(Restrictions.idEq(p.getId()));
		} else {
			if (p.getPname() != null){
				query.add(Restrictions.like("pname", "%"+p.getPname()+"%"));
			} 
			
			if (p.getSex() != null){
				query.add(Restrictions.eq("sex", p.getSex()));
			}
			
			if (p.getColor() != null){
				query.add(Restrictions.eq("color", p.getColor()));
			}
			
			if (p.getBts() != null){
				query.add(Restrictions.in("bloodtype", p.getBts()));
			}
			
			if (p.getBeginAge() != null && p.getEndAge() != null){
				//query.add(Restrictions.between("age", p.getBeginAge(), p.getEndAge()));
				query.add(Restrictions.and(
						Restrictions.gt("age", p.getBeginAge()), 
						Restrictions.lt("age", p.getEndAge())));
			}
			
		}	// if ...else 
		
		petList = query.list();
		System.out.println(petList.size());
		
		for (Pet pet : petList){
			System.out.println(pet.getId() + "::" + pet.getPname());
		}
		
		
		session.close();
		return petList;
	}
	
	public static void main(String[] args) {
		PetDao petDao = new PetDao();
		
		Pet p = new Pet();
		//p.setId(1);
		p.setPname("t");
		p.setBeginAge(2);
		p.setEndAge(6);
		/*List bts = new ArrayList();
		bts.add(1);
		bts.add(0);
		p.setBts(bts);*/
		petDao.qeuryPets(p);
	}
}
