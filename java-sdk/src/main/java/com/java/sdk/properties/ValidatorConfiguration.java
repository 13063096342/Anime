package com.java.sdk.properties;

import org.springframework.context.annotation.Configuration;

/**
 * @author chenfh
 * @date 2020-07-24 10:00
 */
@Configuration
public class ValidatorConfiguration {

    /*@Bean
    public Validator fastValidator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }*/
}
