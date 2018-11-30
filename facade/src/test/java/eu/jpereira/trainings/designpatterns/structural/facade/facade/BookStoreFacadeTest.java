/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.structural.facade.facade;

import static org.mockito.Mockito.*;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.facade.BookstoreFacade;
import eu.jpereira.trainings.designpatterns.structural.facade.DefaultBookstoreFacade;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.BookDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerNotificationService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.OrderingService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.WharehouseService;

/**
 * @author windows
 * 
 */
public class BookStoreFacadeTest extends AbstractClientTest {

	BookDBService bookService;
	CustomerDBService customerService;
	WharehouseService warehouseService;
	OrderingService orderingService;
	CustomerNotificationService cns;
	
	@Test
	public void testPlaceOrder() {
		// Dummy literals
		String isbn = "123";
		String customerId = "wall-e";
		Book dummyBook = new Book(isbn);
		Customer dummyCustomer = new Customer(customerId);
		Order dummyOrder = new Order();
		DispatchReceipt dummyDispatchReceipt = new DispatchReceipt();
		
		bookService=mock(BookDBService.class);
		customerService=mock(CustomerDBService.class);
		warehouseService=mock(WharehouseService.class);
		orderingService=mock(OrderingService.class);
		cns=mock(CustomerNotificationService.class);


		// Prepare stubs
		when(bookService.findBookByISBN(isbn)).thenReturn(dummyBook);
		when(customerService.findCustomerById(customerId)).thenReturn(dummyCustomer);
		when(orderingService.createOrder(dummyCustomer, dummyBook)).thenReturn(dummyOrder);
		when(warehouseService.dispatch(dummyOrder)).thenReturn(dummyDispatchReceipt);
		
		// prepate SUT
		BookstoreFacade facade = createFacade();
		

		// Exercise SUT
		facade.placeOrder(customerId, isbn);
		//warehouseService.dispatch(dummyOrder);
		//customerNotificationService.notifyClient(dummyDispatchReceipt);

		// Verify behavior
		verify(warehouseService).dispatch(dummyOrder);
		verify(customerNotificationService).notifyClient(dummyDispatchReceipt);

	}

	/**
	 * @return
	 */
	protected BookstoreFacade createFacade() {
		// TODO: Implement the interface bookstoreFacade and set the
		// dependencies. We're using mocks, so you'll have to set the mocks as
		// dependencies of the facade
		// Example:
		// impl.setCustomerService(customerService)
		// impl.setWarehouseService(wharehouseService)
		// ...
		// Return an instance of your facade implementation
		DefaultBookstoreFacade impl;
		impl=new DefaultBookstoreFacade();
		
		impl.setCustomerService(customerService);
		impl.setWarehouseService(warehouseService);
		impl.setBookDBService(bookService);
		impl.setOrderingService(orderingService);
		impl.setCustomerNotificationService(customerNotificationService);		
		
		return impl;
	}
}
