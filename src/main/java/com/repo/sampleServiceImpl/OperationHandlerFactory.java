package sampleServiceImpl;

public class OperationHandlerFactory {
    public HandleQuestionnaireOperator createOperationHandler(String operation) {
        if( operation == null || operation.trim().isEmpty()) {
            throw new InvalidRequestException(ITEM_OPER_REQUIRED);
        }
        switch (operation.toLowerCase()) {
            case ServiceConst.PATCH_OPERATION_CREATE:
                return new QuestionnaireItemCreateHandler();
            case ServiceConst.PATCH_OPERATION_UPDATE:
                return new QuestionnaireItemUpdateHandler();
            case ServiceConst.PATCH_OPERATION_DELETE:
                return new QuestionnaireItemDeleteHandler();
            default:
                throw new InvalidRequestException(UNK_ITEM_OPER);
        }
    }
}