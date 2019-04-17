package FlatFilePlugin;


public interface DAOFactory {
    UserDAO generateUserDAO();
    GameDAO generateGameDAO(int deltas);
}
