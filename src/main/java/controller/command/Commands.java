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
    FIRST_STEP_COMMAND(new FirstStepCommand());

    ICommand iCommand;

    Commands(ICommand iCommand){
        this.iCommand = iCommand;
    }

    public ICommand getCommand(){
        return iCommand;
    }
}