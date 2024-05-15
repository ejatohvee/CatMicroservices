package Application.EntityMappers;

import Application.Entities.CatEntity;
import Application.Models.Cat;

public class CatEntityMapper {
    private static PersonEntityMapper personMapper;

    public CatEntityMapper(PersonEntityMapper personMapper) {
        this.personMapper = personMapper;
    }
    public static CatEntity mapToEntity(Cat cat) {
        CatEntity catEntity = new CatEntity(
            cat.getName(),
            cat.getCatColor(),
            cat.getBreed(),
            cat.getBirthdayDate(),
            PersonEntityMapper.mapToEntity(cat.getOwner()));
        catEntity.setCatsId(cat.getCatsId());
        return catEntity;
    }

    public static Cat mapToModel(CatEntity catEntity) {
        Cat cat = new Cat(
            catEntity.getName(),
            catEntity.getCatColor(),
            catEntity.getBreed(),
            catEntity.getBirthdayDate()
        );
        cat.setCatsId(catEntity.getCatsId());
        return cat;
    }
}