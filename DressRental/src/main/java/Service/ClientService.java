package Service;

import Dao.ClientDao;
import Model.Client;

import java.util.List;

public class ClientService {
    private ClientDao clientDao = new ClientDao();

    public ClientService() {}

    public Client findClient(int id) { return clientDao.findById(id); }

    public void saveClient(Client client) { clientDao.save(client); }

    public void deleteClient(Client client) { clientDao.delete(client); }

    public void updateClient(Client client) { clientDao.update(client); }

    public List<Client> findAllClients() { return clientDao.findAll(); }
}
