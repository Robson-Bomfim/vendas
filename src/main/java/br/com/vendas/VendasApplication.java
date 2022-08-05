package br.com.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {

//    @Bean
//    public CommandLineRunner init(@Autowired ClientRepository clientRepository){
//        return args -> {
//            Client client = new Client(null,"José");
//            clientRepository.save(client);
////            System.out.println("Salvando clientes");
////            Client fulano = new Client("Dougllas");
////            clientRepository.save(fulano);
////
////            Ordering pedido = new Ordering();
////            pedido.setClient(fulano);
////            pedido.setOrderingDate(LocalDate.now());
////            pedido.setAmount(BigDecimal.valueOf(100));
////
////            orderingRepository.save(pedido);
////
//////            Client client = clientRepository.findClientFetchOrdering(fulano.getId());
//////            System.out.println(client);
//////            System.out.println(client.getOrderingSet());
////
////            orderingRepository.findByClient(fulano).forEach(System.out::println);
////
////
////            clientRepository.save(new Client("Dougllas"));
////            clientRepository.save(new Client("Outro Cliente"));
////
////            List<Client> todosClientes = clientRepository.encontrarPorNome("Dougllas");
////            todosClientes.forEach(System.out::println);
////
//////            List<Client> todosClientes = clientRepository.findAll();
//////            todosClientes.forEach(System.out::println);
//////
//////            System.out.println("Atualizando clientes");
//////            todosClientes.forEach(c -> {
//////                c.setName(c.getName() + " atualizado.");
//////                clientRepository.save(c);
//////            });
//////
//////            todosClientes = clientRepository.findAll();
//////            todosClientes.forEach(System.out::println);
//////
//////            System.out.println("Buscando clientes");
//////            clientRepository.findByName("Cli").forEach(System.out::println);
//////
//////            System.out.println("deletando clientes");
//////            clientRepository.findAll().forEach(c -> {
//////                clientRepository.delete(c);
//////            });
//////
//////            todosClientes = clientRepository.findAll();
//////            if(todosClientes.isEmpty()){
//////                System.out.println("Nenhum cliente encontrado.");
//////            }else{
//////                todosClientes.forEach(System.out::println);
//////            }
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
