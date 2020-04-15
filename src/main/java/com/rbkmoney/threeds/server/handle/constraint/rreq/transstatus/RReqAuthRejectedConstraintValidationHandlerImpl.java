package com.rbkmoney.threeds.server.handle.constraint.rreq.transstatus;

import com.rbkmoney.threeds.server.domain.message.MessageCategory;
import com.rbkmoney.threeds.server.domain.root.emvco.RReq;
import com.rbkmoney.threeds.server.dto.ConstraintValidationResult;
import com.rbkmoney.threeds.server.handle.constraint.rreq.RReqConstraintValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.rbkmoney.threeds.server.domain.transaction.TransactionStatus.AUTHENTICATION_REJECTED;
import static com.rbkmoney.threeds.server.utils.WrapperUtil.getEnumWrapperValue;
import static com.rbkmoney.threeds.server.utils.WrapperUtil.validateRequiredConditionField;

@Component
@RequiredArgsConstructor
public class RReqAuthRejectedConstraintValidationHandlerImpl implements RReqConstraintValidationHandler {

    @Override
    public boolean canHandle(RReq o) {
        return getEnumWrapperValue(o.getTransStatus()) == AUTHENTICATION_REJECTED;
    }

    @Override
    public ConstraintValidationResult handle(RReq o) {
        if (getEnumWrapperValue(o.getMessageCategory()) == MessageCategory.PAYMENT_AUTH) {
            return validateRequiredConditionField(o.getTransStatusReason(), "transStatusReason");
        }

        return ConstraintValidationResult.success();
    }
}