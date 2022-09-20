package Service;

import Dao.DressDao;
import Model.Dress;

import java.util.List;

public class DressService {
    private DressDao dressDao = new DressDao();

    public DressService() {}

    public Dress findDress(int id) { return dressDao.findById(id); }

    public void saveDress(Dress dress) { dressDao.save(dress); }

    public void deleteDress(Dress dress) { dressDao.delete(dress); }

    public void updateDress(Dress dress) { dressDao.update(dress); }

    public List<Dress> findAllDresses() { return dressDao.findAll(); }
}
