package edu.byu.cs340.tickettoride.shared.Game.Board;

import java.util.HashMap;
import java.util.Map;

import edu.byu.cs340.tickettoride.shared.Game.Enums.City;
import edu.byu.cs340.tickettoride.shared.Game.Enums.Colors;

public class Routes {

    private Map<Integer, Route> routes = new HashMap<>();

    public Routes(){
        routes.put(0, new Route(0, City.VANCOUVER, City.SEATTLE, 1, Colors.RAINBOW));
        routes.put(1, new Route(1, City.VANCOUVER, City.SEATTLE, 1, Colors.RAINBOW));

        routes.put(2, new Route(2, City.SEATTLE, City.PORTLAND, 1, Colors.RAINBOW));
        routes.put(3, new Route(3, City.SEATTLE, City.PORTLAND, 1, Colors.RAINBOW));

        routes.put(4, new Route(4, City.PORTLAND, City.SAN_FRANCISCO, 5, Colors.GREEN));
        routes.put(5, new Route(5, City.PORTLAND, City.SAN_FRANCISCO, 5, Colors.PINK));

        routes.put(6, new Route(6, City.SAN_FRANCISCO, City.LOS_ANGELES, 3, Colors.YELLOW));
        routes.put(7, new Route(7, City.SAN_FRANCISCO, City.LOS_ANGELES, 3, Colors.PINK));

        routes.put(8, new Route(8, City.VANCOUVER, City.CALGARY, 3, Colors.RAINBOW));
        routes.put(9, new Route(9, City.SEATTLE, City.CALGARY, 4, Colors.RAINBOW));

        routes.put(10, new Route(10, City.SEATTLE, City.HELENA, 6, Colors.YELLOW));

        routes.put(11, new Route(11, City.PORTLAND, City.SALT_LAKE_CITY, 6, Colors.BLUE));
        routes.put(12, new Route(12, City.SAN_FRANCISCO, City.SALT_LAKE_CITY, 5, Colors.ORANGE));
        routes.put(13, new Route(13, City.SAN_FRANCISCO, City.SALT_LAKE_CITY, 5, Colors.WHITE));

        routes.put(14, new Route(14, City.LOS_ANGELES, City.LAS_VEGAS, 2, Colors.RAINBOW));
        routes.put(15, new Route(15, City.LOS_ANGELES, City.PHOENIX, 3, Colors.RAINBOW));
        routes.put(16, new Route(16, City.LOS_ANGELES, City.EL_PASO, 6, Colors.BLACK));

        routes.put(17, new Route(17, City.LAS_VEGAS, City.SALT_LAKE_CITY, 3, Colors.ORANGE));

        routes.put(18, new Route(18, City.CALGARY, City.WINNIPEG, 6, Colors.WHITE));
        routes.put(19, new Route(19, City.CALGARY, City.HELENA, 4, Colors.RAINBOW));

        routes.put(20, new Route(20, City.SALT_LAKE_CITY, City.HELENA, 3, Colors.PINK));
        routes.put(21, new Route(21, City.SALT_LAKE_CITY, City.DENVER, 3, Colors.RED));
        routes.put(22, new Route(22, City.SALT_LAKE_CITY, City.DENVER, 3, Colors.YELLOW));

        routes.put(23, new Route(23, City.PHOENIX, City.DENVER, 5, Colors.WHITE));
        routes.put(24, new Route(24, City.PHOENIX, City.SANTA_FE, 3, Colors.RAINBOW));
        routes.put(25, new Route(25, City.PHOENIX, City.EL_PASO, 3, Colors.RAINBOW));

        routes.put(26, new Route(26, City.HELENA, City.WINNIPEG, 4, Colors.BLUE));
        routes.put(27, new Route(27, City.HELENA, City.DULUTH, 6, Colors.ORANGE));
        routes.put(28, new Route(28, City.HELENA, City.OMAHA, 5, Colors.RED));
        routes.put(29, new Route(29, City.HELENA, City.DENVER, 4, Colors.GREEN));

        routes.put(30, new Route(30, City.DENVER, City.KANSAS_CITY, 4, Colors.BLACK));
        routes.put(31, new Route(31, City.DENVER, City.KANSAS_CITY, 4, Colors.ORANGE));
        routes.put(32, new Route(32, City.DENVER, City.OKLAHOMA_CITY, 4, Colors.RED));

        routes.put(33, new Route(33, City.SANTA_FE, City.OKLAHOMA_CITY, 3, Colors.BLUE));

        routes.put(34, new Route(34, City.EL_PASO, City.OKLAHOMA_CITY, 5, Colors.YELLOW));
        routes.put(35, new Route(35, City.EL_PASO, City.DALLAS, 4, Colors.RED));
        routes.put(36, new Route(36, City.EL_PASO, City.HOUSTON, 6, Colors.GREEN));

        routes.put(37, new Route(37, City.WINNIPEG, City.SAULT_ST_MARIE, 6, Colors.RAINBOW));
        routes.put(38, new Route(38, City.WINNIPEG, City.DULUTH, 4, Colors.BLACK));

        routes.put(39, new Route(39, City.DULUTH, City.OMAHA, 2, Colors.RAINBOW));
        routes.put(40, new Route(40, City.DULUTH, City.OMAHA, 2, Colors.RAINBOW));

        routes.put(41, new Route(41, City.OMAHA, City.KANSAS_CITY, 1, Colors.RAINBOW));
        routes.put(42, new Route(42, City.OMAHA, City.KANSAS_CITY, 1, Colors.RAINBOW));

        routes.put(43, new Route(43, City.KANSAS_CITY, City.OKLAHOMA_CITY, 2, Colors.RAINBOW));
        routes.put(44, new Route(44, City.KANSAS_CITY, City.OKLAHOMA_CITY, 2, Colors.RAINBOW));

        routes.put(45, new Route(45, City.OKLAHOMA_CITY, City.DALLAS, 2, Colors.RAINBOW));
        routes.put(46, new Route(46, City.OKLAHOMA_CITY, City.DALLAS, 2, Colors.RAINBOW));

        routes.put(47, new Route(47, City.DALLAS, City.HOUSTON, 1, Colors.RAINBOW));
        routes.put(48, new Route(48, City.DALLAS, City.HOUSTON, 1, Colors.RAINBOW));

        routes.put(49, new Route(49, City.DULUTH, City.SAULT_ST_MARIE, 3, Colors.RAINBOW));
        routes.put(50, new Route(50, City.DULUTH, City.TORONTO, 6, Colors.PINK));
        routes.put(51, new Route(51, City.DULUTH, City.CHICAGO, 3, Colors.RED));

        routes.put(52, new Route(52, City.OMAHA, City.CHICAGO, 4, Colors.BLUE));

        routes.put(53, new Route(53, City.KANSAS_CITY, City.SAINT_LOUIS, 2, Colors.BLUE));
        routes.put(54, new Route(54, City.KANSAS_CITY, City.SAINT_LOUIS, 2, Colors.PINK));

        routes.put(55, new Route(55, City.OKLAHOMA_CITY, City.LITTLE_ROCK, 2, Colors.RAINBOW));
        routes.put(56, new Route(56, City.DALLAS, City.LITTLE_ROCK, 2, Colors.RAINBOW));

        routes.put(57, new Route(57, City.HOUSTON, City.NEW_ORLEANS, 2, Colors.RAINBOW));

        routes.put(58, new Route(58, City.SAINT_LOUIS, City.CHICAGO, 2, Colors.GREEN));
        routes.put(59, new Route(59, City.SAINT_LOUIS, City.CHICAGO, 2, Colors.WHITE));
        routes.put(60, new Route(60, City.SAINT_LOUIS, City.PITTSBURGH, 5, Colors.GREEN));
        routes.put(61, new Route(61, City.SAINT_LOUIS, City.NASHVILLE, 2, Colors.RAINBOW));
        routes.put(62, new Route(62, City.SAINT_LOUIS, City.LITTLE_ROCK, 2, Colors.RAINBOW));

        routes.put(63, new Route(63, City.LITTLE_ROCK, City.NASHVILLE, 3, Colors.WHITE));
        routes.put(64, new Route(64, City.LITTLE_ROCK, City.NEW_ORLEANS, 3, Colors.GREEN));

        routes.put(65, new Route(65, City.SAULT_ST_MARIE, City.MONTREAL, 5, Colors.BLACK));
        routes.put(66, new Route(66, City.SAULT_ST_MARIE, City.TORONTO, 2, Colors.RAINBOW));

        routes.put(67, new Route(67, City.CHICAGO, City.TORONTO, 4, Colors.WHITE));
        routes.put(68, new Route(68, City.CHICAGO, City.PITTSBURGH, 3, Colors.ORANGE));
        routes.put(69, new Route(69, City.CHICAGO, City.PITTSBURGH, 3, Colors.BLACK));

        routes.put(70, new Route(70, City.NASHVILLE, City.PITTSBURGH, 4, Colors.YELLOW));
        routes.put(71, new Route(71, City.NASHVILLE, City.RALEIGH, 3, Colors.BLACK));
        routes.put(72, new Route(72, City.NASHVILLE, City.ATLANTA, 1, Colors.RAINBOW));

        routes.put(73, new Route(73, City.NEW_ORLEANS, City.ATLANTA, 4, Colors.YELLOW));
        routes.put(74, new Route(74, City.NEW_ORLEANS, City.ATLANTA, 4, Colors.ORANGE));
        routes.put(75, new Route(75, City.NEW_ORLEANS, City.MIAMI, 6, Colors.RED));

        routes.put(76, new Route(76, City.TORONTO, City.MONTREAL, 3, Colors.RAINBOW));
        routes.put(77, new Route(77, City.TORONTO, City.PITTSBURGH, 2, Colors.RAINBOW));

        routes.put(78, new Route(78, City.PITTSBURGH, City.NEW_YORK, 2, Colors.WHITE));
        routes.put(79, new Route(79, City.PITTSBURGH, City.NEW_YORK, 2, Colors.GREEN));
        routes.put(80, new Route(80, City.PITTSBURGH, City.WASHINGTON, 2, Colors.RAINBOW));
        routes.put(81, new Route(81, City.PITTSBURGH, City.RALEIGH, 2, Colors.RAINBOW));

        routes.put(82, new Route(82, City.ATLANTA, City.RALEIGH, 2, Colors.RAINBOW));
        routes.put(83, new Route(83, City.ATLANTA, City.RALEIGH, 2, Colors.RAINBOW));
        routes.put(84, new Route(84, City.ATLANTA, City.CHARLESTON, 2, Colors.RAINBOW));
        routes.put(85, new Route(85, City.ATLANTA, City.MIAMI, 5, Colors.BLUE));

        routes.put(86, new Route(86, City.MONTREAL, City.BOSTON, 2, Colors.RAINBOW));
        routes.put(87, new Route(87, City.MONTREAL, City.BOSTON, 2, Colors.RAINBOW));
        routes.put(88, new Route(88, City.MONTREAL, City.NEW_YORK, 3, Colors.BLUE));

        routes.put(89, new Route(89, City.NEW_YORK, City.BOSTON, 2, Colors.YELLOW));
        routes.put(90, new Route(90, City.NEW_YORK, City.BOSTON, 2, Colors.RED));
        routes.put(91, new Route(91, City.NEW_YORK, City.WASHINGTON, 2, Colors.ORANGE));
        routes.put(92, new Route(92, City.NEW_YORK, City.WASHINGTON, 2, Colors.BLACK));

        routes.put(93, new Route(93, City.RALEIGH, City.WASHINGTON, 2, Colors.RAINBOW));
        routes.put(94, new Route(94, City.RALEIGH, City.WASHINGTON, 2, Colors.RAINBOW));
        routes.put(95, new Route(95, City.RALEIGH, City.CHARLESTON, 2, Colors.RAINBOW));

        routes.put(96, new Route(96, City.CHARLESTON, City.MIAMI, 4, Colors.PINK));

        routes.put(97, new Route(97, City.SANTA_FE, City.EL_PASO, 2, Colors.RAINBOW));
        routes.put(98, new Route(98, City.SANTA_FE, City.DENVER, 2, Colors.RAINBOW));
        routes.put(99, new Route(99, City.DENVER, City.OMAHA, 4, Colors.PINK));


    }

    public Route getRoute(int id){
        return routes.get(id);
    }
}
