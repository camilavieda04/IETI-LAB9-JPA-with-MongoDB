package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;
import eci.ieti.data.model.Todo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TodoRepository todoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
   
        System.out.println();

        todoRepository.deleteAll();

        // Todos where the dueDate has expired
        todoRepository.save(new Todo(10L,"travel to Galapagos",10,new Date(2020,1,13),"charles@natural.com","pending"));
        todoRepository.save(new Todo(11L,"travel to India",10,new Date(2018,9,10),"g@natural.com","pending"));
        todoRepository.save(new Todo(12L,"travel to Italia",10,new Date(2017,1,13),"h@natural.com","pending"));
        todoRepository.save(new Todo(13L,"travel to San Andres",8,new Date(2000,9,10),"i@natural.com","pending"));
        todoRepository.save(new Todo(14L,"travel to Canada",7,new Date(1999,1,13),"j@natural.com","pending"));
        todoRepository.save(new Todo(15L,"travel to Indonesia",6,new Date(2000,10,10),"k@natural.com","pending"));
        // Todos that are assigned to given user and have priority greater equal to 5
        todoRepository.save(new Todo(16L,"travel to Rusia",5,new Date(2020,10,25),"s@natural.com","pending"));
        todoRepository.save(new Todo(17L,"travel to Jamaica",5,new Date(2020,11,9),"c@natural.com","pending"));
        todoRepository.save(new Todo(18L,"travel to Brasil",5,new Date(2020,2,4),"v@natural.com","pending"));
        todoRepository.save(new Todo(19L,"travel to Chile",5,new Date(2020,12,24),"l@natural.com","pending"));
        //Users that have assigned more than 2 Todos.
        todoRepository.save(new Todo(20L,"travel to Japon",5,new Date(2020,1,5),"b@natural.com","pending"));
        todoRepository.save(new Todo(21L,"travel to China",8,new Date(2020,2,6),"b@natural.com","pending"));
        todoRepository.save(new Todo(22L,"travel to USA",5,new Date(2020,3,7),"b@natural.com","pending"));

        todoRepository.save(new Todo(23L,"travel to Alemania",5,new Date(2020,4,8),"a@natural.com","pending"));
        todoRepository.save(new Todo(24L,"travel to Holanda",8,new Date(2020,5,9),"a@natural.com","pending"));
        todoRepository.save(new Todo(25L,"travel to Argentina",5,new Date(2020,6,10),"a@natural.com","pending"));

        todoRepository.save(new Todo(26L,"travel to Ecuador",5,new Date(2020,5,9),"m@natural.com","pending"));
        todoRepository.save(new Todo(27L,"travel to Peru",8,new Date(2020,12,10),"m@natural.com","pending"));
        todoRepository.save(new Todo(28L,"travel to Venezuela",5,new Date(2020,20,11),"m@natural.com","pending"));

        todoRepository.save(new Todo(29L,"travel to Puerto rico",5,new Date(2020,14,8),"n@natural.com","pending"));
        todoRepository.save(new Todo(30L,"travel to Mexico",8,new Date(2020,15,9),"n@natural.com","pending"));
        todoRepository.save(new Todo(31L,"travel to Suiza",5,new Date(2020,26,10),"n@natural.com","pending"));

        // Todos that contains a description with a length greater than 30 characters
        todoRepository.save(new Todo(32L,"travel to Francia to visit my grandparents",5,new Date(2020,24,8),"d@natural.com","pending"));
        todoRepository.save(new Todo(33L,"travel to Holanda to visit my little sister",8,new Date(2020,15,9),"e@natural.com","pending"));
        todoRepository.save(new Todo(34L,"travel to Cuba to visit my uncle",5,new Date(2020,6,11),"f@natural.com","pending"));

        todoRepository.findByResponsibleContaining("m@natural.com", PageRequest.of(0, 2)).stream()
                .forEach(System.out::println);
        
        System.out.println();
        

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Alice"));
     
        Customer customer = mongoOperation.findOne(query, Customer.class);
        System.out.println(customer);

         // Todos where the dueDate has expired
        Query query1 = new Query();
        query.addCriteria(Criteria.where("dueDate").lt(new Date()));
        List<Todo> t = mongoOperation.find(query1, Todo.class);
        System.out.println(t);
        // Todos that are assigned to given user and have priority greater equal to 5
        Query query2 = new Query();
        query.addCriteria(Criteria.where("responsible").is("m@atural.com").and("priority").gt(4));
        List<Todo> t2 = mongoOperation.find(query2, Todo.class);
        System.out.println(t2);
         // Todos that contains a description with a length greater than 30 characters
         Query query3 = new Query();
         query.addCriteria(Criteria.where("description").gt(30));
         List<Todo> t3 = mongoOperation.find(query3, Todo.class);
         System.out.println(t3);
     

        
    }

}