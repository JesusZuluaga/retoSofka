package com.sofka.backend.api;

import com.sofka.backend.domain.model.buys.Buy;
import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.domain.usecase.buy.BuyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("buys")
@CrossOrigin(origins = "*")
public class BuyController {

    @Autowired
    private BuyUseCase useCase;

    @PostMapping("/buyProduct")
    public Mono<Buy> buyProduct(@RequestBody Buy buy) {
        return useCase.saveBuy(buy);
    }

    @GetMapping("/allBuys")
    public Mono<Page<Buy>> allBuys(@RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) String sort) {
        return useCase.findAllBuys(size, page, sort);
    }
}
