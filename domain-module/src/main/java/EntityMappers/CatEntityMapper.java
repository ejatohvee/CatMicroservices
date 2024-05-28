package EntityMappers;

import Entities.CatEntity;
import Models.Cat;

public class CatEntityMapper {
    public static CatEntity mapToEntity(Cat cat) {
        CatEntity catEntity = new CatEntity(
            cat.getName(),
            cat.getColor(),
            cat.getBreed(),
            cat.getBirthdayDate(),
            PersonEntityMapper.mapToEntity(cat.getOwner()));
        catEntity.setCatsId(cat.getCatsId());
        return catEntity;
    }

    public static Cat mapToModel(CatEntity catEntity) {
        Cat cat = new Cat(
            catEntity.getName(),
            catEntity.getColor(),
            catEntity.getBreed(),
            catEntity.getBirthdayDate()
        );
        cat.setCatsId(catEntity.getCatsId());
//        PersonEntity personEntity = catEntity.getOwner();
        cat.setOwner(PersonEntityMapper.mapToModel(catEntity.getOwner()));
        return cat;
    }
}