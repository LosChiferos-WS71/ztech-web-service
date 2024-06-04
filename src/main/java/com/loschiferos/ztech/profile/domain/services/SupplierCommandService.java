package com.loschiferos.ztech.profile.domain.services;

import com.loschiferos.ztech.profile.domain.model.commands.CreateSupplierCommand;

public interface SupplierCommandService {
    Long handle(CreateSupplierCommand command);
}
