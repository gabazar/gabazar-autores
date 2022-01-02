package me.gabu.gabazar.autores.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import me.gabu.gabazar.autores.core.exceptions.BadRequestException;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.service.ValidationService;
import me.gabu.gabazar.autores.service.validations.Create;
import me.gabu.gabazar.autores.service.validations.Update;
import me.gabu.gabazar.autores.service.validations.ValidationEnum;

@Service
public class ValidationServiceImpl implements ValidationService {

    private @Autowired Validator validator;

    @Override
    public void validate(Autor autor, ValidationEnum validation) {

        Set<ConstraintViolation<Autor>> contraints = getContraints(autor, validation);

        if (!CollectionUtils.isEmpty(contraints))
            throw new BadRequestException(processaContraint(contraints));

    }

    private Set<ConstraintViolation<Autor>> getContraints(Autor autor, ValidationEnum validation) {
        switch (validation) {
        case CREATE:
            return validator.validate(autor, Create.class);

        case UPDATE:
        default:
            return validator.validate(autor, Update.class);
        }
    }

    private List<String> processaContraint(Set<ConstraintViolation<Autor>> contraints) {
        return contraints.stream()
                .map(violation -> String.format("[%s %s]",
                        StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                .reduce((first, second) -> second).orElse(null),
                        violation.getMessage()))
                .collect(Collectors.toList());
    }

}
