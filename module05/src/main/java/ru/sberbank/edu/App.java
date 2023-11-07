package ru.sberbank.edu;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        GeoPosition geoMoscow=new GeoPosition("55(45'07'')", "37(36'56'')");
//        GeoPosition geoTver=new GeoPosition("56(51'28'')", "35(55'18'')");
//        CityInfo moscow=new CityInfo("Moscow",geoMoscow);
//        CityInfo sp=new CityInfo("SP",geoTver);
//        TravelService travelService=new TravelService();
//        travelService.add(sp);
//        travelService.add(moscow);
//        System.out.println(geoTver.toString());
//        System.out.println(geoMoscow.toString());
//        System.out.print("От Москвы до Твери: ");
//        System.out.print(travelService.getDistance("Moscow","SP"));
    }//todo закомментированный код оставил просто для ознакомления, так как до конца не решил проблему с округлением
    //todo допустим Google считает что от Москвы до Питера 634, а мой метод возвращал 633(int округлял в меньшую сторону)
    //todo я использовал Math.ceil, но есть вероятность ситуации, что округление в большую сторону так же может вызвать не состыковку
    //todo иногда возможна погрешность программы +-1км
}
