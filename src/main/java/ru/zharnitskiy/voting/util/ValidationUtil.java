package ru.zharnitskiy.voting.util;

import ru.zharnitskiy.voting.model.AbstractBaseEntity;
import ru.zharnitskiy.voting.util.exception.NotValidException;

public class ValidationUtil {

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new NotValidException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new NotValidException(entity + " must be with id=" + id);
        }
    }
}
