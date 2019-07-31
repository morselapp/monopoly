package monopoly;

import monopoly.models.Card;
import monopoly.models.Jail;
import monopoly.models.Tax;
import monopoly.models.Works;
import monopoly.models.cell.*;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static void renderCells(Cell[] board, List<City> cities, List<Transport> transports, List<Industry> industries,
                                   List<Jail> jail, List<Free> parking, List<Start> start,
                                   List<Tax> taxes, List<Works> works, List<Card> cards, List<Cell> cells ) {

        cities.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        ((City) cell).setHomestaysCount(city.getHomestaysCount());
                        ((City) cell).setResortsCount(city.getResortsCount());
                        ((City) cell).setPrice(city.getPrice());
                        ((City) cell).setRent(city.getRent());
                        ((City) cell).setMortgage(city.getMortgage());
                        cells.add(cell);
                    });
        });

        transports.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        ((City) cell).setPrice(city.getPrice());
                        ((City) cell).setRent(city.getRent());
                        ((City) cell).setMortgage(city.getMortgage());
                        cells.add(cell);
                    });
        });

        industries.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        ((City) cell).setPrice(city.getPrice());
                        ((City) cell).setRent(city.getRent());
                        ((City) cell).setMortgage(city.getMortgage());
                        cells.add(cell);
                    });
        });

        jail.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        cells.add(cell);
                    });
        });

        parking.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        cells.add(cell);
                    });
        });

        start.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        cells.add(cell);
                    });
        });

        taxes.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        cells.add(cell);
                    });
        });

        works.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        cells.add(cell);
                    });
        });

        cards.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        cells.add(cell);
                    });
        });
    }
}
