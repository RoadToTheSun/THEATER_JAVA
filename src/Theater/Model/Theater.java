package Theater.Model;

import Theater.Model.Factory.SpectaclesFactory;
import Theater.Service.TheaterService.DefaultTheaterService;
import Theater.Service.TheaterService.TheaterService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Theater {

    public static final String NAME = "CORONA";
    public static final int SPOTS_NUMBER = 25;
    //TODO
    // write DI;
    // add @Inject annotations to auto generate beans
    private TheaterService service;
    private final List<Spectacle> spectacles;
    private final List<Collective> collectives;
    private final List<Customer> customers;
    private final List<Employee> employees;

    private static Theater INSTANCE;

    public static Theater getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new Theater();
        return INSTANCE;
    }

    private Theater() {
        service = new DefaultTheaterService(this);
        spectacles = SpectaclesFactory.getSpectacles();
//        spectacles = new ArrayList<>();
        customers = new ArrayList<>();
        collectives = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public List<Collective> getCollectives() {
        return collectives;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Spectacle> getSpectacles() {
        return spectacles;
    }

    public void addCustomers(Customer ... customers) {
        service.addCustomers(customers);
    }

    public TheaterService service() {
        return service;
    }

    public void setService(DefaultTheaterService service) {
        this.service = service;
    }
}
