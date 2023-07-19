package br.com.projeto.regpasweb.controllers;

import br.com.projeto.regpasweb.dto.RequisicaoNovoPassageiro;
import br.com.projeto.regpasweb.dto.RequisicaoEditPassageiro;
import br.com.projeto.regpasweb.models.Passageiro;
import br.com.projeto.regpasweb.models.StatusPassageiro;
import br.com.projeto.regpasweb.repositories.PassageiroRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PassageiroController {
    @Autowired
    private PassageiroRepository passageiroRepository;
    private List<Passageiro> passageiros;

    @GetMapping("/passageiros")
    public ModelAndView index() {
        passageiros = this.passageiroRepository.findAll();
        ModelAndView mv = new ModelAndView("passageiros/index");
        mv.addObject("passageiros", passageiros);
        return mv;
    }


    @GetMapping("/passageiros/new")
    public ModelAndView nnew(RequisicaoNovoPassageiro requisicao) {
        ModelAndView mv = new ModelAndView("passageiros/new");
        mv.addObject("listaStatusPassageiro", StatusPassageiro.values());
        return mv;
    }


    @PostMapping("/passageiros")
    public ModelAndView create(@Valid RequisicaoNovoPassageiro requisicao, BindingResult bindingResult)
            throws IOException {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("passageiros/new");
            mv.addObject("listaStatusPassageiro", StatusPassageiro.values());
        }

        Passageiro passageiro = requisicao.toPassageiro();
        passageiro.setPagamento("Pendente");
        // Verifica se um arquivo de imagem foi enviado
        MultipartFile imagem = requisicao.getImagem();
        if (imagem != null && !imagem.isEmpty()) {
            // Define os dados da imagem no passageiro
            passageiro.setNomeImagem(StringUtils.cleanPath(imagem.getOriginalFilename()));
            passageiro.setTipoImagem(imagem.getContentType());
            passageiro.setDadosImagem(imagem.getBytes());
        }

        this.passageiroRepository.save(passageiro);
        return new ModelAndView("redirect:/passageiros");
    }
    @GetMapping("/passageiros/download/{passageiroId}")
    public ResponseEntity<ByteArrayResource> downloadImagem(@PathVariable Long passageiroId) {
        Passageiro passageiro = passageiroRepository.findById(passageiroId)
                .orElseThrow(() -> new IllegalArgumentException("Passageiro não encontrado: " + passageiroId));

        byte[] imagemDados = passageiro.getDadosImagem();
        String nomeImagem = passageiro.getNomeImagem();
        String tipoImagem = passageiro.getTipoImagem();

        ByteArrayResource resource = new ByteArrayResource(imagemDados);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + nomeImagem)
                .contentType(MediaType.parseMediaType(tipoImagem))
                .contentLength(imagemDados.length)
                .body(resource);
    }
    @GetMapping("/passageiros/onibus1")
    public ModelAndView onibus1() {
        ModelAndView mv = new ModelAndView("passageiros/onibus1");
        mv.addObject("passageiros", passageiros);
        return mv;
    }
    @GetMapping("/passageiros/onibus2")
    public ModelAndView onibus2() {
        ModelAndView mv = new ModelAndView("passageiros/onibus2");
        mv.addObject("passageiros", passageiros);
        return mv;
    }
    @GetMapping("/passageiros/onibus3")
    public ModelAndView onibus3() {
        ModelAndView mv = new ModelAndView("passageiros/onibus3");
        mv.addObject("passageiros", passageiros);
        return mv;
    }
    @GetMapping("/passageiros/sem-onibus")
    public ModelAndView semonibus() {
        ModelAndView mv = new ModelAndView("passageiros/semonibus");
        mv.addObject("passageiros", passageiros);
        return mv;
    }
    //Mapping para futuras adições no projeto
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("passageiros/home");
        mv.addObject("home", passageiros);
        return mv;
    }

    @GetMapping("/passageiros/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoEditPassageiro requisicao)
    {
        Optional<Passageiro> optional = this.passageiroRepository.findById(id);

        if (optional.isPresent())
        {
            Passageiro passageiro = optional.get();
            requisicao.fromPassageiro(passageiro);

            ModelAndView mv = new ModelAndView("passageiros/edit");
            mv.addObject("passageiroId", passageiro.getId());
            mv.addObject("listaStatusPassageiro", StatusPassageiro.values());
            return mv;
        }
        else{
            System.out.println("NAO ACHOU O PASSAGEIRO");
            return new ModelAndView("redirect:/passageiros");
        }
    }

    @PostMapping("/passageiros/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoEditPassageiro requisicao, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("passageiros/edit");
            mv.addObject("listaStatusPassageiro", StatusPassageiro.values());
            return null;
        }
        else {
            Optional<Passageiro> optional = this.passageiroRepository.findById(id);
            if (optional.isPresent()) {
                Passageiro passageiro = requisicao.toPassageiro(optional.get());
                this.passageiroRepository.save(passageiro);


                return new ModelAndView("redirect:/passageiros");
            }
            else{
                return new ModelAndView("redirect:/passageiros");
            }
        }
    }
    @GetMapping("/passageiros/{id}/delete")
    public String delete(@PathVariable Long id)
    {
        try{
            this.passageiroRepository.deleteById(id);
            return "redirect:/passageiros";
        }
        catch(EmptyResultDataAccessException e)
        {
            return "redirect:/passageiros";
        }
    }

}
