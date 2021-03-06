package br.com.alura.mvc.mudi.Controller;

import br.com.alura.mvc.mudi.Model.Pedido;
import br.com.alura.mvc.mudi.Model.StatusPedido;
import br.com.alura.mvc.mudi.Repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public String home(Model model, Principal principal) {

        Sort sort = Sort.by("dataEntrega").descending();
        PageRequest paginacao = PageRequest.of(0, 10, sort);

        List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, paginacao);
        model.addAttribute("pedidos", pedidos);

        return "home";
    }
}
