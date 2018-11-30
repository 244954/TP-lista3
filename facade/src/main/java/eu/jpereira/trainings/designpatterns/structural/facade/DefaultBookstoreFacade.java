package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.BookDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerNotificationService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.OrderingService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.WharehouseService;

public class DefaultBookstoreFacade implements BookstoreFacade {

	WharehouseService warehouseService;
	CustomerDBService customerService;
	BookDBService bookService;
	OrderingService orderingService;
	CustomerNotificationService cns;
	
	public DefaultBookstoreFacade() {
		
	}

	@Override
	public void placeOrder(String customerId, String isbn) {
		Book b;
		Customer c;
		Order o;
		DispatchReceipt d;
		b=this.bookService.findBookByISBN(isbn);
		c=this.customerService.findCustomerById(customerId);
		o=this.orderingService.createOrder(c, b);
		d=this.warehouseService.dispatch(o);
		cns.notifyClient(d);
	}
	public void setCustomerService(CustomerDBService c)
	{
		this.customerService=c;
	}
	public void setWarehouseService(WharehouseService w)
	{
		this.warehouseService=w;
	}
	public void setBookDBService(BookDBService b)
	{
		this.bookService=b;
	}
	public void setOrderingService(OrderingService o)
	{
		this.orderingService=o;
	}
	public void setCustomerNotificationService(CustomerNotificationService cns)
	{
		this.cns=cns;
	}

}
