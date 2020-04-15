package com.rbkmoney.threeds.server.handle.constraint.ares.transstatus;

import com.rbkmoney.threeds.server.domain.message.MessageCategory;
import com.rbkmoney.threeds.server.domain.root.emvco.AReq;
import com.rbkmoney.threeds.server.domain.root.emvco.ARes;
import com.rbkmoney.threeds.server.dto.ConstraintValidationResult;
import com.rbkmoney.threeds.server.handle.constraint.ares.AResConstraintValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.rbkmoney.threeds.server.domain.transaction.TransactionStatus.NOT_AUTHENTICATED_DENIED;
import static com.rbkmoney.threeds.server.utils.WrapperUtil.getEnumWrapperValue;
import static com.rbkmoney.threeds.server.utils.WrapperUtil.validateRequiredConditionField;

@Component
@RequiredArgsConstructor
public class AResAuthDeniedConstraintValidationHandlerImpl implements AResConstraintValidationHandler {

    @Override
    public boolean canHandle(ARes o) {
        return getEnumWrapperValue(o.getTransStatus()) == NOT_AUTHENTICATED_DENIED;
    }

    @Override
    public ConstraintValidationResult handle(ARes o) {
        MessageCategory messageCategory = ((AReq) o.getRequestMessage()).getMessageCategory();

        if (messageCategory == MessageCategory.PAYMENT_AUTH) {
            return validateRequiredConditionField(o.getTransStatusReason(), "transStatusReason");
        }

        return ConstraintValidationResult.success();
    }
}
