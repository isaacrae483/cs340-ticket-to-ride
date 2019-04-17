package FlatFilePlugin;

public class FlatFileFactory implements DAOFactory {
    @Override
    public UserDAO generateUserDAO() {
        return new FlatFileUserDAO();
    }

    @Override
    public GameDAO generateGameDAO(int deltas) {
        return new FlatFileGameDAO(deltas);
    }
}
