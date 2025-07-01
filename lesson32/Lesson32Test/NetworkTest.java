import jdbc.NetworkDao;
import model.Network;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

public class NetworkTest {

    @Test
    public void addNetwork() throws ClassNotFoundException, SQLException {
        Network network = new Network("sone_name", "some_description");
        NetworkDao networkDao = new NetworkDao();
        Network savedNetwork = networkDao.save(network);
        assert savedNetwork != null;
    }
}