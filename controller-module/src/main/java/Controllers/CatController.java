package Controllers;

import Application.Contracts.ICatService;
import Application.Contracts.IPersonService;
import Application.Models.Cat;
import Application.Models.CatColor;
import DtoMappers.CatDtoMapper;
import Dtos.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cats")
@ComponentScan(basePackages = "Application")
public class CatController {
    private final ICatService catService;
    private final IPersonService personService;

    public CatController(@Autowired ICatService catService, IPersonService personService) {
        this.catService = catService;
        this.personService = personService;
    }

    @GetMapping("/{id}")
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

    @PostMapping("/{personId}/cat")
    public ResponseEntity<Void> addCat(@PathVariable UUID personId, @RequestBody CatDto catDto) {
        Cat cat = CatDtoMapper.mapToModel(catDto);
        personService.addCat(cat, personId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCat(@PathVariable UUID id, @RequestBody CatDto catDto) {
        Cat cat = CatDtoMapper.mapToModel(catDto);
        catService.updateCat(id, cat);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable UUID id) {
        catService.deleteCat(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-color/{color}")
    public CompletableFuture<ResponseEntity<List<CatDto>>> getCatsByColor(@PathVariable CatColor color) {
        return catService.getCatsByColor(color)
                .thenApply(cats -> {
                    List<CatDto> catDtos = cats.stream()
                            .map(CatDtoMapper::mapToDto)
                            .collect(Collectors.toList());

                    if (catDtos.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(catDtos);
                });
    }
}