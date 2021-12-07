package controller.command;

import controller.command.impl.*;

public enum Commands {
    ENTER_COMMAND(new EnterCommand()),
    REGISTRATION_COMMAND(new RegistrationCommand()),
    CONFIRM_COMMAND(new ConfirmationCommand()),
    FORGET_PASSWORD_COMMAND(new ForgetPasswordCommand()),
    SEND_EMAIL_COMMAND(new SendEmailCommand()),
    CHECK_CODE_COMMAND(new CheckCodeCommand()),
    SELECT_CAR_TYPE_COMMAND(new SelectCarTypeCommand()),
    VIEW_CAR_COMMAND(new ViewCarCommand()),
    FORM_ORDER_COMMAND(new FormOrderCommand()),
    MAKE_ORDER_COMMAND(new MakeOrderCommand()),
    LOG_OFF_COMMAND(new LogOffCommand()),
    TRUCK_ORDER_COMMAND(new TruckOrderCommand()),
    MINIBUS_ORDER_COMMAND(new MinibusOrderCommand()),
    Filter_All_Cars_COMMAND(new FilterAllCarsCommand()),
    BACK_FROM_ALL_CARS_COMMAND(new BackFromAllCarsCommand()),
    DEFINITE_TEST_DRIVE_COMMAND(new DefiniteTestDriveCommand()),
    MAKE_TEST_DRIVE_ORDER_COMMAND(new MakeTestDriveOrderCommand()),
    TRUCK_SERVICE_ORDER_COMMAND(new TruckServiceOrderCommand()),
    MAKE_SERVICE_ORDER_COMMAND(new MakeServiceOrderCommand()),
    UPLOAD_AVATAR_COMMAND(new UploadAvatarCommand()),
    ACCOUNT_PAGE_COMMAND(new AccountPageCommand()),
    MARK_AS_READ_COMMAND(new MarkAsReadCommand()),
    DELETE_ORDER_FROM_ACCOUNT_COMMAND(new DeleteOrderFromAccountCommand()),
    CHANGE_LANGUAGE_COMMAND(new ChangeLanguageCommand()),
    ADMIN_GET_ALL_USERS_COMMAND(new AdminAllUsersCommand()),
    ADMIN_GET_ORDERS_COMMAND(new AdminAllOrdersCommand()),
    ADMIN_TO_ADD_CAR_PAGE_COMMAND(new AdminGoToAddCarPageCommand()),
    ADMIN_DELETE_CAR_COMMAND(new AdminGoToDeleteCarCommand()),
    ADMIN_EDIT_CAR_DESCRIPTION_COMMAND(new AdminGoToEditDescriptionCommand()),
    ADD_CAR_COMMAND(new AdminAddCarCommand()),
    ADD_MINIBUS_COMMAND(new AdminAddMinibusCommand()),
    DELETE_CAR_COMMAND(new AdminDeleteCarCommand()),
    DELETE_MINIBUS_COMMAND(new AdminDeleteMinibusCommand()),
    CHOOSE_CAR_FOR_CHANGING_INFO_COMMAND(new AdminGoDefiniteCarInfoCommand()),
    CHANGE_INFO_COMMAND(new AdminChangeInfoCommand()),
    DELETE_USER_FROM_ADMIN_COMMAND(new AdminDeleteUserCommand()),
    DELETE_ORDER_FROM_ADMIN_COMMAND(new AdminDeleteOrderCommand()),
    GO_TO_PAGE_COMMAND(new GoToPageCommand()),
    ERROR_COMMAND(new ErrorPageCommand()),
    ORDERS_NUMBER_OF_PAGE_COMMAND(new SelectPageAllOrdersCommand()),
    UPGRADE_USER_TO_ADMIN_COMMAND(new UpgradeUserToAdminCommand()),
    ALL_CARS_NUMBER_OF_PAGE_COMMAND(new SelectPageAllCarsCommand()),
    CARS_NUMBER_OF_PAGE_COMMAND(new SelectPageCarsCommand()),
    MINIBUSES_NUMBER_OF_PAGE_COMMAND(new SelectPageMinibusCommand()),
    CAR_TYPES_NUMBER_OF_PAGE_COMMAND(new SelectPageCarTypeCommand()),
    FILTER_CAR_TYPES_NUMBER_OF_PAGE_COMMAND(new SelectPageFilterCarTypesCommand()),
    MINIBUS_NUMBER_OF_PAGE(new SelectPageMinibusesCommand()),
    USERS_NUMBER_OF_PAGE_COMMAND(new SelectPageAllUsersCommand()),
    FIRST_STEP_COMMAND(new FirstStepCommand());

    ICommand iCommand;

    Commands(ICommand iCommand){
        this.iCommand = iCommand;
    }

    public ICommand getCommand(){
        return iCommand;
    }
}