package gui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import dao.CarProducerDAO;
import dao.ICarProducerDAO;
import pojo.Account;
import pojo.Car;
import pojo.CarProducer;
import pojo.CarRental;
import pojo.Customer;
import service.AccountService;
import service.CarRentalService;
import service.CarService;
import service.CustomerService;
import service.IAccountService;
import service.ICarRentalService;
import service.ICarService;
import service.ICustomerService;

public class gui {

	public static void main(String args[])
	{
		String file = "hibernate.cfg.xml";
		// TODO Auto-generated method stub
		IAccountService accountService = new AccountService(file);
		Account account1 = accountService.findById(15L);
		Account account = new Account();
		account.setAccountName("Anh Tu");
		account.setRole("customer");
		accountService.save(account);
		
		
		CarProducer carProducer = new CarProducer();
	ICarProducerDAO carProducerDAO = new CarProducerDAO(file);
		carProducer = carProducerDAO.findById(6L);
		ICarService carService = new CarService(file);
		Car car2 = carService.findById(7L);
		Car car = new Car();
		car.setCarName("Madza6");
		car.setCarModelYear(2004);
		car.setColor("Black");
		car.setCapacity(200);
		car.setDescription("description");
		LocalDate date = LocalDate.of(2004,03,10);
		car.setImportDate(date);
		car.setCarProducer(carProducer);
		car.setRentPrice(200);
		car.setStatus("Good");
		carService.save(car);
		
		Customer customer = new Customer();
		customer.setAccount(account);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localdate = LocalDate.of(2000,9,9);
		LocalDate localdate2 = LocalDate.of(2000,10,10);
		customer.setBirthday(localdate);
		customer.setCustomerName("Tu");
		customer.setMobile("0987654321");
		customer.setIdentityCard("01231231212");
		customer.setLicenceNumber("023923");
		customer.setLicenceDate(localdate);
		customer.setEmail("tu@gmail.com");
		customer.setPassword("1");
		ICustomerService customerService = new CustomerService(file);
		List<Customer> list = customerService.findAll();
		customerService.save(customer);
		System.out.println(customer);
		ICarRentalService carRentalService = new CarRentalService(file);
		List<CarRental> list2 = carRentalService.findByCarId(23L);
		for(CarRental o : list2) {
			System.out.println(o);
		}
		if(list2.isEmpty()) System.out.println("null");
////		customerService.save(customer);
////		Customer customer = customerService.findById(9L);
//		
//		Customer customer = customerService.searchByEmail("minh1@gmail.com");
//		System.out.println(customer.getCustomerName());
//		ICarRentalService carRentalService = new CarRentalService(file);
//		List<CarRental> list = carRentalService.findByCustomerId(9L);
////		List<CarRental> list = carRentalService.findAll();
//		if(list!=null) 
//			for(CarRental o : list) {
//				System.out.println(o);
//			}
//		CarRental carrental = new CarRental();
//		carrental.setCar(car);
//		carrental.setCustomer(customer);
//		carrental.setPickupDate(localdate2);
//		carrental.setReturnDate(localdate);
//		carrental.setRentPrice(200);
//		carrental.setStatus("processing");
//		carRentalService.save(carrental);
	}
		
	}


