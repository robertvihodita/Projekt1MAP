package Repo;

import Model.Nurse;
import java.util.ArrayList;
import java.util.List;

public class NurseRepository {
    private List<Nurse> nurses = new ArrayList<>();

    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
    }

    public void removeNurse(int id) {
        nurses.removeIf(n -> n.getId() == id);
    }

    public Nurse findById(int id) {
        for (Nurse n : nurses) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }

    public List<Nurse> getAllNurses() {
        return new ArrayList<>(nurses);
    }
}
