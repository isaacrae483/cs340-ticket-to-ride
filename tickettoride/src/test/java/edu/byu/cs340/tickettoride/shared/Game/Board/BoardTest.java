package edu.byu.cs340.tickettoride.shared.Game.Board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by Thomas Lewis on 3/27/19.
 */
public class BoardTest {
    Board mBoard;
    Player mPlayer1;
    Player mPlayer2;
    Player mPlayer3;
    Player mPlayer4;
    Player mPlayer5;

    @Before
    public void setUp() {
        mBoard = new Board();
        try {
            mPlayer1 = new Player(new Username("a"), IPlayer.Color.BLUE);
            mPlayer2 = new Player(new Username("b"), IPlayer.Color.GREEN);
            mPlayer3 = new Player(new Username("c"), IPlayer.Color.YELLOW);
            mPlayer4 = new Player(new Username("d"), IPlayer.Color.BLACK);
            mPlayer5 = new Player(new Username("e"), IPlayer.Color.RED);

        } catch (Throwable e) {
            fail("Player initialization problem");
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void FarAwayConnectedNodesShouldReturnTrue() {
        Routes routes = mBoard.getRoutes();
        //sanfran -> SLC
        routes.getRoute(12).claimRoute(mPlayer1);
        // SLC -> Helena
        routes.getRoute(20).claimRoute(mPlayer1);
        // Helena -> Duluth
        routes.getRoute(27).claimRoute(mPlayer1);
        // Duluth -> Toronto
        routes.getRoute(50).claimRoute(mPlayer1);
        // Toronto -> Montreal
        routes.getRoute(76).claimRoute(mPlayer1);
        // Montreal -> Boston
        routes.getRoute(86).claimRoute(mPlayer1);
        assertTrue("Routes connected from a to b",
                mBoard.areTwoCitiesConnectedForUser(City.SAN_FRANCISCO, City.BOSTON, mPlayer1.getPlayerName(), null));
        assertTrue("Routes connected from b to a",
                mBoard.areTwoCitiesConnectedForUser(City.BOSTON, City.SAN_FRANCISCO, mPlayer1.getPlayerName(), null));
        assertTrue("Routes connected to itself",
                mBoard.areTwoCitiesConnectedForUser(City.BOSTON, City.BOSTON, mPlayer1.getPlayerName(), null));

    }

    @Test
    public void NoConnectedNodesShouldReturnFalse() {
        Routes routes = mBoard.getRoutes();
        //sanfran -> SLC
        routes.getRoute(12).claimRoute(mPlayer1);
        // SLC -> Helena
        routes.getRoute(20).claimRoute(mPlayer1);
        // Helena -> Duluth
        routes.getRoute(27).claimRoute(mPlayer1);
        // Duluth -> Toronto -> HAVING OTHER PLAYER CLAIM
        routes.getRoute(50).claimRoute(mPlayer2);
        // Toronto -> Montreal
        routes.getRoute(76).claimRoute(mPlayer1);
        // Montreal -> Boston
        routes.getRoute(86).claimRoute(mPlayer1);
        assertFalse("Routes not connected from a to b",
                mBoard.areTwoCitiesConnectedForUser(City.SAN_FRANCISCO, City.BOSTON, mPlayer1.getPlayerName(), null));
        assertFalse("Routes not connected from b to a",
                mBoard.areTwoCitiesConnectedForUser(City.BOSTON, City.SAN_FRANCISCO, mPlayer1.getPlayerName(), null));
    }

    @Test
    public void NoRoutesClaimedShouldReturnFalse() {
        assertFalse("Routes connected from a to b",
                mBoard.areTwoCitiesConnectedForUser(City.SAN_FRANCISCO, City.BOSTON, mPlayer1.getPlayerName(), null));
    }

    @Test
    public void BoardInChaosButStillConnected() {
        //Generate some bad entropy
        for (int i = 0; i < 100; i++) {
            Route route = mBoard.routes.getRoute(i);
            switch (i % 5) {
                case 0:
                    route.claimRoute(mPlayer1);
                    break;
                case 1:
                    route.claimRoute(mPlayer2);
                    break;
                case 2:
                    route.claimRoute(mPlayer3);
                    break;
                case 3:
                    route.claimRoute(mPlayer4);
                    break;
                case 4:
                    route.claimRoute(mPlayer5);
                    break;
            }
        }
        Routes routes = mBoard.getRoutes();
        //Let's go from Calgary <-> New Orleans
        // New Orleans -> Houston
        routes.getRoute(57).claimRoute(mPlayer3);
        // Houston -> El paso
        routes.getRoute(36).claimRoute(mPlayer3);
        // El paso -> Oklahoma City
        routes.getRoute(34).claimRoute(mPlayer3);
        // Oklahoma City -> Kansas City
        routes.getRoute(43).claimRoute(mPlayer3);
        // Kansas City -> Omaha
        routes.getRoute(41).claimRoute(mPlayer3);
        // Omaha -> Helena
        routes.getRoute(28).claimRoute(mPlayer3);
        // Helena -> Calgary
        routes.getRoute(19).claimRoute(mPlayer3);



        assertTrue("Routes connected from a to b",
                mBoard.areTwoCitiesConnectedForUser(City.NEW_ORLEANS, City.CALGARY, mPlayer3.getPlayerName(), null));
        assertTrue("Routes connected from b to a",
                mBoard.areTwoCitiesConnectedForUser(City.CALGARY, City.NEW_ORLEANS, mPlayer3.getPlayerName(), null));
        assertTrue("Routes connected to itself",
                mBoard.areTwoCitiesConnectedForUser(City.CALGARY, City.CALGARY, mPlayer3.getPlayerName(), null));
    }

    @Test
    public void TwoWaysToConnect() {
        Routes routes = mBoard.getRoutes();

        //Route A
        //sanfran -> SLC
        routes.getRoute(12).claimRoute(mPlayer4);
        // SLC -> Helena
        routes.getRoute(20).claimRoute(mPlayer4);
        // Helena -> Duluth
        routes.getRoute(27).claimRoute(mPlayer4);
        // Duluth -> Toronto
        routes.getRoute(50).claimRoute(mPlayer4);
        // Toronto -> Montreal
        routes.getRoute(76).claimRoute(mPlayer4);
        // Montreal -> Boston
        routes.getRoute(86).claimRoute(mPlayer4);

        //Route B
        //Toronto -> Pittsburgh
        routes.getRoute(77).claimRoute(mPlayer4);
        //Pittsburgh -> New York
        routes.getRoute(79).claimRoute(mPlayer4);
        //NewYork -> Boston
        routes.getRoute(89).claimRoute(mPlayer4);

        assertTrue("Routes connected from a to b",
                mBoard.areTwoCitiesConnectedForUser(City.SAN_FRANCISCO, City.BOSTON, mPlayer4.getPlayerName(), null));
        assertTrue("Routes connected from b to a",
                mBoard.areTwoCitiesConnectedForUser(City.BOSTON, City.SAN_FRANCISCO, mPlayer4.getPlayerName(), null));
        assertTrue("Routes connected to itself",
                mBoard.areTwoCitiesConnectedForUser(City.BOSTON, City.BOSTON, mPlayer4.getPlayerName(), null));
    }

}
