package de.fhdortmund.swt2.pruefungsmeister.Persistence;

/**
 * Created by jonas on 03.07.17.
 */
public interface PruefungsmeisterDAO {
    <T> void persist(T object);
    <T> void update(T object);
    void deleteAllPlayers();
    void beginTransaction();
    void commitTransaction();
    void rollbackTransaction();
}
