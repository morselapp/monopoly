package monopoly.Utils;

import monopoly.models.*;
import monopoly.models.cell.*;

import java.util.*;

public class Utils {

    public static void renderCells(Cell[] board, List<City> cities, List<Transport> transports, List<Industry> industries,
                                   List<Jail> jails, List<Free> parkings, List<Start> starts,
                                   List<Tax> taxes, List<Works> works, List<Card> cards, List<Cell> cells) {

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

        transports.forEach(transport -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Transport();
                        cell.setId(transport.getId());
                        cell.setName(transport.getName());
                        cell.setType(transport.getType());
                        cell.setIcon(transport.getIcon());
                        cell.setColor(transport.getColor());
                        cell.setDescription(transport.getDescription());
                        ((Transport) cell).setPrice(transport.getPrice());
                        ((Transport) cell).setRent(transport.getRent());
                        ((Transport) cell).setMortgage(transport.getMortgage());
                        cells.add(cell);
                    });
        });

        industries.forEach(industry -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Industry();
                        cell.setId(industry.getId());
                        cell.setName(industry.getName());
                        cell.setType(industry.getType());
                        cell.setIcon(industry.getIcon());
                        cell.setColor(industry.getColor());
                        cell.setDescription(industry.getDescription());
                        ((Industry) cell).setPrice(industry.getPrice());
                        ((Industry) cell).setRent(industry.getRent());
                        ((Industry) cell).setMortgage(industry.getMortgage());
                        cells.add(cell);
                    });
        });

        jails.forEach(jail -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Jail();
                        cell.setId(jail.getId());
                        cell.setName(jail.getName());
                        cell.setType(jail.getType());
                        cell.setIcon(jail.getIcon());
                        cell.setColor(jail.getColor());
                        cell.setDescription(jail.getDescription());
                        cells.add(cell);
                    });
        });

        parkings.forEach(parking -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Free();
                        cell.setId(parking.getId());
                        cell.setName(parking.getName());
                        cell.setType(parking.getType());
                        cell.setIcon(parking.getIcon());
                        cell.setColor(parking.getColor());
                        cell.setDescription(parking.getDescription());
                        cells.add(cell);
                    });
        });

        starts.forEach(start -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Start();
                        cell.setId(start.getId());
                        cell.setName(start.getName());
                        cell.setType(start.getType());
                        cell.setIcon(start.getIcon());
                        cell.setColor(start.getColor());
                        cell.setDescription(start.getDescription());
                        cells.add(cell);
                    });
        });

        taxes.forEach(tax -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Tax();
                        cell.setId(tax.getId());
                        cell.setName(tax.getName());
                        cell.setType(tax.getType());
                        cell.setIcon(tax.getIcon());
                        cell.setColor(tax.getColor());
                        cell.setDescription(tax.getDescription());
                        cells.add(cell);
                    });
        });

        works.forEach(work -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Works();
                        cell.setId(work.getId());
                        cell.setName(work.getName());
                        cell.setType(work.getType());
                        cell.setIcon(work.getIcon());
                        cell.setColor(work.getColor());
                        cell.setDescription(work.getDescription());
                        cells.add(cell);
                    });
        });

        cards.forEach(card -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Card();
                        cell.setId(card.getId());
                        cell.setName(card.getName());
                        cell.setType(card.getType());
                        cell.setIcon(card.getIcon());
                        cell.setColor(card.getColor());
                        cell.setDescription(card.getDescription());
                        cells.add(cell);
                    });
        });
    }
}
