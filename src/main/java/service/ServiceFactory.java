package service;

import service.impl.CarServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final CarService carService = new CarServiceImpl();

    private ServiceFactory(){ }

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public CarService getCarService() {
        return carService;
    }
}
