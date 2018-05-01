package com.mazurak.logos.Homework_U_C_C_JPQL_Criteria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mazurak.logos.Homework_U_C_C_JPQL_Criteria.entity.City;
import com.mazurak.logos.Homework_U_C_C_JPQL_Criteria.entity.Country;
import com.mazurak.logos.Homework_U_C_C_JPQL_Criteria.entity.User;

public class App {
	public static Random random = new Random();

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		// saveDate(em);

		// #1
		// em.createQuery("Select u from User u ",User.class)
		// .getResultList().forEach(System.out::println);
		//
		// #2 order by + DESC
		// em.createQuery("Select c from Country c order by c.id
		// desc",Country.class).getResultList().forEach(System.out::println);

		// #3 order by c.cityName
		// em.createQuery("Select c from City c order by
		// c.cityName",City.class).getResultList().forEach(System.out::println);

		// #4 order by u.fullName DESC
//		em.createQuery("Select u from User u order by u.fullName desc", User.class).getResultList()
//				.forEach(System.out::println);
		//#5 
//		em.createQuery("Select c from Country c where lower(c.nameCoutry) like lower(:start)",Country.class)
//			.setParameter("start", "a%").getResultList().forEach(System.out::println);
//		
		//#6 
//		em.createQuery("Select c from City c where c.cityName like :valF or c.cityName like :valS",City.class)
//			.setParameter("valF", "%n_")
//			.setParameter("valS", "%r_")
//			.getResultList().forEach(System.out::println);
		//#7 min(u.age)
//		Integer age = 
//		em.createQuery("Select min(u.age) from User u",Integer.class)
//			.getSingleResult();
//		System.out.println(age);
//		#8 avg(u.age)
//		Double age = 
//		em.createQuery("Select avg(u.age) from User u",Double.class)
//			.getSingleResult();
//		System.out.println(age);
		//#join
//		em.createQuery("Select u from User u join FETCH u.city",User.class)
//			.getResultList()
//			.forEach(System.out::println);
		// 2, 5, 9, 12, 13, 16
//		em.createQuery("Select u from User u join fetch u.city c where u.id <> 2 and u.id <>5 "
//				+ "and u.id <> 9 and u.id <> 13 and u.id <> 15 and u.id <> 16 ",User.class)
//			.getResultList().forEach(System.out::println);
		
//		em.createQuery("Select u from User u join Fetch u.city where u.id not in (:id)", User.class)
//			.setParameter("id", Arrays.asList(2,5,9,12,13,16))
//			.getResultList().forEach(System.out::println);
		
		em.createQuery("Select u from User u join fetch u.city uc join fetch uc.country ucc",User.class)
			.getResultList().forEach(System.out::println);
		
		em.getTransaction().commit();
		em.close();
		entityManagerFactory.close();

	}

	// свторити метод для заповнення даними

	public static void saveDate(EntityManager em) {
		List<String> countries = readData("countries");
		List<String> cities = readData("cities");
		List<String> users = readData("users");
		for (int i = 0; i < countries.size(); i++) {
			Country country = new Country();
			country.setNameCoutry(countries.get(i).trim());
			em.persist(country);
		}

		List<Country> countryQuery = em.createQuery("Select c from Country c", Country.class).getResultList();
		for (int i = 0; i < cities.size(); i++) {
			City city = new City();
			int coutryIndex = random.nextInt(countryQuery.size());
			city.setCityName(cities.get(i).trim());
			city.setCountry(countryQuery.get(coutryIndex));
			em.persist(city);
		}

		List<City> cityQuery = em.createQuery("Select c from City c", City.class).getResultList();
		for (int i = 0; i < cityQuery.size(); i++) {
			User user = new User();
			user.setFullName(cities.get(i).trim());
			user.setAge(10 + i++);
			int cityIndex = random.nextInt(cityQuery.size());
			user.setCity(cityQuery.get(cityIndex));
			em.persist(user);
		}

	}

	public static List<String> readData(String fileName) {
		List<String> fileData = new ArrayList<>();
		String filePath = fileName + ".txt";
		String line;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			while ((line = bufferedReader.readLine()) != null) {
				fileData.add(line);

			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileData;
	}
}
