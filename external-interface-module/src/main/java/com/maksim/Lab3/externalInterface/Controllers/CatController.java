package com.maksim.Lab3.externalInterface.Controllers;

import DtoMappers.CatDtoMapper;
import Dtos.CatDto;
import Models.Cat;
import Models.CatColor;
import com.maksim.Lab3.cat.Services.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import person.Services.IPersonService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cats")
//@ComponentScan(basePackages = "com.maksim.Lab3.cat.Services")
public class CatController {
    private final ICatService catService;
    private final IPersonService personService;

    @Autowired
    public CatController(ICatService catService, IPersonService personService) {
        this.catService = catService;
        this.personService = personService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @personService.catsOwner(#id, authentication.name)")
    public CompletableFuture<ResponseEntity<CatDto>> getCatById(@PathVariable UUID id) {
        return catService.getCatById(id)
                .thenApply(cat -> {
                    if (cat == null) {
                        return ResponseEntity.notFound().build();
                    }
                    CatDto catDto = CatDtoMapper.mapToDto(cat);
                    return ResponseEntity.ok(catDto);
                });
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @personService.catsOwner(#id, authentication.name)")
    public ResponseEntity<Void> updateCat(@PathVariable UUID id, @RequestBody CatDto catDto) {
        Cat cat = CatDtoMapper.mapToModel(catDto);
        catService.updateCat(id, cat);
        return ResponseEntity.ok().build();
    }

    @PostMapping("makeFriends/{catOneId}/{catTwoId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @personService.catsOwner(#id, authentication.name)")
    public ResponseEntity<Void> addCat(@PathVariable UUID catOneId, @PathVariable UUID catTwoId) {
        catService.makeFriends(catOneId, catTwoId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @personService.catsOwner(#id, authentication.name)")
    public ResponseEntity<Void> deleteCat(@PathVariable UUID id) {
        catService.deleteCat(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-color/{color}")
    public CompletableFuture<ResponseEntity<List<CatDto>>> getCatsByColor(Principal principal, @PathVariable CatColor color, Authentication authentication) {
        return catService.getCatsByColor(color)
                .thenApply(cats -> {
                    if (cats.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(cats.stream()
                            .filter(cat ->
                                    personService.catsOwner(cat.getCatsId(), principal.getName()) ||
                                            authentication.getAuthorities().stream()
                                                    .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN")))
                            .map(CatDtoMapper::mapToDto)
                            .collect(Collectors.toList())
                    );
                });
    }

}