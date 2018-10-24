package hello;

import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice(basePackages="PersonEventHandler.class")
@RepositoryEventHandler(Person.class) 
public class PersonEventHandler extends AbstractRepositoryEventListener  {
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	public static ThreadLocal<TransactionStatus> threadLocalValue = new ThreadLocal<>();


	 Logger logger = Logger.getLogger("Class AuthorEventHandler");
     
	    @HandleBeforeCreate
	    public void handlePersonBeforeCreate(Person person){
	     
	        logger.info("Inside person Before Create....");
	    	System.out.println("eventhandled before");
	        String name = person.getFirstName();
	        System.out.println("firstname:"+name);
	    }
	 
	    @HandleAfterCreate
	    public void handlePersonAfterCreate(Person person){
	        logger.info("Inside person After Create ....");
	    	System.out.println("eventhandled after");
	        String name1 = person.getFirstName();
//	        System.out.println(" firstname is:"+name1);
	    }
	    
	    @HandleBeforeSave
	    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	    public void handlePersonBeforesave(Person person){
	    	System.out.println("before save using "+transactionManager.getClass().getName()+" of "+transactionManager);
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = transactionManager.getTransaction(def);
			threadLocalValue.set(status);
		      }
	    
	    @HandleAfterSave
	    public void handlePersonAfterSave(Person person){
	    	if(person.getFirstName().equals("e"))
			{
				throw new RuntimeException("e not allowed");
			}
			
	    	System.out.println("after save using "+transactionManager.getClass().getName()+" of "+transactionManager);
			TransactionStatus currentTransactionStatus = threadLocalValue.get();
			if(!currentTransactionStatus.isCompleted())
			{
				if(!currentTransactionStatus.isRollbackOnly())
				{
					transactionManager.commit(currentTransactionStatus);	
				}
				else
				{
					transactionManager.rollback(currentTransactionStatus);
				}
			}

	    	}
			
		
		
	    
	    @HandleBeforeDelete
	    public void handlepersonBeforeDelete(Person person){
	        logger.info("Inside Author Before Delete ....");
	    	System.out.println("eventhandled deletes before");
	        String name = person.getFirstName();
	    }
	 
	    @HandleAfterDelete
	    public void handleAuthorAfterDelete(Person person){
	        logger.info("Inside Author After Delete ....");
	    	System.out.println("eventhandled deletes after");
	        String name = person.getFirstName();
	    }
	   
	}
	 


