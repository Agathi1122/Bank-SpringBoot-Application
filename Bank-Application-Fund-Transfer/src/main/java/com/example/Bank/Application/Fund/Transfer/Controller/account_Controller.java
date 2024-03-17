package com.example.Bank.Application.Fund.Transfer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Application.Fund.Transfer.Entity.account;
import com.example.Bank.Application.Fund.Transfer.Entity.fund_Transfer;
import com.example.Bank.Application.Fund.Transfer.Service.account_service;


//restcontroller is an inbuild mapping method like annotaion
//responsible for creating restful controlllers
//Controllers are using in spring mvc
//unlike Rest Controllers are used in restful web services
//The web services that follows the REST architectural style is calle
//RESTful
//Web Services.
//It differentiates between the computer system and web services. 29 //The REST architectural style describes the six barriers.

@RestController
public class account_Controller {

	@GetMapping("/welcome")
	String welcomeMessage()
	{
		return "Welcome Home";
	}
	
	@Autowired
	account_service service;
	
	// 1..create operation
	//@Requestbody --> this is used to convert the body of the Http request
	//to the  java class object with the aid of selected HTTP message converter.
	// This annotation will be used in the method parameter and the body
	//of the http request will be mapped to that method parameter.-->
	// @PostMapping The POST HTTP method is used to create a resource 
	// and @PostMapping annotation for mapping HTTP POST requests onto specific handler methods.
	// Specifically, @PostMapping is a composed annotation that acts as shortcut
	// for @RequestMapping (method RequestMethod.POST)
	
	@PostMapping("/api/accounts")
	public ResponseEntity<account> createAccount(@RequestBody account creationOfAccount)
	{
		
		return service.createAccount(creationOfAccount);
	}
	
	//2..read opertion for all employess
	//@GetMapping annotation maps HTTP GET requests onto specific handler methods
	// It is a composed annotation that acts as a shortcut for @RequestMappin(method RequestMethod.GET)
	//The following application uses @GetMapping to map two request paths on handler methods.
	
	@GetMapping("/api/accounts")
	public List<account> getAllAccounts()
	{
	
		List<account> allAccounts=service.getAllAccount();
		return allAccounts;
	}
	
//3..read operation for specific or particular employee instead of using getmapping we can use requestmapping method..
// because we need particular employee. @Pathvariable refers to local variable finding specific employee_id when user enter their id
//so we need to iterate over the list employees refers to Arraylist value emp stores employee values
// initializing tepemp==null value because we need to store it..
	
	@RequestMapping(value="api/accounts/{account_id}",method= RequestMethod.GET)
	public account getAccount(@PathVariable int account_id)
	{
		account acc=service.getAccount(account_id);
		return acc;
	}
	
	
// 4.read opertion for employee
// The PUT HTTP method is used to update the resource and @PutMapping annotation for mapping HTTP PUT requests onto specific handler methods
// Specifically, @PutMapping is a composed annotation that acts as ashortcut for @RequestMapping(method = RequestMethod. PUT).
// update method also need employee object @RequestBody
	
	
	@PutMapping("/api/accounts/{account_id}")
	public account updateAccount(@PathVariable int account_id, @RequestBody account accountData) {
		account update_acc=service.updateAccount(account_id,accountData);
		return update_acc;
	}
	
//5. Delete operation The DELETE HTTP method is used to delete the resource
// and @DeleteMapping annotation for mapping HTTP DELETE requests onto specific handler methods.
// Specifically, @DeleteMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.DELETE)

	@DeleteMapping("/api/accounts/{account_id}")
	public account deleteAccount(@PathVariable int account_id)
	{
		account acc_delete=service.deleteAccount(account_id);
		return acc_delete;
	}
	
	
	@PostMapping("/api/accounts/transferFund/{fromAcc}/{toAcc}/{amount}")
	public account fundsTransfer(@PathVariable int from_acc,@PathVariable int to_acc,@PathVariable  double amount)
	{
		account acc=service.fundTransfer(from_acc,to_acc,amount);
		return acc;
	}
	//this is the right method to transfer amount 
	@PutMapping("/trans")
	public account fund(@RequestBody fund_Transfer transfer)
	{
		account acc=service.fundTransfer(transfer.getFrom(), transfer.getTo(), transfer.getAmount());
		return acc;
	}
	/**
	 * @GetMapping shortcut for @RequestMapping(method = RequestMethod.GET)

      @PostMapping shortcut for @RequestMapping(method = RequestMethod.POST)

      @PutMapping shortcut for @RequestMapping(method RequestMethod.PUT)

       @DeleteMapping shortcut for @RequestMapping(method RequestMethod. DELETE)

      @PatchMapping - shortcut for @RequestMapping(method = RequestMethod. PATCH)

       And also by using above request method we can access this based on the request from the Postman if it was put it used that request. as like remaining all..
	 */
}
