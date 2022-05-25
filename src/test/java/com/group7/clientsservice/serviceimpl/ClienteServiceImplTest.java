package com.group7.clientsservice.serviceimpl;

import com.group7.clientsservice.dto.ClientsRequestDto;
import com.group7.clientsservice.model.Accounts;
import com.group7.clientsservice.model.Client;
import com.group7.clientsservice.model.Credit;
import com.group7.clientsservice.repository.ClientRepository;
import com.group7.clientsservice.utils.WebClientUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {


    @InjectMocks
    ClientServiceImpl clienteService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private WebClientUtils webClientUtils;
    private Client client;
    private ClientsRequestDto clientDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = Client.builder()
                .id("123")
                .documentType("DNI")
                .documentNumber("74910877")
                .firstName("Cristian")
                .lastName("Paredes")
                .businessName("")
                .type("personal")
                .profile("vip")
                .build();

        clientDto = ClientsRequestDto.builder()
                .documentType("DNI")
                .documentNumber("74910877")
                .firstName("Cristian")
                .lastName("Paredes")
                .businessName("")
                .type("personal")
                .profile("vip")
                .build();
    }

    @Test
    void getAll() {
        when(clientRepository.findAll()).thenReturn(Flux.just(client));
        assertNotNull(clienteService.getAll());
    }

    @Test
    void getById() {
        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        assertNotNull(clienteService.getById("123"));
    }

    @Test
    void ByDocumentTypeAndDocumentNumber() {
        when(clientRepository.findClientByDocumentTypeAndDocumentNumber(any(String.class), any(String.class)))
                .thenReturn(Mono.just(client));
        assertNotNull(clienteService.getByDocTypeAndDocNumber("DNI", "74910877"));
    }

    @Test
    void delete() {
        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        when(clientRepository.delete(any(Client.class))).thenReturn(Mono.empty());
        assertNotNull(clienteService.delete("123"));
    }

    @Test
    void save() {
        when(clientRepository.findClientByDocumentTypeAndDocumentNumber(any(String.class), any(String.class)))
                .thenReturn(Mono.empty());
        when(clientRepository.insert(any(Client.class)))
                .thenReturn(Mono.just(client));

        StepVerifier.create(clienteService.save(clientDto))
                .assertNext(client -> {
                    assertNotNull(client);
                })
                .verifyComplete();

    }

    @Test
    void update() {
        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        when(clientRepository.save(any(Client.class))).thenReturn(Mono.just(client));
        StepVerifier.create(clienteService.update("123", clientDto))
                .assertNext(client -> {
                    assertNotNull(client);
                })
                .verifyComplete();
    }

    @Test
    void existsById() {
        when(clientRepository.existsById(any(String.class))).thenReturn(Mono.just(true));
        assertNotNull(clienteService.existsById(client.getId()));
    }

    @Test
    void getProductsByClient() {

        Accounts accounts = Accounts.builder()
                .id("123")
                .client("456")
                .type("personal")
                .clientProfile("vip")
                .build();

        Credit credit = Credit.builder()
                .id("123")
                .client("456")
                .amount(50)
                .build();

        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        when(webClientUtils.getAccounts(any(String.class))).thenReturn(Flux.just(accounts));
        when(webClientUtils.getCredits(any(String.class))).thenReturn(Flux.just(credit));

        StepVerifier.create(clienteService.getProductsByClient("123"))
                .assertNext(products -> {
                            assertNotNull(products);
                        })
                .verifyComplete();

    }

}