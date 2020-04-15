package com.rbkmoney.threeds.server.handle.constraint.ares.whiteliststatus;

import com.rbkmoney.threeds.server.domain.root.emvco.ARes;
import com.rbkmoney.threeds.server.dto.ConstraintValidationResult;
import com.rbkmoney.threeds.server.handle.constraint.ares.AResConstraintValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.rbkmoney.threeds.server.utils.WrapperUtil.getEnumWrapperValue;
import static com.rbkmoney.threeds.server.utils.WrapperUtil.validateRequiredConditionField;

@Component
@RequiredArgsConstructor
public class AResWhiteListStatusContentConstraintValidationHandlerImpl implements AResConstraintValidationHandler {

    @Override
    public boolean canHandle(ARes o) {
        return getEnumWrapperValue(o.getWhiteListStatus()) != null;
    }

    @Override
    public ConstraintValidationResult handle(ARes o) {
        return validateRequiredConditionField(o.getWhiteListStatusSource(), "whiteListStatusSource");
    }
}
