package io.github.abeelfelipe.Vendas.rest;

import io.github.abeelfelipe.Vendas.model.entity.Cliente;
import io.github.abeelfelipe.Vendas.model.entity.ServicoPrestado;
import io.github.abeelfelipe.Vendas.model.repository.ClienteRepository;
import io.github.abeelfelipe.Vendas.model.repository.ServicoPrestadoCustomRepository;
import io.github.abeelfelipe.Vendas.model.repository.ServicoPrestadoRepository;
import io.github.abeelfelipe.Vendas.rest.dto.ServicoPrestadoDTO;
import io.github.abeelfelipe.Vendas.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servico-prestado")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ServicoPrestadoRepository repository;
    private final ServicoPrestadoCustomRepository repositoryCustom;
    private final ClienteRepository clienterepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar (@RequestBody @Valid ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente =
                clienterepository.findById(dto.getIdCliente())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe"));

        ServicoPrestado servico = new ServicoPrestado();
        servico.setDescricao(dto.getDescricao());
        servico.setData(data);
        servico.setValor(bigDecimalConverter.converter(dto.getValor()));
        servico.setCliente(cliente);

        return repository.save(servico);
    }

    @GetMapping
    public List<ServicoPrestado> consultarServicos(@RequestParam(value = "nome", required = false) String nome,
                                                   @RequestParam(value = "mes", required = false) Integer mes,
                                                   @RequestParam(value = "id", required = false) Integer id) {
        return repositoryCustom.filterByMonthOrName(id, "%" + nome + "%", mes);
    }

}
