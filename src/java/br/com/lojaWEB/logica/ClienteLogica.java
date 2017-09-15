package br.com.lojaWEB.logica;

import br.com.lojaWEB.controller.CtrlCliente;
import br.com.lojaWEB.model.Cliente;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClienteLogica implements Logica {

    private static final long serialVersionUID = 1L;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        res.setContentType("text/html;charset=UTF-8");
        //Retorno da Pagina
        String pagina = "index.jsp";

        //Ação para login 
        //<editor-fold>
        if (req.getParameter("action").equals("log")) {
            try {
                CtrlCliente ctrlCliente = new CtrlCliente();
                Cliente cliente = ctrlCliente.login(req.getParameter("email"), req.getParameter("pws1"));
                req.setAttribute("avisos", "Logado com sucesso.");
                //Chama a session
                HttpSession user = req.getSession();
                //Cria uma variavel de session
                user.setAttribute("id", cliente.getId());
                user.setAttribute("nome", cliente.getNome());
            } catch (SQLException esql) {
                req.setAttribute("alertas", esql.getMessage());
            } catch (Exception e) {
                req.setAttribute("erros", "<strong>Cliente não encontrado.</strong><br>" + e.getMessage());
                pagina = "index.jsp?p=login";
                //Chama a session
                HttpSession user = req.getSession();
                //apaga a variavel de session
                user.removeAttribute("id");
                user.removeAttribute("nome");
            }
        }//</editor-fold>

        //Ação para logoff
        //<editor-fold>
        if (req.getParameter("action").equals("off")) {
            try {
                //Chama a session
                HttpSession user = req.getSession();
                //apaga a variavel de session
                user.invalidate();

            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
            }

        }//</editor-fold>

        //Ação para cadastro
        //<editor-fold>
        if (req.getParameter("action").equals("cad")) {
            Cliente cliente = new Cliente();
            try {
                cliente.setNome(req.getParameter("nome").trim());
                cliente.setEmail(req.getParameter("email").trim());
                cliente.setNumero(req.getParameter("numero").trim());
                cliente.setComplemento(req.getParameter("complemento").trim());
                cliente.setPws(req.getParameter("pws1").trim());
                cliente.isCliente(req.getParameter("pws2").trim());
                CtrlCliente ctrCliente = new CtrlCliente();
                ctrCliente.salvar(cliente);
                req.setAttribute("avisos", cliente.getNome() + " cadastrado com sucesso.");
                cliente = null;

            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
                req.setAttribute("cliente", cliente);
            }
            pagina = "index.jsp?p=cadCliente";
        }//</editor-fold>

        /*
        //Ação para apagar
        //<editor-fold>
        if (req.getParameter("action").equals("remove")) {
            Cliente cliente = new Cliente();
            try {
                CtrlCliente ctrCliente = new CtrlCliente();
                ctrCliente.remover(Long.parseLong(req.getParameter("id")));
            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
                req.setAttribute("cliente", cliente);
            }
            pagina = "index.jsp?p=reportCliente";
        }//</editor-fold>

        //Ação para listar
        //<editor-fold>
        if (req.getParameter("action").equals("list")) {
            try {
                CtrlCliente ctrCliente = new CtrlCliente();
                req.setAttribute("clientes", ctrCliente.listar(req.getParameter("busca")));

            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
            }
            pagina = "index.jsp?p=reportCliente";
        }//</editor-fold>

        //Ação para Editar
        //<editor-fold>
        if (req.getParameter("action").equals("edit")) {
            Cliente cliente = new Cliente();
            try {
                CtrlCliente ctrCliente = new CtrlCliente();
                cliente = ctrCliente.buscaID(Long.parseLong(req.getParameter("id")));
                req.setAttribute("alt", true);
            } catch (Exception e) {
                req.setAttribute("Erros: ", e.getMessage().replace(".\n", ".<br>"));

            }
            req.setAttribute("cliente", cliente);
            pagina = "index.jsp?p=formCliente";
        }//</editor-fold>
        
        
        //Ação para Alterar
        //<editor-fold>
        if (req.getParameter("action").equals("alt")) {
            Cliente cliente = new Cliente();
            try {
                cliente.setId(Long.parseLong(req.getParameter("id").trim()));
                cliente.setNome(req.getParameter("nome").trim());
                cliente.setEmail(req.getParameter("email").trim());
                cliente.setPws(req.getParameter("pws1").trim());
                if (cliente.isCliente(req.getParameter("pws2").trim())) {
                    CtrlCliente ctrCliente = new CtrlCliente();
                    ctrCliente.alterar(cliente);
                    req.setAttribute("avisos", cliente.getNome() + " atualizado com sucesso.");
                    cliente = null;
                }
            } catch (Exception e) {
                req.setAttribute("Erros: ", e.getMessage().replace(".\n", ".<br>"));
                req.setAttribute("cliente", cliente);

            }
            req.setAttribute("cliente", cliente);
            pagina = "index.jsp?p=formCliente";
        }//</editor-fold>
         */
        //Retorna para a pagina
        return pagina;
    }

}
