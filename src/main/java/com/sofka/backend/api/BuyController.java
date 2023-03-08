package com.sofka.backend.api;

import com.sofka.backend.domain.model.buys.Buy;
import com.sofka.backend.domain.usecase.buy.BuyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("buys")
public class BuyController {

    @Autowired
    private BuyUseCase useCase;

    @PostMapping("/buyProduct")
    public Mono<Buy> buyProduct (@RequestBody Buy buy){
        return useCase.saveBuy(buy);
    }
}
